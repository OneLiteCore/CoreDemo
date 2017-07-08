package core.demo.frag.dijkstra;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.util.Pair;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.List;

import core.demo.frag.dijkstra.data.Location;
import core.demo.frag.dijkstra.data.Map;
import core.mate.util.DataUtil;
import core.mate.util.ViewUtil;

/**
 * @author DrkCore
 * @since 2017-02-19
 */
public class MapView extends View {

    public MapView(Context context) {
        super(context);
    }

    public MapView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MapView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /*Touch*/

    private DeltaPointF lastMovePoint = new DeltaPointF();
    private DeltaPointF scaleCenterPoint = new DeltaPointF();

    private DeltaPointF point1 = new DeltaPointF(), point2 = new DeltaPointF();
    private float lastDistance;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        int pointCount = event.getPointerCount();
        int action = event.getAction() & MotionEvent.ACTION_MASK;
        if (pointCount == 1) {//单指移动
            point1.set(event);
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    lastMovePoint.set(point1);
                    break;

                case MotionEvent.ACTION_MOVE:
                    //将平移操作的实际代码解耦成独立的方法
                    onMove(point1.deltaTo(lastMovePoint));
                    lastMovePoint.set(point1);
                    break;
            }
            return true;

        } else if (pointCount == 2) {//双指缩放
            point1.set(event, 0);
            point2.set(event, 1);
            float distance = MathUtil.calcDistance(point1, point2);
            switch (action) {
                case MotionEvent.ACTION_POINTER_DOWN:
                case MotionEvent.ACTION_DOWN:
                    scaleCenterPoint.set((point1.x + point2.x) / 2, (point1.y + point2.y) / 2);
                    lastDistance = -1;
                    break;

                case MotionEvent.ACTION_MOVE:
                    if (lastDistance > 0) {
                        onScale(distance - lastDistance);
                    }
                    lastDistance = distance;
                    break;

            }
            return true;

        } else {//忽略三指及以上的操作
            return false;
        }
    }

    private DeltaPointF moveDelta = new DeltaPointF();

    private void onMove(DeltaPointF point) {
        moveDelta.plus(point);
        invalidate();
    }

    private float scale = 1;

    private void onScale(float distanceDelta) {
        scale += distanceDelta / 320F;
        if (scale < 1) {
            scale = 1;
        }
        invalidate();
    }

    /*Map*/

    //由于地图不需要透明度，使用 RGB_565 可以节约图片的内存体积
    private static final Bitmap.Config BMP_CONFIG = Bitmap.Config.RGB_565;

    //需要显示的路径信息
    private List<Location> mPath;
    //地图的底图
    private Bitmap mMapBmp;
    //地图的尺寸
    private Rect mSrcRect = new Rect();

    public MapView set(Pair<Map, List<Location>> data) {
        return set(data.first, data.second);
    }

    //设置地图和路径信息，并将之绘制到画布上
    public MapView set(Map map, List<Location> locations) {
        if (mMapBmp != null) {//释放前一张地图背景所占用的内存空间
            mMapBmp.recycle();
            mMapBmp = null;
        }
        mPath = null;

        //解析地图
        mMapBmp = BitmapFactory.decodeResource(getResources(), map.getMapImg()).copy(BMP_CONFIG, true);
        mSrcRect.set(0, 0, mMapBmp.getWidth(), mMapBmp.getHeight());

        mPath = locations;

        reset();
        return this;
    }

    /*Draw*/

    private Paint mPaint = new Paint();
    private Rect mTargetRect = new Rect();

    private DeltaPointF transDelta = new DeltaPointF();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mMapBmp == null) {
            //仅当地图设置后执行绘制操作
            return;
        }

        //计算图片内坐标缩放比
        int bmpW = mMapBmp.getWidth(), bmpH = mMapBmp.getHeight();
        int canvasW = canvas.getWidth();
        float bmpWvH = bmpW / (float) bmpH;
        int targetH = (int) (canvasW / bmpWvH);
        float scaleW = canvasW / (float) bmpW;
        float scaleH = targetH / (float) bmpH;
        //设置图片显示区域
        mTargetRect.set(0, 0, canvasW, targetH);

        //处理手势平移及缩放
        canvas.save();
        transDelta.set(moveDelta);

        canvas.translate(transDelta.x, transDelta.y);
        canvas.scale(scale, scale, scaleCenterPoint.x, scaleCenterPoint.y);

        //绘制图片&路径
        canvas.drawBitmap(mMapBmp, mSrcRect, mTargetRect, mPaint);
        if (mPath != null) {
            mPaint.setColor(Color.BLACK);
            mPaint.setStrokeWidth(4);
            Location last = null;
            for (int i = 0, len = DataUtil.getSize(mPath); i < len; i++) {
                Location cur = mPath.get(i);
                if (last != null) {
                    //计算实际绘图坐标
                    point1.set(last.getPosition().x * scaleW, last.getPosition().y * scaleH);
                    point2.set(cur.getPosition().x * scaleW, cur.getPosition().y * scaleH);
                    canvas.drawLine(point1.x, point1.y, point2.x, point2.y, mPaint);
                }
                last = cur;
            }
        }

        //绘制文字
        Location locLeft = DataUtil.getFirstQuietly(mPath);
        Location locRight = DataUtil.getLastQuietly(mPath);
        if (locLeft != null && locRight != null) {
            //确保locLeft标志的是左边的节点
            if (locLeft.getPosition().x > locRight.getPosition().x) {
                Location tmp = locLeft;
                locLeft = locRight;
                locRight = tmp;
            }

            //按比例换算起止点的坐标
            point1.set(locLeft.getPosition().x * scaleW, locLeft.getPosition().y * scaleH);
            point2.set(locRight.getPosition().x * scaleW, locRight.getPosition().y * scaleH);

            String leftName = locLeft.getName();
            String rightName = locRight.getName();

            //计算出文字所占用画布的宽度
            float leftTxtLen = mPaint.measureText(leftName);
            //默认文字的大小为16sp
            mPaint.setTextSize(ViewUtil.spToPx(16));
            //绘制文字
            canvas.drawText(leftName, 0, leftName.length(), point1.x - leftTxtLen, point1.y, mPaint);
            if (!leftName.equals(rightName)) {//同样的文字只绘制一个就行了
                canvas.drawText(rightName, 0, rightName.length(), point2.x, point2.y, mPaint);
            }
        }

        //重置画布
        canvas.restore();
    }

    public void reset() {
        lastMovePoint.reset();
        scaleCenterPoint.reset();
        point1.reset();
        point2.reset();
        lastDistance = 0;
        moveDelta.reset();
        scale = 1;
        transDelta.reset();
        invalidate();
    }
}

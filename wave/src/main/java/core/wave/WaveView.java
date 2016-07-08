package core.wave;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;

public class WaveView extends BaseSurfaceView {

    public WaveView(Context context) {
        this(context, null);
    }

    public WaveView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /*绘图*/

    private final Paint paint = new Paint();

    {
        paint.setStrokeWidth(2);
        paint.setDither(true);
        paint.setAntiAlias(true);
    }

    private final Path topPath = new Path();
    private final Path bottomPath = new Path();
    private final Path smallPath = new Path();

    private static final int DENSITY = 128;
    private float[] drawRange;
    private float[] mapRange;

    private static final String TAG = "WaveView";

    private int width;
    private int height;
    private int centerHeight;
    private int wavePeak;

    private float[][] points = new float[9][];//通过日志可知极值点个数为7~9

    {
        for (int i = 0; i < 9; i++) {
            points[i] = new float[2];
        }
    }

    private final RectF rectF = new RectF();

    @Override
    protected void onRender(Canvas canvas, long millisPassed) {
        //将绘制线段设定成DENSITY个
        if (drawRange == null) {
            //赋值基本参数
            width = canvas.getWidth();
            height = canvas.getHeight();
            centerHeight = height >> 1;
            wavePeak = width >> 2;//确定波的最大值

            drawRange = new float[DENSITY + 1];
            mapRange = new float[DENSITY + 1];
            float gap = width / (float) DENSITY;
            float x;
            for (int i = 0; i <= DENSITY; i++) {
                x = i * gap;
                drawRange[i] = x;
                mapRange[i] = (x / (float) width) * 4 - 2;//将x映射到[-2,2]的区间上
            }
        }

        //绘制背景
        canvas.drawColor(Color.rgb(24, 33, 41));

        //计算正弦函数
        topPath.rewind();
        topPath.moveTo(0, centerHeight);
        bottomPath.rewind();
        bottomPath.moveTo(0, centerHeight);
        smallPath.rewind();
        smallPath.moveTo(0, centerHeight);
        float lastK = millisPassed / 500F;
        float x, lastV, nextV = (float) (wavePeak * calcY(mapRange[0], lastK)), curV = 0;
        float absLastV, absCurV, absNextV;
        int pointSize = 0;
        float[] pointXY;
        boolean lastIsBig = false;
        for (int i = 0; i <= DENSITY; i++) {
            x = drawRange[i];
            lastV = curV;
            curV = nextV;
            nextV = i < DENSITY ? (float) (wavePeak * calcY(mapRange[i + 1], lastK)) : 0;

            topPath.lineTo(x, centerHeight + curV);
            bottomPath.lineTo(x, centerHeight - curV);

            smallPath.lineTo(x, centerHeight + curV / 5F);

            //记录极值点
            absLastV = Math.abs(lastV);
            absCurV = Math.abs(curV);
            absNextV = Math.abs(nextV);
            if (i == 0 || i == DENSITY/*端点*/ || (lastIsBig && absCurV < absLastV && absCurV < absNextV)/*极小值*/) {
                pointXY = points[pointSize++];
                pointXY[0] = x;
                pointXY[1] = 0;
                lastIsBig = false;
            } else if (!lastIsBig && absCurV > absLastV && absCurV > absNextV) {/*极大值*/
                pointXY = points[pointSize++];
                pointXY[0] = x;
                pointXY[1] = curV;
                lastIsBig = true;
            }
        }
        topPath.lineTo(width, centerHeight);
        bottomPath.lineTo(width, centerHeight);

        int saveCount = canvas.saveLayer(0, 0, width, height, null, Canvas.ALL_SAVE_FLAG);
        paint.setXfermode(null);

        //填充正弦函数
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(1);
        canvas.drawPath(topPath, paint);
        canvas.drawPath(bottomPath, paint);

        //绘制极值点
        rectF.set(0, (float) (centerHeight - 0.5 * wavePeak), 0, (float) (centerHeight + 0.5 * wavePeak));
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        float startX, peakY, endX;
        for (int i = 2; i < pointSize; i += 2) {
            startX = points[i - 2][0];
            peakY = points[i - 1][1];
            endX = points[i][0];

            if (peakY >= 0) {
                paint.setShader(new LinearGradient(400, centerHeight + 200, 400, centerHeight - 200,
                        Color.BLUE, Color.GREEN, Shader.TileMode.CLAMP));
            } else {
                paint.setShader(new LinearGradient(400, centerHeight - 200, 400, centerHeight + 200,
                        Color.BLUE, Color.GREEN, Shader.TileMode.CLAMP));
            }
            rectF.set(startX, centerHeight + peakY, endX, centerHeight - peakY);
            canvas.drawRect(rectF, paint);
        }
        paint.setXfermode(null);

        canvas.restoreToCount(saveCount);

        //绘制上弦线
        paint.setShader(null);
        paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLUE);
        canvas.drawPath(topPath, paint);

        //绘制下弦线
        paint.setColor(Color.GREEN);
        canvas.drawPath(bottomPath, paint);

        //绘制中间线
        paint.setColor(Color.argb(64, 255, 255, 255));
        canvas.drawPath(smallPath, paint);
    }

    private double calcY(float mapX, float k) {
        double sinFunc = Math.sin(0.75 * Math.PI * mapX - k * Math.PI);
        double recessionFunc = 0.5 * Math.pow(4 / (4 + Math.pow(mapX, 4)), 2.5);
        return sinFunc * recessionFunc;
    }

}

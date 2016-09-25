package core.demo.frag.overwatch;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.Animatable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;

/**
 * @author DrkCore
 * @since 2016-09-22
 */
public class OverWatchView extends View implements Animatable {

    public OverWatchView(Context context) {
        this(context, null);
    }

    public OverWatchView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OverWatchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAnim();
        initDraw();
        initIcon();
        initMiddleStroke();
        initOutStroke();
    }

    /*继承*/

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //处理宽度
        int width;
        if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.EXACTLY) {
            width = MeasureSpec.getSize(widthMeasureSpec);
        } else {
            width = (int) (iconRadius / 3 * 4);
        }
        int height;
        if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.EXACTLY) {
            height = MeasureSpec.getSize(widthMeasureSpec);
        } else {
            height = (int) (iconRadius / 3 * 4);
        }
        setMeasuredDimension(width, height);
    }

    private void initDraw() {
        paint.setDither(true);
        paint.setAntiAlias(true);
    }

    private int width;
    private int height;
    private int centerX;
    private int centerY;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //初始化各种参数
        width = canvas.getWidth();
        height = canvas.getHeight();
        centerX = width >> 1;
        centerY = height >> 1;

        drawIcon(canvas);
        drawMiddleStroke(canvas);
        drawOutStroke(canvas);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stop();
    }

    /*动画效果*/

    private AnimatorSet animSet;

    private void initAnim() {
        animSet = new AnimatorSet();
        animSet.playTogether(prepareMiddleStrokeAnimator());
        animSet.playTogether(prepareOutStrokeAnimator());
    }

    @Override
    public void start() {
        animSet.start();
    }

    @Override
    public void stop() {
        animSet.cancel();
    }

    @Override
    public boolean isRunning() {
        return animSet.isRunning();
    }

    /*绘制图标*/

    public static final int DEFAULT_ICON_RADIUS_DP = 30;

    private float iconRadius;
    private float iconWidth;
    private float iconGapWidth;
    private RectF iconRectF = new RectF();

    private float[] iconCornerPoints;
    private Path iconCornerLeftPath = new Path();
    private Path iconCornerRightPath = new Path();

    private int iconColor = Color.rgb(206, 207, 215);

    private Paint paint = new Paint();

    private Xfermode dstOutMode = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);

    private void initIcon() {

        iconRadius = dpToPx(DEFAULT_ICON_RADIUS_DP);
        iconWidth = iconRadius * (87 / 300F);
        iconGapWidth = iconRadius * (20 / 300F);

        iconCornerPoints = new float[10];
        iconCornerPoints[0] = iconRadius * ((300 - 71) / 300F);
        iconCornerPoints[1] = iconRadius * (450 / 300F);

        iconCornerPoints[2] = iconRadius * ((300 - 233) / 300F);
        iconCornerPoints[3] = iconRadius * (295 / 300F);

        iconCornerPoints[4] = iconRadius * ((300 - 287) / 300F);
        iconCornerPoints[5] = iconRadius * (165 / 300F);

        iconCornerPoints[6] = iconRadius * ((300 - 287) / 300F);
        iconCornerPoints[7] = iconRadius * (361 / 300F);

        iconCornerPoints[8] = iconRadius * ((300 - 136) / 300F);
        iconCornerPoints[9] = iconRadius * (505 / 300F);
    }

    private Bitmap iconBmp;

    private void drawIcon(Canvas canvas) {
        float left = centerX - iconRadius;
        float top = centerY - iconRadius;
        float right = centerX + iconRadius;
        float bottom = centerY + iconRadius;
        iconRectF.set(left, top, right, bottom);

        if (iconBmp == null) {
            iconBmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas bmpCanvas = new Canvas(iconBmp);
            doDrawIcon(bmpCanvas);
        }
        canvas.drawBitmap(iconBmp, 0, 0, null);
    }

    private void doDrawIcon(Canvas canvas) {
        //标记图层
        int saveCount = canvas.saveLayer(0, 0, width, height, null, Canvas.ALL_SAVE_FLAG);

        //绘制灰圈
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(iconWidth);
        paint.setColor(iconColor);
        canvas.drawArc(iconRectF, 0, 360, false, paint);

        //抹去交界线
        paint.setXfermode(dstOutMode);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(iconGapWidth);
        paint.setColor(Color.RED);
        canvas.rotate(40, centerX, centerY);
        canvas.drawLine(centerX, centerY, centerX, 0, paint);
        canvas.rotate(-80, centerX, centerY);
        canvas.drawLine(centerX, centerY, centerX, 0, paint);
        paint.setXfermode(null);

        //叠加图层
        canvas.restoreToCount(saveCount);

        //绘制中心的两个角
        iconCornerLeftPath.rewind();
        iconCornerRightPath.rewind();
        float tmpX, tmpY;
        for (int i = 0, len = iconCornerPoints.length; i < len; i += 2) {
            tmpX = iconCornerPoints[i];
            tmpY = iconCornerPoints[i + 1];
            tmpY += centerY - iconRadius;
            if (iconCornerLeftPath.isEmpty()) {
                iconCornerLeftPath.moveTo(centerX - tmpX, tmpY);
                iconCornerRightPath.moveTo(centerX + tmpX, tmpY);
            } else {
                iconCornerLeftPath.lineTo(centerX - tmpX, tmpY);
                iconCornerRightPath.lineTo(centerX + tmpX, tmpY);
            }
        }
        iconCornerLeftPath.close();
        iconCornerRightPath.close();
        paint.setColor(iconColor);
        canvas.drawPath(iconCornerLeftPath, paint);
        canvas.drawPath(iconCornerRightPath, paint);
    }

    /*绘制橙色中间圈*/

    private float middleWidth;
    private float middleRadius;
    private RectF middleRectF = new RectF();

    private int middleBgColor = Color.argb(128, 227, 165, 4);

    private int middleColor = Color.rgb(227, 165, 4);

    private float middleAngel_1 = 116;
    private float middleAngel_2 = -74;
    private float middleAngel_3 = 45;

    private float middleStartAngel = 0;
    private float middleSwipeAngel = 300;

    private void initMiddleStroke() {
        middleWidth = iconWidth * 2 / 3;
        middleRadius = iconRadius + iconWidth / 2 + middleWidth;
    }

    private Animator[] prepareMiddleStrokeAnimator() {
        ValueAnimator[] anim = new ValueAnimator[5];
        anim[0] = ValueAnimator.ofFloat(0, 360);
        anim[0].setRepeatCount(ValueAnimator.INFINITE);
        anim[0].setDuration(1500);
        anim[0].setInterpolator(new LinearInterpolator());
        anim[0].addUpdateListener(animation -> {
            middleStartAngel = (float) animation.getAnimatedValue();
            invalidate();
        });

        anim[1] = ValueAnimator.ofFloat(0, 320);
        anim[1].setRepeatMode(ValueAnimator.REVERSE);
        anim[1].setRepeatCount(ValueAnimator.INFINITE);
        anim[1].setDuration(1500);
        anim[1].setInterpolator(new AccelerateDecelerateInterpolator());
        anim[1].addUpdateListener(animation -> {
            middleSwipeAngel = (float) animation.getAnimatedValue();
            invalidate();
        });

        anim[2] = ValueAnimator.ofFloat(middleAngel_1, middleAngel_1 + 360);
        anim[2].setRepeatCount(ValueAnimator.INFINITE);
        anim[2].setDuration(1500);
        anim[2].setInterpolator(new LinearInterpolator());
        anim[2].addUpdateListener(animation -> {
            middleAngel_1 = -(float) animation.getAnimatedValue();
            invalidate();
        });

        anim[3] = ValueAnimator.ofFloat(middleAngel_2, middleAngel_2 + 360);
        anim[3].setRepeatCount(ValueAnimator.INFINITE);
        anim[3].setDuration(1500);
        anim[3].setInterpolator(new LinearInterpolator());
        anim[3].addUpdateListener(animation -> {
            middleAngel_2 = (float) animation.getAnimatedValue();
            invalidate();
        });

        anim[4] = ValueAnimator.ofFloat(middleAngel_3, middleAngel_3 + 360);
        anim[4].setRepeatCount(ValueAnimator.INFINITE);
        anim[4].setDuration(1500);
        anim[4].setInterpolator(new LinearInterpolator());
        anim[4].addUpdateListener(animation -> {
            middleAngel_3 = -(float) animation.getAnimatedValue();
            invalidate();
        });
        return anim;
    }

    private void drawMiddleStroke(Canvas canvas) {
        float left = centerX - middleRadius;
        float top = centerY - middleRadius;
        float right = centerX + middleRadius;
        float bottom = centerY + middleRadius;
        middleRectF.set(left, top, right, bottom);

        paint.setColor(middleBgColor);
        paint.setStrokeWidth(middleWidth);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(middleRectF, middleStartAngel, middleSwipeAngel, false, paint);

        paint.setColor(middleColor);
        canvas.drawArc(middleRectF, middleAngel_1, 10, false, paint);
        canvas.drawArc(middleRectF, middleAngel_2, 54, false, paint);
        canvas.drawArc(middleRectF, middleAngel_3, 40, false, paint);
    }

    /*绘制外圈*/

    private float outWidth;
    private float outRadius;
    private RectF outRectF = new RectF();

    private float outAngel_1 = -27;
    private float outAngel_2 = -106;
    private float outAngel_3 = 155;

    private int outColor = Color.rgb(222, 217, 216);

    private void initOutStroke() {
        outWidth = iconWidth * 1.5F / 3;
        outRadius = middleRadius + iconWidth / 2 + outWidth;
    }

    private Animator[] prepareOutStrokeAnimator() {
        ValueAnimator[] anim = new ValueAnimator[3];
        anim[0] = ValueAnimator.ofFloat(outAngel_1, outAngel_1 + 360);
        anim[0].setRepeatCount(ValueAnimator.INFINITE);
        anim[0].setDuration(1500);
        anim[0].setInterpolator(input -> {
            if (input <= 0.25) {
                input = input - 0.5F;
                input = -2 * input * input + 0.5F;
            } else if (input <= 0.75) {
                input = input - 0.5F;
                input = input / 2 + 0.5F;
            } else {
                input = input - 0.5F;
                input = 2 * input * input + 0.5F;
            }
            return input;
        });
        anim[0].addUpdateListener(animation -> {
            outAngel_1 = (float) animation.getAnimatedValue();
            invalidate();
        });

        anim[1] = ValueAnimator.ofFloat(outAngel_2, outAngel_2 + 360);
        anim[1].setRepeatCount(ValueAnimator.INFINITE);
        anim[1].setDuration(1500);
        anim[1].setInterpolator(new LinearInterpolator());
        anim[1].addUpdateListener(animation -> {
            outAngel_2 = -(float) animation.getAnimatedValue();
            invalidate();
        });

        anim[2] = ValueAnimator.ofFloat(outAngel_3, outAngel_3 + 360);
        anim[2].setRepeatCount(ValueAnimator.INFINITE);
        anim[2].setDuration(1500);
        anim[2].setInterpolator(new LinearInterpolator());
        anim[2].addUpdateListener(animation -> {
            outAngel_3 = (float) animation.getAnimatedValue();
            invalidate();
        });

        return anim;
    }

    private void drawOutStroke(Canvas canvas) {
        float left = centerX - outRadius;
        float top = centerY - outRadius;
        float right = centerX + outRadius;
        float bottom = centerY + outRadius;
        outRectF.set(left, top, right, bottom);

        paint.setColor(outColor);
        paint.setStrokeWidth(outWidth);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(outRectF, outAngel_1, 50, false, paint);
        canvas.drawArc(outRectF, outAngel_2, 8, false, paint);
        canvas.drawArc(outRectF, outAngel_3, 25, false, paint);
    }

    /*拓展*/

    public int dpToPx(float dpValue) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        return (int) (dpValue * displayMetrics.density + 0.5F);
    }

}

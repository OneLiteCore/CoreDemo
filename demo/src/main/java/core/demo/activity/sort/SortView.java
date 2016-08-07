package core.demo.activity.sort;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class SortView extends View {

    public SortView(Context context) {
        super(context);
    }

    public SortView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SortView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /*刷新*/

    public static final int SIZE = 64;
    public static final int MAX = 720;

    private int[] values;
    private final Paint paint = new Paint();

    {
        paint.setColor(Color.RED);
    }

    public int[] getValues() {
        return values;
    }

    public void display(int[] values) {
        if (values.length != SIZE) {
            throw new IllegalArgumentException();
        }
        this.values = values;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (values == null) {
            return;
        }

        float width = canvas.getWidth();
        float height = canvas.getHeight();

        float barWidth = width / SIZE;
        float barHeight;
        for (int i = 0; i < SIZE; i++) {
            barHeight = values[i] / (float) MAX * height;
            canvas.drawRect(i * barWidth, height - barHeight, (i + 1) * barWidth - 1, height, paint);
        }
    }
}

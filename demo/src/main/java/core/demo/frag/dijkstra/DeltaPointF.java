package core.demo.frag.dijkstra;

import android.graphics.Point;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.view.MotionEvent;

/**
 * @author DrkCore
 * @since 2017-03-26
 */
public class DeltaPointF extends PointF {

    public DeltaPointF() {
    }

    public DeltaPointF(float x, float y) {
        super(x, y);
    }

    public DeltaPointF(Point p) {
        super(p);
    }

    public DeltaPointF set(MotionEvent event) {
        return set(event, 0);
    }

    public DeltaPointF set(MotionEvent event, int idx) {
        set(event.getX(idx), event.getY(idx));
        return this;
    }

    public DeltaPointF plus(PointF pointF) {
        set(x + pointF.x, y + pointF.y);
        return this;
    }

    public DeltaPointF deltaTo(PointF target) {
        return deltaTo(target, null);
    }

    public DeltaPointF deltaTo(PointF target, @Nullable DeltaPointF saveTo) {
        if (saveTo == null) {
            saveTo = new DeltaPointF();
        }
        saveTo.set(x - target.x, y - target.y);
        return saveTo;
    }

    public DeltaPointF reset() {
        set(0, 0);
        return this;
    }
}

package core.demo.frag.dijkstra;

import android.graphics.Point;
import android.graphics.PointF;

/**
 * @author DrkCore
 * @since 2017-03-26
 */
public class MathUtil {

    public static int calcDistance(Point p1, Point p2) {
        if (p1.equals(p2)) {
            return 0;
        }

        int deltaX = p1.x - p2.x;
        deltaX = deltaX * deltaX;
        int deltaY = p1.y - p2.y;
        deltaY = deltaY * deltaY;

        return (int) Math.sqrt(deltaX + deltaY);
    }

    public static float calcDistance(PointF p1, PointF p2) {
        if (p1.equals(p2)) {
            return 0;
        }

        float deltaX = p1.x - p2.x;
        deltaX = deltaX * deltaX;
        float deltaY = p1.y - p2.y;
        deltaY = deltaY * deltaY;

        return (float) Math.sqrt(deltaX + deltaY);
    }

}

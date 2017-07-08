package core.demo.frag.dijkstra.data;

import android.graphics.Point;

/**
 * @author DrkCore
 * @since 2017-02-25
 */
public class Location {

    private String mName;
    private Point mPosition;
    private boolean mVisible = true;

    public boolean isVisible() {
        return mVisible;
    }

    Location setInvisible() {
        mVisible = false;
        return this;
    }

    public String getName() {
        return mName;
    }

    public Point getPosition() {
        return mPosition;
    }

    Location(String name, Point position) {
        this.mName = name;
        this.mPosition = position;
    }

    private Map mMap;
    private int mIndex;

    public Map getMap() {
        return mMap;
    }

    int getIndex() {
        return mIndex;
    }

    void set(Map map, int idx) {
        this.mMap = map;
        this.mIndex = idx;
    }

    @Override
    public int hashCode() {
        int result = mName.hashCode();
        result = 31 * result + mPosition.hashCode();
        result = 31 * result + (mVisible ? 1 : 0);
        result = 31 * result + mMap.hashCode();
        result = 31 * result + mIndex;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Location) {
            Location loc = (Location) obj;
            return mPosition.equals(loc.getPosition()) && mName.equals(loc.getName());
        }
        return false;
    }

    @Override
    public String toString() {
        return mName + "_" + mPosition;
    }
}

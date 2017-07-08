package core.demo.frag.dijkstra.data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DrkCore
 * @since 2017-02-25
 */
public class MapFactory {

    private static volatile MapFactory instance = null;
    private final List<Map> mMaps;

    private MapFactory() {
        mMaps = new ArrayList<>();
        mMaps.add(new FirstFloorMap());
        mMaps.add(new SecondFloorMap());
        mMaps.add(new ThirdFloorMap());
    }

    public static MapFactory getInstance() {
        if (instance == null) {
            synchronized (MapFactory.class) {
                if (instance == null) {
                    instance = new MapFactory();
                }
            }
        }
        return instance;
    }

    public List<Map> getMaps() {
        return mMaps;
    }

}

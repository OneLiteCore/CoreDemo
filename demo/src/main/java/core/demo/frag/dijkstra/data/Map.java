package core.demo.frag.dijkstra.data;

import android.support.annotation.DrawableRes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import core.demo.frag.dijkstra.algorithm.Graph;
import core.mate.util.DataUtil;

/**
 * @author DrkCore
 * @since 2017-02-25
 */
public class Map {

    private static final Comparator<Location> comparable = (o1, o2) -> o1.getName().compareTo(o2.getName());

    static void sortLocationByName(List<Location> locations) {
        Collections.sort(locations, comparable);
    }

    private String mName;
    @DrawableRes
    private int mMapImg;
    private List<Location> mLocations;

    private int[][] mEdges;

    public String getName() {
        return mName;
    }

    public int getMapImg() {
        return mMapImg;
    }

    private List<Location> mVisibleLocations;

    public List<Location> getVisibleLocations() {
        return mVisibleLocations;
    }

    public List<Location> getLocations() {
        return mLocations;
    }

    void set(String name, int map, List<Location> locations, int[][] edges) {
        mName = name;
        mMapImg = map;
        mLocations = locations;
        mEdges = edges;

        mVisibleLocations = new ArrayList<>();
        for (int i = 0, len = locations.size(); i < len; i++) {
            Location location = locations.get(i);
            location.set(this, i);
            if (location.isVisible()) {
                mVisibleLocations.add(location);
            }
        }
    }

    /*Graph*/

    //南楼主楼梯
    static final int STAIR_SOUTH = 0;
    //南楼电梯
    static final int STAIR_SOUTH_ELEVATOR = 1;
    //南楼西侧楼梯
    static final int STAIR_SOUTH_WEST = 2;
    //南楼东侧楼梯
    static final int STAIR_SOUTH_EAST = 3;

    //北楼主楼梯
    static final int STAIR_NORTH = 4;
    //北楼北侧楼梯
    static final int STAIR_NORTH_NORTH = 5;
    //北楼东侧楼梯
    static final int STAIR_NORTH_EAST = 6;

    private Location[] stairs = new Location[7];
    private Graph mGraph;

    //将某一路径节点标记为楼梯
    void setStair(Location loc, int position) {
        stairs[position] = loc;
    }

    //获取指定索引位置的楼梯节点
    public Location getStair(int stair){
        return stairs[stair];
    }

    //获取楼梯节点的索引位置
    public int getIndexOfStair(Location stair) {
        return DataUtil.indexOf(stairs, stair);
    }

    //获取用于计算路径的操作类
    Graph getGraph() {
        if (mGraph == null) {
            mGraph = new Graph(mEdges);
        }
        return mGraph;
    }

    //查找起止节点之间的路径
    public List<Location> findPath(Location startLocation, Location endLocation) {
        Graph.Result result = getGraph().search(startLocation.getIndex());
        List<Integer> path = result.find(endLocation.getIndex()).getPath();
        List<Location> locations = new ArrayList<>(path.size());
        for (Integer idx : path) {
            locations.add(mLocations.get(idx));
        }
        return locations;
    }

    //寻找距离指定起始节点最近的跨楼层节点
    public Location findNearestStair(Location from) {
        Location[] nearestStairs = stairs.clone();
        //将楼梯数组按照距离起始节点的距离排序
        Arrays.sort(nearestStairs, (o1, o2) -> {
            int o1Len = getTotalLength(from, o1);
            int o2Len = getTotalLength(from, o2);
            return o1Len - o2Len;
        });
        //取距离最近的节点
        return nearestStairs[0];
    }

    //获取两个路径节点之间的最短距离
    private int getTotalLength(Location from, Location to) {
        return getGraph().search(from.getIndex()).find(to.getIndex()).getLength();
    }

}

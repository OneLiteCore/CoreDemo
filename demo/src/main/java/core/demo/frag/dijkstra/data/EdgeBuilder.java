package core.demo.frag.dijkstra.data;

import java.util.Arrays;
import java.util.List;

import core.demo.frag.dijkstra.MathUtil;
import core.demo.frag.dijkstra.algorithm.Vertex;


/**
 * @author DrkCore
 * @since 2017-03-12
 */
public class EdgeBuilder {

    //不可达标志，代表两路径间没有邻接关系
    private static final int UNREACHABLE = Vertex.UNREACHABLE;
    //所有路径节点
    private final List<Location> mLocations;
    //需要构建出的用于描述距离的二维数组
    private final int[][] mEdges;

    public EdgeBuilder(List<Location> mLocations) {
        //构建二维数组
        this.mLocations = mLocations;
        int size = mLocations.size();
        this.mEdges = new int[size][size];
        for (int i = 0; i < size; i++) {
            Arrays.fill(mEdges[i], UNREACHABLE);
            mEdges[i][i] = 0;//自己必定是可达的
        }
    }

    public int[][] build() {
        //避免外部修改二维数组中的值这里克隆出新的数组
        return mEdges.clone();
    }

    //添加两个路径节点之间的邻接关系
    public void add(Location from, Location to) {
        int fromIdx = mLocations.indexOf(from);
        int toIdx = mLocations.indexOf(to);
        if (fromIdx >= 0 && toIdx >= 0) {
            //通过勾股定理换算出两个路径节点之间的距离
            int distance = MathUtil.calcDistance(from.getPosition(), to.getPosition());
            //填充到距离二维数组中
            mEdges[fromIdx][toIdx] = distance;
            mEdges[toIdx][fromIdx] = distance;
        }
    }

}

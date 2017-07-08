package core.demo.frag.dijkstra.algorithm;

import android.support.annotation.NonNull;

import java.util.LinkedList;
import java.util.List;

/**
 * 地图顶点封装类。
 *
 * @author DrkCore
 * @author 2017-4-1
 */
public class Vertex implements Comparable<Vertex> {

    /**
     * 两个顶点之间没有通路的标志
     */
    public static final int UNREACHABLE = Integer.MAX_VALUE;
    /**
     * 索引序号，方便后续查找节点
     */
    private int mIndex;
    /**
     * 与起始节点的最短距离
     */
    private int mLength;

    /**
     * 前一节点的指针
     */
    private Vertex mPrev;

    Vertex setPrev(Vertex prev) {
        mPrev = prev;
        return this;
    }

    public Vertex getPrev() {
        return mPrev;
    }

    /**
     * 当前节点是否已经访问过的标志
     */
    private boolean visited;

    boolean isVisited() {
        return visited;
    }

    Vertex setVisited(boolean visited) {
        this.visited = visited;
        return this;
    }

    public int getIndex() {
        return mIndex;
    }

    Vertex setLength(int length) {
        this.mLength = length;
        return this;
    }

    public int getLength() {
        return mLength;
    }

    Vertex(int idx) {
        this(idx, UNREACHABLE);
    }

    Vertex(int idx, int length) {
        this.mIndex = idx;
        this.mLength = length;
        this.setVisited(false);
    }

    /**
     * 从起始节点到该节点的所有路径
     */
    private List<Integer> mPath;

    /**
     * 获取起点到此节点所经过的所有路径点，
     * 调用一次后会将数据缓存起来。
     * <p>
     * 具体实现请参阅{@link #doGetPath(Vertex)}。
     *
     * @return
     */
    public List<Integer> getPath() {
        return mPath != null ? mPath : (mPath = doGetPath(this));
    }

    /**
     * 递归获取从起始点到该顶点的所经过的路径点
     *
     * @param vertex
     * @return
     */
    private List<Integer> doGetPath(Vertex vertex) {
        List<Integer> path = null;
        if (vertex != null) {
            Vertex prev = vertex.getPrev();
            if (prev != null) {
                path = doGetPath(prev);
            } else {
                path = new LinkedList<>();
            }
            path.add(vertex.getIndex());
        }
        return path;
    }

    /**
     * 按照距离起点的距离实现{@link Comparable}
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(@NonNull Vertex o) {
        if (mLength == o.mLength) {
            return 0;
        } else {
            return o.mLength > mLength ? -1 : 1;
        }
    }
}
package core.demo.frag.dijkstra.algorithm;

import android.util.SparseArray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author DrkCore
 * @since 2017-4-1
 */
public class Graph {

    /**
     * 路径节点之间的距离权重
     */
    private int[][] mEdges;

    public Graph(int[][] edges) {
        this.mEdges = edges.clone();
    }

    /*calc*/

    //保存起点到其他点的路径信息的封装类。
    public class Result {

        //起始节点的索引位置
        int mStartIdx;
        //所有节点
        List<Vertex> mVertices;

        Result(int startIdx) {
            this.mStartIdx = startIdx;
            int len = mEdges.length;
            //创建节点集合
            mVertices = new ArrayList<>(len);
            for (int i = 0; i < len; i++) {
                mVertices.add(new Vertex(i));
            }
            //起始节点到自己的距离肯定为0
            mVertices.get(startIdx).setLength(0);
        }

        public Vertex find(int idx) {
            return get()[idx];
        }

        //已经获取到的节点结果
        private Vertex[] mResultVertices;

        //获取每个顶点。第一次调用后会将结果缓存起来。
        Vertex[] get() {
            if (mResultVertices == null) {//结果单例
                LinkedList<Vertex> unVisited = new LinkedList<>(mVertices);
                while (!unVisited.isEmpty()) {
                    Collections.sort(unVisited);
                    Vertex vertex = unVisited.poll();
                    //顶点已经计算出最短路径，设置为"已访问"
                    vertex.setVisited(true);
                    //获取所有"未访问"的邻居
                    List<Vertex> neighbors = getNeighbors(vertex);
                    //更新邻居的最短路径
                    updatesDistance(vertex, neighbors);
                }

                //将结果填充到result中
                mResultVertices = new Vertex[mVertices.size()];
                for (Vertex vertex : mVertices) {
                    mResultVertices[vertex.getIndex()] = vertex;
                }
            }
            return mResultVertices;
        }

        //更新周边顶点的距离信息
        private void updatesDistance(Vertex vertex, List<Vertex> neighbors) {
            for (Vertex neighbor : neighbors) {
                int distance = getDistance(vertex, neighbor) + vertex.getLength();
                if (distance >= 0 && distance < neighbor.getLength()) {
                    //将节点设置为邻居的前一节点，使得路径形成一个单向链表
                    neighbor.setPrev(vertex);
                    neighbor.setLength(distance);
                }
            }
        }

        //获取两个顶点之间的距离
        private int getDistance(Vertex source, Vertex destination) {
            int sourceIndex = mVertices.indexOf(source);
            int destIndex = mVertices.indexOf(destination);
            return mEdges[sourceIndex][destIndex];
        }

        //获取指定顶点的邻接顶点。
        private List<Vertex> getNeighbors(Vertex v) {
            List<Vertex> neighbors = new ArrayList<>();
            int position = mVertices.indexOf(v);
            for (int i = 0, size = mVertices.size(); i < size; i++) {
                if (i == position) {
                    //顶点本身，跳过
                    continue;
                }
                int distance = mEdges[position][i];    //到所有顶点的距离
                if (distance < Vertex.UNREACHABLE) {
                    //是邻居(有路径可达)
                    Vertex neighbor = mVertices.get(i);
                    if (!neighbor.isVisited()) {
                        //如果邻居没有访问过，则加入list;
                        neighbors.add(neighbor);
                    }
                }
            }
            return neighbors;
        }

    }

    /**
     * 用于缓存搜索结果的映射表。
     * <p>
     * SparseArray 是 Android 提供的以 int 为键的映射表
     */
    private final SparseArray<Result> cachedResults = new SparseArray<>();

    /**
     * 获取指定起点到所有其他点的最短路径。
     * 每次搜索后结果将会被缓存起来。
     *
     * @param startIdx
     * @return
     */
    public Result search(int startIdx) {
        Result result = cachedResults.get(startIdx);
        if (result == null) {
            result = new Result(startIdx);
            cachedResults.put(startIdx, result);
        }
        return result;
    }

}
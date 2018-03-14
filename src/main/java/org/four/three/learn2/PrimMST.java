package org.four.three.learn2;

import org.two.four.learn2.IndexMinHeap;

import java.util.Vector;

/**
 * 使用优化的Prim算法求图的最小生成树
 * <p>
 * 时间复杂度：O(ElogV)
 *
 * @author cheng
 *         2018/3/14 21:08
 */
public class PrimMST<Weight extends Number & Comparable> {

    /**
     * 图的引用
     */
    private WeightedGraph G;

    /**
     * 最小索引堆，算法辅助数据结构
     */
    private IndexMinHeap<Weight> ipq;

    /**
     * 访问的点所对应的边，算法辅助数据结构
     */
    private Edge<Weight>[] edgeTo;

    /**
     * 标记数组，在算法运行过程中标记结点i是否被访问
     */
    private boolean[] marked;

    /**
     * 最小生成树所包含的所有边
     */
    private Vector<Edge<Weight>> mst;

    /**
     * 最小生成树的权值
     */
    private Number mstWeight;

    public PrimMST(WeightedGraph graph) {

        if (graph.E() < 1) {
            throw new IllegalArgumentException("Invalid argument.");
        }

        G = graph;
        ipq = new IndexMinHeap<>(G.V());
        marked = new boolean[G.V()];
        edgeTo = new Edge[G.V()];
        for (int i = 0; i < G.V(); i++) {
            marked[i] = false;
            edgeTo[i] = null;
        }
        mst = new Vector<Edge<Weight>>();

        // Prim
        visit(0);
        while (!ipq.isEmpty()) {
            // 使用最小索引堆找出已经访问的边中权值最小的边
            // 最小索引堆中存储的是点的索引，通过点的索引找到相对应的边
            int v = ipq.extractMinIndex();
            if (edgeTo[v] == null) {
                throw new RuntimeException("Invalid data.");
            }
            mst.add(edgeTo[v]);
            visit(v);
        }

        // 计算最小生成树的权值
        mstWeight = mst.elementAt(0).wt();
        for (int i = 1; i < mst.size(); i++) {
            mstWeight = mstWeight.doubleValue() + mst.elementAt(i).wt().doubleValue();
        }
    }

    /**
     * 访问结点
     */
    private void visit(int v) {

        if (marked[v]) {
            throw new IllegalArgumentException(" v 已经被访问过.");
        }

        marked[v] = true;

        // 将和结点v相连接的未访问的另一端点，和与之相连的边，放入索引最小堆中
        for (Object item : G.adj(v)) {
            Edge<Weight> e = (Edge<Weight>) item;
            int w = e.other(v);
            // 如果边的另一端点未被访问
            if (!marked[w]) {
                // 如果从没有考虑过这个端点，直接将这个端点和与之相连接的边加入索引最小堆
                if (edgeTo[w] == null) {
                    edgeTo[w] = e;
                    ipq.insert(w, e.wt());
                } else if (e.wt().compareTo(edgeTo[w].wt()) < 0) {
                    // 如果曾经考虑过这个端点，但现在的边比之前考虑的边更短，则进行替换
                    edgeTo[w] = e;
                    ipq.change(w, e.wt());
                }
            }
        }
    }

    /**
     * 返回最小生成树的所有边
     */
    public Vector<Edge<Weight>> mstEdge() {
        return mst;
    }

    /**
     * 返回最小生成树的权值
     */
    public Number result() {
        return mstWeight;
    }

    public static void main(String[] args) {

        // 测试 Prim
        String filename = "src/main/java/org/four/three/text/testG1.txt";
        int V = 8;

        SparseWeightedGraph<Double> g = new SparseWeightedGraph<>(V, false);
        ReadWeightedGraph readGraph = new ReadWeightedGraph(g, filename);

        // Test Prim MST
        System.out.println("Test Prim MST:");
        PrimMST<Double> primMST = new PrimMST<>(g);
        Vector<Edge<Double>> mst = primMST.mstEdge();
        for (int i = 0; i < mst.size(); i++) {
            System.out.println(mst.elementAt(i));
        }
        System.out.println("The MST weight is:" + primMST.result());
    }
}

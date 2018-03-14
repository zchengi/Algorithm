package org.four.three.learn2;

import java.util.Vector;

/**
 * Kruskal算法求最小生成树
 *
 * 时间复杂度：O(ElogE + ElogV)
 *
 * @author cheng
 *         2018/3/14 21:49
 */
public class KruskalMST<Weight extends Number & Comparable> {

    /**
     * 最小生成树所包含的所有边
     */
    private Vector<Edge<Weight>> mst;

    /**
     * 最小生成树的权值
     */
    private Number mstWeight;

    public KruskalMST(WeightedGraph graph) {

        mst = new Vector<Edge<Weight>>();

        // 将图中的所有边存放到一个最小堆中
        MinHeap<Edge<Weight>> pq = new MinHeap<Edge<Weight>>(graph.E());
        for (int i = 0; i < graph.V(); i++) {
            for (Object item : graph.adj(i)) {
                Edge<Weight> e = (Edge<Weight>) item;
                // 对于无向图的一条边在图中存储了两次（v->w; w->v）
                // 所以这里添加判断使得只向最小堆中插入一条边
                if (e.v() <= e.w()) {
                    pq.insert(e);
                }
            }
        }

        // 创建一个并查集，来查看已经访问的结点的连通情况
        UnionFind uf = new UnionFind(graph.V());
        // 最小堆不为空
        // 最小生成树的边不大于graph的顶点数 - 1
        while (!pq.isEmpty() && mst.size() < graph.V() - 1) {

            // 从最小堆中一次从小到大取出所有边
            Edge<Weight> e = pq.extractMin();
            // 如果该边的两个端点是连通的，说明假如这条边将产生环，扔掉这条变
            if (uf.isConnected(e.v(), e.w())) {
                continue;
            }

            // 否则，将这条边添加进最小生成树，同时标记边的两个端点连通
            mst.add(e);
            uf.unionElements(e.v(), e.w());
        }

        // 计算最小生成树
        mstWeight = mst.elementAt(0).wt();
        for (int i = 1; i < mst.size(); i++) {
            mstWeight = mstWeight.doubleValue() + mst.elementAt(i).wt().doubleValue();
        }
    }

    public Vector<Edge<Weight>> mstEdges() {
        return mst;
    }

    public Number result() {
        return mstWeight;
    }

    public static void main(String[] args) {

        // 测试 Kruskal
        String filename = "src/main/java/org/four/three/text/testG1.txt";
        int V = 8;

        SparseWeightedGraph<Double> g = new SparseWeightedGraph<>(V, false);
        ReadWeightedGraph readGraph = new ReadWeightedGraph(g, filename);

        // Test Kruskal
        System.out.println("Test Kruskal:");
        KruskalMST<Double> kruskalMST = new KruskalMST<>(g);
        Vector<Edge<Double>> mst = kruskalMST.mstEdges();
        for (int i = 0; i < mst.size(); i++) {
            System.out.println(mst.elementAt(i));
        }
        System.out.println("The Kruskal weight is:" + kruskalMST.result());
    }
}

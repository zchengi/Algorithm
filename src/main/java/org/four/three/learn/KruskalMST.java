package org.four.three.learn;

import edu.princeton.cs.algs4.MinPQ;
import org.example.Queue;

/**
 * 最小生成树的 Kruskal 算法
 * <p>
 * 时间复杂度：O(ElogE)
 * 空间复杂度：O(E)
 *
 * @author cheng
 *         2018/3/15 19:49
 */
public class KruskalMST {

    private Queue<Edge> mst;
    private double weight;

    public KruskalMST(EdgeWeightGraph G) {
        mst = new Queue<Edge>();
        MinPQ<Edge> pq = new MinPQ<>();
        for (Edge e : G.edges()) {
            pq.insert(e);
        }
        UnionFind uf = new UnionFind(G.V());

        while (!pq.isEmpty() && mst.size() < G.V() - 1) {
            // 从pq中得到权重最小的边和它的顶点
            Edge e = pq.delMin();
            int v = e.either();
            int w = e.other(v);

            // 忽略失效的边
            if (uf.isConnected(v, w)) continue;
            // 合并分量
            uf.unionElements(v, w);
            // 将边添加到最小生成树中
            mst.enqueue(e);

            // 计算权重
            weight += e.weight();
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight() {
        return weight;
    }

    public static void main(String[] args) {

        // tinyEWG.txt mediumEWG.txt
        String filename = "src/main/java/org/four/three/text/mediumEWG.txt";
        EdgeWeightGraph G = new EdgeWeightGraph(filename);
        KruskalMST mst = new KruskalMST(G);

        System.out.println(filename + "：");
        for (Edge e : mst.edges()) {
            System.out.println(e);
        }
        System.out.println();
        System.out.printf("%.5f\n", mst.weight());
    }
}

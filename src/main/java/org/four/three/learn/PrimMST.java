package org.four.three.learn;


import org.example.Queue;
import org.two.four.learn.IndexMinPQ;

/**
 * 最小生成树的Prim算法(即时版本)
 * <p>
 * 时间复杂度：O(ElogV)
 * 空间复杂度：O(V)
 *
 * @author cheng
 *         2018/3/15 19:19
 */
public class PrimMST {

    /**
     * 距离树最近的边
     */
    private Edge[] edgeTo;

    /**
     * distTo[w] = edgeTo[w].weight()
     */
    private double[] distTo;

    /**
     * 如果v在树中则为true
     */
    private boolean[] marked;

    /**
     * 有效的横切边
     */
    private IndexMinPQ<Double> pq;

    public PrimMST(EdgeWeightGraph G) {
        edgeTo = new Edge[G.V()];
        distTo = new double[G.V()];
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        pq = new IndexMinPQ<Double>(G.V());

        distTo[0] = 0.0;
        // 用顶点0和权重0初始化pq
        pq.insert(0, 0.0);

        while (!pq.isEmpty()) {
            // 将最近的顶点添加到树中
            visit(G, pq.delMin());
        }
    }

    private void visit(EdgeWeightGraph G, int v) {
        //将顶点v添加到树中，更新数据
        marked[v] = true;
        for (Edge e : G.adj(v)) {
            int w = e.other(v);

            // v - w 失效
            if (marked[w]) continue;
            // 连接 w 和树的最佳边Edge变为e
            if (e.weight() < distTo[w]) {
                edgeTo[w] = e;
                distTo[w] = e.weight();
                if (pq.contains(w)) {
                    pq.change(w, distTo[w]);
                } else {
                    pq.insert(w, distTo[w]);
                }
            }
        }
    }

    public Iterable<Edge> edges() {
        Queue<Edge> mst = new Queue<>();
        for (Edge e : edgeTo) {
            if (e != null) {
                mst.enqueue(e);
            }
        }
        return mst;
    }

    public double weight() {
        double weight = 0.0;
        for (Edge e : edges()) {
            weight += e.weight();
        }
        return weight;
    }

    public static void main(String[] args) {
        // tinyEWG.txt mediumEWG.txt
        String filename = "src/main/java/org/four/three/text/mediumEWG.txt";
        EdgeWeightGraph G = new EdgeWeightGraph(filename);
        PrimMST mst = new PrimMST(G);

        System.out.println(filename + "：");
        for (Edge e : mst.edges()) {
            System.out.println(e);
        }
        System.out.println();
        System.out.printf("%.5f\n", mst.weight());
    }
}

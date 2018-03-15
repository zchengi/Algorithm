package org.four.three.learn;

import org.example.Queue;
import org.two.four.learn2.MinHeap;

/**
 * 最小生成树的Prim算法的延时实现
 * <p>
 * 时间复杂度：O(ElogE)
 * 空间复杂度：O(E)
 *
 * @author cheng
 *         2018/3/15 16:28
 */
public class LazyPrimMST {

    /**
     * 最小生成树的顶点
     */
    private boolean marked[];

    /**
     * 最小生成树的边
     */
    private Queue<Edge> mst;

    /**
     * 横切边(包含失效的边)
     */
    private MinHeap<Edge> pq;

    private double weight;

    public LazyPrimMST(EdgeWeightGraph G) {

        mst = new Queue<Edge>();
        pq = new MinHeap<Edge>();
        marked = new boolean[G.V()];

        // 假设G是连通的
        visit(G, 0);
        while (!pq.isEmpty()) {
            // 从pq中取到权重最小的边
            Edge e = pq.extractMin();
            // 跳过失效的边
            int v = e.either(), w = e.other(v);
            if (marked[v] && marked[w]) {
                continue;
            }

            // 将最小边添加到树中
            mst.enqueue(e);
            // 计算最小生成树的权重
            weight += e.weight();

            // 将没有访问的顶点添加到树中
            if (!marked[v]) {
                visit(G, v);
            } else {
                visit(G, w);
            }
        }
    }

    private void visit(EdgeWeightGraph G, int v) {

        if (marked[v]) {
            throw new IllegalArgumentException(" v 已经被访问过.");
        }

        // 标记顶点v并将所有连接v和未被标记的顶点加入边pq
        marked[v] = true;
        for (Edge e : G.adj(v)) {
            if (!marked[e.other(v)]) {
                pq.insert(e);
            }
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
        LazyPrimMST mst = new LazyPrimMST(G);

        for (Edge e : mst.edges()) {
            System.out.println(e);
        }
        System.out.printf("%.5f\n", mst.weight());
    }
}

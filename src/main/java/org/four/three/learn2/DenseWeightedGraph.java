package org.four.three.learn2;

import java.util.Vector;

/**
 * 稠密图 - 邻接矩阵（加权）
 *
 * @author cheng
 *         2018/3/14 15:23
 */
public class DenseWeightedGraph<Weight extends Number & Comparable> implements WeightedGraph {

    /**
     * 结点数
     */
    private int n;

    /**
     * 边数
     */
    private int m;

    /**
     * 是否为有向图
     */
    private boolean directed;

    /**
     * 图的具体数据
     */
    private Edge<Weight>[][] g;

    public DenseWeightedGraph(int n, boolean directed) {
        if (n < 0) throw new IllegalArgumentException("Number of vertices must be nonegative!");

        this.n = n;
        // 初始化没有任何边
        this.m = 0;
        this.directed = directed;
        // g 初始化为 n * n 的布尔矩阵，每个g[i][j]均为false，表示没有任何边
        // false 为 boolean型变量的默认值
        g = new Edge[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                g[i][j] = null;
            }
        }
    }

    /**
     * 向图中添加一个边
     */
    public void addEdge(Edge e) {

        if (e.v() < 0 || e.v() >= n)
            throw new IllegalArgumentException("Called addEdge() invalid argument:" + e.v() + "!");
        if (e.w() < 0 || e.w() >= n)
            throw new IllegalArgumentException("Called addEdge() invalid argument:" + e.w() + "!");

        if (hasEdge(e.v(), e.w())) {
            return;
        }

        g[e.v()][e.w()] = new Edge(e);
        if (e.v() != e.w() && !directed) {
            g[e.w()][e.v()] = new Edge(e.w(), e.v(), e.wt());
        }

        m++;
    }

    /**
     * 返回图中一个顶点的所有邻边
     * 由于java使用引用机制，返回一个Vector不会带来额外的开销
     */
    public Iterable<Edge<Weight>> adj(int v) {
        if (v < 0 || v >= n) throw new IllegalArgumentException("Called adj() invalid argument:" + v + "!");

        Vector<Edge<Weight>> adjV = new Vector<>();
        for (int i = 0; i < n; i++) {
            if (g[v][i] != null) {
                adjV.add(g[v][i]);
            }
        }
        return adjV;
    }

    public void show() {
        for (int i = 0; i < n; i++) {
            System.out.print("vertex " + i + ":\t");
            for (int j = 0; j < n; j++) {
                if (g[i][j] != null) {
                    System.out.print(g[i][j].wt() + "\t");
                } else {
                    System.out.print("NULL\t");
                }
            }
            System.out.println();
        }
    }

    /**
     * 验证图中是否有从v到w的边
     */
    public boolean hasEdge(int v, int w) {
        if (v < 0 || v >= n) throw new IllegalArgumentException("Called hasEdge() invalid argument:" + v + "!");
        if (w < 0 || w >= n) throw new IllegalArgumentException("Called hasEdge() invalid argument:" + w + "!");
        return g[v][w] != null;
    }

    /**
     * 返回结点个数
     */
    public int V() {
        return n;
    }

    /**
     * 返回边的个数
     */
    public int E() {
        return n;
    }
}

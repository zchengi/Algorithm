package org.four.three.learn2;


import java.util.Vector;

/**
 * 稀疏图 - 邻接表（加权）
 *
 * @author cheng
 *         2018/3/14 15:51
 */
public class SparseWeightedGraph<Weight extends Number & Comparable> implements WeightedGraph {

    private int n;

    private int m;

    /**
     * 是否为有向图
     */
    private boolean directed;

    private Vector<Edge<Weight>>[] g;

    public SparseWeightedGraph(int n, boolean directed) {
        if (n < 0) throw new IllegalArgumentException("Number of vertices must be nonegative!");

        this.n = n;
        this.m = 0;
        this.directed = directed;

        g = (Vector<Edge<Weight>>[]) new Vector[n];
        // g 初始化为n个空的vector，表示每一个g[i]都为空，即没有任何边
        for (int i = 0; i < n; i++) {
            g[i] = new Vector<Edge<Weight>>();
        }
    }

    public void addEdge(Edge e) {

        if (e.v() < 0 || e.v() >= n) {
            throw new IllegalArgumentException("Called addEdge() invalid argument:" + e.v() + "!");
        }
        if (e.w() < 0 || e.w() >= n) {
            throw new IllegalArgumentException("Called addEdge() invalid argument:" + e.w() + "!");
        }

        g[e.v()].add(new Edge(e));
        if (e.v() != e.w() && !directed) {
            g[e.w()].add(new Edge(e.w(), e.v(), e.wt()));
        }

        m++;
    }

    /**
     * 返回图中一个顶点的所有邻边
     * 由于java使用引用机制，返回一个Vector不会带来额外的开销
     */
    public Iterable<Edge<Weight>> adj(int v) {
        if (v < 0 || v >= n) throw new IllegalArgumentException("Called adj() invalid argument:" + v + "!");
        return g[v];
    }

    public void show() {
        for (int i = 0; i < n; i++) {
            System.out.print("vertex " + i + ":\t");
            for (int j = 0; j < g[i].size(); j++) {
                Edge e = g[i].elementAt(j);
                System.out.print("( to:" + e.other(i) + ",wt:" + e.wt() + ")\t");
            }
            System.out.println();
        }
    }

    public boolean hasEdge(int v, int w) {
        if (v < 0 || v >= n) throw new IllegalArgumentException("Called hasEdge() invalid argument:" + v + "!");
        if (w < 0 || w >= n) throw new IllegalArgumentException("Called hasEdge() invalid argument:" + w + "!");

        for (int i = 0; i < g[v].size(); i++) {
            if (g[v].elementAt(i).other(v) == w) return true;
        }
        return false;
    }

    public int V() {
        return n;
    }

    public int E() {
        return m;
    }
}

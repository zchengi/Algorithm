package org.four.one.learn2;

import java.util.Vector;

/**
 * 稀疏图 - 邻接表
 *
 * @author cheng
 *         2018/3/7 11:19
 */
public class SparseGraph implements Graph {

    private int n;

    private int m;

    private boolean directed;

    private Vector<Integer>[] g;

    public SparseGraph(int n, boolean directed) {
        if (n < 0) throw new IllegalArgumentException("DenseGraph underflow!");

        this.n = n;
        this.m = 0;
        this.directed = directed;

        g = (Vector<Integer>[]) new Vector[n];
        for (int i = 0; i < n; i++) {
            g[i] = new Vector<Integer>();
        }
    }

    public void addEdge(int v, int w) {

        if (v < 0 || v >= n) throw new IllegalArgumentException("Called addEdge() invalid argument:" + v + "!");
        if (w < 0 || w >= n) throw new IllegalArgumentException("Called addEdge() invalid argument:" + w + "!");

        g[v].add(w);
        if (v != w && !directed) {
            g[w].add(v);
        }

        m++;
    }

    /**
     * 返回图中一个顶点的所有邻边
     * 由于java使用引用机制，返回一个Vector不会带来额外的开销
     */
    public Iterable<Integer> adj(int v) {
        if (v < 0 || v >= n) throw new IllegalArgumentException("Called addEdge() invalid argument:" + v + "!");
        return g[v];
    }

    public void show() {
        for (int i = 0; i < n; i++) {
            System.out.print("vertex " + i + ":\t");
            for (int j = 0; j < g[i].size(); j++) {
                System.out.print(g[i].elementAt(j) + "\t");
            }
            System.out.println();
        }
    }

    public boolean hasEdge(int v, int w) {
        if (v < 0 || v >= n) throw new IllegalArgumentException("Called addEdge() invalid argument:" + v + "!");
        if (w < 0 || w >= n) throw new IllegalArgumentException("Called addEdge() invalid argument:" + w + "!");

        for (int i = 0; i < g[v].size(); i++) {
            if (g[v].elementAt(i) == w) return true;
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

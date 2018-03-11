package org.four.one.learn;

import edu.princeton.cs.algs4.In;
import org.example.Stack;

import java.util.NoSuchElementException;

/**
 * 4.1 无向图的API
 * 支持平行边和自环
 * *******************************************************************************
 * public class Graph
 * *******************************************************************************
 * Graph(int V)                         创建一个含有V个顶点但不含有边的图
 * Graph(In in)                         从标准输入流In中读入一副图
 * Graph(String filename)               从文件名中读入一幅图
 * Graph(Graph G)                       复制一幅图
 * int V()                              顶点数
 * int E()                              边数
 * void addEdge(int v, int w)           添加一条边
 * boolean hasEdge(int v, int w)        判断是否存在一条边连接 v 和 w
 * int degree(int v)                    计算v的度数
 * Iterable<Integer> adj(int v)         和v相邻的所有顶点
 * String toString()                    对象的字符串表示
 * void validateVertex(int v)           验证顶点是否合法
 * *******************************************************************************
 *
 * @author cheng
 *         2018/3/8 11:59
 */
public class Graph {

    /**
     * 换行符：Linux、Windows
     */
    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V;

    private int E;

    private Bag<Integer>[] adj;

    public Graph(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices must be nonegative!");

        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < adj.length; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    public Graph(In in) {
        try {
            this.V = in.readInt();
            if (V < 0) throw new IllegalArgumentException("Number of vertices in a Graph must be nonegative!");
            adj = (Bag<Integer>[]) new Bag[V];
            for (int v = 0; v < V; v++) {
                adj[v] = new Bag<Integer>();
            }

            int E = in.readInt();
            if (E < 0) throw new IllegalArgumentException("Number of edges in a Graph must be nonegative!");
            for (int i = 0; i < E; i++) {
                int v = in.readInt();
                int w = in.readInt();

                validateVertex(v);
                validateVertex(w);
                addEdge(v, w);
            }
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("Invalid input format in Graph constructor", e);
        }
    }

    public Graph(String filename) {
        this(new In(filename));
    }

    public Graph(Graph G) {
        this(G.V);
        this.E = G.E;

        // 使用 Stack 反向遍历，以便邻接表与原始顺序相同
        Stack<Integer> reverse = new Stack<>();
        for (int v = 0; v < G.V; v++) {
            for (int w : G.adj[v]) {
                reverse.push(w);
            }
            while (!reverse.isEmpty()) {
                adj[v].add(reverse.pop());
            }
        }
    }

    public void addEdge(int v, int w) {

        validateVertex(v);
        validateVertex(w);

        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    public boolean hasEdge(int v, int w) {

        if (v < 0 || v >= V || w < 0 || w >= V) return false;

        for (int x : adj(v)) {
            if (w == x) return true;
        }

        return false;
    }

    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    public int degree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V).append(" vertices, ").append(E).append(" edges ").append(NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v).append(": ");
            for (int w : adj[v]) {
                s.append(w).append(" ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("Vertex " + v + " is not between 0 and " + (V - 1));
        }
    }

    public static void main(String[] args) {

        // tinyG.txt mediumG.txt
        String filename = "src/main/java/org/four/one/text/tinyG.txt";
        Graph g = new Graph(filename);

        System.out.println(g);
        System.out.println(g.degree(0));
    }
}

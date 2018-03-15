package org.four.three.learn;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;

/**
 * 算法4.3节 加权无向图的API(稀疏图)
 * ********************************************************************
 * public class EdgeWeightGraph
 * ********************************************************************
 * EdgeWeightGraph (int V)              创建一副含有V个顶点的空图
 * public EdgeWeightGraph(int V, int E) 随机生成一副含有V个顶点E条边的图
 * EdgeWeightGraph (String filename)    从文件名中读取图
 * EdgeWeightGraph (In in)              从输入流中读取图
 * EdgeWeightGraph (EdgeWeightGraph G)  初始化一个G的副本
 * int V()                              图的顶点数
 * int E()                              图的边数
 * vod addEdge()                        向途中添加一条边e
 * Iterable<Edge> adj(int v)            和v相关联的所有边
 * public int degree(int v)             返回v的度数
 * Iterable<Edge> edges()               图的所有边
 * String toString()                    对象的字符串表示
 * ********************************************************************
 * <p>
 * 注：- 允许存在平行边  - 运行存在自环
 *
 * @author cheng
 *         2018/3/15 15:29
 */
public class EdgeWeightGraph {

    private static final String NEWLINE = System.getProperty("line.separator");

    /**
     * 顶点总数
     */
    private final int V;

    /**
     * 边的总数
     */
    private int E;

    /**
     * 邻接表
     */
    private Bag<Edge>[] adj;

    public EdgeWeightGraph(int V) {
        if (V < 0) {
            throw new IllegalArgumentException("Number of vertex must be nonegative.");
        }

        this.V = V;
        this.E = 0;
        adj = (Bag<Edge>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Edge>();
        }
    }

    public EdgeWeightGraph(int V, int E) {
        this(V);
        if (E < 0) {
            throw new IllegalArgumentException("Number of edge mus be nonegative.");
        }
        for (int i = 0; i < E; i++) {
            int v = (int) (Math.random() * V);
            int w = (int) (Math.random() * V);
            double weight = Math.round(100 * Math.random()) / 100.0;
            Edge e = new Edge(v, w, weight);
            addEdge(e);
        }
    }

    public EdgeWeightGraph(String filename) {
        this(new In(filename));
    }

    public EdgeWeightGraph(In in) {
        this(in.readInt());
        int E = in.readInt();
        if (E < 0) {
            throw new IllegalArgumentException("Number of edge mus be nonegative.");
        }

        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            validateVertex(v);
            validateVertex(w);
            double weight = in.readDouble();
            Edge e = new Edge(v, w, weight);
            addEdge(e);
        }
    }

    public EdgeWeightGraph(EdgeWeightGraph G) {
        this(G.V);
        this.E = G.E;
        for (int v = 0; v < G.V; v++) {
            Stack<Edge> reverse = new Stack<>();
            for (Edge e : G.adj[v]) {
                reverse.push(e);
            }
            for (Edge e : reverse) {
                adj[v].add(e);
            }
        }
    }

    public void addEdge(Edge e) {

        int v = e.either();
        int w = e.other(v);
        validateVertex(v);
        validateVertex(w);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    public Iterable<Edge> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    public int degree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    public Iterable<Edge> edges() {
        Bag<Edge> list = new Bag<>();
        for (int v = 0; v < V; v++) {
            // 自环个数
            int selfLoops = 0;
            for (Edge e : adj(v)) {
                // 因为无向图一条边存储两次，所以这里去除一半
                if (e.other(v) > v) {
                    list.add(e);
                } else if (e.other(v) == v) {
                    // 自环边只添加一次
                    if (selfLoops % 2 == 0) {
                        list.add(e);
                    }
                    selfLoops++;
                }
            }
        }

        return list;
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

    public String toString() {

        StringBuilder s = new StringBuilder();
        s.append(V).append(" ").append(E).append(NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v).append(": ");
            for (Edge e : adj[v]) {
                s.append(e).append(" ");
            }
            s.append(NEWLINE);
        }

        return s.toString();
    }

    public static void main(String[] args) {

        // tinyEWG.txt
        String filename = "src/main/java/org/four/three/text/tinyEWG.txt";
        EdgeWeightGraph G = new EdgeWeightGraph(filename);
        System.out.println(G);
    }
}

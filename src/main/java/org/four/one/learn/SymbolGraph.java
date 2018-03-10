package org.four.one.learn;


import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import org.three.two.learn.BinarySymbolTable;

/**
 * 4.1.7节 符号图的API
 * ********************************************************************************************************
 * public class SymbolGraph
 * ********************************************************************************************************
 * SymbolGraph(String filename, String delimiter)   根据 filename 指定的文件构造图，使用delimiter来分割顶点名
 * boolean contains(String key)                     key 是一个顶点吗？
 * int indexOf(String key)                          key 的索引
 * String nameOf(int v)                             索引 v 的顶点名
 * Graph graph()                                    隐藏的 Graph 对象
 * ********************************************************************************************************
 *
 * @author cheng
 *         2018/3/10 15:09
 */
public class SymbolGraph {

    /**
     * 符号表 -> 索引
     */
    private BinarySymbolTable<String, Integer> st;

    /**
     * 索引 -> 符号表
     */
    private String[] keys;

    /**
     * 图
     */
    private Graph graph;

    public SymbolGraph(String filename, String delimiter) {
        st = new BinarySymbolTable<>();

        // 第一遍
        In in = new In(filename);
        // 构造符号表索引
        while (!in.isEmpty()) {
            // 读取字符串
            String[] a = in.readLine().split(delimiter);
            // 为每个不同的字符串关联一个索引
            for (String name : a) {
                if (!st.contains(name)) {
                    st.put(name, st.size());
                }
            }
        }
        System.out.println("Done reading " + filename);

        // 用来获取顶点名的方向索引是一个数组
        keys = new String[st.size()];
        for (String name : st.keys()) {
            keys[st.get(name)] = name;
        }

        graph = new Graph(st.size());
        // 第二遍
        in = new In(filename);
        // 构造图
        while (in.hasNextLine()) {
            // 将每一个行的第一个顶点和该行的其他顶点相连
            String[] a = in.readLine().split(delimiter);
            int v = st.get(a[0]);
            for (int i = 1; i < a.length; i++) {
                int w = st.get(a[i]);
                graph.addEdge(v, w);
            }
        }
    }

    public boolean contains(String s) {
        return st.contains(s);
    }

    public int indexOf(String s) {
        return st.get(s);
    }

    public String nameOf(int v) {
        validateVertex(v);
        return keys[v];
    }

    public Graph graph() {
        return graph;
    }

    public void validateVertex(int v) {
        int V = graph.V();
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("Vertex " + v + " is not between 0 and " + (V - 1));
        }
    }

    public static void main(String[] args) {

        // routes.txt " " ; movies.txt "/"
        String filename = "src/main/java/org/four/one/text/movies.txt";
        String delimiter = "/";
        SymbolGraph sg = new SymbolGraph(filename, delimiter);

        Graph graph = sg.graph;
        // routes.txt : JFK、LAX
        // movies.txt : Tin Men (1987)、Bacon, Kevin
        while (StdIn.hasNextLine()) {
            String source = StdIn.readLine();
            if (sg.contains(source)) {
                int s = sg.indexOf(source);
                for (int v : graph.adj(s)) {
                    System.out.println("  " + sg.nameOf(v));
                }
            } else {
                System.out.println("Input not contain '" + source + "'");
            }
        }
    }
}

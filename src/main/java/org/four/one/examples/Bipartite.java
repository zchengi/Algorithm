package org.four.one.examples;

import org.example.Stack;
import org.four.one.learn.Graph;

/**
 * 双色问题：G是二分图吗？
 * <p>
 * 使用深度优先搜索来确定图形是否具有双分区;
 * 如果是的话，返回 1; 如果不是，则返回奇数长度的循环。
 * 在最坏的情况下，它需要时间与V + E成正比;
 * 时间复杂度：O(E + V)
 *
 * @author cheng
 *         2018/3/9 14:59
 */
public class Bipartite {

    /**
     * 是否是二分图
     */
    private boolean isBipartite;

    /**
     * color[v] 给出了二分图一侧的顶点
     */
    private boolean[] color;

    private boolean[] marked;

    private int[] edgeTo;

    private Stack<Integer> cycle;

    public Bipartite(Graph G) {
        isBipartite = true;
        color = new boolean[G.V()];
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];

        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
            }
        }

        assert check(G);
    }

    private void dfs(Graph G, int v) {

        marked[v] = true;
        for (int w : G.adj(v)) {
            if (cycle != null) return;

            if (!marked[w]) {
                edgeTo[w] = v;
                color[w] = !color[v];
                dfs(G, w);
            } else if (color[w] == color[v]) {
                isBipartite = false;

                cycle = new Stack<>();
                cycle.push(w);
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
            }
        }
    }

    public boolean isBipartite() {
        return isBipartite;
    }

    public boolean color(int v) {
        validateVertex(v);
        if (!isBipartite) {
            throw new UnsupportedOperationException("Graph is not bipartite.");
        }
        return color[v];
    }

    public Iterable<Integer> oddCycle() {
        return cycle;
    }

    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("Vertex _" + v + " is not between 0 and " + (V - 1));
        }
    }

    private boolean check(Graph G) {
        if (isBipartite) {
            for (int v = 0; v < G.V(); v++) {
                for (int w : G.adj(v)) {
                    if (color[v] == color[w]) {
                        System.out.printf("Edge %d-%d with %d and %d in same side of bipartite.\n", v, w, v, w);
                        return false;
                    }
                }
            }
        } else {
            // graph has an odd-length cycle

            int first = -1;
            int last = -1;
            for (int v : oddCycle()) {
                if (first == -1) first = v;
                last = v;
            }
            if (first != last) {
                System.out.printf("Cycle begins with %d and ends with %d.\n", first, last);
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        // tinyG.txt
        String filename = "src/main/java/org/four/one/text/tinyG.txt";
        Graph g = new Graph(filename);
        System.out.println(g);
        Bipartite b = new Bipartite(g);

        if (b.isBipartite()) {
            System.out.println("Graph is not bipartite.");
            for (int v = 0; v < g.V(); v++) {
                System.out.println(v + ": " + b.color(v));
            }
        } else {
            System.out.println("Graph has an odd-length cycle: ");
            for (int x : b.oddCycle()) {
                System.out.print(x + " ");
            }
            System.out.println();
        }
    }
}

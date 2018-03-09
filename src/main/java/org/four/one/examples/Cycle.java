package org.four.one.examples;

import org.example.Stack;
import org.four.one.learn.Graph;

/**
 * 检测环：G是无环图吗？（假设不存在自环或平行边）
 * <p>
 * 时间复杂度：O(V + E)
 *
 * @author cheng
 *         2018/3/9 14:18
 */
public class Cycle {

    private boolean[] marked;

    private int[] edgeTo;

    private Stack<Integer> cycle;

    public Cycle(Graph G) {
        if (hasSelfLoop(G)) return;
        if (hasParallelEdges(G)) return;

        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, -1, v);
            }
        }
    }

    /**
     * 判断 G 是否存在自环
     */
    private boolean hasSelfLoop(Graph G) {

        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj(v)) {
                // 如果 w 与 v 相等，则存在自环
                if (v == w) {
                    cycle = new Stack<>();
                    cycle.push(v);
                    cycle.push(v);
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 判断 G 是否存在平行边
     */
    private boolean hasParallelEdges(Graph G) {

        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            // 将每次遍历的 w 的 marked[w] 变为 true，如果再次遇到 w，则证明存在平行边
            for (int w : G.adj(v)) {
                if (marked[w]) {
                    cycle = new Stack<>();
                    cycle.push(v);
                    cycle.push(w);
                    cycle.push(v);
                    return true;
                }
                marked[w] = true;
            }

            // reset to marked[v] = false for all v
            for (int w : G.adj(v)) {
                marked[w] = false;
            }
        }
        return false;
    }

    private void dfs(Graph G, int u, int v) {

        marked[v] = true;
        for (int w : G.adj(v)) {

            if (cycle != null) return;

            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, v, w);
            } else if (w != u) {
                cycle = new Stack<>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public static void main(String[] args) {

        // tinyG.txt
        String filename = "src/main/java/org/four/one/text/tinyG.txt";
        Graph g = new Graph(filename);
        Cycle finder = new Cycle(g);

        if (finder.hasCycle()) {
            System.out.println("Graph 不是二分图.");
            for (int v : finder.cycle()) {
                System.out.print(v + " ");
            }
        } else {
            System.out.println("Graph is acyclic.");
        }

    }
}

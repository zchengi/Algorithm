package org.four.one.learn;

import org.example.Stack;

/**
 * 算法4.1 使用深度优先搜索查找图中的路径
 *
 * @author cheng
 *         2018/3/9 10:13
 */
public class DepthFirstPaths {

    private boolean[] marked;

    /**
     * 从起点到一个顶点的已知路径上的最后一个顶点
     */
    private int[] edgeTo;

    /**
     * 起点
     */
    private final int s;

    public DepthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        validateVertex(s);
        dfs(G, s);
    }

    private void dfs(Graph G, int v) {

        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    public boolean hasPathTo(int v) {
        validateVertex(v);
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        validateVertex(v);
        if (!hasPathTo(v)) return null;

        Stack<Integer> path = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }

    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("Vertex " + v + " is not between 0 and " + (V - 1));
    }

    public static void main(String[] args) {

        // tinyCG.txt
        String filename = "src/main/java/org/four/one/text/tinyCG.txt";
        Graph g = new Graph(filename);
        int s = 0;

        DepthFirstPaths search = new DepthFirstPaths(g, s);
        for (int v = 0; v < g.V(); v++) {
            System.out.print(s + " to " + v + ": ");
            if (search.hasPathTo(v)) {
                for (int x : search.pathTo(v)) {
                    if (x == s) System.out.print(s);
                    else System.out.print(" -> " + x);
                }
            }
            System.out.println();
        }
    }
}

package org.four.one.learn;

import org.example.Queue;
import org.example.Stack;

/**
 * 算法4.2 使用广度优先搜索查找图中的路径
 *
 * @author cheng
 *         2018/3/9 10:45
 */
public class BreadthFirstPaths {

    private static final int INFINITY = Integer.MAX_VALUE;

    private boolean[] marked;

    /**
     * 记录源顶点到当前顶点的最短路径经过的顶点
     */
    private int[] edgeTo;

    /**
     * 记录源顶点到当前顶点的最短路径的边数
     */
    private int[] distTo;

    public BreadthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        distTo = new int[G.V()];
        edgeTo = new int[G.V()];
        for (int v = 0; v < G.V(); v++) {
            distTo[v] = INFINITY;
        }
        validateVertex(s);
        bfs(G, s);

        assert check(G, s);
    }

    /**
     * *计算 sources 中任意一个源顶点与图G中每个其他顶点之间的最短路径。
     */
    public BreadthFirstPaths(Graph G, Iterable<Integer> sources) {
        marked = new boolean[G.V()];
        distTo = new int[G.V()];
        edgeTo = new int[G.V()];
        for (int v = 0; v < G.V(); v++) {
            distTo[v] = INFINITY;
        }

        validateVertices(sources);
        bfs(G, sources);
    }

    private void bfs(Graph G, int s) {

        Queue<Integer> queue = new Queue<>();
        // 起点到起点的边为 0
        distTo[s] = 0;
        // 标记起点
        marked[s] = true;
        // 将起点加入队列
        queue.enqueue(s);
        while (!queue.isEmpty()) {
            // 从队列中删除下一个顶点
            int v = queue.dequeue();
            for (int w : G.adj(v)) {
                // 对于每个未标记的相邻顶点
                if (!marked[w]) {
                    // 保存最短路径的最后一条边
                    edgeTo[w] = v;
                    // 保存当前顶点到源顶点的最短边数
                    distTo[w] = distTo[v] + 1;
                    // 标记当前 w 因为最短路径已知
                    marked[w] = true;
                    // 将它加入队列中
                    queue.enqueue(w);
                }
            }
        }
    }

    /**
     * 来自多个来源顶点的广度优先搜索
     */
    private void bfs(Graph G, Iterable<Integer> sources) {

        Queue<Integer> queue = new Queue<>();
        for (int s : sources) {
            marked[s] = true;
            distTo[s] = 0;
            queue.enqueue(s);
        }

        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    queue.enqueue(w);
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        validateVertex(v);
        return marked[v];
    }

    /**
     * 返回 v 与 源顶点之间最短路径的边数
     */
    public int distTo(int v) {
        validateVertex(v);
        return distTo[v];
    }

    public Iterable<Integer> pathTo(int v) {
        validateVertex(v);
        if (!hasPathTo(v)) return null;

        Stack<Integer> path = new Stack<>();
        int x;
        for (x = v; distTo[x] != 0; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(x);

        return path;
    }

    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("Vertex " + v + " is not between 0 and " + (V - 1));
    }

    private void validateVertices(Iterable<Integer> vertices) {
        if (vertices == null) {
            throw new IllegalArgumentException("Argument is null.");
        }
        int V = marked.length;
        for (int v : vertices) {
            if (v < 0 || v >= V) {
                throw new IllegalArgumentException("Vertex " + v + " is not between 0 and " + (V - 1));
            }
        }
    }

    private boolean check(Graph G, int s) {

        if (distTo[s] != 0) {
            System.out.println("Distance if source " + s + " to itself = " + distTo[s]);
            return false;
        }

        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj(v)) {
                if (hasPathTo(v) != hasPathTo(w)) {
                    System.out.println("edge " + v + "-" + w);
                    System.out.println("hasPath(" + v + ") = " + hasPathTo(v));
                    System.out.println("hasPath(" + w + ") = " + hasPathTo(w));
                    return false;
                }

                if (hasPathTo(v) && (distTo[w] > distTo[v] + 1)) {
                    System.out.println("edge " + v + "-" + w);
                    System.out.println("distTo[" + v + "] = " + distTo[v]);
                    System.out.println("distTo[" + w + "] = " + distTo[w]);
                    return false;
                }
            }
        }

        for (int w = 0; w < G.V(); w++) {
            if (!hasPathTo(w) || w == s) continue;
            int v = edgeTo[w];
            if (distTo[w] != distTo[v] + 1) {
                System.out.println("shortest path edge " + v + "-" + w);
                System.out.println("distTo[" + v + "] = " + distTo[v]);
                System.out.println("distTo[" + w + "] = " + distTo[w]);
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        // tinyCG.txt
        String filename = "src/main/java/org/four/one/text/tinyCG.txt";
        Graph g = new Graph(filename);
        int s = 0;

        BreadthFirstPaths bfs = new BreadthFirstPaths(g, s);

        for (int v = 0; v < g.V(); v++) {
            if (bfs.hasPathTo(v)) {
                System.out.printf("%d to %d (%d):  ", s, v, bfs.distTo(v));
                for (int x : bfs.pathTo(v)) {
                    if (x == s) System.out.print(x);
                    else System.out.print(" -> " + x);
                }
                System.out.println();
            } else {
                System.out.printf("%d to %d (-): not connected\n", s, v);
            }
        }
    }
}

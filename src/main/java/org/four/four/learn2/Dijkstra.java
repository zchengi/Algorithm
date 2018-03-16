package org.four.four.learn2;

import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.Vector;

/**
 * Dijkstra算法求最短路径
 * <p>
 * 图中不能有负权边
 * 时间复杂度：O(ElogV)
 *
 * @author cheng
 *         2018/3/16 16:20
 */
public class Dijkstra<Weight extends Number & Comparable> {

    /**
     * 图的引用
     */
    private WeightedGraph G;

    /**
     * 起始点
     */
    private int s;

    /**
     * distTo[i] 存储从起始点s到i的最短路径长度
     */
    private Number[] distTo;

    /**
     * 标记数组，在算法运行过程中标记结点i是否已经找到最短路径
     */
    private boolean[] marked;

    /**
     * from[i] 记录最短路径中，到达i点的边是哪一条
     * 可以用来恢复整个最短路径
     */
    private Edge<Weight>[] from;

    public Dijkstra(WeightedGraph G, int s) {

        if (s < 0 || s >= G.V()) {
            throw new IllegalArgumentException("Invalid argument.");
        }

        // 算法初始化
        this.G = G;
        this.s = s;
        distTo = new Number[G.V()];
        marked = new boolean[G.V()];
        from = new Edge[G.V()];
        for (int i = 0; i < G.V(); i++) {
            distTo[i] = 0.0;
            marked[i] = false;
            from[i] = null;
        }

        // 使用索引堆记录当前找到的到达每个顶点的最短路径
        IndexMinHeap<Weight> ipq = new IndexMinHeap<Weight>(G.V());

        // 对于起始点s进行初始化
        distTo[s] = 0.0;
        from[s] = new Edge<Weight>(s, s, (Weight) (Number) 0.0);
        ipq.insert(s, (Weight) distTo[s]);
        marked[s] = true;
        while (!ipq.isEmpty()) {
            int v = ipq.extractMinIndex();

            // disTo[v] 就是s到v的最短距离
            marked[v] = true;

            // 对v的所有相邻结点进行更新
            for (Object item : G.adj(v)) {
                Edge<Weight> e = (Edge<Weight>) item;
                int w = e.other(v);

                // 如果从s点到w点的最短路径还没有找到
                if (!marked[w]) {
                    // 如果w点以前没有访问过，
                    // 或者访问过，但是通过当前的v点到w点的距离更短，则进行更新
                    if (from[w] == null || distTo[v].doubleValue() + e.wt().doubleValue() < distTo[w].doubleValue()) {
                        distTo[w] = distTo[v].doubleValue() + e.wt().doubleValue();
                        from[w] = e;

                        if (ipq.contain(w)) {
                            ipq.change(w, (Weight) distTo[w]);
                        } else {
                            ipq.insert(w, (Weight) distTo[w]);
                        }
                    }
                }
            }
        }
    }

    /**
     * 返回从s点到w点的最短路径长度
     */
    public Number shortestPathTo(int w) {
        if (w < 0 || w >= G.V()) {
            throw new IllegalArgumentException("Invalid argument.");
        }
        if (!hasPathTo(w)) {
            throw new NoSuchElementException("Dijkstra underflow.");
        }

        return distTo[w];
    }

    /**
     * 判断从s点到w点是否连通
     */
    public boolean hasPathTo(int w) {
        if (w < 0 || w >= G.V()) {
            throw new IllegalArgumentException("Invalid argument.");
        }

        return marked[w];
    }

    /**
     * 寻找从s到w的最短路径，将整个路径经过的边存放在vector中
     */
    public Vector<Edge<Weight>> shortestPath(int w) {
        if (w < 0 || w >= G.V()) {
            throw new IllegalArgumentException("Invalid argument.");
        }
        if (!hasPathTo(w)) {
            throw new NoSuchElementException("Dijkstra underflow.");
        }

        // 通过from数组逆向查找从s到w的路径，存放到栈中
        Stack<Edge<Weight>> s = new Stack<Edge<Weight>>();
        Edge<Weight> e = from[w];
        while (e.v() != this.s) {
            s.push(e);
            e = from[e.v()];
        }
        s.push(e);

        // 从栈中依次取出元素，获得顺序的从s到w的路径
        Vector<Edge<Weight>> res = new Vector<Edge<Weight>>();
        while (!s.empty()) {
            e = s.pop();
            res.add(e);
        }

        return res;
    }

    /**
     * 打印出从s点到w点的路径
     */
    public void showPath(int w) {
        if (w < 0 || w >= G.V()) {
            throw new IllegalArgumentException("Invalid argument.");
        }
        if (!hasPathTo(w)) {
            throw new NoSuchElementException("Dijkstra underflow.");
        }

        Vector<Edge<Weight>> path = shortestPath(w);
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.elementAt(i).v() + " -> ");
            if (i == path.size() - 1) {
                System.out.println(path.elementAt(i).w());
            }
        }
    }

    public static void main(String[] args) {

        String filename = "src/main/java/org/four/four/text/testG1.txt";
        int V = 5;

        SparseWeightedGraph<Integer> g = new SparseWeightedGraph<Integer>(V, false);
        // Dijkstra最短距离算法同样适用于有向图
        // SparseWeightedGraph<Integer> g = new SparseWeightedGraph<Integer>(V, true);
        ReadWeightedGraph readGraph = new ReadWeightedGraph(g, filename);

        System.out.println("Test Dijkstra:");
        Dijkstra<Integer> dijkstra = new Dijkstra<>(g, 0);
        for (int w = 1; w < V; w++) {
            if (dijkstra.hasPathTo(w)) {
                System.out.println("Shortest Path to " + w + " : " + dijkstra.shortestPathTo(w));
                dijkstra.showPath(w);
            } else {
                System.out.println("No Path to " + w);
            }
        }
        System.out.println();
    }
}

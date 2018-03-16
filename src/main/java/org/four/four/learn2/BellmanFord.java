package org.four.four.learn2;

import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.Vector;

/**
 * 使用BellmanFord算法求最短路径
 * <p>
 * 一般用于有向图，因为如果无向图中有一条负权边，也就是相当于有了一个负权环。
 * 时间复杂度：O(EV)
 *
 * @author cheng
 *         2018/3/16 17:43
 */
public class BellmanFord<Weight extends Number & Comparable> {

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
     * from[i] 记录最短路径中，到达i点的边是哪一条
     * 可以用来恢复整个最短路径
     */
    private Edge<Weight>[] from;

    /**
     * 标记图中是否有负权环
     */
    private boolean hasNegativeCycle;

    public BellmanFord(WeightedGraph G, int s) {

        this.G = G;
        this.s = s;
        distTo = new Number[G.V()];
        from = new Edge[G.V()];
        // 初始化所有的结点s都不可达，由from数组来表示
        for (int i = 0; i < G.V(); i++) {
            from[i] = null;
        }

        // 设置distTo[s] = 0，并且让from[s]不为NULL，表示初始s结点可达距离为0
        distTo[s] = 0.0;
        from[s] = new Edge<Weight>(s, s, (Weight) (Number) 0.0);

        // Bellman-Ford 的过程
        // 进行V-1次循环，每一次循环求出从起点到其余所有点，最多使用pass步可到达的最短距离
        for (int pass = 1; pass < G.V(); pass++) {

            // 每次循环中对所有的边进行一遍松弛操作
            // 遍历所有边的方式是先遍历所有顶点，然后遍历和所有顶点相邻的边
            for (int i = 0; i < G.V(); i++) {
                // 使用邻边迭代器和所有顶点相邻的所有边
                for (Object item : G.adj(i)) {
                    Edge<Weight> e = (Edge<Weight>) item;
                    // 对于每一个边首先判断 e->v() 可达
                    // 之后看如果 e->w() 以前没有到达过，显然可以更新 distTo[e->w()]
                    // 或者 e->w() 以前虽然到达过，但是通过这个e可以获得一个更短的距离，
                    // 即可以进行一次松弛操作，也可以更新distTo[e->w()]
                    if (from[e.v()] != null && (from[e.w()] == null ||
                            distTo[e.v()].doubleValue() + e.wt().doubleValue() < distTo[e.w()].doubleValue())) {
                        distTo[e.w()] = distTo[e.v()].doubleValue() + e.wt().doubleValue();
                        from[e.w()] = e;
                    }
                }
            }
        }
        hasNegativeCycle = detectNegativeCycle();
    }

    /**
     * 判断图中是否有负权环
     */
    private boolean detectNegativeCycle() {

        for (int i = 0; i < G.V(); i++) {
            for (Object item : G.adj(i)) {
                Edge<Weight> e = (Edge<Weight>) item;
                if (from[e.v()] != null && distTo[e.v()].doubleValue() + e.wt().doubleValue() < distTo[e.w()].doubleValue()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean negativeCycle() {
        return hasNegativeCycle;
    }

    /**
     * 返回从s点到w点的最短路径长度
     */
    public Number shortestPathTo(int w) {
        if (w < 0 || w >= G.V()) {
            throw new IllegalArgumentException("Invalid argument.");
        }
        if (hasNegativeCycle) {
            throw new NoSuchElementException("The graph contain negative cycle.");
        }
        if (!hasPathTo(w)) {
            throw new NoSuchElementException("BellmanFord underflow.");
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

        return from[w] != null;
    }

    /**
     * 寻找从s到w的最短路径，将整个路径经过的边存放在vector中
     */
    public Vector<Edge<Weight>> shortestPath(int w) {
        if (w < 0 || w >= G.V()) {
            throw new IllegalArgumentException("Invalid argument.");
        }
        if (hasNegativeCycle) {
            throw new NoSuchElementException("The graph contain negative cycle.");
        }
        if (!hasPathTo(w)) {
            throw new NoSuchElementException("BellmanFord underflow.");
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
        if (hasNegativeCycle) {
            throw new NoSuchElementException("The graph contain negative cycle.");
        }
        if (!hasPathTo(w)) {
            throw new NoSuchElementException("BellmanFord underflow.");
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

        // testG1.txt testG2.txt testG_negative_circle.txt
        String filename = "src/main/java/org/four/four/text/testG2.txt";
        int V = 5;

        SparseWeightedGraph<Integer> g = new SparseWeightedGraph<Integer>(V, true);
        ReadWeightedGraph readGraph = new ReadWeightedGraph(g, filename);

        System.out.println("Test Bellman-Ford:");

        int s = 0;
        BellmanFord<Integer> bellmanFord = new BellmanFord<>(g, s);
        if (bellmanFord.negativeCycle()) {
            System.out.println("The graph contain negative cycle!");
        } else {
            for (int w = 0; w < V; w++) {
                if (w == s) continue;

                if (bellmanFord.hasPathTo(w)) {
                    System.out.println("Shortest Path to " + w + " : " + bellmanFord.shortestPathTo(w));
                    bellmanFord.showPath(w);
                } else {
                    System.out.println("No Path to " + w);
                }
            }
        }
        System.out.println();
    }
}

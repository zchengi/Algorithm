package org.four.one.examples;

import com.sun.nio.sctp.IllegalReceiveException;
import org.example.Queue;
import org.four.one.learn.Graph;

/**
 * 离心率问题（使用广度优先搜索）
 * ***************************************************************
 * public class GraphProperties
 * ***************************************************************
 * GraphProperties(Graph G)     构造函数（如果G不是连通的，抛出异常）
 * int eccentricity(int v)      v的离心率
 * int diameter()               G的直径
 * int radius()                 G的半径
 * int center()                 G的某个中点
 * boolean isCenter(int v)      G的某个点是不是中点
 * int girth()                  返回G的周长
 * ***************************************************************
 *
 * @author cheng
 *         2018/3/12 14:40
 */
public class GraphProperties {

    private int[] eccentricity;
    private int diameter;
    private int radius;
    private int center;

    public GraphProperties(Graph G) {
        eccentricity = new int[G.V()];
        int diameter = Integer.MIN_VALUE;
        int radius = Integer.MAX_VALUE;
        int center = -1;

        for (int v = 0; v < G.V(); v++) {
            // 获取顶点的离心率
            eccentricity[v] = bfs(G, v);
            if (eccentricity[v] == Integer.MAX_VALUE) {
                throw new IllegalReceiveException();
            }
            // 离心率最大 = 直径
            if (diameter < eccentricity[v]) {
                diameter = eccentricity[v];
            }
            // 离心率最小 = 半径
            if (radius > eccentricity[v]) {
                radius = eccentricity[v];
                center = v;
            }
        }
        this.diameter = diameter;
        this.radius = radius;
        this.center = center;
    }

    /**
     * 广度优先搜索计算离心率
     */
    private int bfs(Graph G, int s) {

        Queue<Integer> queue = new Queue<>();
        // 记录当前顶点的离心率
        int[] distTo = new int[G.V()];
        for (int v = 0; v < G.V(); v++) {
            distTo[v] = Integer.MAX_VALUE;
        }

        distTo[s] = 0;
        boolean[] marked = new boolean[G.V()];
        marked[s] = true;
        queue.enqueue(s);

        int max = 0;
        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    max = distTo[w];
                    queue.enqueue(w);
                }
            }
        }

        return max;
    }

    public int eccentricity(int v) {
        return eccentricity[v];
    }

    public int diameter() {
        return diameter;
    }

    public int radius() {
        return radius;
    }

    public int center() {
        return center;
    }

    public boolean isCenter(int v) {
        return eccentricity[v] == radius;
    }

    public static void main(String[] args) {

        int V = 5;
        Graph G = new Graph(V);
        G.addEdge(0, 1);
        G.addEdge(0, 4);
        G.addEdge(1, 2);
        G.addEdge(1, 3);
        G.addEdge(1, 4);
        G.addEdge(2, 3);
        G.addEdge(3, 4);

        System.out.println(G);

        GraphProperties gp = new GraphProperties(G);
        System.out.println("Diameter = " + gp.diameter());
        System.out.println("Radius = " + gp.radius());
        System.out.println("Center = " + gp.center());
    }
}

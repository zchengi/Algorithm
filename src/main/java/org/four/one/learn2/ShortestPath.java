package org.four.one.learn2;

import org.example.Stack;

import java.util.LinkedList;
import java.util.Vector;

/**
 * 无权图的广度优先遍历和最短路径
 *
 * @author cheng
 *         2018/3/7 16:15
 */
public class ShortestPath {

    private Graph G;

    private int s;

    private boolean[] visited;

    private int[] from;

    /**
     * 记录路径中结点的次序，ord[i] 表示 i 结点在路径中的次序
     */
    private int[] ord;

    public ShortestPath(Graph graph, int s) {
        // 寻找图 graph 从 s 点到其他点的路径

        if (s < 0 || s >= graph.V()) {
            throw new IllegalArgumentException("invalid argument: " + s + " !");
        }

        // 算法初始化
        G = graph;
        visited = new boolean[G.V()];
        from = new int[G.V()];
        ord = new int[G.V()];

        for (int i = 0; i < G.V(); i++) {
            visited[i] = false;
            from[i] = -1;
            ord[i] = -1;
        }
        this.s = s;

        // 无向图的最短路径算法，从 s 开始广度优先遍历整张图
        LinkedList<Integer> q = new LinkedList<>();

        q.push(s);
        visited[s] = true;
        ord[s] = 0;
        while (!q.isEmpty()) {
            int v = q.pop();
            for (Integer i : G.adj(v)) {
                if (!visited[i]) {
                    q.push(i);
                    visited[i] = true;
                    from[i] = v;
                    ord[i] = ord[v] + 1;
                }
            }
        }
    }

    public boolean hasPath(int w) {

        if (w < 0 || w >= G.V()) {
            throw new IllegalArgumentException("Called hasPath() with invalid arguments: " + w + "!");
        }

        return visited[w];
    }

    /**
     * 查询从 s 点到 w 点的路径，存放在vector中
     */
    public Vector<Integer> path(int w) {

        if (!hasPath(w)) throw new IllegalArgumentException("Called path() with invalid arguments: " + w + "!");

        Stack<Integer> stack = new Stack<>();

        // 通过from数组逆向查找到从 s 到 w 的路径，存放到栈中
        int p = w;
        while (p != -1) {
            stack.push(p);
            p = from[p];
        }

        // 从栈中依次取出元素，获得顺序的从 s 到 w 的路径
        Vector<Integer> res = new Vector<>();
        while (!stack.isEmpty()) {
            res.add(stack.pop());
        }

        return res;
    }

    /**
     * 打印从 s 点到 w 点的路径
     */
    public void showPath(int w) {

        if (!hasPath(w)) throw new IllegalArgumentException("Called showPath() with invalid arguments: " + w + "!");

        Vector<Integer> vector = path(w);

        for (int i = 0; i < vector.size(); i++) {
            System.out.print(vector.elementAt(i));
            if (i == vector.size() - 1) System.out.println();
            else System.out.print(" -> ");
        }
    }

    /**
     * 查看从 s 点到达 w 点的最短路径长度
     * 若从 s 到 w 不可达，返回-1
     */
    public int length(int w) {
        if (w < 0 || w >= G.V()) {
            throw new IllegalArgumentException("Called length() with invalid arguments: " + w + "!");
        }

        return ord[w];
    }
}

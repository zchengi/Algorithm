package org.four.one.learn2;

import java.util.Stack;
import java.util.Vector;

/**
 * 获得两点间的一条路径
 *
 * @author cheng
 *         2018/3/7 15:40
 */
public class Path {

    /**
     * 图的引用
     */
    private Graph G;

    /**
     * 起始点
     */
    private int s;

    /**
     * 记录dfs的过程中结点是否被访问
     */
    private boolean[] visited;

    /**
     * 记录路径，form[i]表示查找的路径上i的上一个结点
     */
    private int[] from;


    public Path(Graph graph, int s) {

        // 寻找图 graph 从 s 点 到其他点的路径

        if (s < 0 || s >= graph.V()) {
            throw new IllegalArgumentException("invalid argument: " + s + " !");
        }

        // 算法初始化
        G = graph;
        visited = new boolean[G.V()];
        from = new int[G.V()];

        for (int i = 0; i < G.V(); i++) {
            visited[i] = false;
            from[i] = -1;
        }

        this.s = s;

        // 寻路算法
        dfs(s);
    }

    /**
     * 图的深度优先遍历
     */
    private void dfs(int v) {

        visited[v] = true;

        for (int i : G.adj(v)) {
            if (!visited[i]) {
                from[i] = v;
                dfs(i);
            }
        }
    }

    /**
     * 查询从 s 点到 w 点是否有路径
     */
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

        if (!hasPath(w)) throw new IllegalArgumentException("Called path() with invalid arguments: " + w + "!");

        Vector<Integer> vector = path(w);

        for (int i = 0; i < vector.size(); i++) {
            System.out.print(vector.elementAt(i));
            if (i == vector.size() - 1) System.out.println();
            else System.out.print(" -> ");
        }
    }
}

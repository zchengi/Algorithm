package org.four.one.learn;

import org.example.Queue;

/**
 * 算法4.3 使用深度优先搜索找出图中的所有连通分量
 * <p>
 * *****************************************************************************
 * public class CC
 * *****************************************************************************
 * CC(Graph G)                          预处理构造函数
 * boolean connected(int v, int w)      v 和 w 连通吗
 * int count()                          连通分量数
 * int id(int v)                        v所在的连通分量的标识符（0 ~ count() -1）
 * *****************************************************************************
 *
 * @author cheng
 *         2018/3/9 11:53
 */
public class CC {

    private boolean[] marked;

    /**
     * id[v] = 包含 v 的连通分量的标识符
     * 标识符0：第一个连通分量的所有顶点
     * 标识符1：第二个连通分量的所有顶点
     * ··· ···
     */
    private int[] id;

    /**
     * 记录每个连通分量的大小
     */
    private int[] size;

    /**
     * 连通分量数
     */
    private int count;

    public CC(Graph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        size = new int[G.V()];
        for (int s = 0; s < G.V(); s++) {
            if (!marked[s]) {
                dfs(G, s);
                count++;
            }
        }
    }

    public CC() {
        // TODO 加权的无向图
    }

    private void dfs(Graph G, int v) {

        marked[v] = true;
        id[v] = count;
        size[count]++;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    public int id(int v) {
        return id[v];
    }

    public int size(int v) {
        validateVertex(v);
        return size[id[v]];
    }

    public int count() {
        return count;
    }

    public boolean connected(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return id[v] == id[w];
    }

    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("Vertex " + v + " is not between 0 and " + V);
        }
    }

    public static void main(String[] args) {

        // tinyG.txt
        String filename = "src/main/java/org/four/one/text/tinyG.txt";
        Graph g = new Graph(filename);

        CC cc = new CC(g);

        int m = cc.count;
        System.out.println(m + " components");

        Queue<Integer>[] components = (Queue<Integer>[]) new Queue[m];
        for (int i = 0; i < m; i++) {
            components[i] = new Queue<>();
        }

        for (int v = 0; v < g.V(); v++) {
            components[cc.id(v)].enqueue(v);
        }

        for (int i = 0; i < m; i++) {
            for (int v : components[i]) {
                System.out.print(v + " ");
            }
            System.out.println();
        }
    }
}

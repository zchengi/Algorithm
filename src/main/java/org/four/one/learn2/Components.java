package org.four.one.learn2;

/**
 * 求无权图的连通分量
 *
 * @author cheng
 *         2018/3/7 14:58
 */
public class Components {

    /**
     * 图的引用
     */
    Graph G;

    /**
     * 记录 dfs 的过程中结点是否被访问
     */
    private boolean[] visited;

    /**
     * 记录连通分量个数
     */
    private int ccount;

    /**
     * 每个结点所对应的连通分量标记
     */
    private int[] id;

    public Components(Graph graph) {
        // 求出无权图的连通分量

        // 算法初始化
        G = graph;
        visited = new boolean[G.V()];
        id = new int[G.V()];
        ccount = 0;

        for (int i = 0; i < G.V(); i++) {
            visited[i] = false;
            id[i] = -1;
        }

        // 求出图的连通分量
        for (int i = 0; i < G.V(); i++) {
            if (!visited[i]) {
                dfs(i);
                ccount++;
            }
        }
    }

    /**
     * 图的深度优先遍历
     */
    private void dfs(int v) {

        visited[v] = true;
        id[v] = ccount;

        for (int i : G.adj(v)) {
            if (!visited[i]) {
                dfs(i);
            }
        }
    }

    public int count() {
        return ccount;
    }

    public boolean isConnected(int v, int w) {

        if (v < 0 || v >= G.V()) {
            throw new IllegalArgumentException("Called isConnected() invalid argument: " + v + "!");
        }

        return id[v] == id[w];
    }
}

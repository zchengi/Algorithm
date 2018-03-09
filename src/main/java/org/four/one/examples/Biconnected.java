package org.four.one.examples;

import org.four.one.learn.Graph;

/**
 * 双连通性：关节点（割点）是一个顶点，它的移除增加了连通分量数。
 * 如果没有关节点，则图是双连通的。
 * <p>
 * 在无向连通图中，删除一个顶点v及其相连的边后，原图从一个连通分量变成了两个或多个连通分量，则称顶点v为割点，
 * 同时也称关节点(Articulation Point)。一个没有关节点的连通图称为重连通图(biconnected graph)。
 * 若在连通图上至少删去 k 个顶点才能破坏图的连通性，则称此图的连通度为k。
 * <p>
 * - 对根节点u，若其有两棵或两棵以上的子树，则该根结点u为割点；
 * - 对叶子节点u（非根节点），若其子树的节点均没有指向u的祖先节点的回边，说明删除u之后，根结点与u的子树的节点不再连通；则节点u为割点。
 * <p>
 * 时间复杂度：O(E + V)
 *
 * @author cheng
 *         2018/3/9 15:47
 */
public class Biconnected {

    private int[] low;
    private int[] pre;
    private int count;

    /**
     * 记录关节点
     */
    private boolean[] articulation;

    public Biconnected(Graph G) {
        low = new int[G.V()];
        pre = new int[G.V()];
        articulation = new boolean[G.V()];

        for (int v = 0; v < G.V(); v++) {
            low[v] = -1;
            pre[v] = -1;
        }

        for (int v = 0; v < G.V(); v++) {
            if (pre[v] == -1) {
                dfs(G, v, v);
            }
        }
    }

    private void dfs(Graph G, int u, int v) {

        int children = 0;
        pre[v] = count++;
        low[v] = pre[v];

        for (int w : G.adj(v)) {
            if (pre[w] == -1) {
                children++;
                dfs(G, v, w);

                // update low number
                low[v] = Math.min(low[v], low[w]);

                // non-root of DFS is an Articulation point if low[w] >= pre[v]
                if (low[w] >= pre[v] && u != v) {
                    articulation[v] = true;
                }
            } else if (w != u) {

                // update low number - ignore reverse of edge leading to v
                low[v] = Math.min(low[v], pre[w]);
            }
        }

        // root of DFS is an articulation point if it has more than 1 child
        if (u == v && children > 1) {
            articulation[v] = true;
        }
    }

    public boolean isArticulation(int v) {
        return articulation[v];
    }

    public static void main(String[] args) {

        // tinyG.txt
        String filename = "src/main/java/org/four/one/text/tinyG.txt";
        Graph g = new Graph(filename);
        Biconnected bic = new Biconnected(g);

        // 打印 关节点
        System.out.println("Articulation points: ");
        for (int v = 0; v < g.V(); v++) {
            if (bic.isArticulation(v)) {
                System.out.print(v + " ");
            }
        }
    }
}

package org.four.one.examples;

import org.four.one.learn.Graph;

/**
 * 识别桥的边并将其打印出来，分解一个有向图成两边相连的组；
 * 假设没有平行边，如果有平行边，会被错误的认定为桥；
 * <p>
 * 桥：是存在于无向图中的这样的一条边，如果去掉这一条边，那么整张无向图会分为两部分，
 * 这样的一条边称为桥无向连通图中，如果删除某边后，图变成不连通，则称该边为桥。
 * <p>
 * 时间复杂度：O(E + V)
 *
 * @author cheng
 *         2018/3/9 15:27
 */
public class Bridge {

    /**
     * 桥数
     */
    private int bridges;

    /**
     * 计数器
     */
    private int count;

    /**
     * pre[v] = order in which dfs examines v
     * 深度优先遍历对树顶点进行编号
     */
    private int[] pre;

    /**
     * low[v] = lowest pre order of any vertex connected to v
     * 记录顶点V能通过其子顶点或者子顶点的一条背边，所能达到的最低顶点编号
     */
    private int[] low;

    public Bridge(Graph G) {
        low = new int[G.V()];
        pre = new int[G.V()];

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
        pre[v] = count++;
        low[v] = pre[v];

        for (int w : G.adj(v)) {
            if (pre[w] == -1) {
                dfs(G, v, w);
                low[v] = Math.min(low[v], low[w]);

                if (low[w] == pre[w]) {
                    System.out.println(v + "-" + w + " is a bridge.");
                    bridges++;
                }
            } else if (w != u) {
                // update low number - ignore reverse of edge leading to v
                low[v] = Math.min(low[v], pre[w]);
            }
        }
    }

    public int components() {
        return bridges ;
    }

    public static void main(String[] args) {

        // tinyG.txt
        String filename = "src/main/java/org/four/one/text/tinyG.txt";
        Graph g = new Graph(filename);
        Bridge bridge = new Bridge(g);

        System.out.println("Edge connected components = " + bridge.components());
    }
}

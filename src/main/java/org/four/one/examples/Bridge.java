package org.four.one.examples;

import org.four.one.learn.Graph;

/**
 * 识别桥的边并将其打印出来，分解一个有向图成两边相连的组；
 * 假设没有平行边，如果有平行边，会被错误的认定为桥；
 * <p>
 * 定义：当且仅当某条边不包含在任意环中时，这条边就是桥；
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
     * 最短路径正向边数
     */
    private int[] pre;

    /**
     * low[v] = lowest pre order of any vertex connected to v
     * 最短路径反向边数
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

package org.four.one.learn;

/**
 * 表 4.1.4 图处理算法的 API - 深度优先搜索
 *
 * @author cheng
 *         2018/3/8 13:38
 */
public class DepthFirstSearch {

    private boolean[] marked;

    private int count;

    public DepthFirstSearch(Graph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;

        count++;
        for (int w : G.adj(v)) {
            if (!marked(w)) dfs(G, w);
        }
    }

    public boolean marked(int w) {
        return marked[w];
    }

    public int count() {
        return count;
    }

    public static void main(String[] args) {

        // tinyG.txt
        String filename = "src/main/java/org/four/one/text/tinyG.txt";
        Graph g = new Graph(filename);
        int s = 0;

        DepthFirstSearch dfs = new DepthFirstSearch(g, s);

        for (int v = 0; v < g.V(); v++) {
            if (dfs.marked(v)) {
                System.out.print(v + " ");
            }
        }

        System.out.println();

        // 判断当前图是否是连通的
        if (dfs.count != g.V()) {
            System.out.print("NOT ");
        }
        System.out.println("connected.");
    }
}



package org.four.one.exercises;

import org.example.Stack;
import org.four.one.learn.Graph;

/**
 * 4.1.28 修改Cycle，运行图含有自环和平行边。
 * 删除判断自环和平行边的代码即可。
 *
 * @author cheng
 *         2018/3/14 11:48
 */
public class Exercise28 {
    private static class Cycle {

        private boolean[] marked;

        private int[] edgeTo;

        private Stack<Integer> cycle;

        public Cycle(Graph G) {

            marked = new boolean[G.V()];
            edgeTo = new int[G.V()];
            for (int v = 0; v < G.V(); v++) {
                if (!marked[v]) {
                    dfs(G, -1, v);
                }
            }
        }


        private void dfs(Graph G, int u, int v) {

            marked[v] = true;
            for (int w : G.adj(v)) {

                if (cycle != null) return;

                if (!marked[w]) {
                    edgeTo[w] = v;
                    dfs(G, v, w);
                } else if (w != u) {
                    // 这里判断一个连通分量中是否存在与当前 w 相连的顶点（自己除外）
                    // 如果存在，则它们均与当前根顶点相连，即证明存在环
                    cycle = new Stack<>();
                    for (int x = v; x != w; x = edgeTo[x]) {
                        cycle.push(x);
                    }
                    cycle.push(w);
                    cycle.push(v);
                }
            }
        }

        public Iterable<Integer> cycle() {
            return cycle;
        }

        public boolean hasCycle() {
            return cycle != null;
        }
    }

    public static void main(String[] args) {

        // tinyG.txt
        String filename = "src/main/java/org/four/one/text/tinyG.txt";
        Graph g = new Graph(filename);
        org.four.one.examples.Cycle finder = new org.four.one.examples.Cycle(g);

        if (finder.hasCycle()) {
            System.out.println("Graph 不是无环图.");
            for (int v : finder.cycle()) {
                System.out.print(v + " ");
            }
        } else {
            System.out.println("Graph is acyclic.");
        }
    }
}

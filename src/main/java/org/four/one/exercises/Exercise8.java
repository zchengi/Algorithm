package org.four.one.exercises;


import org.four.one.learn.Graph;

/**
 * 4.1.8 按照正文中的要求，用Union-find算法实现4.1.2.3中搜索的API。
 *
 * @author cheng
 *         2018/3/12 10:54
 */
public class Exercise8 {
    private static class Search {

        private int[] parent;
        private int[] rank;
        private int count;
        private int s;

        public Search(Graph G, int s) {
            this.s = s;
            parent = new int[G.V()];
            rank = new int[G.V()];
            count = 0;

            for (int i = 0; i < G.V(); i++) {
                parent[i] = i;
                rank[i] = 1;
            }

            for (int v = 0; v < G.V(); v++) {
                for (int w : G.adj(v)) {
                    unionVertex(v, w);
                }
            }

            for (int v = 0; v < G.V(); v++) {
                if (marked(v)) {
                    count++;
                }
            }
        }

        private void unionVertex(int v, int w) {
            int vRoot = find(v);
            int wRoot = find(w);

            if (vRoot == wRoot) return;

            if (rank[vRoot] < rank[wRoot]) {
                parent[vRoot] = wRoot;
            } else if (rank[vRoot] > rank[wRoot]) {
                parent[wRoot] = vRoot;
            } else {
                parent[wRoot] = vRoot;
                rank[wRoot]++;
            }
        }

        public boolean marked(int v) {
            return find(v) == find(s);
        }

        private int find(int v) {
            while (v != parent[v]) {
                v = parent[v];
            }
            return v;
        }

        public int count() {
            return count;
        }
    }

    public static void main(String[] args) {

        // tinyG.txt
        String filename = "src/main/java/org/four/one/text/tinyG.txt";
        int s = 0;
        Graph G = new Graph(filename);
        Search search = new Search(G, s);

        for (int v = 0; v < G.V(); v++) {
            if (search.marked(v)) {
                System.out.print(v + " ");
            }
        }
        System.out.println();
        System.out.println("连通分量数： " + search.count());

        if (search.count() != G.V()) {
            System.out.print("Not ");
        }
        System.out.println("connected.");
    }
}

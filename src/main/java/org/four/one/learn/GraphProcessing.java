package org.four.one.learn;

/**
 * 表4.1.2 最常用的图处理代码
 *
 * @author cheng
 *         2018/3/8 13:10
 */
public final class GraphProcessing {

    private GraphProcessing() {
    }

    /**
     * 计算 v 的度数
     */
    public static int degree(Graph G, int v) {
        int degree = 0;
        for (int w : G.adj(v)) {
            degree++;
        }
        return degree;
    }

    /**
     * 计算所有顶点的最大度数
     */
    public static int maxDegree(Graph G) {
        int max = 0;
        for (int v = 0; v < G.V(); v++) {
            if (degree(G, v) > max) max = degree(G, v);
        }
        return max;
    }

    /**
     * 计算所有顶点的平均度数
     */

    public static double avgDegree(Graph G) {
        return 2.0 * G.E() / G.V();
    }

    /**
     * 计算自环的个数
     * 自环：即一条连接一个顶点和其自身的边；
     */
    public static int numberOfSelfLoops(Graph G) {

        int count = 0;
        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj(v)) {
                if (v == w) count++;
            }
        }
        // 每条边都被记录过两次
        return count / 2;
    }

    public static void main(String[] args) {

        // mediumG.txt
        String filename = "src/main/java/org/four/one/text/mediumG.txt";
        Graph g = new Graph(filename);

        System.out.println(GraphProcessing.degree(g, 0));
        System.out.println(GraphProcessing.maxDegree(g));
        System.out.println(GraphProcessing.avgDegree(g));
        System.out.println(GraphProcessing.numberOfSelfLoops(g));
    }
}

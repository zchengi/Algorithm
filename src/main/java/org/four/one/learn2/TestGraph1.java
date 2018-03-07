package org.four.one.learn2;

/**
 * 稀疏图 和 稠密图 生成测试
 *
 * @author cheng
 *         2018/3/7 12:22
 */
public class TestGraph1 {
    public static void main(String[] args) {

        int N = 20;
        int M = 100;

        // 声明一个无向稀疏图
        SparseGraph sparseGraph = new SparseGraph(N, false);

        for (int i = 0; i < M; i++) {
            int a = (int) (Math.random() * N);
            int b = (int) (Math.random() * N);
            sparseGraph.addEdge(a, b);
        }

        // 时间复杂度：O(E)
        for (int v = 0; v < N; v++) {
            System.out.print(v + " : ");
            for (Integer i : sparseGraph.adj(v)) {
                System.out.print(i + " ");
            }
            System.out.println();
        }

        System.out.println("------------------------------------");

        // 声明一个无向稠密图
        DenseGraph denseGraph = new DenseGraph(N, false);

        for (int i = 0; i < M; i++) {
            int a = (int) (Math.random() * N);
            int b = (int) (Math.random() * N);
            denseGraph.addEdge(a, b);
        }

        // 时间复杂度：O(V^2)
        for (int v = 0; v < N; v++) {
            System.out.print(v + " : ");
            for (Integer i : denseGraph.adj(v)) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}

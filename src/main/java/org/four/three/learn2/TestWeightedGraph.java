package org.four.three.learn2;

/**
 * 加权的稀疏图和稠密图 生成测试
 *
 * @author cheng
 *         2018/3/14 15:49
 */
public class TestWeightedGraph {
    public static void main(String[] args) {

        // 使用两种图的存储方式读取testG1.txt文件
        String filename = "src/main/java/org/four/three/text/testG1.txt";
        SparseWeightedGraph<Double> g1 = new SparseWeightedGraph<>(8, false);
        ReadWeightedGraph rg1 = new ReadWeightedGraph(g1, filename);
        System.out.println("Test g1 in Sparse Weighted Graph:");
        g1.show();

        System.out.println();

        DenseWeightedGraph<Double> g2 = new DenseWeightedGraph<>(8, false);
        ReadWeightedGraph rg2 = new ReadWeightedGraph(g2, filename);
        System.out.println("Test g2 in Dense Graph:");
        g2.show();
    }
}

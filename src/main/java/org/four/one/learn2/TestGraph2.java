package org.four.one.learn2;

/**
 * 测试 通过文件读取图的信息
 *
 * @author cheng
 *         2018/3/7 13:03
 */
public class TestGraph2 {
    public static void main(String[] args) {

        // 使用两种图的存储方式读取 testG1.txt 文件
        String filename = "src/main/java/org/four/one/text/testG1.txt";

        SparseGraph g1 = new SparseGraph(13, false);
        ReadGraph readGraph1 = new ReadGraph(g1, filename);
        System.out.println("test G1 in sparse Graph: ");
        g1.show();

        System.out.println();

        DenseGraph g2 = new DenseGraph(13, false);
        ReadGraph readGraph2 = new ReadGraph(g2, filename);
        System.out.println("test G2 in sparse Graph: ");
        g2.show();

        System.out.println();

        // 使用两种图的存储方式读取 testG2.txt 文件
        filename = "src/main/java/org/four/one/text/testG2.txt";

        SparseGraph g3 = new SparseGraph(6, false);
        ReadGraph readGraph3 = new ReadGraph(g3, filename);
        System.out.println("test G3 in sparse Graph: ");
        g3.show();

        System.out.println();

        DenseGraph g4 = new DenseGraph(6, false);
        ReadGraph readGraph4 = new ReadGraph(g4, filename);
        System.out.println("test G4 in sparse Graph: ");
        g4.show();
    }
}

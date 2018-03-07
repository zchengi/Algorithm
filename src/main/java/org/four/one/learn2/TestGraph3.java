package org.four.one.learn2;

/**
 * 测试图的连通分量算法
 *
 * @author cheng
 *         2018/3/7 15:15
 */
public class TestGraph3 {
    public static void main(String[] args) {

        // TestG1.txt
        String filename = "src/main/java/org/four/one/text/testG1.txt";

        SparseGraph g1 = new SparseGraph(13, false);
        ReadGraph readGraph1 = new ReadGraph(g1, filename);
        Components component1 = new Components(g1);
        System.out.println("TestG1.txt, Component Count: " + component1.count());

        System.out.println();

        // TestG2.txt
        filename = "src/main/java/org/four/one/text/testG2.txt";

        SparseGraph g2 = new SparseGraph(6, false);
        ReadGraph readGraph2 = new ReadGraph(g2, filename);
        Components component2 = new Components(g2);
        System.out.println("TestG2.txt, Component Count: " + component2.count());
        System.out.println();
    }
}

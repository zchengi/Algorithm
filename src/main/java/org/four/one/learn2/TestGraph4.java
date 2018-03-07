package org.four.one.learn2;

/**
 * 测试图的寻路算法
 *
 * @author cheng
 *         2018/3/7 16:00
 */
public class TestGraph4 {
    public static void main(String[] args) {

        // TestG.txt
        String filename = "src/main/java/org/four/one/text/testG.txt";

        SparseGraph g = new SparseGraph(7, false);
        ReadGraph readGraph = new ReadGraph(g, filename);
        g.show();

        System.out.println();

        Path path = new Path(g, 0);
        System.out.println("Path from 0 to 6: ");
        path.showPath(6);
    }
}

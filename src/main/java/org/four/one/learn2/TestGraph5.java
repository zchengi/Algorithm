package org.four.one.learn2;

/**
 * 测试无权图最短路径算法
 *
 * @author cheng
 *         2018/3/7 16:28
 */
public class TestGraph5 {
    public static void main(String[] args) {

        // TestG.txt
        String filename = "src/main/java/org/four/one/text/testG.txt";

        SparseGraph g = new SparseGraph(7, false);
        ReadGraph readGraph = new ReadGraph(g, filename);
        g.show();

        System.out.println();

        // 比较使用深度优先遍历和广度优先遍历获得路径的不同
        // 广度优先遍历获得的是无权图的最短路径
        Path dfs = new Path(g, 0);
        System.out.println("DFS from 0 to 6: ");
        dfs.showPath(6);

        ShortestPath bfs = new ShortestPath(g, 0);
        System.out.println("BFS from 0 to 6: ");
        bfs.showPath(6);
    }
}

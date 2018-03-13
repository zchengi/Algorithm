package org.four.one.exercises;

import org.four.one.learn.BreadthFirstPaths;
import org.four.one.learn.Graph;
import org.four.one.learn.SymbolGraph;

/**
 * 4.1.22 编写一段程序BaconHistogram，打印一幅Kevin Bacon数的柱状图，显示movies.txt中
 * Kevin Bacon数为0、1、2、3······的演员分别有多少。将值为无穷大（不与 Kevin Bacon连通）的人归为一类。
 *
 * @author cheng
 *         2018/3/13 10:41
 */
public class Exercise22 {
    public static void main(String[] args) {

        String filename = "src/main/java/org/four/one/text/movies.txt";
        String delimiter = "/";
        String source = "Bacon, Kevin";

        SymbolGraph sg = new SymbolGraph(filename, delimiter);
        Graph G = sg.graph();
        if (!sg.contains(source)) {
            System.out.println(source + " not in database.");
            return;
        }

        // 对 s 运行 广度优先搜索
        int s = sg.indexOf(source);
        BreadthFirstPaths bfs = new BreadthFirstPaths(G, s);

        // 计算 Kevin Bacon numbers 的直方图 - 无穷大时为 100
        int MAX_BACON = 100;
        int[] hist = new int[MAX_BACON + 1];
        for (int v = 0; v < G.V(); v++) {
            int bacon = Math.min(MAX_BACON, bfs.distTo(v));
            hist[bacon]++;

            // 打印演员和电影与 Kevin Bacon numbers
            if (bacon / 2 >= 7 && bacon < MAX_BACON) {
                System.out.printf("%d %s\n", bacon / 2, sg.nameOf(v));
            }
        }

        // 打印直方图 - 开始索引都是演员
        for (int i = 0; i < MAX_BACON; i += 2) {
            if (hist[i] == 0) break;
            System.out.printf("%3d %8d\n", i / 2, hist[i]);
        }
        System.out.printf("Inf %8d\n", hist[MAX_BACON]);
    }
}

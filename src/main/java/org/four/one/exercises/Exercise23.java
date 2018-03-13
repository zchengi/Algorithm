package org.four.one.exercises;

import org.four.one.learn.BreadthFirstPaths;
import org.four.one.learn.Graph;
import org.four.one.learn.SymbolGraph;

/**
 * 4.1.23 计算由moves.txt得到的图的连通分量的数量和包含的顶点数小于10的连通分量的数量。
 * 计算最大的连通分量的离心率、直径、半径和中点。Kevin Bacon在最大的连通分量之中吗？
 *
 * @author cheng
 *         2018/3/13 10:55
 */
public class Exercise23 {
    public static void main(String[] args) {

        String filename = "src/main/java/org/four/one/text/movies.txt";
        String delimiter = "/";

        SymbolGraph sg = new SymbolGraph(filename, delimiter);
        Graph G = sg.graph();

        // 记录每个顶点是否被遍历过
        boolean[] marked = new boolean[G.V()];
        marked[0] = true;
        // 记录连通分量包含的顶点数 如果为 0：表示该点已被遍历过
        int[] vertexs = new int[G.V()];

        for (int s = 0; s < G.V(); s++) {
            // 如果没有遍历
            if (!marked[s]) {
                // 使用广度优先搜索找出所有与 s 相连的点
                BreadthFirstPaths bfs = new BreadthFirstPaths(G, s);
                // 遍历所有顶点
                for (int v = 0; v < G.V(); v++) {
                    // 如果当前连通分量包含 v, 则连通分量的个数++
                    if (bfs.distTo(v) != Integer.MAX_VALUE) {
                        marked[v] = true;
                        vertexs[s]++;
                    }
                }
            }
        }

        // count：连通分量的数量； less：包含的顶点数小于10的连通分量的数量
        int count = 0, less = 0;
        // max ：存在于最大连通分量中的一个顶点的索引
        int max = 0;
        for (int v = 0; v < vertexs.length; v++) {
            int i = vertexs[v];
            if (i > 0) {
                count++;
            }
            if (i > 0 && i <= 10) {
                less++;
            }
            if (vertexs[max] < i) {
                max = v;
            }
        }
        System.out.println("连通分量的数量: " + count);
        System.out.println("包含的顶点数小于10的连通分量的数量: " + less);

        // 最大连通分量的离心率、直径、半径和中点  -- 数据太大 处理不了
        /*GraphProperties gp = new GraphProperties(G);
        int maxEccentricity = gp.eccentricity(max);
        int diameter = gp.diameter();
        int radius = gp.radius();
        int center = gp.center();
        System.out.println(maxEccentricity + " " + diameter + " " + radius + " " + center);*/

        int kb = sg.indexOf("Bacon, Kevin");
        boolean isExist = kb == max;
        BreadthFirstPaths bfs = new BreadthFirstPaths(G, max);
        for (int v = 0; v < G.V(); v++) {
            if (bfs.distTo(v) == kb) {
                isExist = true;
                break;
            }
        }
        System.out.println("Kevin Bacon" + (isExist ? "" : "不") + "在最大的连通分量之中");

    }
}

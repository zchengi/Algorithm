package org.four.one.examples;

import edu.princeton.cs.algs4.StdIn;
import org.four.one.learn.DepthFirstPaths;
import org.four.one.learn.Graph;
import org.four.one.learn.SymbolGraph;

/**
 * 算法4.1.7节 间隔的度数
 * 使用深度优先搜索，找出两个人的间隔
 *
 * @author cheng
 *         2018/3/14 11:08
 */
public class DegreeOfSeparationDFS {
    public static void main(String[] args) {

        // movies.txt  "/"  "Bacon, Kevin"
        String filename = "src/main/java/org/four/one/text/movies.txt";
        String delimiter = "/";
        String source = "Bacon, Kevin";

        SymbolGraph sg = new SymbolGraph(filename, delimiter);
        Graph G = sg.graph();
        if (!sg.contains(source)) {
            System.out.println(source + " not in database.");
            return;
        }

        // Kidman, Nicole 、 Grant, Cary
        int s = sg.indexOf(source);
        DepthFirstPaths dfs = new DepthFirstPaths(G, s);
        while (!StdIn.isEmpty()) {
            String sink = StdIn.readLine();
            if (sg.contains(sink)) {
                int t = sg.indexOf(sink);
                if (dfs.hasPathTo(t)) {
                    for (int v : dfs.pathTo(t)) {
                        System.out.println(" " + sg.nameOf(v));
                    }
                } else {
                    System.out.println("Not connected.");
                }
            } else {
                System.out.println("Not in database.");
            }
        }
    }
}

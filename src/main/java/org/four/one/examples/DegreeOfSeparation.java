package org.four.one.examples;

import edu.princeton.cs.algs4.StdIn;
import org.four.one.learn.BreadthFirstPaths;
import org.four.one.learn.Graph;
import org.four.one.learn.SymbolGraph;

/**
 * 算法4.1.7节 间隔的度数
 *
 * @author cheng
 *         2018/3/10 15:50
 */
public class DegreeOfSeparation {

    private DegreeOfSeparation() {
    }

    public static void main(String[] args) {

        // movies.txt
        String filename = "src/main/java/org/four/one/text/movies.txt";
        String delimiter = "/";
        String source = "Animal House (1978)";

        SymbolGraph sg = new SymbolGraph(filename, delimiter);
        Graph graph = sg.graph();
        if (!sg.contains(source)) {
            System.out.println(source + " not in database.");
            return;
        }

        int s = sg.indexOf(source);
        BreadthFirstPaths bfs = new BreadthFirstPaths(graph, s);

        while (!StdIn.isEmpty()) {
            String sink = StdIn.readLine();
            if (sg.contains(sink)) {
                int t = sg.indexOf(sink);
                if (bfs.hasPathTo(t)) {
                    for (int v : bfs.pathTo(t)) {
                        System.out.println(" " + sg.nameOf(v));
                    }
                }
            } else {
                System.out.println(sink+ " not in database.");
            }
        }
    }
}

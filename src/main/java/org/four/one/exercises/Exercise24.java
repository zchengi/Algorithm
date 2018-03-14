package org.four.one.exercises;

import edu.princeton.cs.algs4.StdIn;
import org.four.one.learn.BreadthFirstPaths;
import org.four.one.learn.Graph;
import org.four.one.learn.SymbolGraph;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 4.1.24 修改DegreeOfSeparation，从命令行接受一个整型参数y，忽略上映年数超过y的电影。
 *
 * @author cheng
 *         2018/3/14 10:18
 */
public class Exercise24 {
    public static void main(String[] args) {

        // movies.txt  "/"  "Bacon, Kevin"
        String filename = "src/main/java/org/four/one/text/movies.txt";
        String delimiter = "/";
        String source = "Bacon, Kevin";

        // 忽略上映年数超过 y 的电影
        int y = 2000;

        SymbolGraph sg = new SymbolGraph(filename, delimiter);
        Graph graph = sg.graph();
        if (!sg.contains(source)) {
            System.out.println(source + " not in database.");
            return;
        }

        int s = sg.indexOf(source);
        BreadthFirstPaths bfs = new BreadthFirstPaths(graph, s);

        // Kidman, Nicole 、 Grant, Cary
        while (!StdIn.isEmpty()) {
            String sink = StdIn.readLine();
            if (sg.contains(sink)) {
                int t = sg.indexOf(sink);
                if (bfs.hasPathTo(t)) {
                    for (int v : bfs.pathTo(t)) {
                        String message = sg.nameOf(v);
                        if (message.matches(".*\\(.*")) {
                            Pattern p = Pattern.compile("[^0-9]");
                            Matcher m = p.matcher(message);
                            String match = m.replaceAll("").trim();
                            int year = Integer.parseInt(match);
                            if (year < y) {
                                System.out.println(v + " " + message);
                            }
                        } else {
                            System.out.println(v + " " + message);
                        }
                    }
                }
            } else {
                System.out.println(sink + " not in database.");
            }
        }
    }
}

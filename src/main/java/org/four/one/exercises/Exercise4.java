package org.four.one.exercises;

import org.four.one.learn.Graph;

/**
 * 4.1.4 为Graph添加一个方法hasEdge()，它接受两个整型参数v和w。如果图含有边v-w，则返回true，否则返回false。
 *
 * @author cheng
 *         2018/3/11 12:23
 */
public class Exercise4 {
    /*
     *
     * 见 learn 的 Graph.java
     *
     */
    public static void main(String[] args) {

        // tinyG.txt
        String filename = "src/main/java/org/four/one/text/tinyG.txt";
        Graph G = new Graph(filename);
        System.out.println(G);

        int v = 1, w = 0;
        System.out.println(G.hasEdge(v, w));
    }
}

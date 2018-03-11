package org.four.one.exercises;

import org.four.one.learn.Graph;

/**
 * 4.1.3 为Graph添加一个复制构造函数，它接受一幅图G然后创建并初始这幅图的一个副本。
 * G的用例对它作出的任何改动都不应该影响到它的副本。
 *
 * @author cheng
 *         2018/3/11 12:17
 */
public class Exercise3 {
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

        Graph copyG = new Graph(G);
        System.out.println(copyG);
    }
}

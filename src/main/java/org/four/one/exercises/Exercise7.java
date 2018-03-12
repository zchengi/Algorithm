package org.four.one.exercises;

import org.four.one.learn.Graph;

/**
 * 4.1.7 为Graph编写一个测试用例，用命令行参数指定名字的输入流中接受一幅图，
 * 然后用toString()方法将其打印出来。
 *
 * @author cheng
 *         2018/3/12 10:51
 */
public class Exercise7 {
    public static void main(String[] args) {

        // tinyG.txt
        String filename = "src/main/java/org/four/one/text/tinyG.txt";
        Graph g = new Graph(filename);

        System.out.println(g);
        System.out.println(g.degree(0));
    }
}

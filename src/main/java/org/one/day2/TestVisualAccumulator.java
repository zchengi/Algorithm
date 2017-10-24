package org.one.day2;


import edu.princeton.cs.algs4.StdOut;

/**
 * @author one
 *         2017/10/17 22:34
 */
public class TestVisualAccumulator {
    public static void main(String[] args) {
        int T = Integer.parseInt("2000");
        VisualAccumulator a = new VisualAccumulator(T, 1.0);
        for (int i = 0; i < T; i++) {
            a.addDataValue(Math.random());
            StdOut.println(a);
        }
    }
}

package org.one.day4;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


/**
 * 背包的典型用例
 *
 * @author cheng
 *         2017/10/19 15:39
 */
public class Stats {
    public static void main(String[] args) {
        Bag<Double> numbers = new Bag<>();
        while (!StdIn.isEmpty()) {
            numbers.add(StdIn.readDouble());
        }
        int n = numbers.size();

        double sum = 0.0;
        for (double x : numbers) {
            sum += x;
        }
        // 平均值
        double mean = sum / n;
        sum = 0.0;
        for (double x : numbers) {
            sum += (x - mean) * (x - mean);
        }
        // 样本平均差
        double std = Math.sqrt(sum / (n - 1));
        StdOut.printf("Mean: %.2f\n", mean);
        StdOut.printf("Std dev: %.2f\n", std);
    }
}

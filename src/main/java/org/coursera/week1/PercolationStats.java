package org.coursera.week1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * @author cheng
 *         2018/1/6 19:23
 *         <p>
 *         Programming Assignment 1: Percolation
 *         -题目：2.仿真实验
 *         -分析：
 *         需要注意"随机选取row和col进行open"，如果简单的使用random(int n)，选取[0,n)获取row和col，
 *         会有很多重复节点被选中，随着n越大，命中率就越低。于是这里采用生成一个[0,n*n)的数组，
 *         数组内容随机排序，依次读取数组内容，就相当于随机取site。
 */
public class PercolationStats {

    /**
     * 尝试次数
     */
    private final int t;

    private final double mean;

    private final double stddev;

    /**
     * perform trials independent experiments on an n-by-n grid
     */
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("n ≤ 0 or trials ≤ 0");
        }

        t = trials;
        // 每一次尝试的渗透率得分
        double[] fractions = new double[t];
        // t次尝试
        for (int i = 0; i < t; i++) {
            Percolation p = new Percolation(n);
            int openNum = 0;
            // 为了实现随机open一个site，模仿QuickUnion的定位方法
            // 先生成一个[0,n*n)的数组，数组内容随机排序，依次读取数组内容，就相当于随机site
            int[] random = StdRandom.permutation(n * n);
            for (int pos : random) {
                // pos = (row - 1) * n + col - 1;
                int row = pos / n + 1;
                int col = pos % n + 1;
                p.open(row, col);
                openNum++;
                // 只有openNum>=n时才有判断是否渗透的必要
                if (openNum >= n && p.percolates()) {
                    break;
                }
            }
            // 单词尝试的渗透率
            double pt = (double) openNum / (n * n);
            fractions[i] = pt;
        }
        /*
         * 作业提交时的某个测试案例要求mean()、stddev()、confidenceLo()、confidenceHi()
         * 在任何时候任何次序调用的情况下都必须返回相同的值，故需要在构造函数中计算mean和stddev
         */
        // 作业提交时的某个测试案例要求调用一次StdStats.mean方法
        mean = StdStats.mean(fractions);
        // 作业提交时的某个测试案例要求调用一次StdStats.stddev方法
        stddev = StdStats.stddev(fractions);
    }

    /**
     * sample mean of percolation threshold
     */
    public double mean() {
        return mean;
    }

    /**
     * sample standard deviation of percolation threshold
     */
    public double stddev() {
        return stddev;
    }

    /**
     * low endpoint of 95% confidence interval
     */
    public double confidenceLo() {
        return mean - 1.96 * stddev / Math.sqrt(t);
    }

    /**
     * high endpoint of 95% confidence interval
     */
    public double confidenceHi() {
        return mean + 1.96 * stddev / Math.sqrt(t);
    }

    public static void main(String[] args) {
        // test client (described below)
        int n = Integer.parseInt(args[0]);// 100
        int t = Integer.parseInt(args[1]);// 200

        PercolationStats ps = new PercolationStats(n, t);

        StdOut.printf("%-25s %s %f \n", "means", "=", ps.mean());
        StdOut.printf("%-25s %s %f \n", "stddev", "=", ps.stddev());
        StdOut.printf("%-25s %s%f%s%f%s\n", "95% confidence interval"
                , "= [", ps.confidenceLo(), ", ", ps.confidenceHi(), "]");
    }
}

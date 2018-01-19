package org.two.one.learn;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import org.two.two.learn.Merge;

/**
 * 2.1.5比较两种排序算法
 *
 * @author cheng
 *         2018/1/15 21:05
 */
public class SortCompare {

    /**
     * 计算算法运行时间
     */
    public static double time(String alg, Double[] a) {
        final Stopwatch timer = new Stopwatch();
        if ("Selection".equals(alg)) Selection.sort(a);
        if ("Insertion".equals(alg)) Insertion.sort(a);
        if ("Shell".equals(alg)) Shell.sort(a);
        if ("MergeSort".equals(alg)) Merge.sortBU(a);
        return timer.elapsedTime();
    }

    /**
     * 用算法alg将t个长度为n的数组排序
     */
    public static double timerRandomInput(String alg, int n, int t) {
        double total = 0.0;
        Double[] a = new Double[n];
        // 进行一次测试（生成一个数组并排序）
        for (int i = 0; i < t; i++) {
            for (int j = 0; j < n; j++) {
                a[j] = StdRandom.uniform();
            }
            total += time(alg, a);
        }
        return total;
    }

    public static void main(String[] args) {
        In in = new In();
        int n = 10000;
        int t = 10;

        double t2 = timerRandomInput("Selection", n, t);
        double t1 = timerRandomInput("Insertion", n, t);
        double t3 = timerRandomInput("Shell", n, t);
        double t4 = timerRandomInput("MergeSort", n, t);

        System.out.println("Selection算法用时：" + t2);
        System.out.println("Insertion算法用时：" + t1);
        System.out.println("Shell算法用时：" + t3);
        System.out.println("MergeSort算法用时：" + t4);
    }
}

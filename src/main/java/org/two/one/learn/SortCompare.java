package org.two.one.learn;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

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
        if ("Insertion".equals(alg)) {
           Insertion.sort(a);
        }
        if ("Selection".equals(alg)) {
            Selection.sort(a);
        }
        if ("Shell".equals(alg)) {
            Shell.sort(a);
        }
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
        // 1000 100
        int n = in.readInt();
        int t = in.readInt();

        double t1 = timerRandomInput("Insertion", n, t);
        double t2 = timerRandomInput("Selection", n, t);
        double t3 = timerRandomInput("Shell", n, t);

        System.out.println("Insertion算法用时：" + t1);
        System.out.println("Selection算法用时：" + t2);
        System.out.println("Shell算法用时：" + t3);
    }
}

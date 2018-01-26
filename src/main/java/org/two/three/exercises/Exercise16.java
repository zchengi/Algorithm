package org.two.three.exercises;

import edu.princeton.cs.algs4.StdOut;
import org.one.day9.StopWatch;
import org.tool.ArrayGenerator;

/**
 * 2.3.16 最佳情况。编写一段程序来生成使算法2.5中的sort()方法表现最佳的数组
 * （无重复数）：数组大小为N且不包含重复元素，每次切分后两个子数组的大小最多差1
 * （子数组的大小与含有N个相同呀un苏的数组的切分情况相同）。
 * （对于这道练习题，我们不需要在排序开始时就打乱数组）。
 * 以下练习描述了快速排序的几个变体。它们每个都需要分别实现，但你也很自然地
 * 希望使用SortCompare进行实验来评估每种改动的效果。
 *
 * @author cheng
 *         2018/1/26 16:19
 */
public class Exercise16 {
    /**
     * 最佳数组
     */
    public static int[] best(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = i;
        }
        best(a, 0, n - 1);
        return a;
    }

    /**
     * sort()方法表现最佳的数组
     */
    private static void best(int[] a, int lo, int hi) {
        if (lo >= hi) return;
        int mid = (lo + hi) >> 1;
        best(a, lo, mid - 1);
        best(a, mid + 1, hi);
        exch(a, lo, mid);
    }

    private static void exch(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static double quick(int[] a) {
        StopWatch timer = new StopWatch();
        quick(a, 0, a.length - 1);
        return timer.elapsedTime();
    }

    private static void quick(int[] a, int lo, int hi) {
        if (lo >= hi) return;
        int i = lo, j = hi + 1, v = a[lo];
        while (true) {
            while (i < hi && a[++i] < v) ;
            while (a[--j] > v) ;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, j, lo);
        quick(a, lo, j - 1);
        quick(a, j + 1, hi);
    }

    public static void main(String[] args) {
        int N = 10000000;
        int[] average = ArrayGenerator.ints(0, N - 1);
        int[] best = best(N);

        StdOut.printf("最佳情况 : %.3f\n", quick(best));
        StdOut.printf("平均情况 : %.3f\n", quick(average));
    }
}

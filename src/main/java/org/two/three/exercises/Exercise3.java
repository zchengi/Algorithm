package org.two.three.exercises;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

import static org.tool.ArrayGenerator.*;

/**
 * 2.3.3 对于长度为N的数组，在Quick.sort()执行时，其最大的元素最多会被交换多少次？
 *
 * @author cheng
 *         2018/1/22 22:02
 */

public class Exercise3 {
    private static int exchanges;
    private static int max;

    public static void searchMax(int[] a) {
        max = a[0];
        for (int i = 1; i < a.length; i++)
            if (a[i] > max) max = a[i];
    }

    public static void quick(int[] a) {
        quick(a, 0, a.length - 1);
    }

    private static void quick(int[] a, int lo, int hi) {
        if (lo >= hi) return;

        // int j = partition(a, lo, hi);
        int j = partition2(a, lo, hi);

        quick(a, lo, j - 1);
        quick(a, j + 1, hi);
    }

    private static void exch(int[] a, int i, int j) {
        if (max == a[i] || max == a[j]) exchanges++;
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    /**
     * 单向扫描
     */
    public static int partition(int[] a, int lo, int hi) {
        int i = lo - 1, j = lo, v = a[hi];
        while (j < hi) {
            if (a[j] < v)
                exch(a, j, ++i);
            j++;
        }
        exch(a, ++i, hi);
        return i;
    }

    /**
     * 双向扫描
     */
    private static int partition2(int[] a, int lo, int hi) {
        // 将数组切分为a[lo .. i-1], a[i], a[i+1 .. hi]
        // 左右扫描指针
        int i = lo, j = hi + 1;
        // 切分元素
        int v = a[lo];
        while (true) {
            // 扫描左右，检查扫描是否结束并交换元素
            while (a[++i] < v) if (i == hi) break;
            while (v < a[--j]) if (j == lo) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        // 将 v = a[j] 放入正确的位置
        exch(a, lo, j);
        // a[lo .. j-1] <= a[j] <= a[j+1 .. hi] 达成
        return j;
    }

    public static void infiniteLoopSearch() {
        while (true) {
            // 生成一个元素取值在 [0, 5] 无重复的随机打乱序列
            int[] a = ints(1, 12);
            int[] copy = copy(a);
            exchanges = 0;
            searchMax(a);
            quick(a);
            assert isSorted(a);
            if (exchanges > 5) {
                print(copy);
                StdOut.printf("最大元素 %d 的交换次数为 : %d\n", max, exchanges);
                System.out.println(Arrays.toString(a));
                break;
            }
        }

    }

    public static void main(String[] args) {
        infiniteLoopSearch();
    }
}
/*
 * 单向扫描
 *
 * 最大元素最多会被交换 N - 1 次
 *
 * 1   2   3   4   5
 * 最大元素 5 的交换次数为 : 5
 *
 *
 *
 * 双向扫描
 * 最大元素最多会被交换 Math.floor(N/2) 次
 *
 * 1   2   3   4   5
 * 最大元素 5 的交换次数为 : 3
 *
 * 1   2   3   4   5   6   7   8
 * 最大元素 8 的交换次数为 : 4
 *
 * 1   2   3   4   5   6   7   8   9
 * 最大元素 9 的交换次数为 : 4
 *
 * 1   2   3   4   5   6   7   8   9   10
 * 最大元素 10 的交换次数为 : 5
 *
 * 1   2   3   4   5   6   7   8   9   10  11  12  13
 * 最大元素 13 的交换次数为 : 6
 *
 */
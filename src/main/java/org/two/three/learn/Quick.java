package org.two.three.learn;

import edu.princeton.cs.algs4.StdRandom;
import org.tool.ArrayGenerator;
import org.two.one.learn.Insertion;

import java.util.Arrays;

/**
 * 算法2.5 快速排序
 *
 * @author cheng
 *         2018/1/22 10:46
 */
public class Quick {

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        // 改进1：切换到插入排序
        // if (hi <= lo) return;
        int m = 10;
        if (hi <= lo + m) Insertion.sort(a);
        // 转换参数m的最佳值是和系统相关的，但是5 ~ 15之间的任意值在大多数情况下都能令人满意。

        // 切分
        int j = partition(a, lo, hi);
        // 将左半部分a[lo .. j-1]排序
        sort(a, lo, j - 1);
        // 将右半部分a[j+1 .. hi]排序
        sort(a, j + 1, hi);
    }

    /**
     * 快速排序的切分
     */
    private static int partition(Comparable[] a, int lo, int hi) {
        // 将数组切分为a[lo .. i-1], a[i], a[i+1 .. hi]
        // 左右扫描指针
        int i = lo, j = hi + 1;
        // 切分元素
        Comparable v = a[lo];
        while (true) {
            // 扫描左右，检查扫描是否结束并交换元素
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) if (j == lo) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        // 将 v = a[j] 放入正确的位置
        exch(a, lo, j);
        // a[lo .. j-1] <= a[j] <= a[j+1 .. hi] 达成
        return j;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    @SuppressWarnings("unchecked")
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }


    public static void main(String[] args) {
        Integer[] arr = ArrayGenerator.Integers(0, 20);
        System.out.println(Arrays.toString(arr));

        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}

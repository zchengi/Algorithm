package org.two.three.learn;

import org.tool.SortTestHelper;
import org.two.one.learn.Insertion;

/**
 * 算法2.5 快速排序
 * 单路扫描
 *
 * @author cheng
 *         2018/1/22 10:46
 */
public class Quick {

    public static void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    /**
     * 对a[lo...hi]部分进行快速排序
     */
    private static void sort(Comparable[] a, int lo, int hi) {
        // 优化1：切换到插入排序
        // if (hi <= lo) return;
        // 转换参数m的最佳值是和系统相关的，但是5 ~ 15之间的任意值在大多数情况下都能令人满意。
        if (hi - lo <= 15) {
            Insertion.sort(a, lo, hi);
            return;
        }

        // 切分
        int p = partition(a, lo, hi);
        // 将左半部分a[lo...p-1]排序
        sort(a, lo, p - 1);
        // 将右半部分a[p+1...hi]排序
        sort(a, p + 1, hi);
    }

    /**
     * 快速排序的切分
     * 返回p, 使得arr[lo...p-1] < arr[p] ; arr[p+1...hi] > arr[p]
     */
    private static int partition(Comparable[] a, int lo, int hi) {

        // Comparable v = a[lo];

        // 优化2 随机在a[lo...hi]的范围中，选择一个数组作为标定点pivot
        // 解决了对于近乎有序的数组快速排序退化为O(n^2)的问题
        // 但是相应的，对于普通数组而言，性能下降了一点点
        exch(a, lo, (int) (Math.random() * (hi - lo + 1)) + lo);
        Comparable v = a[lo];

        int j = lo;
        // 最终 arr[l+1...j] < v; arr[j+1...i-1] > v
        for (int i = lo + 1; i <= hi; i++) {
            if (a[i].compareTo(v) < 0) {
                exch(a, ++j, i);
            }
        }
        exch(a, lo, j);

        return j;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {

        // Quick Sort也是一个O(nlogn)复杂度的算法
        // 可以在1秒之内轻松处理100万数量级的数据
        Integer[] arr = SortTestHelper.generateRandomArray(1000000, 1, 5000000);
        SortTestHelper.testSort("org.two.three.learn.Quick", arr);
        // SortTestHelper.printArray(arr);

    }
}

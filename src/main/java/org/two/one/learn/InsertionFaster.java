package org.two.one.learn;

import org.tool.ArrayGenerator;
import org.tool.SortTestHelper;

/**
 * 算法2.2 插入排序优化
 * <p>
 * 将exch()交换改为赋值
 * <p>
 * 优化后，插入排序比选择排序性能略好。
 * 对于有序性强的数组，插入排序远远优于选择排序
 *
 * @author cheng
 *         2018/1/23 12:14
 */
public class InsertionFaster {

    public static void sort(Comparable[] a) {
        int n = a.length;
        for (int i = 1; i < n; i++) {
            // 寻找元素 a[i] 合适的插入位置
            Comparable copy = a[i];
            // j保存元素copy应该插入的位置
            int j;
            for (j = i; j > 0 && less(copy, a[j - 1]); j--) {
                a[j] = a[j - 1];
            }
            a[j] = copy;
        }
    }

    /**
     * 对arr[l...r]的区间使用InsertionSort排序
     */
    public static void sort(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            // 寻找元素 a[i] 合适的插入位置
            Comparable copy = a[i];
            // j保存元素copy应该插入的位置
            int j;
            for (j = i; j > lo && less(copy, a[j - 1]); j--) {
                a[j] = a[j - 1];
            }
            a[j] = copy;
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void main(String[] args) {

        // 近乎有序的数组
        Integer[] arr = SortTestHelper.generateNearlyOrderedArray(1000000, 1000);
        Integer[] copy = ArrayGenerator.copy(arr);

        // 下面两行交换位置，对结果有影响？
        SortTestHelper.testSort("org.two.one.learn.Insertion", arr);
        SortTestHelper.testSort("org.two.one.learn.InsertionFaster", copy);
    }
}

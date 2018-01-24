package org.two.three.learn;

import org.tool.SortTestHelper;
import org.two.one.learn.Insertion;

/**
 * 2.3.3 三向切分的快速排序
 *
 * @author cheng
 *         2018/1/22 20:11
 */
public class Quick3Ways {

    public static void sort(Comparable[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    /**
     * 三路快速排序处理 a[lo...hi]
     * 将a[lo...hi]分为 < v ; == v ; > v 三部分
     * 之后递归对 < v ; > v 两部分继续进行三路快速排序
     */
    private static void sort(Comparable[] arr, int lo, int hi) {

        if (hi - lo <= 15) {
            Insertion.sort(arr, lo, hi);
            return;
        }

        // partition
        exch(arr, lo, (int) (Math.random() * (hi - lo + 1)) + lo);
        Comparable v = arr[lo];

        // arr[lo+1...lt] < v
        int lt = lo;
        // arr[gt...hi] > v
        int gt = hi + 1;
        // arr[lt+1...i) == v
        int i = lo + 1;
        while (i < gt) {
            if (arr[i].compareTo(v) < 0) {
                exch(arr, i++, ++lt);
            } else if (arr[i].compareTo(v) > 0) {
                exch(arr, i, --gt);
            } else {
                // arr[i] == v
                i++;
            }
        }
        exch(arr, lo, lt);

        // 现在arr[lo .. lt-1] < v = arr[lt .. gt-1] < arr[gt .. hi]成立
        sort(arr, lo, lt - 1);
        sort(arr, gt, hi);
    }

    private static void exch(Comparable[] arr, int i, int j) {
        Comparable temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {

        // 三路快速排序算法也是一个O(nlogn)复杂度的算法
        // 可以在1秒之内轻松处理100万数量级的数据
        int n = 1000000;
        Integer[] arr = SortTestHelper.generateRandomArray(n, 0, n);
        SortTestHelper.testSort("org.two.three.learn.Quick3Ways", arr);
    }
}

package org.two.three.exercises;

import edu.princeton.cs.algs4.StdRandom;
import org.tool.SortTestHelper;

/**
 * 2.3.17 哨兵。修改算法2.5，去掉内循环while边界检查。由于切分元素本身就是一个哨兵
 * （v不可呢小于a[lo]），左侧边界的检查是多余的。要去掉另一个检查，可以在打乱数组后
 * 将数组的最大元素放在a[length-1]中。该元素永远不会移动（除非和相等的元素交换），
 * 可以在所有包含它的子数组中组成哨兵。
 * 注意：在处理内部子数组时，右子数组中最左侧的元素可以作为左子数组右边界的哨兵。
 *
 * @author cheng
 *         2018/1/26 16:40
 */
public class Exercise17 {

    public static void sort(Comparable[] arr) {
        StdRandom.shuffle(arr);
        int temp = 0;
        for (int i = 0; i < arr.length; i++) {
            if (less(arr[temp], arr[i])) {
                temp = i;
            }
        }
        exch(arr, temp, arr.length - 1);
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(Comparable[] arr, int lo, int hi) {
        if (lo >= hi) return;
        int p = partition(arr, lo, hi);
        sort(arr, lo, p - 1);
        sort(arr, p + 1, hi);
    }

    private static int partition(Comparable[] arr, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        Comparable v = arr[lo];

        while (true) {
            while (less(arr[++i], v)) ;
            while (less(v, arr[--j])) ;
            if (i >= j) break;
            exch(arr, i, j);
        }
        exch(arr, lo, j);
        return j;
    }

    private static void exch(Comparable[] arr, int i, int j) {
        Comparable temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void main(String[] args) {

        int n = 1000000;
        Integer[] arr = SortTestHelper.generateRandomArray(n, 0, n);
        SortTestHelper.testSort("org.two.three.exercises.Exercise17", arr);
    }
}

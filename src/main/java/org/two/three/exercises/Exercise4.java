package org.two.three.exercises;

import org.tool.SortTestHelper;

/**
 * 2.3.4 假如跳过开头打乱数组的操作，给出六个含有10个元素的数组，
 * 使得Quick.sort()所需的比较次数达到最坏情况。
 *
 * @author cheng
 *         2018/1/24 20:44
 */
public class Exercise4 {

    private static int compares;

    private static void sort(Comparable[] arr) {
        compares = 0;
        int n = arr.length;
        sort(arr, 0, n - 1);
    }

    private static void sort(Comparable[] arr, int lo, int hi) {
        if (lo >= hi) return;

        int p = partition(arr, lo, hi);
        sort(arr, lo, p - 1);
        sort(arr, p + 1, hi);
    }

    private static int partition(Comparable[] arr, int lo, int hi) {

        Comparable v = arr[lo];
        int i = lo + 1;
        int j = hi;

        while (true) {
            while (i <= hi && less(arr[i], v)) i++;
            while (j >= lo + 1 && less(v, arr[j])) j--;

            if (i >= j) break;
            swap(arr, i++, j--);
        }
        swap(arr, lo, j);
        return j;
    }

    private static void swap(Comparable[] arr, int i, int j) {
        Comparable temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static boolean less(Comparable v, Comparable w) {
        compares++;
        return v.compareTo(w) < 0;
    }


    public static void main(String[] args) {
        int n = 20;
        // Integer[] arr = SortTestHelper.generateRandomArray(n, 0, n);
        Integer[] arr = {40, 41, 42, 43, 44, 45, 46, 47, 48, 49};

        sort(arr);
        SortTestHelper.printArray(arr);
        System.out.println(compares);

        // 对于上述切分方法，升序序列是比较次数最多的，
        // 全部相等的序列，以及逆序序列都不需要那么多的比较次数
        // 说明了没有优化的双路快速排序，对于处理已经拍好的数组效率很低

        // [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]              54
        // [10, 11, 12, 13, 14, 15, 16, 17, 18, 19]    54
        // [20, 21, 22, 23, 24, 25, 26, 27, 28, 29]    54
        // [30, 31, 32, 33, 34, 35, 36, 37, 38, 39]    54
        // [40, 41, 42, 43, 44, 45, 46, 47, 48, 49]    54
        // [50, 51, 52, 53, 54, 55, 56, 57, 58, 59]    54
    }
}

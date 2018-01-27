package org.two.three.exercises;

import org.tool.SortTestHelper;

import java.util.Arrays;

/**
 * 2.3.22 快速三向切分。用将重复元素放置于子数组两端的方式实现了一个信息量最优的算法。
 * 注意：这里实现的代码和正文中给出的代码是等价的，因为这里额外的交换用于和切分元素相等的元素，
 * 而正文中的代码将额外的交换用于和切分元素不等的元素。
 *
 * @author cheng
 *         2018/1/27 23:25
 */
public class Exercise22 {

    /**
     * 额外的操作用于交换和枢轴相等的元素。
     */
    public static void sort(Comparable[] arr) {
        int n = arr.length;
        sort(arr, 0, n - 1);
    }

    private static void sort(Comparable[] arr, int lo, int hi) {
        if (lo >= hi) return;

        Comparable v = arr[lo];
        int p = lo;
        int q = hi + 1;
        int i = lo;
        int j = hi + 1;

        while (true) {

            while (i < hi && less(arr[++i], v)) ;
            while (less(v, arr[--j])) ;
            if (i >= j) break;

            swap(arr, i, j);

            if (arr[i] == v) {
                swap(arr, i, ++p);
            }
            if (arr[j] == v) {
                swap(arr, j, --q);
            }
        }

        swap(arr, lo, j);

        int lt = j - 1;
        int gt = j + 1;
        int k = lo + 1;
        int m = hi;
        while (k <= p) {
            swap(arr, k++, lt--);
        }
        while (m >= q) {
            swap(arr, m--, gt++);
        }

        sort(arr, lo, lt);
        sort(arr, gt, hi);
    }

    private static void swap(Comparable[] arr, int i, int j) {
        Comparable temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    @SuppressWarnings("unchecked")
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void main(String[] args) {

        int n = 1000000;
        Integer[] arr1 = SortTestHelper.generateRandomArray(n, 0, n);
        Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);
        Integer[] arr3 = Arrays.copyOf(arr1, arr1.length);


        System.out.println("无重复元素：");
        SortTestHelper.testSort("org.two.three.learn.Quick2Ways", arr1);
        SortTestHelper.testSort("org.two.three.learn.Quick3Ways", arr2);
        SortTestHelper.testSort("org.two.three.exercises.Exercise22", arr3);

        System.out.println("\n大量重复元素：");
        arr1 = SortTestHelper.generateRandomArray(n, 0, 100);
        arr2 = Arrays.copyOf(arr1, arr1.length);
        arr3 = Arrays.copyOf(arr1, arr1.length);

        SortTestHelper.testSort("org.two.three.learn.Quick2Ways", arr1);
        SortTestHelper.testSort("org.two.three.learn.Quick3Ways", arr2);
        SortTestHelper.testSort("org.two.three.exercises.Exercise22", arr3);

        System.out.println("\n全部相同元素：");
        arr1 = SortTestHelper.generateRandomArray(n, 0, 0);
        arr2 = Arrays.copyOf(arr1, arr1.length);
        arr3 = Arrays.copyOf(arr1, arr1.length);

        SortTestHelper.testSort("org.two.three.learn.Quick2Ways", arr1);
        SortTestHelper.testSort("org.two.three.learn.Quick3Ways", arr2);
        SortTestHelper.testSort("org.two.three.exercises.Exercise22", arr3);
    }
}

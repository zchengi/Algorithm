package org.two.three.exercises;

import org.tool.SortTestHelper;

/**
 * 2.3.6 编写一段代码来计算Cn的准确值，在n=100、n=1000和10000
 * 的情况下比较准确值和估计值2NlnN的差距。
 *
 * @author cheng
 *         2018/1/24 21:49
 */
public class Exercise6 {
    private static int compares;

    public static int sort(Comparable[] arr) {
        compares = 0;
        int n = arr.length;
        sort(arr, 0, n - 1);
        return compares;
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

    /**
     * 比较次数小于理论值
     */
    public static void main(String[] args) {
        int n = 100;
        Integer[] arr = SortTestHelper.generateRandomArray(n, 0, n);
        sort(arr);
        System.out.println("精确值：" + compares + " 理论值： " + (int) (2 * n * Math.log(n)));

        n = 1000;
        arr = SortTestHelper.generateRandomArray(n, 0, n);
        sort(arr);
        System.out.println("精确值：" + compares + " 理论值： " + (int) (2 * n * Math.log(n)));

        n = 10000;
        arr = SortTestHelper.generateRandomArray(n, 0, n);
        sort(arr);
        System.out.println("精确值：" + compares + " 理论值： " + (int) (2 * n * Math.log(n)));

    }
}

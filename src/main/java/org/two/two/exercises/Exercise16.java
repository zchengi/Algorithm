package org.two.two.exercises;

import org.tool.ArrayGenerator;

/**
 * 2.2.16 自然的归并排序。编写一个自底向上的归并排序，当需要将两个子数组排序时能够利用
 * 数组中已经有序的部分。首先找到一个有序的子数组（移动指针直到当前元素比上一个元素小为止），
 * 然后再找出另一个并将它们归并。根据数组大小和数组中递增子数组的最大长度分析算法的运行时间。
 *
 * @author cheng
 *         2018/1/19 17:39
 */
public class Exercise16 {
    private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        int n = a.length;
        aux = new Comparable[n];
        for (int sz = 1; sz < n; sz += sz) {
            for (int lo = 0; lo < n - sz; lo += 2 * sz) {
                merge(a, lo, lo + sz - 1, Math.min(lo + 2 * sz - 1, n - 1));
            }
        }
    }

    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }

    public static void mergeNatural(Comparable[] a) {
        if (a.length == 1) return;
        int lo = 0, mid = 0, hi = 0, n = a.length;
        aux = new Comparable[n];
        while (true) {
            mid = 0;
            while (mid < n - 1 && less(a[mid], a[mid + 1])) mid++;

            hi = mid + 1;
            while (hi < n - 1 && less(a[hi], a[hi + 1])) hi++;

            if (hi == n) return;
            merge(a, lo, mid, hi);
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void main(String[] args) {
        Integer[] arr = ArrayGenerator.Integers(1, 20);
        ArrayGenerator.print(arr);
        mergeNatural(arr);
        ArrayGenerator.print(arr);
    }
}

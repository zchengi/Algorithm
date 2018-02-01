package org.two.five.exercises;

import java.util.Arrays;

/**
 * 2.5.4 实现一个方法String[] dedup(String[] a)，返回一个有序的 a[]，
 * 并删除去其中的重复部分。
 * <p>
 * 先使用归并排序，然后去重；是否可以在归并排序的同时去重？
 *
 * @author cheng
 *         2018/2/1 17:24
 */
public class Exercise4 {
    private static String aux[];

    public static String[] dedup(String[] arr) {
        int n = arr.length;
        aux = new String[arr.length];
        mergeSort(arr, 0, n - 1);

        int i = 0;
        int j = 1;
        while (j < n) {
            if (arr[i].compareTo(arr[j]) != 0) {
                arr[++i] = arr[j++];
            } else {
                j++;
            }
        }
        String[] ret = new String[i + 1];
        System.arraycopy(arr, 0, ret, 0, i + 1);
        return ret;
    }

    private static void mergeSort(String[] arr, int lo, int hi) {
        if (hi <= lo) return;

        int mid = (hi - lo) / 2 + lo;

        mergeSort(arr, lo, mid);
        mergeSort(arr, mid + 1, hi);
        merge(arr, lo, mid, hi);
    }

    private static void merge(String[] arr, int lo, int mid, int hi) {
        System.arraycopy(arr, lo, aux, lo, hi - lo + 1);

        int i = lo;
        int j = mid + 1;
        String temp = arr[lo];
        for (int k = lo; k <= hi; k++) {
            if (i > mid) arr[k] = aux[j++];
            else if (j > hi) arr[k] = aux[i++];
            else if (less(arr[i], arr[j])) arr[k] = aux[i++];
            else arr[k] = aux[j++];
        }
    }

    private static boolean less(String v, String w) {
        return v.compareTo(w) < 0;
    }


    public static void main(String[] args) {
        String[] arr = {"a", "w", "a", "g", "d", "s", "c", "h", "r", "b", "k", "l", "b", "c", "t", "z"};

        // 去重操作
        System.out.println(Arrays.toString(dedup(arr)));
    }
}

package org.coursera.week3;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

/**
 * Test1 Merging with smaller auxiliary array.
 * <p>
 * 分析：对两个大小分别为n的有序子数组进行归并，要求空间复杂度为n，
 * 正常情况下归并排序在此处的空间复杂度为2n，但是由于两个子数组分别是有序的，
 * 故用大小为n的额外子空间辅助归并是个很合理的要求，实现如下：
 *
 * @author cheng
 *         2018/2/2 15:29
 */
public class MergeSortedSubArray {

    private static void merge(Comparable[] arr) {
        int n = arr.length / 2;
        Comparable[] aux = new Comparable[n];
        //  取左半边sorted的元素至辅助数组，因为未来归并左侧元素位置可能会被右侧元素占据
        System.arraycopy(arr, 0, aux, 0, n);
        System.out.println(Arrays.toString(aux));

        int l = 0;
        int r = n;
        for (int k = 0; k < 2 * n; k++) {
            // 辅助元素数组全部用完，arr右侧不需要挪动位置。
            if (l >= n) break;
                // arr原右侧元素全部放置合适的位置，后面只需把辅助数组的元素挪到array右侧。
            else if (r >= 2 * n) arr[k] = aux[l++];
            else if (less(arr[r], aux[l])) arr[k] = arr[r++];
            else arr[k] = aux[l++];
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void main(String[] args) {
        int n =10;
        int[] subArr1 = new int[n];
        int[] subArr2 = new int[n];
        for (int i = 0; i < n; i++) {
            subArr1[i] = StdRandom.uniform(100);
            subArr2[i] = StdRandom.uniform(100);
        }
        Arrays.sort(subArr1);
        Arrays.sort(subArr2);

        Integer[] arr = new Integer[2 * n];
        for (int i = 0; i < n; i++) {
            arr[i] = subArr1[i];
            arr[n + i] = subArr2[i];
        }

        System.out.println(Arrays.toString(arr));
        merge(arr);
        System.out.println(Arrays.toString(arr));
    }
}

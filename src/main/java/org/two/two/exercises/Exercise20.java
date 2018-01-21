package org.two.two.exercises;

import org.tool.ArrayGenerator;

import java.util.Arrays;

/**
 * 2.2.20 间接排序。编写一个不改变数组的归并排序，它返回一个int[]数组perm，
 * 其中perm[i]的值是原数组中第i小的元素的位置。
 *
 * @author cheng
 *         2018/1/21 20:19
 */
public class Exercise20 {
    private static int[] aux;

    public static int[] merge(int[] a) {
        int[] perm = new int[a.length];
        aux = new int[a.length];
        System.arraycopy(a, 0, perm, 0, a.length);
        merge(a, perm, 0, a.length - 1);
        return perm;
    }

    private static void merge(int[] a, int[] perm, int lo, int hi) {
        if (lo >= hi) return;
        int mid = (lo + hi) / 2;
        merge(a, perm, lo, mid);
        merge(a, perm, mid + 1, hi);
        mergeSort(a, perm, lo, mid, hi);
    }

    private static void mergeSort(int[] a, int[] perm, int lo, int mid, int hi) {
        int i = lo;
        int j = mid + 1;
        System.arraycopy(perm, lo, aux, lo, hi + 1 - lo);

        for (int k = lo; k <= hi; k++) {
            if (i > mid) perm[k] = aux[j++];
            else if (j > hi) perm[k] = aux[i++];
            else if (aux[j] < aux[i]) perm[k] = aux[j++];
            else perm[k] = aux[i++];
        }
    }

    public static void main(String[] args) {
        int[] arr = ArrayGenerator.ints(0, 20);
        System.out.println("---------- 排序前原数组 ----------");
        System.out.println(Arrays.toString(arr));

        int[] perm = merge(arr);
        System.out.println("\n----------    排序后    ----------");
        System.out.println(Arrays.toString(perm));

        System.out.println("\n---------- 排序后原数组 ----------");
        System.out.println(Arrays.toString(arr));
    }
}

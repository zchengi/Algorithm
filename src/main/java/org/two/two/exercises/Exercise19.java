package org.two.two.exercises;

import org.tool.ArrayGenerator;

/**
 * 2.2.19 倒置。编写一个线性对数级别的算法统计给定数组中的“倒置”数量。
 * 这个数量和Kendall tau距离有关，请见2.5节。
 *
 * @author cheng
 *         2018/1/21 20:05
 */
public class Exercise19 {
    private static int[] aux;

    public static int merge(int[] a) {
        aux = new int[a.length];
        return merge(a, 0, a.length - 1);
    }

    private static int merge(int[] a, int lo, int hi) {
        int inversions = 0;
        if (lo >= hi) return 0;
        int mid = (hi + lo) / 2;

        inversions += merge(a, lo, mid);
        inversions += merge(a, mid + 1, hi);
        inversions += mergeSort(a, lo, mid, hi);
        return inversions;
    }

    private static int mergeSort(int[] a, int lo, int mid, int hi) {
        int inversions = 0;
        System.arraycopy(a, lo, aux, lo, hi - lo + 1);

        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (aux[j] < aux[i]) {
                a[k] = a[j++];
                inversions += (mid - i + 1);
            } else a[k] = aux[i++];
        }
        return inversions;
    }

    public static int brute(int[] a) {
        int inversions = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[i]) inversions++;
            }
        }
        return inversions;
    }

    public static void main(String[] args) {
        int[] arr = ArrayGenerator.ints(0, 20);
        int[] copy = ArrayGenerator.copy(arr);
        System.out.println("线性对数级别的统计： " + merge(arr));
        System.out.println("暴力解法的统计： " + brute(copy));
    }
}

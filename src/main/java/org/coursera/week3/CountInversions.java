package org.coursera.week3;

import org.tool.SortTestHelper;

/**
 * Test2 Counting inversions.
 * <p>
 * 分析：使用归并排序计算数组中的逆序对，时间复杂度为：nlogn;
 * 在归并排序中，某个右侧的元素a[j]小于左侧元素a[i]时，a[j]与区间[a[i],a[mid]]
 * 中的所有元素都可以组成逆序对，这个区间的元素个数为：mid+i-1个。
 *
 * @author cheng
 *         2018/2/2 15:55
 */
public class CountInversions {
    private static Comparable[] aux;

    public static long countInversions(Comparable[] arr) {
        int n = arr.length;
        aux = new Comparable[n];
        return mergeSort(arr, 0, n - 1);
    }

    private static long mergeSort(Comparable[] arr, int lo, int hi) {
        if (hi <= lo) return 0;

        int mid = (hi - lo) / 2 + lo;

        long res1 = mergeSort(arr, lo, mid);
        long res2 = mergeSort(arr, mid + 1, hi);

        if (arr[mid].compareTo(arr[mid + 1]) < 0) return 0;

        return res1 + res2 + merge(arr, lo, mid, hi);
    }

    private static long merge(Comparable[] arr, int lo, int mid, int hi) {
        System.arraycopy(arr, lo, aux, lo, hi - lo + 1);
        int i = lo;
        int j = mid + 1;
        long res = 0;

        for (int k = lo; k <= hi; k++) {
            if (i > mid) arr[k] = aux[j++];
            else if (j > hi) arr[k] = aux[i++];
            else if (aux[j].compareTo(aux[i]) < 0) {
                arr[k] = aux[j++];
                res += mid - i + 1;
            } else arr[k] = aux[i++];
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 1000000;
        Integer[] arr = SortTestHelper.generateInversedArray(n);
        System.out.println("Test Inversion Count for Inversed Array, n = " + n + ": " + countInversions(arr));
    }
}

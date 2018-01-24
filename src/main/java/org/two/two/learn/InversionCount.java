package org.two.two.learn;

import org.tool.SortTestHelper;

/**
 * 归并排序思路求数组中的逆序对
 *
 * @author cheng
 *         2018/1/24 11:01
 */
public class InversionCount {

    private static Comparable[] aux;

    public static long sort(Comparable[] arr) {
        int n = arr.length;
        aux = new Comparable[arr.length];
        return sort(arr, 0, n - 1);
    }

    private static long sort(Comparable[] arr, int lo, int hi) {
        if (lo >= hi) return 0;

        int mid = (hi - lo) / 2 + lo;

        long res1 = sort(arr, lo, mid);
        long res2 = sort(arr, mid + 1, hi);

        if (arr[mid].compareTo(arr[mid + 1]) < 0) return 0;
        return res1 + res2 + merge(arr, lo, mid, hi);
    }

    private static long merge(Comparable[] arr, int lo, int mid, int hi) {
        System.arraycopy(arr, lo, aux, lo, hi - lo + 1);

        // 初始化逆序对个数 res = 0
        long res = 0;

        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                arr[k] = aux[j++];
            } else if (j > hi) {
                arr[k] = aux[i++];
            } else if (aux[i].compareTo(aux[j]) <= 0) {
                arr[k] = aux[i++];
            } else {
                arr[k] = aux[j++];

                // 此时,因为右半边部分k所指的元素小
                // 这个元素和左半部分的所有未处理的元素都构成了逆序数对
                // 左半部分此时未处理的元素个数为 mid - i + 1
                res += mid - i + 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 1000000;

        // 测试1：测试随机数组
        Integer[] arr = SortTestHelper.generateRandomArray(n, 0, n);
        System.out.println("Test Inversion Count for Random Array, n = " + n + ": " + sort(arr));

        // 测试2：测试完全有序的数组
        // 结果应该为0
        arr = SortTestHelper.generateOrderedArray(n);
        System.out.println("Test Inversion Count for Ordered Array, n = " + n + ": " + sort(arr));

        // 测试3：测试完全逆序的数组
        // 结果应该为 n(n-1)/2
        arr = SortTestHelper.generateInversedArray(n);
        System.out.println("Test Inversion Count for Inversed Array, n = " + n + ": " + sort(arr));
        // 499999500000
    }
}

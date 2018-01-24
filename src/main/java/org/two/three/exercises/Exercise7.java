package org.two.three.exercises;

import edu.princeton.cs.algs4.StdOut;
import org.tool.SortTestHelper;

/**
 * 2.3.7 在使用快速排序将N个不重复的元素排序时，计算大小为0、1和2的子数组的数量。
 * 如果你喜欢数学，请推到；不喜欢，做一些实验并提出猜想。
 *
 * @author cheng
 *         2018/1/24 22:08
 */
public class Exercise7 {
    private static int size0;
    private static int size1;
    private static int size2;
    private static int size3;

    private static void sort(Comparable[] arr) {
        size0 = size1 = size2 = size3 = 0;
        int n = arr.length;
        sort(arr, 0, n - 1);
    }

    private static void sort(Comparable[] arr, int lo, int hi) {
        int size = hi - lo;
        if (size == 0) size0++;
        if (size == 1) size1++;
        if (size == 2) size2++;
        if (size == 3) size3++;
        if (size <= 0) return;

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

            if (i > j) break;
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
        return v.compareTo(w) < 0;
    }

    // 把<0的也算入大小为0的数组
    // 通过统计可以得出，他们的大小是与N存在一定比例的关系
    public static void main(String[] args) {
        int T = 1000, i = T;
        int s0 = 0, s1 = 0, s2 = 0, s3 = 0;
        int n = 10000;

        while (i-- > 0) {
            // n个不重复的元素
            Integer[] arr = SortTestHelper.generateNearlyOrderedArray(n, n * 10);
            sort(arr);

            s0 += size0;
            s1 += size1;
            s2 += size2;
            s3 += size3;
        }

        StdOut.printf("尺寸为 0 的子数组有 %d 个, 占数组长度的 %.1f%%\n", s0 /= T, (s0 * 100.0) / n);
        StdOut.printf("尺寸为 1 的子数组有 %d 个, 占数组长度的 %.1f%%\n", s1 /= T, (s1 * 100.0) / n);
        StdOut.printf("尺寸为 2 的子数组有 %d 个, 占数组长度的 %.1f%%\n", s2 /= T, (s2 * 100.0) / n);
        StdOut.printf("尺寸为 3 的子数组有 %d 个, 占数组长度的 %.1f%%\n", s3 /= T, (s3 * 100.0) / n);

        // 这里还有尺寸为 -1 的，占数组长度的 33.3%

        // 尺寸为 0 的子数组有 3333 个, 占数组长度的 33.3%
        // 尺寸为 1 的子数组有 1667 个, 占数组长度的 16.7%
        // 尺寸为 2 的子数组有 1000 个, 占数组长度的 10.0%
        // 尺寸为 3 的子数组有 666 个, 占数组长度的 6.7%
    }
}

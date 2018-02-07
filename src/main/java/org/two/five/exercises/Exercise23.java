package org.two.five.exercises;

import java.util.Arrays;

import static org.tool.ArrayGenerator.ints;
import static org.tool.ArrayGenerator.print;

/**
 * 2.5.23 选择的取样：实现使用取样来改进select()函数的想法。提示：使用中位数可能并不总是有效。
 * 求任意数组第k小(大)的值，即求数组中某个位置的值为多少。
 * <p>
 * 分析：采用三路查找。
 *
 * @author cheng
 *         2018/2/7 23:34
 */
public class Exercise23 {
    public static int select(int[] arr, int k) {
        if (--k >= arr.length || k <= 0) {
            throw new IllegalArgumentException("k can not be " + k);
        }
        int lo = 0;
        int hi = arr.length - 1;

        // 三取样
        while (lo <= hi) {
            int mid = (hi - lo) / 2 + lo;

            if (arr[mid] < arr[lo]) exch(arr, mid, lo);
            if (arr[hi] < arr[lo]) exch(arr, hi, lo);
            if (arr[mid] < arr[hi]) exch(arr, mid, hi);

            int pivot = arr[hi];
            int i = lo - 1;
            int j = hi;

            while (true) {
                while (arr[++i] < pivot) ;
                while (arr[--j] > pivot) ;
                if (i >= j) break;
                exch(arr, i, j);
            }
            exch(arr, hi, i);
            if (i > k) hi = i - 1;
            else if (i < k) lo = i + 1;
            else return arr[i];
        }
        return -1;
    }

    private static void exch(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = ints(100, 10, 1000);
        System.out.println("排序前：");
        print(arr);
        int k = 5;
        System.out.println();

        System.out.println("第 " + k + " 小的数字是 " + select(arr, k));

        System.out.println("排序后：");
        Arrays.sort(arr);
        print(arr);
    }
}

package org.two.four.learn2;

import org.tool.SortTestHelper;

/**
 * 原地堆排序
 * 不使用一个额外的最大堆，直接在数组上原地排序
 *
 * @author cheng
 *         2018/1/30 17:00
 */
public class HeapSort {
    private HeapSort() {
    }

    public static void sort(Comparable[] arr) {
        int n = arr.length;

        // 注意，此时我们的堆从0开始索引的
        // 从（最后一个元素的索引-1）/2 开始
        // 最后一个元素的索引为 = n - 1
        for (int i = (n - 1 - 1) / 2; i >= 0; i--) {
            shiftDown(arr, n, i);
        }


        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i);
            shiftDown(arr, i, 0);
        }
    }

    /**
     * 原地shiftDown的过程
     */
    private static void shiftDown(Comparable[] arr, int n, int k) {
        while (2 * k + 1 < n) {
            int j = 2 * k + 1;
            if (j + 1 < n && less(arr[j], arr[j + 1])) {
                j += 1;
            }
            if (!less(arr[k], arr[j])) {
                break;
            }
            swap(arr, k, j);
            k = j;
        }
    }

    /**
     * 优化shiftDown的过程
     * 使用赋值的方式取代不断的swap，该优化思路和我们之前对插入排序进行优化的思路是一样的。
     */
    private static void shiftDown2(Comparable[] arr, int n, int k) {
        Comparable e = arr[k];
        while (2 * k + 1 < n) {
            int j = 2 * k + 1;
            if (j + 1 < n && less(arr[j], arr[j + 1])) {
                j++;
            }
            if (!less(e, arr[j])) {
                break;
            }
            arr[k] = arr[j];
            k = j;
        }
        arr[k] = e;
    }

    private static void swap(Object[] arr, int i, int j) {
        Object temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void main(String[] args) {
        int n = 1000000;
        int i = 10;
        long time = 0;
        while (i-- > 0) {
            Integer[] arr = SortTestHelper.generateRandomArray(n, 0, n);
            sort(arr);
            time += SortTestHelper.testSort2("org.two.four.learn2.HeapSort", arr);
        }

        System.out.println(time);
        // shiftDown1 （数据量100万，运行十次所需时间）:  3766 ms
        // shiftDown2 （数据量100万，运行十次所需时间）:  3082 ms
    }
}

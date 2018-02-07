package org.two.one.learn;

import org.tool.SortTestHelper;

/**
 * 算法补充2.1 冒泡排序
 *
 * @author cheng
 *         2018/1/23 15:24
 */
public class Bubble {

    public static void sort(Comparable[] arr) {
        int n = arr.length;
        boolean swapped;

        do {
            swapped = false;
            for (int i = 1; i < n; i++) {
                if (arr[i - 1].compareTo(arr[i]) > 0) {
                    swap(arr, i - 1, i);
                    swapped = true;
                }
            }
            // 优化，每一次Bubble Sort都将最大的元素放在了最后的位置
            // 所以下一次排序，最后的元素可以不再考虑
            n--;
        } while (swapped);
    }

    private static void swap(Comparable[] arr, int i, int j) {
        Comparable temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int n = 10000;
        Integer[] arr = SortTestHelper.generateRandomArray(n, 1, n);

        SortTestHelper.testSort("org.two.one.learn.Bubble", arr);
        System.out.println(SortTestHelper.isSorted(arr));
    }
}

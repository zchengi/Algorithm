package org.two.one.learn;

import org.tool.ArrayGenerator;
import org.tool.SortTestHelper;

/**
 * 算法2.1 选择排序优化
 * <p>
 * 在每一轮中，可以同时找到当前未处理元素的最大值和最小值，
 * 然后将最大值赋给arr[right]，最小值赋给arr[left]
 *
 * @author cheng
 *         2018/1/23 14:02
 */
public class SelectionFaster {

    public static void sort(Comparable[] arr) {

        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int minIndex = left;
            int maxIndex = right;

            // 在每一轮查找时，保证arr[minIndex] <= arr[maxIndex]
            if (arr[minIndex].compareTo(arr[maxIndex]) > 0) {
                exch(arr, minIndex, maxIndex);
            }

            for (int i = left + 1; i < right; i++) {
                if (arr[i].compareTo(arr[minIndex]) < 0) {
                    minIndex = i;
                } else if (arr[i].compareTo(arr[maxIndex]) > 0) {
                    maxIndex = i;
                }
            }
            exch(arr, left, minIndex);
            exch(arr, right, maxIndex);

            left++;
            right--;
        }
    }

    private static void exch(Comparable[] arr, int i, int j) {
        Comparable temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {

        int n = 20000;
        // 生成一个近乎有序的数组
        Integer[] arr = SortTestHelper.generateNearlyOrderedArray(n, 100000);
        Integer[] copy = ArrayGenerator.copy(arr);

        // 未改进的选择排序
        SortTestHelper.testSort("org.two.one.learn.Selection", arr);
        // 改进的选择排序
        SortTestHelper.testSort("org.two.one.learn.SelectionFaster", copy);
        // SortTestHelper.printArray(arr);
    }
}

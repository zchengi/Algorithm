package org.two.one.learn;

import org.tool.ArrayGenerator;
import org.tool.SortTestHelper;

/**
 * 算法补充2.1 冒泡排序优化
 *
 * 另一种写法，添加有序标记优化
 *
 * @author cheng
 *         2018/7/16 17:01
 */
public class BubbleFaster2 {
    public static void sort(Comparable[] arr) {

        Comparable<Object> tmp;
        // 记录最后一次交换的位置
        int lastExchangeIndex = 0;
        // 无序数列边界，每次比较只需要比到这里为止
        int sortBorder = arr.length - 1;

        for (int i = 0; i < arr.length; i++) {

            // 有序标记
            boolean isSorted = true;
            for (int j = 0; j < sortBorder; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    // 有元素交换，所有不是有序，标记变为false
                    isSorted = false;
                    // 把无序数列的边界更新为最后一次交换元素的为止
                    lastExchangeIndex = j;
                }
            }

            sortBorder = lastExchangeIndex;
            if (isSorted) {
                break;
            }
        }

    }

    private static void swap(Comparable[] arr, int i, int j) {
        Comparable temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {

        Integer[] arr = SortTestHelper.generateRandomArray(10000, 1, 100000);
        Integer[] copy = ArrayGenerator.copy(arr);
        Integer[] copy2 = ArrayGenerator.copy(arr);
        Integer[] copy3 = ArrayGenerator.copy(arr);

        sort(copy3);

        SortTestHelper.testSort("org.two.one.learn.Bubble", arr);
        SortTestHelper.testSort("org.two.one.learn.BubbleFaster", copy);
        SortTestHelper.testSort("org.two.one.learn.BubbleFaster2", copy2);
    }
}

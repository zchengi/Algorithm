package org.two.one.learn;

import org.tool.ArrayGenerator;
import org.tool.SortTestHelper;

/**
 * 算法补充2.1 冒泡排序优化
 *
 * @author cheng
 *         2018/7/16 17:01
 */
public class BubbleFaster3 {

    public static void sort(Comparable[] arr) {

        int n = arr.length;
        // 使用newN优化
        int newN;

        do {
            newN = 0;
            for (int i = 1; i < n; i++) {
                if (arr[i - 1].compareTo(arr[i]) > 0) {
                    swap(arr, i - 1, i);

                    // 记录最后一次的交换位置，在此之后的元素在下一轮扫描中均不考虑
                    // 与Bubble中 n-- 效果一样
                    newN = i;
                }
            }
            n = newN;
        } while (newN > 0);
    }

    private static void swap(Comparable[] arr, int i, int j) {
        Comparable temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {

        Integer[] arr = SortTestHelper.generateRandomArray(10000, 1, 100000);
        Integer[] copy = ArrayGenerator.copy(arr);

        SortTestHelper.testSort("org.two.one.learn.Bubble", arr);
        SortTestHelper.testSort("org.two.one.learn.BubbleFaster", copy);
    }
}

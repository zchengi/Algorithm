package org.two.one.learn;

import org.tool.SortTestHelper;

import java.util.Arrays;

/**
 * 算法比较
 *
 * @author cheng
 *         2018/2/2 23:36
 */
public class SortCompare1 {
    /**
     * 比较 SelectionSort, InsertionSort 和 BubbleSort 和 ShellSort 四种排序算法的性能效率
     * ShellSort 是这四种排序算法中性能最优的排序算法
     */
    public static void main(String[] args) {

        int n = 20000;

        // 测试1 一般测试
        System.out.println("Test for random array, size = " + n + " , random range [0, " + n + "]");

        Integer[] arr1 = SortTestHelper.generateRandomArray(n, 0, n);
        Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);
        Integer[] arr3 = Arrays.copyOf(arr1, arr1.length);
        Integer[] arr4 = Arrays.copyOf(arr1, arr1.length);

        SortTestHelper.testSort("org.two.one.learn.SelectionFaster", arr1);
        SortTestHelper.testSort("org.two.one.learn.InsertionFaster", arr2);
        SortTestHelper.testSort("org.two.one.learn.BubbleFaster", arr3);
        SortTestHelper.testSort("org.two.one.learn.Shell", arr4);
        System.out.println();

        // 测试2 测试近乎有序的数组
        int swapTimes = 100;
        System.out.println("Test for nearly ordered array, size = " + n + " , swap time = " + swapTimes);

        arr1 = SortTestHelper.generateNearlyOrderedArray(n, swapTimes);
        arr2 = Arrays.copyOf(arr1, arr1.length);
        arr3 = Arrays.copyOf(arr1, arr1.length);
        arr4 = Arrays.copyOf(arr1, arr1.length);

        SortTestHelper.testSort("org.two.one.learn.SelectionFaster", arr1);
        SortTestHelper.testSort("org.two.one.learn.InsertionFaster", arr2);
        SortTestHelper.testSort("org.two.one.learn.BubbleFaster", arr3);
        SortTestHelper.testSort("org.two.one.learn.Shell", arr4);
    }
}

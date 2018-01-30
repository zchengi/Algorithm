package org.two.four.learn2;

import org.tool.SortTestHelper;

import java.util.Arrays;

/**
 * Merge Sort,三种Quick Sort 和 三种Heap Sort 的性能效率
 * 注意：这几种排序算法都是O(nlogn)级别的排序算法
 *
 * @author cheng
 *         2018/1/30 12:08
 */
public class SortCompare5 {
    /**
     * 可以看出，最终优化的原地堆排序比其他两种都快。
     * 这里因为Java语言由于JVN内部机制的因素，测量的性能时间可能是跳跃不稳定的。
     */
    public static void main(String[] args) {
        int n = 1000000;

        // 测试1 一般性能测试
        System.out.println("Test for random array, size = " + n + " , random range [0, " + n + "]");

        Integer[] arr1 = SortTestHelper.generateRandomArray(n, 0, n);
        Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);
        Integer[] arr3 = Arrays.copyOf(arr1, arr1.length);
        Integer[] arr4 = Arrays.copyOf(arr1, arr1.length);
        Integer[] arr5 = Arrays.copyOf(arr1, arr1.length);
        Integer[] arr6 = Arrays.copyOf(arr1, arr1.length);
        Integer[] arr7 = Arrays.copyOf(arr1, arr1.length);


        SortTestHelper.testSort("org.two.two.learn.Merge", arr1);
        SortTestHelper.testSort("org.two.three.learn.Quick", arr2);
        SortTestHelper.testSort("org.two.three.learn.Quick2Ways", arr3);
        SortTestHelper.testSort("org.two.three.learn.Quick3Ways", arr4);
        SortTestHelper.testSort("org.two.four.learn2.HeapSort1", arr5);
        SortTestHelper.testSort("org.two.four.learn2.HeapSort2", arr6);
        SortTestHelper.testSort("org.two.four.learn2.HeapSort", arr7);

        System.out.println();

        //测试2 测试近乎有序的数组
        int swapTimes = 100;
        assert swapTimes >= 0;

        System.out.println("Test for nearly ordered array, size = " + n + " , swap time = " + swapTimes);

        arr1 = SortTestHelper.generateNearlyOrderedArray(n, swapTimes);
        arr2 = Arrays.copyOf(arr1, arr1.length);
        arr3 = Arrays.copyOf(arr1, arr1.length);
        arr4 = Arrays.copyOf(arr1, arr1.length);
        arr5 = Arrays.copyOf(arr1, arr1.length);
        arr6 = Arrays.copyOf(arr1, arr1.length);
        arr7 = Arrays.copyOf(arr1, arr1.length);

        SortTestHelper.testSort("org.two.two.learn.Merge", arr1);
        SortTestHelper.testSort("org.two.three.learn.Quick", arr2);
        SortTestHelper.testSort("org.two.three.learn.Quick2Ways", arr3);
        SortTestHelper.testSort("org.two.three.learn.Quick3Ways", arr4);
        SortTestHelper.testSort("org.two.four.learn2.HeapSort1", arr5);
        SortTestHelper.testSort("org.two.four.learn2.HeapSort2", arr6);
        SortTestHelper.testSort("org.two.four.learn2.HeapSort", arr7);

        System.out.println();

        //测试2 测试存在包含大量相同元素的数组
        System.out.println("Test for random array, size = " + n + " , random range [0,10]");

        arr1 = SortTestHelper.generateRandomArray(n, 0, 10);
        arr2 = Arrays.copyOf(arr1, arr1.length);
        arr3 = Arrays.copyOf(arr1, arr1.length);
        arr4 = Arrays.copyOf(arr1, arr1.length);
        arr5 = Arrays.copyOf(arr1, arr1.length);
        arr6 = Arrays.copyOf(arr1, arr1.length);
        arr7 = Arrays.copyOf(arr1, arr1.length);

        SortTestHelper.testSort("org.two.two.learn.Merge", arr1);
        // 这种情况下, 普通的 QuickSort 退化为 O(n^2) 的算法, 不做测试
        // SortTestHelper.testSort("org.two.three.learn.Quick", arr2);
        SortTestHelper.testSort("org.two.three.learn.Quick2Ways", arr3);
        SortTestHelper.testSort("org.two.three.learn.Quick3Ways", arr4);
        SortTestHelper.testSort("org.two.four.learn2.HeapSort1", arr5);
        SortTestHelper.testSort("org.two.four.learn2.HeapSort2", arr6);
        SortTestHelper.testSort("org.two.four.learn2.HeapSort", arr7);

    }
}

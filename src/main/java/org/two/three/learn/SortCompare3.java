package org.two.three.learn;

import org.tool.SortTestHelper;

import java.util.Arrays;

/**
 * 算法性能比较
 *
 * @author cheng
 *         2018/1/23 21:43
 */
public class SortCompare3 {

    // 比较Merge Sort和Quick Sort两种排序算法的性能效率
    // 两种排序算法虽然都是O(nlogn)级别的，但是Quick Sort算法有常数级的优势
    // Quick Sort要比Merge Sort快，即使我们对Merge Sort进行了优化

    // 比较Merge Sort和Quick Sort 2 Ways两种排序算法的性能效率

    // 比较Merge Sort和双路快速排序和三路快排三种排序算法的性能效率
    // 对于包含有大量重复数据的数组, 三路快排有巨大的优势
    // 对于一般性的随机数组和近乎有序的数组, 三路快排的效率虽然不是最优的, 但是是在非常可以接受的范围里
    // 因此, 在一些语言中, 三路快排是默认的语言库函数中使用的排序算法。比如Java
    public static void main(String[] args) {

        int n = 1000000;

        // 测试1 一般性测试
        System.out.println("Test for random array, size = " + n + ", random range = [0, " + n + "]");

        Integer[] arr1 = SortTestHelper.generateRandomArray(n, 0, n);
        Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);
        Integer[] arr3 = Arrays.copyOf(arr1, arr1.length);
        Integer[] arr4 = Arrays.copyOf(arr1, arr1.length);

        SortTestHelper.testSort("org.two.two.learn.Merge", arr1);
        SortTestHelper.testSort("org.two.three.learn.Quick", arr2);
        SortTestHelper.testSort("org.two.three.learn.Quick2Ways", arr3);
        SortTestHelper.testSort("org.two.three.learn.Quick3Ways", arr4);


        // 测试2 测试近乎有序的数组
        // 但是对于近乎有序的数组，快速排序算法退化成了O(n^2)级别的算法
        // 思考为什么对于近乎有序的数组，快速排序退化成O(n^2)的算法？
        int swapTimes = 100;
        assert swapTimes >= 0;

        System.out.println("Test for nearly ordered array, size = " + n + ", swap time = " + swapTimes);

        arr1 = SortTestHelper.generateNearlyOrderedArray(n, swapTimes);
        arr2 = Arrays.copyOf(arr1, arr1.length);
        arr3 = Arrays.copyOf(arr1, arr1.length);
        arr4 = Arrays.copyOf(arr1, arr1.length);

        SortTestHelper.testSort("org.two.two.learn.Merge", arr1);
        SortTestHelper.testSort("org.two.three.learn.Quick", arr2);
        SortTestHelper.testSort("org.two.three.learn.Quick2Ways", arr3);
        SortTestHelper.testSort("org.two.three.learn.Quick3Ways", arr4);


        // 测试3 测试存在包含大量相同元素的数组
        // 但此时，对于含有大量相同元素的数组，快速排序算法再次退化成O(n^2)级别的算法
        // 思考为什么会这样？
        System.out.println("Test for random array, size = " + n + " , random range = [0,10]");

        arr1 = SortTestHelper.generateRandomArray(n, 0, 10);
        arr2 = Arrays.copyOf(arr1, arr1.length);
        arr3 = Arrays.copyOf(arr1, arr1.length);

        SortTestHelper.testSort("org.two.two.learn.Merge", arr1);
        // SortTestHelper.testSort("org.two.three.learn.Quick", arr2);
        // 使用双路快速排序后, 快速排序算法可以轻松的处理包含大量元素的数组
        SortTestHelper.testSort("org.two.three.learn.Quick2Ways", arr2);
        SortTestHelper.testSort("org.two.three.learn.Quick3Ways", arr3);
    }
}

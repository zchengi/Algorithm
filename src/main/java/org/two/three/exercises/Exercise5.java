package org.two.three.exercises;

import org.tool.SortTestHelper;

import java.util.Arrays;

/**
 * 2.3.4 给出一段代码将已知有两种主键值的数组排序。
 *
 * @author cheng
 *         2018/1/24 21:18
 */
public class Exercise5 {


    // 对于有大量重复元素的快速排序，使用三向切分的快速排序效率最好
    public static void main(String[] args) {

        int n = 100000;
        Integer[] arr1 = SortTestHelper.generateRandomArray(n, 0, 10);
        Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);

        // 3路快速排序
        SortTestHelper.testSort("org.two.three.learn.Quick3Ways", arr1);
        // 2路快速排序
        SortTestHelper.testSort("org.two.three.learn.Quick2Ways", arr2);

    }
}

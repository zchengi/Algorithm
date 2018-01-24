package org.two.two.learn;

import org.tool.SortTestHelper;

import java.util.Arrays;

/**
 * 算法比较
 *
 * @author cheng
 *         2018/1/24 10:42
 */
public class SortCompare2 {

    /**
     * 比较Merge Sort 和 Merge Sort BU 的性能效率
     * 使用更科学的比较方式，每次比较都运行多次测试用例，取平均值
     * 这里只比较优化后的情况
     * 总体来说，Merge Sort BU  比 Merge Sort 快一些。但优化后，二者性能差距不明显
     * 详细分析：http://coding.imooc.com/learn/questiondetail/3208.html
     */
    public static void main(String[] args) {

        // 测试T个测试用例，每个测试用例的数组大小为n
        int t = 100;
        int n = 1000000;

        // 比较Merge Sort 和 Merge Sort BU 两种算法在含优化的情况下的性能效率
        long time1 = 0;
        long time2 = 0;
        for (int i = 0; i < t; i++) {
            Integer[] arr1 = SortTestHelper.generateRandomArray(n, 0, n);
            Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);

            time1 += SortTestHelper.testSort2("org.two.two.learn.Merge", arr1);
            time2 += SortTestHelper.testSort2("org.two.two.learn.MergeBU", arr2);
        }

        System.out.println("With Optimization:");
        System.out.println("Merge Sort    Average Run Time: " + time1 / t + " ms");
        System.out.println("Merge Sort BU Average Run Time: " + time2 / t + " ms");

//        With Optimization:
//        Merge Sort Average Run Time: 456 ms
//        Merge Sort BU Average Run Time: 523 ms
    }
}

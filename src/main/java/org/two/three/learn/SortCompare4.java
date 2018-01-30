package org.two.three.learn;

import org.tool.SortTestHelper;

import java.util.Arrays;

/**
 * ShellSort, MergeSort 和 QuickSort 的比较
 *
 * @author cheng
 *         2018/1/24 12:03
 */
public class SortCompare4 {
    // 比较 Shell Sort 和 Merge Sort 和 三种 Quick Sort 的性能效率
    // 使用更科学的比较方式, 每次比较都运行多次测试用例, 取平均值
    // 可以看出, Shell Sort虽然慢于高级的排序方式, 但仍然是非常有竞争力的一种排序算法
    // 其所花费的时间完全在可以容忍的范围内, 远不像O(n^2)的排序算法, 在数据量较大的时候无法忍受
    // 同时, Shell Sort实现简单, 只使用循环的方式解决排序问题, 不需要实现递归, 不占用系统占空间, 也不依赖随机数
    // 所以, 如果算法实现所使用的环境不利于实现复杂的排序算法, 或者在项目工程的测试阶段, 完全可以暂时使用Shell Sort来进行排序任务
    public static void main(String[] args) {
        // 测试t个测试用例，每个测试用例的数组大小为n
        int t = 100;
        int n = 1000000;

        long time1 = 0, time2 = 0, time3 = 0, time4 = 0, time5 = 0;
        for (int i = 0; i < t; i++) {
            Integer[] arr1 = SortTestHelper.generateRandomArray(n, 0, n);
            Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);
            Integer[] arr3 = Arrays.copyOf(arr1, arr1.length);
            Integer[] arr4 = Arrays.copyOf(arr1, arr1.length);
            Integer[] arr5 = Arrays.copyOf(arr1, arr1.length);

            // time1 += SortTestHelper.testSort2("org.two.one.learn.Shell", arr1);
            time2 += SortTestHelper.testSort2("org.two.two.learn.Merge", arr2);
            time3 += SortTestHelper.testSort2("org.two.three.learn.Quick", arr3);
            time4 += SortTestHelper.testSort2("org.two.three.learn.Quick2Ways", arr4);
            time5 += SortTestHelper.testSort2("org.two.three.learn.Quick3Ways", arr5);
        }

        System.out.println("Sorting " + n + " elements " + t + " times. Calculate the average run time.");
        System.out.println("Shell Sort        Average Run Time: " + time1 / t + " ms");
        System.out.println("Merge Sort        Average Run Time: " + time2 / t + " ms");
        System.out.println("Quick Sort        Average Run Time: " + time3 / t + " ms");
        System.out.println("Quick Sort 2 Ways Average Run Time: " + time4 / t + " ms");
        System.out.println("Quick Sort 3 Ways Average Run Time: " + time5 / t + " ms");
//        Sorting 1000000 elements 100 times. Calculate the average run time.
//        Shell Sort        Average Run Time: 0 ms   因为希尔排序太慢 ，这里没有测试
//        Merge Sort        Average Run Time: 392 ms
//        Quick Sort        Average Run Time: 379 ms
//        Quick Sort 2 Ways Average Run Time: 319 ms
//        Quick Sort 3 Ways Average Run Time: 497 ms

    }
}

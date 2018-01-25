package org.two.three.exercises;


import org.tool.SortTestHelper;

/**
 * 2.3.11 假如在遇到和切分元素重复的元素时我们继续扫描数组而不是停下来，
 * 证明使用这种方法的快速排序在处理只有若干种元素值的数组时的运行时间是平方级别。
 *
 * 未证明，算法不同。
 *
 * @author cheng
 *         2018/1/25 22:40
 */
public class Exercise11 {


    public static void main(String[] args) {

        int n = 1000;
        double pre = 0;
        for (int i = 0; i < 5; i++, n *= 2) {
            Integer[] arr = SortTestHelper.generateRandomArray(n, 1, 3);
            long time = SortTestHelper.testSort2("org.two.three.learn.Quick", arr);
            System.out.println("规模：" + n + " 耗时: " + (double)time/1000 + "s 倍率：" + time / pre);
            pre = time;
        }

    }
}

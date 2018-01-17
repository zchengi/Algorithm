package org.two.one.exercises;

import edu.princeton.cs.algs4.StdRandom;

/**
 * 2.1.12 令希尔排序打印出递增序列的每个元素所带来的比较次数和数组大小的比值。
 * 编写一个测试用例对随机Double数组进行希尔排序，验证该值是一个小常数，数组大
 * 小按照10的幂次递增，不小于100。
 *
 * @author cheng
 *         2018/1/17 10:52
 */
public class Exercise12 {
    public static void main(String[] args) {
        // 查看最后结果
        // 可以发现相同的 h 在数组大小不同时所产生的比值十分接近。
        int size = 100;
        for (int i = 0; i < 5; i++) {
            Double[] a = new Double[size];
            for (int j = 0; j < size; j++) {
                a[j] = StdRandom.uniform() * 100;
            }
            System.out.println("ArraySize: " + size);
            Exercise11.sort(a);
            size *= 10;
        }
    }
}

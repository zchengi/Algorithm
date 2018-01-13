package org.one.day14;

import java.util.Arrays;

/**
 * 1.4.16 最接近的一堆（一维）
 * 编写一个程序，给定一个含有N个double值的数组a[]，在其中找到一对最接近的值：
 * 两者之差（绝对值）最小的两个数。
 * 程序最坏的情况下所需的运行时间应该是线性对数级别的。
 *
 * @author cheng
 *         2018/1/13 22:19
 */
public class Example16 {
    public static void closestPairFaster(double[] a) {
        Arrays.sort(a);
        double minNum = Double.MAX_VALUE;
        int minI = 0;
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i + 1] - a[i] < minNum) {
                minI = i;
                minNum = a[i + 1] - a[i];
            }
        }
        System.out.println("最接近的两个数为：" + a[minI] + "和" + a[minI + 1]);
    }

    public static void main(String[] args) {
        double[] a = {1, 45, 994, 878, 76, -45};
        closestPairFaster(a);
    }
}

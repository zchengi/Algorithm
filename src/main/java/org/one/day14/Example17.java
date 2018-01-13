package org.one.day14;

import java.util.Arrays;

/**
 * 1.4.17 最遥远的一堆（一维）
 * 编写一个程序，给定一个含有N个double值的数组a[]，在其中找到一对最遥远的值：
 * 两者之差（绝对值）最大的两个数。
 * 程序最坏的情况下所需的运行时间应该是线性对数级别的。
 *
 * @author cheng
 *         2018/1/13 22:19
 */
public class Example17 {
    public static void theMostDistantCouple(double[] a) {
        Arrays.sort(a);
        double maxNum = Double.MIN_VALUE;
        double minNum = Double.MAX_VALUE;
        for (int i = 0; i < a.length; i++) {
            if (a[i] >= maxNum) {
                maxNum = a[i];
            }
            if (a[i] < minNum) {
                minNum = a[i];
            }
        }
        System.out.println("最遥远的两个数为：" + minNum + "和" + maxNum);
    }

    public static void main(String[] args) {
        double[] a = {1, 45, 994, 878, 76, 45};
        theMostDistantCouple(a);
    }
}

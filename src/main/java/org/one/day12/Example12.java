package org.one.day12;

import java.util.Arrays;

/**
 * 1.4.12 编写一个程序，有序打印给定的两个有序数组（含有N个int值）中的所有公共元素，
 * 程序在最坏情况下所需的运行时间应该和N成正比。
 *
 * @author cheng
 *         2018/1/3 12:11
 */
public class Example12 {
    public static void sameElements(int a[], int b[]) {
        int count=0;
        for (int i = 0, j = 0; i < a.length && j < b.length; ) {
            if (a[i] < b[j]) {
                i++;
            } else if (a[i] > b[j]) {
                j++;
            } else {
                System.out.println("" + a[i]);
                i++;
                j++;
                count++;
            }
        }
        System.out.println(count);
    }

    public static void main(String[] args) {
        int[] b1 = { 1, 2, 3, 5, 4, 5, 6, 77, 7, 8, 8, 9, 1, 11, 22, 234, 90,
                234, 345 };
        int[] b2 = { 1, 1, 1, 1, 1, 1, 1, 3, 1, 1, 345, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 1, 234, 1 };
        Arrays.sort(b1);
        Arrays.sort(b2);
        sameElements(b1, b2);
    }
}

package org.two.one.learn;

import org.tool.ArrayGenerator;

import java.util.Arrays;

/**
 * 插入排序另一种实现
 *
 * @author cheng
 *         2018/4/25 11:09
 */
public class Insertion2 {

    public static void sort(int[] arr) {

        int n = arr.length - 1;
        for (int i = 0, j = i; i < n; j = ++i) {
            int temp = arr[j + 1];

            while (temp < arr[j]) {
                arr[j + 1] = arr[j];
                if (j-- == 0) {
                    break;
                }
            }
            arr[j + 1] = temp;
        }
    }

    public static void main(String[] args) {

        int[] arr = ArrayGenerator.ints(20, 10, 100);
        Insertion2.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}

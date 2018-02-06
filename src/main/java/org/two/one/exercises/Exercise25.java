package org.two.one.exercises;

import org.one.day9.StopWatch;
import org.tool.ArrayGenerator;

/**
 * 2.1.25 不需要交换的插入排序。在插入排序的实现中使较大元素右移一位只需要访问一次数组（而不使用exch()）。
 * 使用SortCompare来评估这种做法。
 *
 * @author cheng
 *         2018/1/20 21:40
 */
public class Exercise25 {
    public static double insertion_A(int[] a) {
        StopWatch timer = new StopWatch();
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int t = a[i];
            int j;
            for (j = i - 1; j >= 0 && t < a[j]; j--) {
                a[j + 1] = a[j];
            }
            a[j + 1] = t;
        }
        return timer.elapsedTime();
    }

    public static double insertion_B(int[] a) {
        StopWatch timer = new StopWatch();
        int n = a.length;
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0 && a[j - 1] > a[j]; j--) {
                int temp = a[j];
                a[j] = a[j - 1];
                a[j - 1] = temp;
            }
        }
        return timer.elapsedTime();
    }

    public static void main(String[] args) {
        int[] array = ArrayGenerator.ints(1, 100000);
        int[] copy = ArrayGenerator.copy(array);

        System.out.println("不交换： "+ insertion_A(copy));
        System.out.println("交换： "+ insertion_B(array));
    }
}

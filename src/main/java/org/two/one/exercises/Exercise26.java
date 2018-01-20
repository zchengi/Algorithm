package org.two.one.exercises;


import org.one.day9.StopWatch;
import org.tool.ArrayGenerator;

/**
 * 2.1.26 原始数据类型。编写一个能够处理int值的插入排序的新版本，比较它和正文中所给出的实现
 * （能够隐式地用自动装箱和拆箱转换Integer值并排序）的性能。
 *
 * @author cheng
 *         2018/1/20 21:56
 */
public class Exercise26 {
    public static double shell(int[] a) {
        StopWatch timer = new StopWatch();
        int n = a.length;
        int h = 1;
        while (h < n / 3) h = h * 3 + 1;
        while (h >= 1) {
            for (int i = h; i < n; i++) {
                int temp = a[i];
                int j;
                for (j = i - h; j >= 0 && a[j + h] < a[j]; j -= h) {
                    a[j + h] = a[j];
                }
                a[j + h] = temp;
            }
            h /= 3;
        }
        return timer.elapsedTime();
    }

    public static double shell(Integer[] a) {
        StopWatch timer = new StopWatch();
        int n = a.length;
        int h = 1;
        while (h < n / 3) h = n * 3 + 1;
        while (h >= 1) {
            for (int i = h; i < n; i++) {
                int temp = a[i];
                int j;
                for (j = i - h; j >= 0 && a[j + h].compareTo(a[j]) < 0; j -= h) {
                    a[j + h] = a[j];
                }
                a[j + h] = temp;
            }
            h /= 3;
        }
        return timer.elapsedTime();
    }

    public static void main(String[] args) {
        Integer[] array = ArrayGenerator.Integers(0, 4000000);
        int[] copy = ArrayGenerator.IntegerToInt(array);

        System.out.println("基本数据类型： " + shell(copy));
        System.out.println("包装类： " + shell(array));
    }
}

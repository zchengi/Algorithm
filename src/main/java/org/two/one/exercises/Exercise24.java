package org.two.one.exercises;

import org.one.day9.StopWatch;
import org.tool.ArrayGenerator;


/**
 * 2.1.24 插入排序的哨兵。在插入排序的实现中先找出最小的元素并将其置于数组的最左边，
 * 这样就能去掉内循环的判断条件j>0。使用SortCompare来评估这种做法的效果。
 * 注意：这是一个常见的规避边界测试的方法，能够省略判断条件的元素通常被称为哨兵。
 *
 * @author cheng
 *         2018/1/20 20:15
 */
public class Exercise24 {

    private static double insertion_A(int[] a) {
        StopWatch timer = new StopWatch();
        int n = a.length;
        int min = Integer.MAX_VALUE, minIndex = 0;
        for (int i = 0; i < a.length; i++)
            if (a[i] < min) {
                min = a[i];
                minIndex = i;
            }
        int temp = a[0];
        a[0] = a[minIndex];
        a[minIndex] = temp;
        for (int i = 2; i < n; i++) {
            for (int j = i; a[j] < a[j - 1]; j--) {
                temp = a[j];
                a[j] = a[j - 1];
                a[j - 1] = temp;
            }
        }
        return timer.elapsedTime();
    }

    private static double insertion_B(int[] a) {
        StopWatch timer = new StopWatch();
        int n = a.length;
        for (int i = 1; i < n; i++)
            for (int j = i; j > 0 && a[j] < a[j - 1]; j--) {
                int t = a[j];
                a[j] = a[j - 1];
                a[j - 1] = t;
            }
        return timer.elapsedTime();
    }

    public static void main(String[] args) {
        // 当数组过大时，出现异常 运行时间与代码顺序有关 50000 100000
        int[] array = ArrayGenerator.ints(1, 50000);
        int[] copy = ArrayGenerator.copy(array);

        // 当数组大小为是100000时，下面两行代码，谁在前面谁慢，暂不知原因。
        double timer1 = insertion_A(copy);
        double timer2 = insertion_B(array);
        System.out.println("哨兵法： " + timer1);
        System.out.println("普通法： " + timer2);
    }
}


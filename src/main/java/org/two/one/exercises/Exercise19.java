package org.two.one.exercises;

import java.util.Arrays;

import static org.tool.ArrayGenerator.copy;
import static org.tool.ArrayGenerator.ints;


/**
 * 2.1.19 希尔排序的最坏情况。用1到100构造一个含有100个元素的数组并且用希尔排序
 * 和递增序列1 4 13 40 对其排序，使比较次数尽可能多。
 * <p>
 * 2.1.20 希尔排序的最好情况。最好情况是什么？证明你的结论。
 *
 * @author cheng
 *         2018/1/19 13:46
 */
public class Exercise19 {

    private static int compares;

    public static void shell(int[] a) {
        int n = a.length;
        int h = 1;
        while (h < n / 3) h = h * 3 + 1;
        while (h >= 1) {
            for (int i = h; i < n; i++) {
                int temp = a[i];
                int j;
                for (j = i - h; j >= 0 && less(temp, a[j]); j -= h) {
                    a[j + h] = a[j];
                }
                a[j + h] = temp;
            }
            h /= 3;
        }
    }

    private static boolean less(int a, int b) {
        compares++;
        return a < b;
    }

    public static void main(String[] args) {
        int[] arr;
        int[] copy;
        while (true) {
            compares = 0;
            arr = ints(1, 10);
            copy = copy(arr);
            shell(new int[]{1,2,3,4,5,6});
            if (compares > 0) break;
        }
        System.out.println("compares: " + compares);
        System.out.println(Arrays.toString(copy));
    }

    /*
     * 供参考的最差情况为 n^(3/2)
     *
     * 最好情况：
     *      已经排序的数组；
     *      希尔排序同样是拆分成若干次排序，显然最好情况是已经排序的数组
     */
}

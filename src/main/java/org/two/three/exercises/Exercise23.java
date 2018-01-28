package org.two.three.exercises;

import org.one.day9.StopWatch;
import org.tool.ArrayGenerator;

import java.util.Arrays;

/**
 * 2.3.23 Java的排序库函数。在练习2.3.22的代码中使用Tukey's ninther 方法来找出切分元素——选择3组，
 * 每组三个元素，分别取三族元素的中位数，然后取三个中位数的中位数作为切分元素，切在排序小数组时切换到插入排序。
 *
 * @author cheng
 *         2018/1/28 19:51
 */
public class Exercise23 {
    public static double _quick(int[] a) {
        StopWatch timer = new StopWatch();
        _quick(a, 0, a.length - 1);
        return timer.elapsedTime();
    }

    private static void _quick(int[] a, int lo, int hi) {
        if (lo >= hi) return;
        int i = lo;
        int j = hi + 1;
        int v = a[lo];
        while (true) {
            while (i < hi && a[++i] < v) ;
            while (a[--j] > v) ;
            if (i >= j) break;
            int t = a[i];
            a[i] = a[j];
            a[j] = t;
        }
        int t = a[lo];
        a[lo] = a[j];
        a[j] = t;
        _quick(a, lo, j - 1);
        _quick(a, j + 1, hi);
    }

    public static double __quick(int[] a) {
        StopWatch timer = new StopWatch();
        __quick(a, 0, a.length - 1);
        return timer.elapsedTime();
    }

    private static void __quick(int[] a, int lo, int hi) {
        if (hi - lo + 1 < 9) {
            for (int i = lo; i <= hi; i++) {
                int t = a[i];
                int j;
                for (j = i - 1; j >= 0 && t < a[j]; j--) {
                    a[j + 1] = t;
                }
                a[j + 1] = t;
            }
            return;
        }

        int i = lo;
        int j = hi + 1;
        int v = a[lo];
        while (true) {
            while (i < hi && a[++i] < v) ;
            while (a[--j] > v) ;
            if (i >= j) break;
            int t = a[i];
            a[i] = a[j];
            a[j] = t;
        }
        int t = a[lo];
        a[lo] = a[j];
        a[j] = t;
        __quick(a, lo, j - 1);
        __quick(a, j + 1, hi);
    }

    public static double quick(int[] a) {
        StopWatch timer = new StopWatch();
        quick(a, 0, a.length - 1);
        return timer.elapsedTime();
    }

    private static void quick(int[] a, int lo, int hi) {
        if (hi - lo + 1 < 9) {
            for (int i = lo; i <= hi; i++) {
                int t = a[i];
                int j;
                for (j = i - 1; j >= 0 && a[j] > t; j--) {
                    a[j + 1] = a[j];
                }
                a[j + 1] = t;
            }
            return;
        }

        int interval = (hi - lo + 1) >>> 3;
        int e5 = (lo + hi) >>> 1;
        int e1 = lo;
        int e9 = hi;
        int e2 = e1 + interval;
        int e3 = e2 + interval;
        int e4 = e3 + interval;
        int e8 = e9 - interval;
        int e7 = e8 - interval;
        int e6 = e7 - interval;

        int m1 = Math.min(Math.max(a[e1], a[e2]), a[e3]);
        m1 = m1 == a[e1] ? e1 : m1 == a[e2] ? e2 : e3;
        int m2 = Math.min(Math.max(a[e4], a[e5]), a[e6]);
        m2 = m2 == a[e4] ? e4 : m2 == a[e5] ? e5 : e6;
        int m3 = Math.min(Math.max(a[e7], a[e8]), a[e9]);
        m3 = m3 == a[e7] ? e7 : m3 == a[e8] ? e8 : e9;
        int median = Math.min(Math.max(a[m1], a[m2]), a[m3]);
        median = median == a[m1] ? m1 : median == a[m2] ? m2 : m3;

        int t = a[median];
        a[median] = a[lo];
        a[lo] = t;

        int v = a[lo];
        int i = lo;
        int j = hi + 1;

        while (true) {
            while (i < hi && a[++i] < v) ;
            while (a[--j] > v) ;
            if (i >= j) break;
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
        int te = a[lo];
        a[lo] = a[j];
        a[j] = te;

        quick(a, lo, j - 1);
        quick(a, j + 1, hi);
    }

    public static void main(String[] args) {
        int[] a1 = ArrayGenerator.ints(0, 4000000);
        // int[] a = allSameInts(4000000, 0);
        // int[] a = ascendInts(0, 10000000);
        // int[] a = descendInts(1000000, 0);
        // int[] a = intsVrg(10000000, 1, 2, 3, 4);
        int[] a2 = Arrays.copyOf(a1, a1.length);
        int[] a3 = Arrays.copyOf(a1, a1.length);

        System.out.println("Tukey's ninther : " + quick(a1));
        System.out.println("普通快排 : " + _quick(a2));
        System.out.println("对照 : " + __quick(a3));

        System.out.println(ArrayGenerator.isSorted(a1));
        System.out.println(ArrayGenerator.isSorted(a2));
        System.out.println(ArrayGenerator.isSorted(a3));
    }
     /*
     *
     *  升序序列
     *
     *  栈溢出
     *  栈溢出
     *  Tukey's ninther : 0.561
     *
     *  降序序列
     *
     *  栈溢出
     *  栈溢出
     *  Tukey's ninther : 0.222
     *  全部元素相同
     *
     *  Tukey's ninther : 0.238
     *  对照 : 0.376
     *  普通快排 : 0.354
     *
     *  乱序无重复元素
     *
     *  Tukey's ninther : 0.913
     *  对照 : 0.989
     *  普通快排 : 1.154
     *  大量重复元素
     *
     *  对照 : 0.962
     *  普通快排 : 1.432
     *  Tukey's ninther : 1.096
     *
     */
}

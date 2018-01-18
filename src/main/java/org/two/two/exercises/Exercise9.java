package org.two.two.exercises;

import edu.princeton.cs.algs4.StdRandom;
import org.two.one.learn.Example;
import org.two.one.learn.Insertion;

/**
 * 2.2.9  在库函数中使用aux[]这样的静态数组是不妥当的，因为可能会有多个程序同时使用这个类。
 * 实现一个不用静态数组的Merge类，但也不要将aux[]变为merge()的局部变量。
 * 提示： 可以将辅助数组作为参数传递给递归的sort()方法。
 * <p>
 * 2.2.11 改进。实现2.2.2节所述的对归并排序的三项改进：加快小数组排序速度，检测数组是否已经有序
 * 以及通过在递归中交换参数来避免数组复制。
 *
 * @author cheng
 *         2018/1/18 18:00
 */
public class Exercise9 extends Example {
    public static void sort(Comparable[] a) {
        Comparable[] aux = a.clone();
        sort(a, aux, 0, a.length - 1, true);
    }

    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi, boolean ok) {
        // 优化1
        if (hi - lo <= 10) {
            Insertion.sort(a, lo, hi);
            return;
        }

        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid, !ok);
        sort(a, aux, mid + 1, hi, !ok);

        // if (less(a[mid], a[mid + 1])) return;  //优化2 无法使用，因为每次必须交换两个数组，必须要merge

        // 优化3 交替使用两个数组
        if (ok) merge(a, aux, lo, mid, hi);
        else merge(aux, a, lo, mid, hi);
    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        // aux --> a
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[i], aux[j])) a[k] = aux[i++];
            else a[k] = aux[j++];
        }
    }

    public static void main(String[] args) {
        int n = 100;
        Integer[] a = new Integer[n];
        for (int i = 0; i < n; i++) {
            a[i] = i;
        }
        StdRandom.shuffle(a);

        show(a);
        sort(a);
        show(a);
        assert isSorted(a);
    }
}

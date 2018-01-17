package org.two.two.exercises;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;
import org.two.one.learn.Example;

/**
 * 2.2.6 编写一个程序来计算自顶向下和自底向上的归并排序访问数组的准确次数。
 * 使用这个程序将N=1至512的结果绘成曲线图，并将其和上限6NlgN比较。
 *
 * @author cheng
 *         2018/1/17 19:34
 */
public class Exercise6 extends Example {

    /**
     * 访问数组的次数
     */
    private static int count = 0;

    private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    /**
     * 自顶向下
     */
    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }

    /**
     * 自底向上
     */
    private static void sort2(Comparable[] a) {
        int n = a.length;
        aux = new Comparable[n];
        for (int sz = 1; sz < n; sz += sz) {
            for (int lo = 0; lo + sz < n; lo += sz + sz) {
                merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, n - 1));
            }
        }
    }

    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
            count++;
        }
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[i], aux[j])) a[k] = aux[i++];
            else a[k] = aux[j++];
            count++;
        }
    }

    public static void main(String[] args) {
        StdDraw.setScale(0, 512);
        StdDraw.setYscale(-5, 20000);
        double lastC1 = 0, lastC2 = 0;
        for (int i = 1; i <= 512; i++) {
            int n = i;
            Integer[] a = new Integer[n];
            for (int j = 0; j < n; j++) {
                a[j] = j;
            }
            StdRandom.shuffle(a);

            Integer[] b = a.clone();
            Exercise6 ex = new Exercise6();
            ex.sort(a);
            int c1 = ex.count;

            ex.count = 0;
            ex.sort2(a);
            int c2 = ex.count;

            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line((i - 1), lastC1, i, c1);

            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line((i - 1), lastC2, i, c2);

            StdDraw.setPenColor(StdDraw.GREEN);
            // 访问上限N*log(N)
            StdDraw.line((i-1),6*(n-1)*Math.log(n-1),i,6*n*Math.log(n));

            lastC1 = c1;
            lastC2 = c2;
        }
    }
}

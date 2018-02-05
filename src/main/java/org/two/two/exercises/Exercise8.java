package org.two.two.exercises;

import edu.princeton.cs.algs4.StdDraw;
import org.two.one.learn.Template;

/**
 * 2.2.8 假设将算法2.4修改为：只要a[mid] <= a[mid+1]就不用调用merge()方法，
 * 请证明并用归并排序处理一个已经有序的数组所需的比较次数是线性级别的。
 * <p>
 * 如图所示，如果数组是有序的，归并排序是线性级别
 *
 * @author cheng
 *         2018/1/18 17:29
 */
public class Exercise8 extends Template {

    /**
     * 访问数组的次数
     */
    private static int count = 0;
    private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        if (!less(a[mid], a[mid + 1])) {
            merge(a, lo, mid, hi);
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
        StdDraw.setXscale(0, 512);
        StdDraw.setYscale(-5, 20000);
        double lastC1 = 0;
        for (int i = 0; i < 512; i++) {
            int n = i;
            Integer[] a = new Integer[n];
            for (int j = 0; j < n; j++) {
                a[j] = j;
            }
            // StdRandom.shuffle(a);

            Exercise8 ex = new Exercise8();
            ex.sort(a);
            int c1 = ex.count;

            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line((i - 1), lastC1, i, c1);
            // ex.count = 0;
        }
    }
}

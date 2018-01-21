package org.two.two.exercises;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;
import org.tool.ArrayGenerator;

/**
 * 2.2.22 三向归并排序。假设我们是把数组分成三个部分而不是两个部分并将它们分别排序，
 * 然后进行三向归并。这种算法的运行时间的增长数量级是多少？
 *
 * @author cheng
 *         2018/1/21 21:22
 */
public class Exercise22 {
    private static int[] aux;

    public static double merge_3(int[] a) {
        Stopwatch timer = new Stopwatch();
        aux = new int[a.length];
        merge_3(a, 0, a.length - 1);
        return timer.elapsedTime();
    }

    private static void merge_3(int[] a, int lo, int hi) {
        if (lo >= hi) return;
        // 递归基和分割点的选取很重要
        int mid_left = lo + (hi - lo) / 3;
        int mid_right = hi - (hi - lo) / 3;

        merge_3(a, lo, mid_left);
        merge_3(a, mid_left + 1, mid_right);
        merge_3(a, mid_right + 1, hi);
        mergeSort_3(a, lo, mid_left, mid_right, hi);
    }

    private static void mergeSort_3(int[] a, int lo, int mid_left, int mid_right, int hi) {
        System.arraycopy(a, lo, aux, lo, hi + 1 - lo);

        int i = lo;
        int j = mid_left + 1;
        int k = mid_right + 1;
        for (int m = lo; m <= hi; m++) {
            if (i > mid_left) {    // 左侧取完
                // 转化为二路归并
                if (j > mid_right) a[m] = aux[k++];
                else if (k > hi) a[m] = aux[j++];
                else if (aux[j] < aux[k]) a[m] = aux[j++];
                else a[m] = aux[k++];
            } else if (j > mid_right) { // 中间取完
                // 转化为二路归并
                if (i > mid_left) a[m] = aux[k++];
                else if (k > hi) a[m] = aux[i++];
                else if (aux[i] < aux[k]) a[m] = aux[i++];
                else a[m] = aux[k++];
            } else if (k > hi) { // 右侧取完
                // 转化为二路归并
                if (j > mid_right) a[m] = aux[i++];
                else if (i > mid_left) a[m] = aux[j++];
                else if (aux[j] < aux[i]) a[m] = aux[j++];
                else a[m] = aux[i++];
            } else if (minIndex(aux, i, j, k) == i) {
                a[m] = aux[i++];
            } else if (minIndex(aux, i, j, k) == j) {
                a[m] = aux[j++];
            } else if (minIndex(aux, i, j, k) == k) {
                a[m] = aux[k++];
            }
        }

    }

    private static int minIndex(int[] a, int i, int j, int k) {
        if (Math.min(Math.min(a[i], a[j]), a[k]) == a[k]) {
            return k;
        } else if (Math.min(a[i], a[j]) == a[i]) {
            return i;
        } else {
            return j;
        }
    }

    public static void doublingRationTest() {
        double pre = 0;
        double cur = 0;
        for (int i = 100, j = 0; j < 24; i += i, j++) {
            int[] a = ArrayGenerator.ints(i);
            StdOut.printf(
                    "规模 : %d "
                            + "耗时 : %.3f "
                            + "倍率 : %.1f\n",
                    i,
                    (cur = merge_3(a)),
                    cur / pre);
            pre = cur;
        }
    }

    public static void main(String[] args) {
        // 接近线性级别的增长
        doublingRationTest();
    }
}

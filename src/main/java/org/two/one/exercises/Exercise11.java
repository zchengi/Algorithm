package org.two.one.exercises;

import edu.princeton.cs.algs4.In;

/**
 * 2.1.11 将希尔排序中实时计算递增序列改为预先计算并存储在一个数组中。
 * <p>
 * 分析：本题意思是将h的值存入到一个数组中。
 *
 * @author cheng
 *         2018/1/17 10:07
 */
public class Exercise11 {

    public static void sort(Comparable[] a) {
        int n = a.length;
        // 预先准备好的h值数组
        int[] h = new int[2];

        int hTemp = 1;
        // 序列的大小
        int sequenceSize = 0;
        for (; hTemp < n; sequenceSize++) {
            // 如果数组不够大则双倍扩展
            if (sequenceSize >= h.length) {
                int[] expand = new int[h.length * 2];
                for (int i = 0; i < h.length; i++) {
                    expand[i] = h[i];
                }
                h = expand;
            }
            h[sequenceSize] = hTemp;
            hTemp = hTemp * 3 + 1;
        }
        for (int t = sequenceSize - 1; t >= 0; t--) {
            // 序列比较次数
            int compareTime = 0;
            for (int i = h[t]; i < n; i++) {
                for (int j = i; j >= h[t] && less(a[j], a[j - h[t]]); j -= h[t]) {
                    compareTime++;
                    exch(a, j, j - h[t]);
                }
            }
            // 返回h的值和序列比较次数与数组大小的比值
            System.out.println("h : " + h[t] + "\t CompareTime/ArraySize = " + (double) compareTime / n);
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static void show(Comparable[] a) {
        for (Comparable item : a) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[] a = new In().readAllStrings();
        sort(a);
        assert isSorted(a);
        show(a);
    }
}

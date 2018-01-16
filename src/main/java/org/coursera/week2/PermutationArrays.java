package org.coursera.week2;

import java.util.Arrays;

/**
 * Test5 Permutation
 * 题目：计算两个数组排序之后是否相等
 *
 * @author cheng
 *         2018/1/16 14:47
 */
public class PermutationArrays {
    private int[] a;
    private int[] b;
    private int n;

    public PermutationArrays(int[] a, int[] b, int n) {
        this.a = a;
        this.b = b;
        this.n = n;
    }

    private boolean less(int v, int w) {
        return v < w;
    }

    private void exch(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private void sortByShell(int[] a) {
        int h = 1;
        int n = a.length;
        while (h < n / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exch(a, j, j - h);
                }
            }
            h = h / 3;
        }
    }

    public boolean isPermutation() {
        sortByShell(a);
        System.out.println(Arrays.toString(a));
        sortByShell(b);
        System.out.println(Arrays.toString(b));
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 4, 5, 6, 11, 9, 7, 8, 0, 3};
        int[] b = {0, 9, 8, 7, 6, 5, 4, 3, 2, 1, 11};
        PermutationArrays pa = new PermutationArrays(a, b, 10);
        System.out.println(pa.isPermutation());
    }
}

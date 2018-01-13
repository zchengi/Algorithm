package org.one.day14;

import edu.princeton.cs.algs4.In;

import java.util.Arrays;

/**
 * 1.4.15 快速3-sum
 * 为3-sum给出一个平方级别的算法
 *
 * @author cheng
 *         2018/1/13 22:19
 */
public class Example15 {
    public static int ThreeSumFast(int[] a) {
        Arrays.sort(a);
        int n = a.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            int left = i + 1;
            int right = a.length - 1;
            while (left < right) {
                if (a[left] + a[right] + a[i] < 0) {
                    left++;
                } else if (a[left] + a[right] + a[i] > 0) {
                    right--;
                } else {
                    count++;
                    left++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        In in = new In("src/main/java/org/one/day14/1Kints.txt");
        int[] a = in.readAllInts();
        System.out.println(ThreeSumFast(a));
    }
}

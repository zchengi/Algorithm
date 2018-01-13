package org.one.day14;

import edu.princeton.cs.algs4.In;

import java.util.Arrays;

/**
 * 1.4.14 4-sum
 * 为4-sum设计一个算法。
 *
 * @author cheng
 *         2018/1/13 22:19
 */
public class Example14 {
    public static int fourSum(int[] a) {
        int length = a.length;
        int count = 0;
        for (int i = 0; i < length - 3; i++) {
            for (int j = i + 1; j < length - 2; j++) {
                for (int k = j + 1, l = length - 1; k < l; ) {
                    if (a[i] + a[j] + a[k] + a[l] < 0) {
                        k++;
                    } else if (a[i] + a[j] + a[k] + a[l] > 0) {
                        l--;
                    } else {
                        count++;
                        k++;
                        l--;
                    }
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        In in = new In("src/main/java/org/one/day14/1Kints.txt");
        int[] a = in.readAllInts();
        Arrays.sort(a);
        System.out.println(fourSum(a) + "对");
    }
}

package org.one.day10;

import edu.princeton.cs.algs4.In;
import org.one.day1.BinarySearch;

import java.util.Arrays;

/**
 * 1.4.5.2
 * 3-sum问题的快速算法
 *
 * @author cheng
 *         2017/10/25 12:10
 */
public class ThreeSumFast {
    /**
     * 计算和为0的三元组的数目
     */
    public static int count(int[] a) {
        Arrays.sort(a);
        int n = a.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (BinarySearch.rank(-a[i] - a[j], a) > j) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] a = new In("src/main/java/org/one/day9/4Kints.txt").readAllInts();
        System.out.println(count(a));
    }
}

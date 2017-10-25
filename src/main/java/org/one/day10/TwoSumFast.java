package org.one.day10;

import edu.princeton.cs.algs4.In;
import org.one.day1.BinarySearch;

import java.util.Arrays;

/**
 * 1.4.5.1
 * 2-sum问题的线性对数级别的解法
 *
 * @author cheng
 *         2017/10/25 11:57
 */
public class TwoSumFast {

    /**
     * 计算和为0的整数对的数目
     */
    public static int count(int[] a) {
        Arrays.sort(a);
        int n = a.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (BinarySearch.rank(-a[i], a) > i) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] a = new In("src/main/java/org/one/day10/1Mints.txt").readAllInts();
        System.out.println(count(a));
    }
}

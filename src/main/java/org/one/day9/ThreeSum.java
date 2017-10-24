package org.one.day9;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 1.4.2.1 举例
 * @author cheng
 *         2017/10/24 16:16
 */
public class ThreeSum {

    /**
     * 统计和为0的三整数元组的数量
     */
    public static int count(int[] a) {
        int n = a.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                for (int k = j+1; k < n; k++) {
                    if (a[i] + a[j] + a[k] == 0) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
    public static void main(String[] args) {
        // 1Kints.txt 2Kints.txt 4Kints.txt
        int[] a = new In("src/main/java/org/one/day9/2Kints.txt").readAllInts();
        StdOut.println(count(a));
    }
}

package org.one.day12;

import edu.princeton.cs.algs4.In;

/**
 * 1.4.2 修改ThreeSum，正确处理两个较大的int值相加可能溢出的情况。
 *
 * @author cheng
 *         2018/1/2 18:56
 */
public class Example2 {
    //将int类型转为long类型即可
    public static int count(long[] a) {
        int n = a.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (a[i] + a[j] + a[k] == 0) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        long[] a = new In("src/main/java/org/one/day9/4Kints.txt").readAllLongs();
        System.out.println(count(a));
    }
}

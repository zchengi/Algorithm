package org.two.three.exercises;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * 2.3.8 Quick.sort()在处理N个全部重复的元素时大约需要多少次比较。
 * <p>
 * 大概是1.2*N*lnN
 *
 * @author cheng
 *         2018/1/24 23:05
 */
public class Exercise8 {
    public static void main(String[] args) {
        int n = 100;
        Integer[] arr = new Integer[n];
        Arrays.fill(arr, 1);

        int compares = Exercise6.sort(arr);

        StdOut.printf("规模 : %d 比较次数 : %d 理论值 : %.0f\n",
                n, compares, n * (Math.log(n) / Math.log(2)));

        //  n * log(n) / log(2)
        //  规模 : 100 比较次数 : 564 理论值 : 664
    }
}

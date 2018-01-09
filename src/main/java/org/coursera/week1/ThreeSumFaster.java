package org.coursera.week1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * @author cheng
 *         2018/1/9 19:41
 *
 *          Test4: 3-sum 平方级别
 *         -分析：
 */
public class ThreeSumFaster {
    public int threeSumFaster(long[] a) {
        int cnt = 0;
        int len = a.length;
        for (int j = 0; j < len - 2; j++) {
            for (int k = j + 1, h = len - 1; k < h; ) {
                if (a[j] + a[k] + a[h] < 0) {
                    k++;
                } else if (a[j] + a[k] + a[h] > 0) {
                    h--;
                } else {
                    k++;
                    h--;
                    ++cnt;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {

        In in = new In("src/main/java/org/coursera/week1/8Kints.txt");
        long[] a = in.readAllLongs();
        Arrays.sort(a);
        ThreeSumFaster sum = new ThreeSumFaster();
        StdOut.println(sum.threeSumFaster(a) + "对");
    }

}


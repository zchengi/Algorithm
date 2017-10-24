package org.one.day9;

import edu.princeton.cs.algs4.StdRandom;

/**
 * 1.4.2.3
 * 实验数据的分析
 *
 * @author cheng
 *         2017/10/24 16:53
 */
public class DoublingTest {

    /**
     * 为处理n个随机的六位整数的ThreeSum.count()计时
     */
    public static double timeTrial(int N) {
        int MAX = 1000000;
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform(-MAX, MAX);
        }
        StopWatch timer = new StopWatch();
        int count = ThreeSum.count(a);
        return timer.elapsedTime();
    }

    public static void main(String[] args) {
        //打印运行时间的表格
        for (int n = 250; true; n += n) {
            //打印问题规模为n时程序的用时
            double time = timeTrial(n);
            System.out.printf("%7d %5.1f\n", n, time);
        }
    }
}

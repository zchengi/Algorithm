package org.one.day9;


import edu.princeton.cs.algs4.StdRandom;

/**
 * 表1.4.1
 * 一种表示计时器的抽象数据类型
 *
 * @author cheng
 *         2017/10/24 16:40
 */
public class StopWatch {

    /**
     * 开始运行时间
     */
    private final long start;

    /**
     * 创建一个计时器
     */
    public StopWatch() {
        start = System.currentTimeMillis();
    }

    /**
     * 返回对象创建以来所经过的时间
     */
    public double elapsedTime() {
        long now = System.currentTimeMillis();
        return (now - start) / 1000.0;
    }

    public static void main(String[] args) {
        int n = 1000;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = StdRandom.uniform(-1000000, 1000000);
        }
        StopWatch timer = new StopWatch();
        int count = ThreeSum.count(a);
        double time = timer.elapsedTime();
        System.out.println(count + " triples " + time + " seconds");
    }
}

package org.one.day12;

import edu.princeton.cs.algs4.*;

/**
 * 1.4.3 修改DoublingTest,使用StdDraw产生类似于正文中的标准图像和对数图形,根据需要调整比例使图像总能够充满窗口的大部分区域
 *
 * @author cheng
 *         2018/1/2 19:04
 */
public class Example3 {

    public static double timeTrial(int N) { // Time ThreeSum2.count() for N
        // random 6-digit ints.
        int MAX = 1000000;
        int[] a = new int[N];
        for (int i = 0; i < N; i++)
            a[i] = StdRandom.uniform(-MAX, MAX);
        Stopwatch timer = new Stopwatch();
        int cnt = ThreeSum.count(a);
        return timer.elapsedTime();
    }

    public static void drawStandard(int[] n, double[] time) {
        StdDraw.setXscale(-0.5, 1.2 * n[n.length - 1] / 1000.0);
        StdDraw.setYscale(-0.5, time[n.length - 1] * 1.2);
        // 建立坐标系
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.001);
        StdDraw.line(0, 0, 1.1 * n[n.length - 1] / 1000, 0);
        StdDraw.line(0, 0, 0, 1.1 * time[n.length - 1]);
        for (int i = 1; i < 1.1 * n[n.length - 1] / 1000.0; i++)
            StdDraw.text(i, -0.2, i + "K");
        for (double t = 2; t < time[n.length - 1] * 1.1; t += 2)
            StdDraw.text(-0.2, t, t + "");
        // 绘制图像
        StdDraw.setPenColor(StdDraw.BOOK_RED);
        StdDraw.setPenRadius(0.02);
        for (int i = 0; i < n.length; i++)
            StdDraw.point(n[i] / 1000.0, time[i]);
    }

    //绘制对数图形
    public static void drawLog(int[] arrN, double[] timeN) {
        //将时间转换为其对数
        double[] timelog = new double[timeN.length];
        for (int i = 0; i < timeN.length; i++)
            timelog[i] = Math.log(timeN[i]);
        StdDraw.setXscale(-0.5, 1.2 * arrN[arrN.length - 1] / 1000.0);
        StdDraw.setYscale(-0.5, timelog[arrN.length - 1] * 1.2);
        //建立坐标系
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.001);
        StdDraw.line(0, 0, 1.1 * arrN[arrN.length - 1] / 1000, 0);
        StdDraw.line(0, 0, 0, 1.1 * timelog[arrN.length - 1]);
        for (int i = 1; i < 1.1 * arrN[arrN.length - 1] / 1000.0; i++)
            StdDraw.text(i, -0.2, i + "K");
        for (double t = 0; t < timelog[arrN.length - 1] * 1.1; t += timelog[arrN.length - 1] / 5)
            StdDraw.text(-0.2, t, String.format("%.2f", t));
        //绘制图像
        StdDraw.setPenColor(StdDraw.BOOK_RED);
        StdDraw.setPenRadius(0.02);
        for (int i = 0; i < arrN.length; i++)
            StdDraw.point(arrN[i] / 1000.0, timelog[i]);
    }

    public static void main(String[] args) {
        int MAX = 2000;
        int i = 0;
        int step = 10;
        int len = MAX / step + 1;
        int[] arrN = new int[len];
        double[] timeN = new double[len];
        for (int N = 0; N <= MAX; N = N + step) {
            double time = timeTrial(N);
            StdOut.printf("%7d %5.7f\n", N, time);
            arrN[i] = N;
            timeN[i++] = time;
        }
        drawStandard(arrN, timeN);
        drawLog(arrN, timeN);
    }
}
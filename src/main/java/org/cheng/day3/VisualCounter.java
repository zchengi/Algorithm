package org.cheng.day3;

import edu.princeton.cs.algs4.StdDraw;

/**
 * Example10:
 * 编写一个类VisualCounter，支持加一和减一操作。
 * 它的构造函数接受两个参数n和max，其中n指定了操作的最大次数，max指定了计数器的最大绝对值。
 * 作为副作用，用图像显示每次计数器变化后的值。
 *
 * @author cheng
 *         2017/10/18 22:15
 */
public class VisualCounter {
    private int count;
    private int n;
    private int N;
    private int max;

    public VisualCounter(int n, int max) {
        N = n;
        this.max = max;
        count = 0;
        this.n = 0;
        StdDraw.setXscale(0, N);
        StdDraw.setYscale(-max, max);
        StdDraw.setPenRadius(0.01);
        StdDraw.point(0, 0);
    }

    /**
     * 加一操作
     */
    public void increment() {
        if (n + 1 > N) {
            System.out.println("操作太多！");
            return;
        }
        if (count + 1 > max) {
            System.out.println("超过了最大值！");
            return;
        }
        count++;
        n++;
        StdDraw.point(n, count);
    }

    /**
     * 减一操作
     */
    public void decrement() {
        if (n + 1 > N) {
            System.out.println("Operate too much!");
            return;
        }
        if (count - 1 < -max) {
            System.out.println("Exceed the min!");
            return;
        }
        count--;
        n++;
        StdDraw.point(n, count);
    }

    /**
     * 计数器
     */
    public int getCount() {
        return count;
    }

    public static void main(String[] args) {
        VisualCounter counter = new VisualCounter(20, 5);
        for (int i = 0; i < 10; i++) {
            counter.increment();
        }
        for (int i = 0; i < 15; i++) {
            counter.decrement();
        }
        for (int i = 0; i < 10; i++) {
            counter.increment();
        }
        System.out.println(counter.getCount());
    }
}

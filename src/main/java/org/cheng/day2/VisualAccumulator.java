package org.cheng.day2;

import edu.princeton.cs.algs4.StdDraw;

/**
 * @author cheng
 *         2017/10/17 22:38
 */
public class VisualAccumulator {

    private double total;
    private int N;

    VisualAccumulator(int trials, double max) {
        StdDraw.setXscale(0, trials);
        StdDraw.setYscale(0, max);
        StdDraw.setPenRadius(.005);
    }

    /**
     * 添加一个新的数据值
     *
     * @param val 添加的值
     */
    void addDataValue(double val) {
        N++;
        total += val;
        StdDraw.setPenColor(StdDraw.DARK_GRAY);
        StdDraw.point(N, val);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.point(N, total / N);
    }

    /**
     * 所有数据的平均值
     *
     * @return 平均值
     */
    private double mean() {
        return total / N;
    }

    @Override
    public String toString() {
        return "Mean (" + N + " values): "
                + String.format("%7.5f", mean());
    }
}

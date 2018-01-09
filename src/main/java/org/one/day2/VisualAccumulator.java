package org.one.day2;

import edu.princeton.cs.algs4.StdDraw;

/**
 * 累加器（实现可视化）
 * @author cheng
 *         2017/10/17 22:38
 */
public class VisualAccumulator {

    /**
     * 数据总数
     */
    private int n = 0;
    /**
     * 数据总值
     */
    private double sum = 0.0;

    public VisualAccumulator() {
    }

    /**
     * 初始化累加器
     *
     * @param trials 总值
     * @param max    最大值
     */
    public VisualAccumulator(int trials, double max) {
        StdDraw.setXscale(0, trials);
        StdDraw.setYscale(0, max);
        StdDraw.setPenRadius(.005);
    }

    /**
     * 将指定的数值添加到累加器
     *
     * @param val 添加的值
     */
    void addDataValue(double val) {
        n++;
        sum += val;
        StdDraw.setPenColor(StdDraw.DARK_GRAY);
        StdDraw.point(n, val);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.point(n, sum / n);
    }

    /**
     * 计算数据值的平均值
     *
     * @return 平均值
     */
    private double mean() {
        return sum / n;
    }

    @Override
    public String toString() {
        return "Mean (" + n + " values): "
                + String.format("%7.5f", mean());
    }
}

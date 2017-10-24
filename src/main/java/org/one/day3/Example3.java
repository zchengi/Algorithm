package org.one.day3;

import edu.princeton.cs.algs4.*;

/**
 * 编写一个Interval2D的用例，从命令行接受参数n、min和max。
 * 生成n个随机的2D区间，其宽和高均匀地分布在单位正方形中的min和max之间。
 * 用StdDraw画出它们并打印出相交区间隔的数量以及有包含关系的间隔对数量。
 *
 * @author one
 *         2017/10/18 18:44
 */
public class Example3 {
    public static void main(String[] args) {
        //区间数
        int n = StdIn.readInt();
        double min = StdIn.readInt();
        double max = StdIn.readInt();

        StdDraw.setXscale(min, max);
        StdDraw.setYscale(min, max);
        Point2D[] leftTopPoints = new Point2D[n];
        Point2D[] rightBottomPoints = new Point2D[n];

        //随机n个区间
        Interval2D[] intervals = new Interval2D[n];
        for (int i = 0; i < n; i++) {
            double a = StdRandom.uniform(min, max);
            double b = StdRandom.uniform(min, max);
            double left, right, top, bottom;
            Interval1D x, y;
            if (a < b) {
                left = a;
                right = b;
            } else {
                left = b;
                right = a;
            }
            x = new Interval1D(left, right);
            a = StdRandom.uniform(min, max);
            b = StdRandom.uniform(min, max);
            if (a < b) {
                top = a;
                bottom = b;
            } else {
                top = b;
                bottom = a;
            }
            y = new Interval1D(top, bottom);
            leftTopPoints[i] = new Point2D(left, top);
            rightBottomPoints[i] = new Point2D(right, bottom);
            intervals[i] = new Interval2D(x, y);
            intervals[i].draw();
        }

        int containNum = 0, intervalNum = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n; j++) {
                // 判断是否相交
                if (j > i && intervals[i].intersects(intervals[j])) {
                    intervalNum++;
                }
                // 判断是否包含：包含leftTopPoints和rightBottomPoints两点即是包含了这个区间
                if (j != i && intervals[i].contains(leftTopPoints[j]) && intervals[i].contains(rightBottomPoints[j])) {
                    containNum++;
                }
            }
        }
        System.out.println("Interval count: " + intervalNum);
        System.out.println("Contain count: " + containNum);
    }
}

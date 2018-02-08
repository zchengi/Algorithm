package org.two.five.exercises;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 2.5.26 简单多边形。给定平面上的N个点，用它们画一个多边形。
 * 提示：找到y坐标最小的点p，在有多个最小y坐标的点时取x坐标最小者，然后将其他点按照以p为原点的幅角大小的顺序依次连起来。
 *
 * @author cheng
 *         2018/2/8 21:34
 */
public class Exercise26 {
    static class Point {
        private double x;
        private double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public static Point[] randomPoint(int N, double xl, double xr, double yb, double yu) {
            Point[] p = new Point[N];
            for (int i = 0; i < N; i++)
                p[i] = new Point(StdRandom.uniform(xl, xr),
                        StdRandom.uniform(yb, yu));
            return p;
        }

        public double angleTo(Point p) {
            double dx = x - p.x;
            double dy = y - p.y;
            double angle = Math.atan(Math.abs(dy) / Math.abs(dx)) * 180 / Math.PI;
            if (dx < 0 && dy > 0) angle = 180 - angle;
            if (dx <= 0 && dy <= 0) angle = 180 + angle;
            if (dx > 0 && dy < 0) angle = 360 - angle;
            return angle;
        }

        public String toString() {
            return String.format("{ %.3f %.3f }", x, y);
        }

        public static Comparator<Point> sortDisToCenter(final Point center) {
            return new Comparator<Point>() {
                public int compare(Point p, Point q) {
                    double dis1 = (p.x - center.x) * (p.x - center.x) + (p.y - center.y) * (p.y - center.y);
                    double dis2 = (q.x - center.x) * (q.x - center.x) + (q.y - center.y) * (q.y - center.y);
                    return (int) (dis1 - dis2);
                }
            };
        }

        public Comparator<Point> sortAngle() {
            return new Comparator<Point>() {
                public int compare(Point p, Point q) {
                    double a1 = p.angleTo(Point.this);
                    double a2 = q.angleTo(Point.this);
                    return a1 < a2 ? -1 :
                            a1 > a2 ? 1 : 0;
                }
            };
        }

        public static Point nearest(Point[] ps, Point stdCenter) {
            int lo = 0, hi = ps.length - 1, k = 0;
            while (lo <= hi) {
                int j = parition(ps, lo, hi, stdCenter);
                if (j > k) hi = j - 1;
                else if (j < k) lo = j + 1;
                else return ps[j];
            }
            throw new RuntimeException("unkonw error!");
        }

        private static boolean less(Point p, Point q, Point stdCenter) {
            return Point.sortDisToCenter(stdCenter).compare(p, q) < 0;
        }

        private static void exch(Point[] ps, int i, int j) {
            Point t = ps[i];
            ps[i] = ps[j];
            ps[j] = t;
        }

        private static int parition(Point[] ps, int lo, int hi, Point stdCenter) {
            Point v = ps[lo];
            int i = lo, j = hi + 1;
            while (true) {
                while (i < hi && less(ps[++i], v, stdCenter)) ;
                while (less(v, ps[--j], stdCenter)) ;
                if (i >= j) break;
                exch(ps, i, j);
            }
            exch(ps, j, lo);
            return j;
        }
    }

    public static void main(String[] args) {

        // 将 N 个点铺满画布
        int N = 1000;
        double XL = 0, XR = 200, YB = 0, YU = 200;
        Point[] p = Point.randomPoint(N, XL, XR, YB, YU);
        StdDraw.setXscale(XL, XR);
        StdDraw.setYscale(YB, YU);
        for (Point pp : p)
            StdDraw.point(pp.x, pp.y);

        // 把笔触设大
        StdDraw.setPenRadius(0.01);

        // 找到最接近中心的点并绘制
        Point center = Point.nearest(p, new Point((XL + XR) / 2.0, (YB + YU) / 2.0));
        StdDraw.point(center.x, center.y);

        // 设置多边形的边数
        int count = 16;
        int interval = p.length / count;

        // 将点按照距中心点的幅角排序
        Arrays.sort(p, center.sortAngle());

        // 描边
        int i;
        Point pre, cur = p[0];
        for (i = interval; i < p.length; i += interval) {
            pre = p[i - interval];
            cur = p[i];
            StdDraw.line(pre.x, pre.y, cur.x, cur.y);
        }

        // 闭合形状
        StdDraw.line(p[0].x, p[0].y, cur.x, cur.y);
    }
}
package org.coursera.week2;

import edu.princeton.cs.algs4.StdRandom;

import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Test4 Intersection of two sets
 * 题目：计算两个大小相等的point数组中重复点的个数
 *
 * @author cheng
 *         2018/1/16 14:31
 */
public class PlanePoints {

    private Set<Point> s;

    private int samePointsNum;

    public PlanePoints(int n, Point[] inputA, Point[] inputB) {
        s = new HashSet<>();
        for (int i = 0; i < n; i++) {
            s.add(inputA[i]);
            s.add(inputB[i]);
        }
        samePointsNum = 2 * n - s.size();
    }

    public int samePointsNum() {
        return samePointsNum;
    }

    public static void main(String[] args) {
        int n = 10;
        Point[] a = new Point[n];
        Point[] b = new Point[n];
        System.out.println(a.length + "---" + b.length);

        for (int i = 0; i < n; i++) {
            a[i] = new Point();
            a[i].setLocation(StdRandom.uniform(n), StdRandom.uniform(n));
            b[i] = new Point();
            b[i].setLocation(StdRandom.uniform(n), StdRandom.uniform(n));
        }
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
        PlanePoints planePoints = new PlanePoints(n, a, b);
        System.out.println(planePoints.samePointsNum);
    }
}

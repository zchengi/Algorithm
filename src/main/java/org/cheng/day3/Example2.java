package org.cheng.day3;

import edu.princeton.cs.algs4.Interval1D;
import edu.princeton.cs.algs4.StdIn;

import java.util.Scanner;

/**
 * 编写一个Interval1D的用例，从命令行接收一个整数n。
 * 从标准输入中读取n个区间（每个区间由一对double值定义）并打印出相交的区间。
 *
 * @author cheng
 *         2017/10/18 18:11
 */
public class Example2 {
    public static void main(String[] args) {
        // 获得整数n
        int n = new Scanner(System.in).nextInt();
        Interval1D[] intervals = new Interval1D[n];
        for (int i = 0; i < n; i++) {
            //控制台输入n以内的n个区间
            intervals[i] = new Interval1D(StdIn.readDouble(), StdIn.readDouble());
        }
        //如果不少于两个区间，则依次判断每个区间是否有相交
        if (n >= 2) {
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (intervals[i].intersects(intervals[j])) {
                        System.out.println(intervals[i] + " intersects " + intervals[j]);
                    }
                }
            }
        }
    }
}

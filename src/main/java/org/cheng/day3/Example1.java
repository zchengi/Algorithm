package org.cheng.day3;

import edu.princeton.cs.algs4.Point2D;

import java.util.Scanner;

/**
 * 编写一个Point2D的用例,从命令行接受一个整数N。
 * 在单位正方形中生成n个随机点,然后计算亮点之间的最近距离。
 *
 * @author cheng
 *         2017/10/18 17:44
 */
public class Example1 {
    public static void main(String[] args) {
        // 获取键盘输入的数
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        // 初始化点绘制工具
        Point2D[] points = new Point2D[n];
        // 在画布上生成所有随机点
        for (int i = 0; i < n; i++) {
            points[i] = new Point2D(Math.random(), Math.random());
            points[i].draw();
            System.out.println(i);
        }
        //输出最近距离
        if (n > 1) {
            double min = points[0].distanceTo(points[1]);
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (points[i].distanceTo(points[j]) < min) {
                        min = points[i].distanceTo(points[j]);
                    }
                }
            }
            System.out.println("Min distance: " + min);
        }
    }
}

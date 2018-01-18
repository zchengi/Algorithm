package org.two.one.exercises;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;
import org.two.one.learn.Example;

/**
 * 2.1.17 动画。修改插入排序和选择排序的代码，使之将数组内容绘制成正文中所示的棒状图。在每一
 * 轮排序后重绘图片来产生动画效果，并以一张“有序”的图片作为结束，即所有圆棒均已按照高度有序排列。
 * 提示：使用类似正文中的用例来随机生成Double值，在排序代码的适当位置调用show()方法，并在show()
 * 方法中清理画布并绘制棒状图。
 *
 * @author cheng
 *         2018/1/18 14:37
 */
public class Exercise17 extends Example {
    public static void sort(Comparable[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (less(a[j], a[min])) min = j;
            }
            exch(a, i, min);
        }
    }

    public static void sort2(Comparable[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
            }
        }
    }

    public static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;

        drawSort(a);
        StdDraw.pause(300);
    }

    private static void drawSort(Comparable[] a) {
        StdDraw.clear();
        StdDraw.setXscale(-1, a.length + 1);

        Comparable max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (less(max, a[i])) max = a[i];
        }
        StdDraw.setYscale(-2, (Integer) max + 1);

        for (int i = 0; i < a.length; i++) {
            int y = (Integer) a[i];
            StdDraw.filledRectangle(i + 0.5, y / 2.0, 0.3, y / 2.0);
        }
    }

    public static void main(String[] args) {
        // 选择排序和插入排序的动态图
        int n = 15;
        Integer[] a = new Integer[n];
        for (int i = 0; i < n; i++) {
            a[i] = i;
        }
        StdRandom.shuffle(a);
        sort(a);
        // sort2(a);
    }
}

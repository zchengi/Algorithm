package org.two.one.exercises;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;
import org.two.one.learn.Template;

/**
 * 2.1.18 可视轨迹。修改你为上一题给出的解答，为插入排序和选择排序生成和正文中类似的可视轨迹。
 * 提示：使用setYscale()函数是一个明智的选择。附加题：添加必要的代码，与正文中的图片一样用红色
 * 和灰色强调不用角色的元素。
 *
 * @author cheng
 *         2018/1/18 14:37
 */
public class Exercise18 extends Template {
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
        drawSort(a, i, j);
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;

        // drawSort(a);
        StdDraw.pause(500);
    }

    private static void drawSort(Comparable[] a, int x1, int x2) {
        StdDraw.clear();
        StdDraw.setXscale(-1, a.length + 1);

        Comparable max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (less(max, a[i])) max = a[i];
        }
        StdDraw.setYscale(-2, (Integer) max + 1);

        for (int i = 0; i < a.length; i++) {
            int y = (Integer) a[i];
            if (i == x1 || i == x2) {
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.filledRectangle(i + 0.5, y / 2.0, 0.3, y / 2.0);
                StdDraw.setPenColor(StdDraw.BLACK);
            } else {
                StdDraw.filledRectangle(i + 0.5, y / 2.0, 0.3, y / 2.0);
            }
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

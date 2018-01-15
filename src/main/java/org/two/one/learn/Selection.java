package org.two.one.learn;

import edu.princeton.cs.algs4.In;

/**
 * 算法2.1 选择排序
 *
 * 分析: 该算法将第i小的元素放到a[i]之中。数组的第i个位置的左边是i个最小的元素且它们不会再被访问。
 *
 * @author cheng
 *         2018/1/15 17:37
 */
public class Selection {

    /**
     * 将数组按升序排列
     */
    public static void sort(Comparable[] a) {
        // 数组长度
        int n = a.length;
        // 将a[i]和a[i+1..N]中最小的元素交换
        for (int i = 0; i < n; i++) {
            // 最小元素的索引
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            exch(a, i, min);
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    /**
     * 在单行中打印数组
     */
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    /**
     * 测试数组元素是否有序
     */
    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        // 从标准输入读取字符串，将它们排序并输出
        String[] a = new In().readAllStrings();
        sort(a);
        assert isSorted(a);
        show(a);
    }
}

package org.two.one.learn;

import edu.princeton.cs.algs4.In;

/**
 * 算法2.3 希尔排序
 * <p>
 * 分析：如果我们在插入排序中加入一个外循环来将h按照递增序列递减，我们就能
 * 得到这个简洁的希尔排序。增幅h的初始值是数组长度乘以一个常数因子，最小为1。
 *
 * @author cheng
 *         2018/1/15 21:45
 */
public class Shell {

    /**
     * 将数组按升序排列
     */
    public static void sort(Comparable[] a) {

        int n = a.length;

        int h = 1;
        // 1, 4, 13, 40, 121, 364, 1093,.....
        while (h < n / 3) h = 3 * h + 1;

        //将数组变为h有序
        while (h >= 1) {
            for (int i = h; i < n; i++) {
                // 对a[i],a[i-h],a[i-2*h],a[i-3*h]...使用插入排序
                /* for (int j = i; j >= h && less(a[j], a[j - h]); j-=h) {
                    exch(a, j, j - h);
                }*/
                // 优化
                Comparable temp = a[i];
                int j = i;
                for (; j >= h && less(temp, a[j - h]); j--) {
                    a[j] = a[j - h];
                }
                a[j] = temp;
            }
            h = h / 3;
        }
    }

    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    /**
     * 在单行中打印数组
     */
    public static void show(Comparable[] a) {
        for (Comparable item : a) {
            System.out.print(item + " ");
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
        // S H E L L S O R T E X A M P L E
        String[] a = new In().readAllStrings();
        sort(a);
        assert isSorted(a);
        show(a);
    }
}


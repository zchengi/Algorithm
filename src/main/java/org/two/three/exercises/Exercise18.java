package org.two.three.exercises;

import org.tool.SortTestHelper;

import java.util.Arrays;

/**
 * 2.3.18 三取样切分。为快速排序实现正文所属的三向切分（参见2.3.3.2）。
 * 运行双倍测试来确认这种改动的效果。
 *
 * @author cheng
 *         2018/1/26 16:53
 */
public class Exercise18 {

    public static void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        // 在这里如果切换插入排序，即解决了小数组三取样的 bug，又避免了过多上下文切换的开销，可谓一举两得
        int size = hi - lo + 1;
        // 单元素自然有序
        if (size <= 1) return;
        if (size == 2) {
            // 双元素直接判断是否交换位置
            if (a[lo].compareTo(a[hi]) > 0) exch(a, lo, hi);
            return;
        }

        // 三取样切分，排好了 lo mid hi 的相对位置
        int mid = (lo + hi) >> 1;
        if (a[mid].compareTo(a[lo]) < 0) exch(a, mid, lo);
        if (a[hi].compareTo(a[lo]) < 0) exch(a, hi, lo);
        if (a[hi].compareTo(a[mid]) < 0) exch(a, mid, hi);

        // 如果现在是三元素，那么该子数组已然有序
        if (size == 3) return;

        exch(a, mid, hi - 1);
        int i = lo - 1, j = hi - 1;
        Comparable pivot = a[hi - 1];
        while (true) {
            while (a[++i].compareTo(pivot) < 0) ;
            while (a[--j].compareTo(pivot) > 0) ;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, hi - 1, i);

        sort(a, lo, i - 1);
        sort(a, i + 1, hi);
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        int n = 1000000;
        Integer[] arr1 = SortTestHelper.generateRandomArray(n, 0, n);
        Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);
        SortTestHelper.testSort("org.two.three.exercises.Exercise18", arr1);
        // SortTestHelper.printArray(arr1);
        SortTestHelper.testSort("org.two.three.learn.Quick", arr2);
    }
}

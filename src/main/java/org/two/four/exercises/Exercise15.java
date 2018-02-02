package org.two.four.exercises;

import org.tool.SortTestHelper;

/**
 * 2.4.15 设计一个程序，在线性时间内检测数组 pq[] 是否是一个面向最小元素的堆。
 *
 * @author cheng
 *         2018/2/2 18:50
 */
public class Exercise15 {

    /**
     * 最小堆
     * 从0开始索引的pq
     */
    public static boolean isMinPQ(Comparable[] pq) {
        int n = pq.length;
        int count = n - 1;

        int i = n / 2 - 1;
        while (i >= 0) {
            int k = i * 2 + 1;
            if (k > count) {
                i--;
                continue;
            }
            if (k < count && pq[k].compareTo(pq[k + 1]) > 0) k++;
            if (pq[i--].compareTo(pq[k]) > 0) return false;
        }
        return true;
    }

    /**
     * 最大堆
     */
    public static boolean isMaxPQ(Comparable[] arr) {
        int n = arr.length;
        for (int i = n - 1; i < n * 2; i--) {
            for (int k = i; k > 1; k--) {
                if (arr[k >> 1].compareTo(arr[i]) < 0) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int n = 100;
        // 有序数组
        Integer[] arr = SortTestHelper.generateOrderedArray(n);
        arr[50] = 23;
        System.out.println(isMinPQ(arr));

        // 逆序数组
        arr = SortTestHelper.generateInversedArray(n);
        // arr[50] = 75;
        System.out.println(isMaxPQ(arr));
    }
}

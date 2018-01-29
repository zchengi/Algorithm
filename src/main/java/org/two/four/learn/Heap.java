package org.two.four.learn;

import org.tool.SortTestHelper;

/**
 * 算法2.7 堆排序
 *
 * @author cheng
 *         2018/1/29 21:19
 */
public class Heap {

    /**
     * 这个类不该被实例化
     */
    private Heap() {
    }

    public static void sort(Comparable[] arr) {
        int n = arr.length;
        // 堆的构造（堆有序）
        for (int k = n / 2; k >= 1; k--) {
            sink(arr, k, n);
        }
        // 下沉排序
        while (n > 1) {
            // 将最大元素删除，然后放入堆缩小后数组中空出来的位置
            exch(arr, 1, n--);
            // 再构造一个堆有序
            sink(arr, 1, n);
        }
    }

    private static void sink(Comparable[] pq, int i, int n) {
        while (2 * i <= n) {
            int j = 2 * i;
            if (j < n && less(pq, j, j + 1)) {
                j++;
            }
            if (!less(pq, i, j)) {
                break;
            }
            exch(pq, i, j);
            i = j;
        }
    }


    private static boolean less(Comparable[] pq, int i, int j) {
        // i,j基于1，索引基于0
        return pq[i - 1].compareTo(pq[j - 1]) < 0;
    }

    private static void exch(Comparable[] pq, int i, int j) {
        // i,j基于1，索引基于0
        Comparable temp = pq[i - 1];
        pq[i - 1] = pq[j - 1];
        pq[j - 1] = temp;
    }

    public static void main(String[] args) {
        int n = 1000000;
        Integer[] arr = SortTestHelper.generateRandomArray(n, 0, 2 * n);
        SortTestHelper.testSort("org.two.four.learn.Heap", arr);
        System.out.println(SortTestHelper.isSorted(arr));
    }
}

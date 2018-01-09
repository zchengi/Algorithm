package org.one.day3;

import edu.princeton.cs.algs4.Counter;

import java.util.Arrays;

/**
 * 使用Counter统计在有查找中被检查的键的总次数并查找全部结束后打印该值。
 *
 * @author cheng
 *         2017/10/18 21:54
 */
public class Example9 {
    /**
     * 调用私有构造器
     *
     * @param key     查找的值
     * @param a       数组
     * @param counter 累加器
     */
    public static int rank(int key, int[] a, Counter counter) {
        return rank(key, a, 0, a.length - 1, counter);
    }

    /**
     * 计算查找了几次，以及是否找到
     *
     * @param key     查找的值
     * @param a       数组
     * @param lo      最小索引
     * @param hi      最小索引
     * @param counter 累加器
     * @return 找到:返回该值索引;未找到:返回-1
     */
    private static int rank(int key, int[] a, int lo, int hi, Counter counter) {
        if (lo > hi) {
            return -1;
        }
        counter.increment();
        int mid = lo + (hi - lo) / 2;
        if (key < a[mid]) {
            return rank(key, a, lo, mid - 1, counter);
        } else if (key > a[mid]) {
            return rank(key, a, mid + 1, hi, counter);
        } else {
            return mid;
        }
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Arrays.sort(a);
        int key = 5;
        Counter counter = new Counter("keys");
        System.out.println(rank(key, a, counter));
        System.out.println(counter);
    }
}

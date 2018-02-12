package org.three.one.exercises;


import org.one.day9.StopWatch;

import java.util.Arrays;

/**
 * 3.1.24 插值法查找。假设符号表的键支持算数操作（例如，它们可能是Double或者Integer类型的值）。
 * 编写一个二分炒作来模拟查字典的行为，例如当单词的字母在字母表的开头时我们也会在字典的前半部分进行查找。
 * 具体来说，设Klo为符号表的第一个键，Khi为符号表的最后一个键，当要查找Kx时，先和 (Kx - Klo) / (Khi - Klo)    进行比较，
 * 而非取中间元素。用SearchComparable调用FrequencyCounter来比较你的实现和BinarySearchST的性能。
 *
 * 分析：插值查找比较灵活,并不是简单的从中间进行的,它是根据我们需要查询的值的渐进进行搜索的。
 * 插值查找的不同点在于每一次并不是从中间切分,而是根据离所求值的距离进行搜索的。
 *
 * 结论：插值法查找比较次数更少。
 *
 * @author cheng
 *         2018/2/11 23:45
 */
public class Exercise24 {

    public static int compares;

    public static int interpolationSearch(int[] a, int key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            double scale = 1.0 * (key - lo) / (hi - lo);
            int mid = lo + (int) (scale * (hi - lo));
            compares++;

            if (key < mid) hi = mid - 1;
            else if (key > mid) lo = mid + 1;
            else return mid;
        }
        return -1;
    }

    public static int BinarySearch(int[] a, int key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = (hi - lo) / 2 + lo;
            compares++;

            if (key < mid) hi = mid - 1;
            else if (key > mid) lo = mid + 1;
            else return mid;
        }
        return -1;
    }

    public static void main(String[] args) {
        int n = 10000000;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = (int) (Math.random() * n) + 1;
        }
        Arrays.sort(a);

        int key = (int) (Math.random() * n) + 1;
        StopWatch timer = new StopWatch();

        int res1 = interpolationSearch(a, key);
        int cmp1 = compares;
        double t1 = timer.elapsedTime();

        compares = 0;

        timer = new StopWatch();
        int res2 = BinarySearch(a, key);
        int cmp2 = compares;
        double t2 = timer.elapsedTime();

        System.out.println("插值法查找值：" + key + "，结果为：" + res1 + "比较次数：" + cmp1 + "，耗时：" + t1);
        System.out.println("插值法查找值：" + key + "，结果为：" + res2 + "比较次数：" + cmp2 + "，耗时：" + t2);
    }
}

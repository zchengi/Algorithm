package org.three.one.exercises;

import org.tool.SortTestHelper;

/**
 * 3.1.23 二分查找的分析。请证明对于大小为N的符号表，一次二分查找所需的最大比较次数正好是N的二进制表示的位数，
 * 因为右移一位的操作会将二进制的N变为二进制的[N/2]。
 * <p>
 * 分析：最大比较次数：当key为最大或最小值时，2^M <= N ; M + 1 为最大比较次数；
 * 也就是对二进制的 N 可以右移的次数，也是 N 能除以二的次数。
 *
 * @author cheng
 *         2018/2/11 23:30
 */
public class Exercise23 {

    private static int compares;

    public static int binarySearch(Integer[] arr, int key) {
        int lo = 0;
        int hi = arr.length - 1;
        while (lo <= hi) {
            int mid = (hi - lo) / 2 + lo;
            int less = arr[mid] - key;
            compares++;
            if (less < 0) lo = mid + 1;
            else if (less > 0) hi = mid - 1;
            else return mid;
        }
        return -1;
    }

    public static void main(String[] args) {
        int n = 1000;
        int key = 999;
        Integer[] arr = SortTestHelper.generateOrderedArray(n);

        System.out.println("key 的索引为：" + binarySearch(arr, key));
        System.out.println("比较次数：" + compares);
        System.out.println("N 的二进制长度：" + Integer.toBinaryString(n).length());
    }
}

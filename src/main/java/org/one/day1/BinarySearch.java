package org.one.day1;


import java.util.Arrays;

/**
 * @author one
 *         2017/10/16 16:12
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] whitelist = {1, 2, 3, 4, 5, 6, 78, 123, 1254, 235, 23, 4, 23, 364, 6, 457, 45, 76, 4546};
        Arrays.sort(whitelist);
        System.out.println(rank(1, whitelist));
        System.out.println(rank(6, whitelist));
        System.out.println(rank(7, whitelist));
        System.out.println(1.0/0.0);
    }

    /**
     * 二分查找
     *
     * @param key 查找值
     * @param a   查找数组
     * @return 是否存在：存在-返回索引；不存在-返回-1
     */
    private static int rank(int key, int[] a) {
        // 数组必须是有序的
        int lo = 0;
        // 数组长度
        int hi = a.length - 1;
        while (lo <= hi) {
            // 被查找的键要么不存在，要么必然存在于a[lo..hi]之中
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) {
                hi = mid - 1;
            } else if (key > a[mid]) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}

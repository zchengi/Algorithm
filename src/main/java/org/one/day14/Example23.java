package org.one.day14;

import java.util.Arrays;

/**
 * 1.4.23 分数的二分查找
 * 设计一个算法，使用对数级别的比较次数找出有理数p/q，其中0 < p < q < N，比较形式为
 * 给定的数是否小于x？提示：两个分母均小于N的有理数之差不小于1/N^2。
 * <p>
 * 将二分超找中判断相等的条件改为两个数的差小于等于1/N^。
 *
 * @author cheng
 *         2018/1/14 18:23
 */
public class Example23 {
    public static int rank(double key, double[] a) {
        Arrays.sort(a);
        int low = 0;
        int high = a.length - 1;
        double threshold = 1.0 / (a.length * a.length);
        while (low <= high) {
            int mid = low + (high - low) / 2;
            // 这里的判断条件做个改动
            if (Math.abs(a[mid] - key) <= threshold) {
                return mid;
            } else if (key > a[mid]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        double[] fractions = {1.0 / 2.0, 2.0 / 3.0, 3.0 / 4.0, 4.0 / 5.0, 5.0 / 6.0};
        int index = rank(3.0 / 4.0, fractions);
        System.out.print("3.0/4.0 在第" + index + "个");
    }
}

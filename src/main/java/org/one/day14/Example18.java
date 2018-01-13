package org.one.day14;

/**
 * 1.4.18 数组的局部最小元素
 * 编写一个程序，给定一个含有N个不同整数的数组，找到一个局部最小元素：
 * 满足a[i] < a[i-1]，且a[i] < a[i+1] 的索引i。程序在最坏情况下所需的比较次数为~2lgN。
 * 分析：  检查数组的中间值a[N-2]以及和它相邻的元素a[N/2-1]和a[N/2+1]。如果a[N/2]是一个
 * 局部最小值则算法终止；否则则在较小的相邻元素的半边中继续查找。
 * <p>
 * 分析：数组可能为空，或者只有一个元素或者两个元素。当a[i]的前后两个元素都存在时，
 * 需要满足“a[i] < a[i-1]，且a[i] < a[i+1]”这个条件，但是如果a[i]是第一个元素或者是最后一个元素，
 * 那么只需要看一边。所以对于任何一个数组，“局部最小元素”一定是存在的。
 *
 * @author cheng
 *         2018/1/13 22:19
 */
public class Example18 {
    public static int localMinimum(int[] x) {
        if (x == null || x.length == 0) {
            return -1;
        }
        if (x.length == 1 || x[0] < x[1]) {
            return 0;
        }

        int mid;
        int left = 1;
        int right = x.length - 2;
        while (left < right) {
            mid = (left + right) / 2;
            if (x[mid - 1] < x[mid]) {
                right = mid - 1;
            } else if (x[mid + 1] < x[mid]) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[] a = {2, 1, 3, 4, 5, 6, 11, 14, 8, 4};
        int index = localMinimum(a);
        System.out.println("局部最小元素" + "a[" + index + "]值为" + a[index]);
    }
}

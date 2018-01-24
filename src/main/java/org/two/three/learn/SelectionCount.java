package org.two.three.learn;

import org.tool.SortTestHelper;
import org.two.one.learn.Insertion;

/**
 * 求任意数组第k小(大)的值，即求数组中某个位置的值为多少。
 *
 * @author cheng
 *         2018/1/24 12:11
 */
public class SelectionCount {
    public static Comparable sort(Comparable[] arr, int k) {
        int n = arr.length;
        assert k >= 0 && k < n;
        // 转换为寻找索引为 k-1 的元素的大小
        return sort(arr, 0, n - 1, k - 1);
    }

    /**
     * 求出nums[l...r]范围里第k小的数
     */
    private static Comparable sort(Comparable[] nums, int lo, int hi, int k) {

        if (lo == hi) return nums[lo];

        // partition之后, nums[p]的正确位置就在索引p上
        int p = partition(nums, lo, hi);

        if (k == p)    // 如果 k == p, 直接返回nums[p]
            return nums[p];
        else if (k < p)    // 如果 k < p, 只需要在nums[l...p-1]中找第k小元素即可
            return sort(nums, lo, p - 1, k);
        else // 如果 k > p, 则需要在nums[p+1...r]中找第k小元素
            return sort(nums, p + 1, hi, k);
    }

    /**
     * 对arr[lo...hi]部分进行partition操作
     * 返回p, 使得arr[lo...p-1] < arr[p] ; arr[p+1...hi] > arr[p]
     * partition 过程, 和快排的partition一样
     * 思考: 双路快排和三路快排的思想能不能用在selectionCount算法中?
     * 不能。
     */
    private static int partition(Comparable[] arr, int lo, int hi) {
        // 随机在arr[l...r]的范围中, 选择一个数值作为标定点pivot
        swap(arr, lo, (int) (Math.random() * (hi - lo + 1)) + lo);

        Comparable v = arr[lo];

        int j = lo; // arr[l+1...j] < v ; arr[j+1...i) > v
        for (int i = lo + 1; i <= hi; i++)
            if (arr[i].compareTo(v) < 0) {
                j++;
                swap(arr, j, i);
            }

        swap(arr, lo, j);

        return j;
    }

    private static void swap(Comparable[] arr, int i, int j) {
        Comparable temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {

        int n = 50;
        int k = 20;
        Integer[] arr = SortTestHelper.generateRandomArray(n, 0, 100);
        System.out.println(sort(arr, k));
        Insertion.sort(arr);
        // 按照索引求值，应该 - 1
        System.out.println(arr[k - 1]);
    }
}

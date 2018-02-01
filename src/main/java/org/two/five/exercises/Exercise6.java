package org.two.five.exercises;

import org.tool.ArrayGenerator;

import java.util.Arrays;

/**
 * 2.5.6 用递归实现select()
 * 求任意数组第k小(大)的值，即求数组中某个位置的值为多少。
 *
 * @author cheng
 *         2018/2/1 19:51
 */
public class Exercise6 {

    /**
     * 比较次数
     */
    public static int compares;

    /**
     * 非递归实现
     * 快速排序思想；
     */
    public static int quickSelect(int[] nums, int k) {
        k--;
        if (k < 0 || k > nums.length - 1) throw new IllegalArgumentException("请输入正确的位置!");

        int lo = 0;
        int hi = nums.length - 1;
        while (lo <= hi) {
            swap(nums, lo, (int) (Math.random() * (hi - lo + 1)) + lo);

            int pivot = nums[lo];
            int i = lo;
            int j = hi + 1;

            while (true) {
                while (i < hi && nums[++i] < pivot) ;
                while (nums[--j] > pivot) ;
                if (i >= j) break;
                swap(nums, i, j);
            }
            swap(nums, lo, j);

            if (j > k) hi = j - 1;
            else if (j < k) lo = j + 1;
            else return nums[j];
        }
        return -1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 递归实现
     * 快速排序思想；
     */
    public static int quickSelect2(int[] nums, int k) {
        compares = 0;
        if (k < 0 || k > nums.length - 1) throw new IllegalArgumentException("请输入正确的位置!");
        return quickSort(nums, 0, nums.length - 1, k - 1);
    }

    private static int quickSort(int[] nums, int lo, int hi, int k) {
        if (hi == lo) return nums[lo];

        int p = partition(nums, lo, hi);

        if (k == p)
            return nums[k];
        else if (k > p)
            return quickSort(nums, p + 1, hi, k);
        else
            return quickSort(nums, lo, p - 1, k);
    }

    private static int partition(int[] nums, int lo, int hi) {
        swap(nums, lo, (int) (Math.random() * (hi - lo + 1)) + lo);
        int v = nums[lo];

        int j = lo;
        for (int i = lo + 1; i <= hi; i++) {
            if (nums[i] < v) {
                j++;
                swap(nums, j, i);
            }
            compares++;
        }
        swap(nums, lo, j);
        return j;
    }

    public static void main(String[] args) {
        int[] arr = ArrayGenerator.ints(30, 0, 100);
        int[] copy = Arrays.copyOf(arr, arr.length);
        int k = 9;
        System.out.println("第" + k + "大的数字是：" + quickSelect(arr, k));
        System.out.println("第" + k + "大的数字是：" + quickSelect2(copy, k));

        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}

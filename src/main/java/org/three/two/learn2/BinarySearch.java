package org.three.two.learn2;

import org.tool.SortTestHelper;

/**
 * 二分查找法
 *
 * @author cheng
 *         2018/2/17 15:07
 */
public class BinarySearch {
    /**
     * 在有序数组arr中，查找target
     * 如果找到target，返回相应的索引index
     * 如果找不到target，返会 -1
     */
    public static int binarySearch(int[] arr, int n, int target) {
        // 在 arr[l...r]之中查找target
        int l = 0;
        int r = n - 1;

        while (l <= r) {
            int mid = (r - l) / 2 + l;
            if (arr[mid] > target) r = mid - 1;
            else if (arr[mid] < target) l = mid + 1;
            else return mid;
        }
        return -1;
    }

    /**
     * 递归实现
     */
    public static int binarySearch2(int[] arr, int target) {
        return binarySearch2(arr, target, 0, arr.length - 1);
    }

    private static int binarySearch2(int[] arr, int target, int l, int r) {
        if (l > r) return -1;

        int mid = (r - l) / 2 + l;
        if (arr[mid] < target) return binarySearch2(arr, target, mid + 1, r);
        if (arr[mid] > target) return binarySearch2(arr, target, l, mid - 1);
        return mid;
    }

    /**
     * 二分查找法, 在有序数组arr中, 查找target
     * 如果找到target, 返回第一个target相应的索引index
     * 如果没有找到target, 返回比target小的最大值相应的索引, 如果这个最大值有多个, 返回最大索引
     * 如果这个target比整个数组的最小元素值还要小, 则不存在这个target的floor值, 返回-1
     */
    public static int floor(Comparable[] arr, Comparable target) {
        int l = -1;
        int r = arr.length - 1;
        while (l < r) {
            int mid = l + (r - l + 1) / 2;
            int cmp = arr[mid].compareTo(target);
            if (cmp < 0) l = mid;
            else if (cmp > 0) r = mid - 1;
            else return mid;
        }

        return l;
    }

    /**
     * 二分查找法, 在有序数组arr中, 查找target
     * 如果找到target, 返回最后一个target相应的索引index
     * 如果没有找到target, 返回比target大的最小值相应的索引, 如果这个最小值有多个, 返回最小的索引
     * 如果这个target比整个数组的最大元素值还要大, 则不存在这个target的ceil值, 返回整个数组元素个数n
     */
    private static int ceil(Comparable[] arr, Comparable target) {
        // 寻找比target大的最小索引值
        int l = 0;
        int r = arr.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            int cmp = arr[mid].compareTo(target);
            if (cmp > 0) r = mid;
            else if (cmp <= 0) l = mid + 1;
        }

        // 如果该索引-1就是target本身, 该索引+1即为返回值
        if (r - 1 >= 0 && arr[r - 1] == target)
            return r - 1;

        // 否则, 该索引即为返回值
        return r;
    }

    public static void main(String[] args) {
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        // 非递归
        System.out.println(binarySearch(arr, arr.length, 4));
        // 递归
        System.out.println(binarySearch2(arr, 4));

        int n = 10;
        Integer[] arr2 = SortTestHelper.generateOrderedArray(n);

        // floor
        System.out.println(floor(arr2, 4));

        // ceil
        System.out.println(ceil(arr2, 4));
    }
}

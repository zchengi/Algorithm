package org.three.two.learn2;

import org.tool.SortTestHelper;

import java.util.Arrays;

/**
 * 线性查找法
 *
 * @author cheng
 *         2018/2/23 13:37
 */
public class LinearSearch {
    private LinearSearch() {
    }

    /**
     * 线性查找法，实现lower_bound
     * 即在一个有序数组arr中, 寻找大于等于target的元素的第一个索引
     * 如果存在, 则返回相应的索引index
     * 否则, 返回arr的元素个数 n
     */
    public static int lower_bound(Comparable[] arr, Comparable target) {
        if (arr == null) throw new IllegalArgumentException("Arr can not be null!");

        for (int i = 0; i < arr.length; i++) {
            if (arr[i].compareTo(target) >= 0) {
                return i;
            }
        }
        return arr.length;
    }

    /**
     * 线性查找法，upper_bound
     * 即在一个有序数组arr中, 寻找大于等于target的元素的第一个索引
     * 如果存在, 则返回相应的索引index
     * 否则, 返回arr的元素个数 n
     */
    public static int upper_bound(Comparable[] arr, Comparable target) {
        if ( arr == null) throw new IllegalArgumentException("Arr can not be null!");

        for (int i = 0; i < arr.length; i++) {
            if (arr[i].compareTo(target) > 0) {
                return i;
            }
        }
        return arr.length;
    }


    /**
     * 使用简单的线性查找法来验证二分查找法
     */
    public static void main(String[] args) {
        int n = 1000;
        int m = 1000;
        Integer[] arr = SortTestHelper.generateRandomArray(n,0,m);
        Arrays.sort(arr);

        for (int i = -1; i <= m + 1; i++) {
            if (BinarySearch.lower_bound(arr, i) != LinearSearch.lower_bound(arr, i)) {
                throw new IllegalArgumentException("Lower_bound error!");
            }

            if (BinarySearch.upper_bound(arr, i) != LinearSearch.upper_bound(arr, i)) {
                throw new IllegalArgumentException("Upper_bound error!");
            }
        }
        System.out.println("Test completed!");
    }
}



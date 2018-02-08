package org.two.five.exercises;

import org.tool.SortTestHelper;

import java.util.Arrays;

/**
 * 平行数组的排序。在将平行数组排序时，可以将索引排序并返回一个index[]数组。为Insertion添加一个indirectSort()方法，
 * 接受一个Comparable的对象数组a[]作为参数，但它不会将a[]中的元素重新排列，而是返回一个整型数组index[]使得a[index[0]]
 * 到a[index[N-1]]正好是升序的。
 *
 * @author cheng
 *         2018/2/8 21:42
 */
public class Exercise27 {
    public static int[] indirectSort(Comparable[] arr) {
        int n = arr.length;
        int[] indexes = new int[n];
        for (int i = 0; i < n; i++) {
            indexes[i] = i;
        }
        for (int i = 0; i < n; i++) {
            Comparable temp = arr[indexes[i]];
            int j;
            int indexTemp = indexes[i];
            for (j = i; j > 0 && temp.compareTo(arr[indexes[j - 1]]) < 0; j--) {
                indexes[j] = indexes[j - 1];
            }
            indexes[j] = indexTemp;
        }
        return indexes;
    }

    public static void main(String[] args) {
        int n = 10;
        Integer[] arr = SortTestHelper.generateRandomArray(n, 0, n * 10);
        System.out.println(Arrays.toString(arr));
        int[] indexes = indirectSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[indexes[i]] + " ");
        }
    }
}

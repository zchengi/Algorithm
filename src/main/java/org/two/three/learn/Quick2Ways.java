package org.two.three.learn;

import org.tool.SortTestHelper;
import org.two.one.learn.Insertion;

/**
 * 算法2.5 快速排序
 * 双路扫描
 * 解决了处理大量相同元素，快速排序算法再次退化成O(n^2)级别的问题
 *
 * @author cheng
 *         2018/1/23 22:23
 */
public class Quick2Ways {

    public static void sort(Comparable[] arr) {
        int n = arr.length;
        sort(arr, 0, n - 1);
    }

    private static void sort(Comparable[] arr, int lo, int hi) {

        // 对于小规模数组，使用插入排序
        if (hi - lo <= 15) {
            Insertion.sort(arr, lo, hi);
            return;
        }

        int p = partition(arr, lo, hi);
        sort(arr, lo, p - 1);
        sort(arr, p + 1, hi);
    }

    /**
     * 返回p, 使得arr[lo...p-1] < arr[p] ; arr[p+1...hi] > arr[p]
     */
    private static int partition(Comparable[] arr, int lo, int hi) {

        // 随机在arr[lo...hi]的范围中，选择一个数值作为表定点pivot
        swap(arr, lo, (int) (Math.random() * (hi - lo + 1)) + lo);
        Comparable v = arr[lo];

        // arr[lo+1...i) <= v ; arr(j...hi] >= v
        int i = lo + 1;
        int j = hi;
        while (true) {
            // 注意这里的边界, arr[i].compareTo(v) < 0, 不能是arr[i].compareTo(v) <= 0
            // 思考为什么？
            while (i <= hi && arr[i].compareTo(v) < 0) i++;

            // 注意这里的边界, arr[j].compareTo(v) > 0, 不能是arr[j].compareTo(v) >= 0
            // 思考一下为什么?
            while (j >= lo + 1 && arr[j].compareTo(v) > 0) j--;

            // 对于上面两个边界的设定 答案参考 http://coding.imooc.com/learn/questiondetail/4920.html
            // 不使用“=”是为了避免出现一方含有多个等于v的元素，这样做可以使两颗子树较平衡，防止一棵树过大，一棵过小。

            // 此处 i >= j 和 i = j 对于排序的影响不大，都可以。
            if (i >= j) break;
            swap(arr, i++, j--);
        }
        // 当前j处于小于arr[lo]的右半边的最后一个元素
        swap(arr, lo, j);
        return j;
    }

    private static void swap(Comparable[] arr, int i, int j) {
        Comparable temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {

        // 双路快速排序算法也是一个O(nlogn)复杂度的算法
        // 可以在1秒之内轻松处理100万数量级的数据
        int n = 1000000;
        Integer[] arr = SortTestHelper.generateRandomArray(n, 0, 100000);
        SortTestHelper.testSort("org.two.three.learn.Quick2Ways", arr);
        System.out.println(SortTestHelper.isSorted(arr));
    }
}

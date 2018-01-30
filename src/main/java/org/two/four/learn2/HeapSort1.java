package org.two.four.learn2;

import org.tool.SortTestHelper;

/**
 * 堆排序1
 *
 * @author cheng
 *         2018/1/30 12:04
 */
public class HeapSort1 {

    /**
     * 算法类不允许产生任何实例
     */
    private HeapSort1() {
    }

    /**
     * 对整个arr数组使用HeapSort1排序
     * HeapSort1，将所有的元素一次添加到堆中，在将所有元素从堆中依次取出来，即完成了排序
     * 无论是创建堆还是从堆中依次取出元素的过程，时间复杂度均为O(nlogn)
     * 整个堆排序的整体时间复杂度为O(nlogn)
     * 排序后的数组是从小到大排列
     */
    public static void sort(Comparable[] arr) {
        int n = arr.length;
        MaxHeap<Comparable> maxHeap = new MaxHeap<>(n);

        for (Comparable item : arr) {
            maxHeap.insert(item);
        }
        for (int i = n - 1; i >= 0; i--) {
            arr[i] = maxHeap.extractMax();
        }
    }

    public static void main(String[] args) {
        int n = 1000000;
        Integer[] arr = SortTestHelper.generateRandomArray(n, 0, n * 10);

        // 从小到大排序
        sort(arr);
        System.out.println(SortTestHelper.isSorted(arr));
    }
}

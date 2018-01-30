package org.two.four.learn2;

import org.tool.SortTestHelper;

/**
 * 堆排序2 比1更快一点
 *
 * @author cheng
 *         2018/1/30 12:04
 */
public class HeapSort2 {

    /**
     * 算法类不允许产生任何实例
     */
    private HeapSort2() {
    }

    /**
     * 对整个arr数组使用HeapSort2排序
     * HeapSort2，借助我们的heapify过程创建堆
     * 此时，创建堆的过程时间复杂度为O(n)，将所有元素依次从堆中取出来，时间复杂度为O(nlogn)
     * 堆排序的总体时间复杂度依然是O(nlogn)，但是比HeapSort1性能更优，因为创建堆的性能更优。
     * 排序后的数组是从小到大排列
     */
    public static void sort(Comparable[] arr) {
        int n = arr.length;
        MaxHeap<Comparable> maxHeap = new MaxHeap<>(arr);

        for (int i = n - 1; i >= 0; i--) {
            arr[i] = maxHeap.extractMax();
        }
    }

    public static void main(String[] args) {
        int n = 1000000;
        Integer[] arr = SortTestHelper.generateRandomArray(n, 0, n * 10);

        // 从小到大排序
        SortTestHelper.testSort("org.two.four.learn2.HeapSort2", arr);
        System.out.println(SortTestHelper.isSorted(arr));
    }
}
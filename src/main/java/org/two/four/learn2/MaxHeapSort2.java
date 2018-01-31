package org.two.four.learn2;

import org.tool.SortTestHelper;

import java.util.Arrays;

/**
 * 堆排序2 比1更快一点
 *
 * @author cheng
 *         2018/1/30 12:04
 */
public class MaxHeapSort2 {

    /**
     * 算法类不允许产生任何实例
     */
    private MaxHeapSort2() {
    }

    /**
     * 对整个arr数组使用HeapSort2排序
     * MaxHeapSort2，借助我们的heapify过程创建堆
     * 此时，创建堆的过程时间复杂度为O(n)，将所有元素依次从堆中取出来，时间复杂度为O(nlogn)
     * 堆排序的总体时间复杂度依然是O(nlogn)，但是比HeapSort1性能更优，因为创建堆的性能更优。
     * 排序后的数组是从小到大排列
     */
    public static void sort(Comparable[] arr) {
        int n = arr.length;
        MaxHeap<Comparable> maxHeap = new MaxHeap<>(arr);

        while (n > 0) {
            arr[--n] = maxHeap.extractMax();
        }
    }

    public static void main(String[] args) {
        int n = 1000000;
        Integer[] arr = SortTestHelper.generateRandomArray(n, 0, n * 10);

        // 从小到大排序
        SortTestHelper.testSort("org.two.four.learn2.MaxHeapSort2", arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(SortTestHelper.isSorted(arr));
    }
}
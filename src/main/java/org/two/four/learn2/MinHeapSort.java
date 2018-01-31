package org.two.four.learn2;

import org.tool.SortTestHelper;

/**
 * 最小堆排序
 *
 * @author cheng
 *         2018/1/31 11:59
 */
public class MinHeapSort {
    /**
     * 算法类不允许产生任何实例
     */
    private MinHeapSort() {
    }

    /**
     * 将所有的元素依次添加到最小堆中, 再在将所有元素从堆中依次取出来, 完成排序
     * 使用这样的一个最小堆排序, 来检验我们的最小堆的正确性
     * 思 考：使用最小堆可不可以编写如第6小节所介绍的优化的快速排序算法？
     */
    public static void sort(Comparable[] arr) {
        int n = arr.length;
        MinHeap<Comparable> minHeap = new MinHeap<>(arr);

        for (int i = 0; i < n; i++) {
            arr[i] = minHeap.extractMin();
        }
    }


    public static void main(String[] args) {
        int n = 1000000;
        Integer[] arr = SortTestHelper.generateRandomArray(n, 0, n * 10);

        // 从小到大排序
        SortTestHelper.testSort("org.two.four.learn2.MinHeapSort", arr);
        System.out.println(SortTestHelper.isSorted(arr));
    }
}

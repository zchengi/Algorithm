package org.two.four.learn2;

import org.tool.SortTestHelper;

/**
 * 使用最小索引堆进行堆排序, 来验证我们的最大索引堆的正确性
 *
 * @author cheng
 *         2018/1/30 20:22
 */
public class IndexMinHeapSort {
    public IndexMinHeapSort() {
    }

    public static void sort(Comparable[] arr) {
        int n = arr.length;

        IndexMinHeap<Comparable> indexMinHeap = new IndexMinHeap<>(n);
        for (int i = 0; i < n; i++) {
            indexMinHeap.insert(i, arr[i]);
        }

        for (int i = 0; i < n; i++) {
            arr[i] = indexMinHeap.extractMin();
        }
    }

    public static void main(String[] args) {
        int n = 1000000;
        Integer[] arr = SortTestHelper.generateRandomArray(n, 0, 100000);

        // 测试 Index Min Heap Sort
        sort(arr);
        System.out.println(SortTestHelper.isSorted(arr));
    }
}

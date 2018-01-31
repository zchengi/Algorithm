package org.two.four.learn2;

import org.tool.SortTestHelper;

import java.util.Arrays;

/**
 * 使用最大索引堆进行堆排序，来验证我们的最大索引堆的正确性
 * 最大索引堆的主要作用不是用于排序，我们在这里使用排序只是为了验证我们的最大索引堆实现的正确性。
 * 在后续的图论中，无论是最小生成树算法，还是最短路径算法，我们都需要使用索引堆进行优化......
 *
 * @author cheng
 *         2018/1/30 20:22
 */
public class IndexMaxHeapSort {
    public IndexMaxHeapSort() {
    }

    public static void sort(Comparable[] arr) {
        int n = arr.length;

        IndexMaxHeap<Comparable> indexMaxHeap = new IndexMaxHeap<>(n);
        for (int i = 0; i < n; i++) {
            indexMaxHeap.insert(i, arr[i]);
        }

        for (int i = n - 1; i >= 0; i--) {
            arr[i] = indexMaxHeap.extractMax();
        }
    }

    public static void sort2(Comparable[] arr) {
        int n = arr.length;

        IndexMaxHeap2<Comparable> indexMaxHeap = new IndexMaxHeap2<>(n);
        for (int i = 0; i < n; i++) {
            indexMaxHeap.insert(i, arr[i]);
        }

        for (int i = n - 1; i >= 0; i--) {
            arr[i] = indexMaxHeap.extractMax();
        }
    }

    public static void main(String[] args) {

        int n = 1000000;
        Integer[] arr1 = SortTestHelper.generateRandomArray(n, 0, n);
        Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);

        // 测试 Index Heap Sort(未优化)
        sort(arr1);
        // 测试 Index Heap Sort(优化)
        sort2(arr2);

        System.out.println(SortTestHelper.isSorted(arr1));
        System.out.println(SortTestHelper.isSorted(arr2));
    }
}

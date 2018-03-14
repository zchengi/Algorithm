package org.four.three.learn2;

import java.util.NoSuchElementException;

/**
 * 最小堆
 * 在堆的有关操作中，需要比较堆中元素的大小，所以Item需要extends Comparable
 *
 * @author cheng
 *         2018/3/14 16:24
 */
public class MinHeap<Item extends Comparable> {

    private Item[] data;
    private int count;
    private int capacity;

    public MinHeap(int capacity) {
        data = (Item[]) new Comparable[capacity + 1];
        count = 0;
        this.capacity = capacity;
    }

    public MinHeap(Item arr[]) {
        int n = arr.length;
        data = (Item[]) new Comparable[n + 1];
        capacity = n;

        System.arraycopy(arr, 0, data, 1, n);

        count = n;

        for (int i = count / 2; i >= 1; i--) {
            shiftDown(i);
        }
    }

    public void insert(Item item) {

        if (count + 1 > capacity) throw new NoSuchElementException("Heap overflow.");

        data[++count] = item;
        shiftUp(count);
    }

    public Item extractMin() {
        if (count <= 0) throw new NoSuchElementException("Heap underflow.");

        Item ret = data[1];

        swap(1, count--);
        shiftDown(1);
        return ret;
    }

    private void shiftUp(int k) {
        while (k > 1 && data[k / 2].compareTo(data[k]) > 0) {
            swap(k, k / 2);
            k /= 2;
        }
    }

    private void shiftDown(int k) {

        while (2 * k <= count) {
            // 在这轮循环中，data[k]和data[j]交换位置
            int j = 2 * k;
            if (j + 1 <= count && data[j + 1].compareTo(data[j]) < 0) {
                j++;
            }

            if (data[k].compareTo(data[j]) <= 0) {
                break;
            }
            swap(k, j);
            k = j;
        }
    }

    private void swap(int i, int j) {
        Item t = data[i];
        data[i] = data[j];
        data[j] = t;
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public static void main(String[] args) {

        MinHeap<Integer> minHeap = new MinHeap<Integer>(100);

        // 堆中元素个数
        int N = 100;
        // 堆中元素取值范围[0, M]
        int M = 100;

        for (int i = 0; i < N; i++) {
            minHeap.insert((int) (Math.random() * M));
        }

        Integer[] arr = new Integer[N];

        // 将minHeap中的数据逐渐使用extractMax取出来
        // 取出来的顺序应该是按照从小到大的顺序取出来的
        for (int i = 0; i < N; i++) {
            arr[i] = minHeap.extractMin();
            System.out.print(arr[i] + " ");
        }

        System.out.println();

        // 确保arr数组是从小到大排列的
        for (int i = 1; i < N; i++) {
            if (arr[i - 1] > arr[i]) {
                System.out.println("Array is not ordered.");
            }
        }
    }
}

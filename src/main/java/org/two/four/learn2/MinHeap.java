package org.two.four.learn2;

import java.util.NoSuchElementException;

/**
 * 最小堆
 *
 * @author cheng
 *         2018/1/31 10:20
 */
public class MinHeap<Item extends Comparable> {
    private Item[] data;

    private int count;


    public MinHeap() {
        this(10);
    }

    public MinHeap(int capacity) {
        data = (Item[]) new Comparable[capacity + 1];
        count = 0;
    }

    public MinHeap(Item[] arr) {
        int n = arr.length;
        count = n;

        data = (Item[]) new Comparable[arr.length + 1];
        System.arraycopy(arr, 0, data, 1, n);

        for (int i = count / 2; i >= 1; i--) {
            shiftDown(i);
        }
    }

    public void insert(Item item) {
        if (count == (data.length - 1)) {
            resize(count * 2);
        }
        data[++count] = item;
        shiftUp(count);
    }

    public Item extractMin() {
        if (count == 0) {
            throw new NoSuchElementException("堆中已经没有元素了!");
        }
        Item res = data[1];
        data[1] = data[count--];
        shiftDown(1);

        if (count == (data.length - 1) / 4) {
            resize((data.length - 1) / 2);
        }
        return res;
    }

    /**
     * 获取最小堆中的堆顶元素
     */
    public Item getMin() {
        if (count == 0) {
            throw new NoSuchElementException("堆中已经没有元素了!");
        }
        return data[1];
    }

    private void shiftUp(int k) {
        Item temp = data[k];
        while (k > 1 && less(temp, data[k / 2])) {
            data[k] = data[k / 2];
            k = k / 2;
        }
        data[k] = temp;
    }

    private void shiftDown(int k) {
        Item temp = data[k];
        while (2 * k <= count) {
            int j = 2 * k;
            if (j < count && less(data[j + 1], data[j])) {
                j++;
            }
            if (!less(data[j], temp)) {
                break;
            }
            data[k] = data[j];
            k = j;
        }
        data[k] = temp;
    }

    private boolean less(Item i, Item j) {
        return i.compareTo(j) < 0;
    }

    private void resize(int newCapacity) {
        Item[] copy = (Item[]) new Comparable[newCapacity + 1];
        System.arraycopy(data, 0, copy, 0, count + 1);
        data = copy;
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public static void main(String[] args) {
        MinHeap<Integer> minHeap = new MinHeap<>();

        // 堆中元素个数
        int n = 50;
        // 堆中元素取值范围[0,m)
        int m = 100;
        for (int i = 0; i < n; i++) {
            minHeap.insert((int) (Math.random() * m));
        }

        Integer[] arr = new Integer[n];

        // 将minHeap中的数据逐渐使用extractMin取出来，取出来的顺序应该是按照从小到大的顺序取出来的
        for (int i = 0; i < n; i++) {
            arr[i] = minHeap.extractMin();
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        // 确保arr数组是从小到大排列的
        for (int i = 1; i < n; i++) {
            if (arr[i] < arr[i - 1]) {
                System.out.println("数组排序失败!");
            }
        }
    }
}

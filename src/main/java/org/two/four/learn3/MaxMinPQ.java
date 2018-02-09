package org.two.four.learn3;

import java.util.NoSuchElementException;

/**
 * 同时面对最大和最小元素的优先队列。设计一个数据类型，支持如下操作：插入元素、删除最大元素、
 * 删除最小元素（所需时间均为对数级别），以及找到最大元素、找到最小元素（所需时间均为常数级别）。
 * 提示：用两个堆。
 * <p>
 * 分析：使用两个索引数组建立优先队列，一个维护最大堆，一个维护最小堆；
 * 这里因为使用的是索引数组维护堆，所以在删除的时候，并不能删除data中的元素，只能删除索引数组；
 * 如果要同时满足删除data中元素且是对数级别，我觉得可以使用两个Item[]数组维护堆，一个最大堆，
 * 一个最小堆，不使用索引；相当于完全分开的构造了一个最大堆一个最小堆，只是放在一个类中而已。
 *
 * @author cheng
 *         2018/2/9 22：41
 */
public class MaxMinPQ<Item extends Comparable<Item>> {
    private Item[] data;
    private int[] maxIndexes;
    private int[] minIndexes;

    /**
     * 记录当前堆元素个数
     */
    private int count;
    /**
     * 记录插入元素个数
     */
    private int insertCount;
    /**
     * 记录删除元素个数
     */
    private int deleteCount;

    public MaxMinPQ(int n) {
        data = (Item[]) new Comparable[n + 1];
        maxIndexes = new int[n + 1];
        minIndexes = new int[n + 1];
        count = 0;
        insertCount = 0;
        deleteCount = 0;
    }

    public void insert(Item item) {
        if (count == data.length) {
            throw new NoSuchElementException("Priority queue overflow!");
        }
        data[++count] = item;
        maxIndexes[count] = count;
        minIndexes[count] = count;

        insertCount++;
        shiftUp(count);
    }

    private void shiftUp(int k) {
        int temp = k;
        while (k > 1 && less(maxIndexes[k / 2], maxIndexes[k])) {
            swap(maxIndexes, k / 2, k);
            k = k / 2;
        }
        while (temp > 1 && less(minIndexes[temp], minIndexes[temp / 2])) {
            swap(minIndexes, temp, temp / 2);
            temp = temp / 2;
        }
    }

    public Item delMax() {
        if (isEmpty()) throw new NoSuchElementException("priority queue underflow");

        Item max = getMax();
        maxIndexes[1] = maxIndexes[count--];

        shiftDownForMax(1);
        maxIndexes[count + 1] = 0;
        deleteCount++;
        return max;
    }

    public Item delMin() {
        if (isEmpty()) throw new NoSuchElementException("priority queue underflow");

        Item min = getMin();
        minIndexes[1] = minIndexes[count--];

        shiftDownForMin(1);
        minIndexes[count + 1] = 0;
        deleteCount++;
        return min;
    }

    public void shiftDownForMax(int k) {
        // 从最大索引数组删除索引值
        while (2 * k <= count) {
            int j = 2 * k;
            if (less(maxIndexes[j], maxIndexes[j + 1])) {
                j++;
            }
            if (!less(maxIndexes[k], maxIndexes[j])) break;
            swap(maxIndexes, k, j);
            k = j;
        }
    }

    public void shiftDownForMin(int k) {
        // 从最小索引数组删除索引值
        while (2 * k <= count) {
            int j = 2 * k;
            if (less(minIndexes[j + 1], minIndexes[j])) {
                j++;
            }
            if (!less(minIndexes[j], minIndexes[k])) break;
            swap(minIndexes, k, j);
            k = j;
        }
    }

    public Item getMax() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow!");
        return data[maxIndexes[1]];
    }

    public Item getMin() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow!");
        return data[minIndexes[1]];
    }

    private boolean less(int i, int j) {
        return data[i].compareTo(data[j]) < 0;
    }

    private void swap(int[] ints, int i, int j) {
        int temp = ints[i];
        ints[i] = ints[j];
        ints[j] = temp;
    }

    public boolean isEmpty() {
        return insertCount == deleteCount;
    }

    public static void main(String[] args) {
        int n = 20;
        MaxMinPQ<Integer> heap = new MaxMinPQ<>(n);

        for (int i = 0; i < n; i++) {
            heap.insert((int) (Math.random() * 100) + 1);
        }
        // System.out.println(heap.getMax());
        // System.out.println(heap.getMin());

        while (!heap.isEmpty()) {
            System.out.println(heap.delMax() + " " + heap.delMin());
        }

        /*Integer[] arr = new Integer[n];
        for (int i = 1; i <= n; i++) {
            arr[n - i] = heap.delMax();
        }
        System.out.println(SortTestHelper.isSorted(arr));*/
    }
}

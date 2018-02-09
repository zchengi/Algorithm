package org.two.four.exercises;

import java.util.NoSuchElementException;

/**
 * 2.4.29 同时面对最大和最小元素的优先队列。设计一个数据类型，支持如下操作：插入元素、删除最大元素、
 * 删除最小元素（所需时间均为对数级别），以及找到最大元素、找到最小元素（所需时间均为常数级别）。
 * 提示：用两个堆。
 * <p>
 * 分析：使用两个索引数组建立优先队列，一个维护最大堆，一个维护最小堆；
 *
 * @author cheng
 *         2018/2/9 19:28
 */
public class Exercise29 {
    static class MaxMinPQ<Item extends Comparable<Item>> {
        private Item[] data;
        private int[] maxIndexes;
        private int[] minIndexes;

        /**
         * 记录当前堆元素个数
         */
        private int count;

        public MaxMinPQ(int n) {
            data = (Item[]) new Comparable[n + 1];
            maxIndexes = new int[n + 1];
            minIndexes = new int[n + 1];
            count = 0;
        }

        public void insert(Item item) {
            if (count == data.length) {
                throw new NoSuchElementException("Priority queue overflow!");
            }
            data[++count] = item;
            maxIndexes[count] = count;
            minIndexes[count] = count;

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

            int delDataIndex = maxIndexes[1];
            int minK = getIndex(delDataIndex, minIndexes);

            maxIndexes[1] = maxIndexes[count];
            minIndexes[minK] = minIndexes[count];
            count--;

            shiftDown(1, minK);

            data[delDataIndex] = null;
            maxIndexes[count + 1] = 0;
            minIndexes[count + 1] = 0;
            return max;
        }

        public Item delMin() {
            if (isEmpty()) throw new NoSuchElementException("priority queue underflow");

            Item min = getMin();
            int delDataIndex = minIndexes[1];
            int maxK = getIndex(delDataIndex, maxIndexes);

            minIndexes[1] = minIndexes[count];
            maxIndexes[maxK] = maxIndexes[count];

            count--;
            shiftDown(maxK, 1);

            data[delDataIndex] = null;
            minIndexes[count + 1] = 0;
            maxIndexes[count + 1] = 0;
            return min;
        }

        public void shiftDown(int maxK, int minK) {
            // 从最大索引数组删除索引值
            while (2 * maxK <= count) {
                int j = 2 * maxK;
                if (less(maxIndexes[j], maxIndexes[j + 1])) {
                    j++;
                }
                if (!less(maxIndexes[maxK], maxIndexes[j])) break;
                swap(maxIndexes, maxK, j);
                maxK = j;
            }

            // 从最小索引数组删除索引值
            while (2 * minK <= count) {
                int j = 2 * minK;
                if (less(minIndexes[j + 1], minIndexes[j])) {
                    j++;
                }
                if (!less(minIndexes[j], minIndexes[minK])) break;
                swap(minIndexes, minK, j);
                minK = j;
            }
        }

        /**
         * 获取当前值在另一个索引数组的索引值
         */
        private int getIndex(int index, int[] ints) {
            int k = 0;
            for (int i = 1; i <= count; i++) {
                if (index == ints[i]) k = i;
            }
            return k;
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
            return count == 0;
        }
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

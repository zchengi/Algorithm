package org.two.four.exercises;

import org.tool.SortTestHelper;

import java.util.NoSuchElementException;

/**
 * 2.4.27 找出最小元素。在MaxPQ中加入一个min()方法。你的实现所需的时间和空间都应该是常数。
 *
 * 分析：新增一个min用于记录当前队列中的最小值，在每次insert()和delMax()操作的时候更新min的值；
 *
 * @author cheng
 *         2018/2/6 19:43
 */
public class Exercise27 {
    static class MaxPQ<Item extends Comparable<Item>> {
        private Item[] data;
        private Item min;
        private int count;

        public MaxPQ() {
            this(10);
        }

        public MaxPQ(int capacity) {
            data = (Item[]) new Comparable[capacity + 1];
            count = 0;
            min = null;
        }

        public void insert(Item item) {
            if (item == null) throw new IllegalArgumentException("parameter must be not null!");
            if (count == data.length - 1)
                resize(count << 1);
            int k = ++count;
            while (k > 1 && data[k >> 1].compareTo(item) < 0) {
                data[k] = data[k >> 1];
                k >>= 1;
            }
            data[k] = item;
            if (min == null) min = item;
            else if (min.compareTo(item) < 0) min = item;
        }

        public Item delMax() {
            if (isEmpty()) throw new NoSuchElementException("priority queue overflow!");
            Item max = data[1];
            data[1] = data[count--];

            Item last = data[1];
            int k = 1;
            while ((k << 1) <= count) {
                int j = k << 1;
                if (data[j].compareTo(last) < 0) j++;
                if (last.compareTo(data[j]) >= 0) break;
                data[k] = data[j];
                k = j;
            }
            data[k] = last;
            data[count + 1] = null;

            if (count > 0 && count == (data.length - 1) / 2) resize(count * 2);

            if (count == 0) min = null;
            return max;
        }

        private void resize(int newCapacity) {
            Item[] temp = (Item[]) new Comparable[newCapacity + 1];
            System.arraycopy(data, 1, temp, 1, count);
            data = temp;
        }

        public boolean isEmpty() {
            return count == 0;
        }

        public Item min() {
            return min;
        }
    }

    public static void main(String[] args) {
        int n = 40;
        Integer[] arr = SortTestHelper.generateRandomArray(n, 0, n * n);
        MaxPQ<Integer> maxPQ = new MaxPQ<>();
        for (Integer i : arr) {
            maxPQ.insert(i);
        }
        while (!maxPQ.isEmpty()) {
            System.out.println(maxPQ.delMax() + " 最小值是：" + maxPQ.min());
        }
    }
}

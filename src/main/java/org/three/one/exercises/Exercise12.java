package org.three.one.exercises;

import org.example.Queue;

/**
 * 3.1.12  修改BinarySearchST，用一个Item对象的数组而非两个平行数组来保存键和值。
 * 添加一个构造函数，接受一个Item的数组为参数并将其归并排序。
 *
 * @author cheng
 *         2018/2/11 13:25
 */
public class Exercise12 {
    static class BinarySearchST<Key extends Comparable<Key>, Value> {
        private static class Item<Key, Value> {
            Key key;
            Value value;

            public Item(Key key, Value value) {
                this.key = key;
                this.value = value;
            }

            @Override
            public String toString() {
                return String.format("{ %s %s}", key, value);
            }
        }

        private Item<Key, Value>[] data;
        private int count;

        public BinarySearchST(int n) {
            data = (Item<Key, Value>[]) new Item[n];
            count = 0;
        }

        public BinarySearchST(Item<Key, Value>[] data) {
            this.data = data;
            merge(this.data);
            count = 0;
        }

        public Value get(Key key) {
            for (Item<Key, Value> item : data) {
                if (key.equals(item.key)) {
                    return item.value;
                }
            }
            return null;
        }

        private void merge(Item<Key, Value>[] data) {
            Item<Key, Value>[] aux = (Item<Key, Value>[]) new Item[data.length];
            merge(data, aux, 0, data.length - 1);
        }

        private void merge(Item<Key, Value>[] data, Item<Key, Value>[] aux, int lo, int hi) {
            if (lo >= hi) return;

            int mid = (hi - lo) / 2 + lo;

            merge(data, aux, lo, mid);
            merge(data, aux, mid + 1, hi);
            if (less(data, mid, mid + 1)) return;
            mergeSort(data, aux, lo, mid, hi);
        }

        private void mergeSort(Item<Key, Value>[] arr, Item<Key, Value>[] aux, int lo, int mid, int hi) {
            System.arraycopy(arr, lo, aux, lo, hi - lo + 1);

            int i = lo;
            int j = mid + 1;
            for (int k = i; k <= hi; k++) {
                if (i > mid) arr[k] = aux[j++];
                else if (j > hi) arr[k] = aux[i++];
                else if (less(aux, i, j)) arr[k] = aux[i++];
                else arr[k] = aux[j++];
            }
        }

        public Iterable<Key> keys() {
            Queue<Key> queue = new Queue<>();
            for (Item<Key, Value> item : data) {
                queue.enqueue(item.key);
            }
            return queue;
        }

        private boolean less(Item<Key, Value>[] arr, int i, int j) {
            return arr[i].key.compareTo(arr[j].key) < 0;
        }
    }

    public static void main(String[] args) {
        int n = 10;
        BinarySearchST.Item<Integer, Integer>[] items = new BinarySearchST.Item[n];
        for (int i = 0; i < n; i++) {
            items[i] = new BinarySearchST.Item<>((int) (Math.random() * 100), i);
        }
        BinarySearchST<Integer, Integer> st = new BinarySearchST<>(items);
        for (Integer i : st.keys()) {
            System.out.println(i + " " + st.get(i));
        }
    }
}

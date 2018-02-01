package org.two.four.exercises;

import java.util.Arrays;

import static org.tool.ArrayGenerator.Integers;

/**
 * 2.4.14 对于没有重复元素的大小为N的堆，一次删除最大元素的操作中最少要交换几个元素呢？
 * 构造一个能够达到这个交换次数的大小为15的堆。连续两次删除最大元素呢？三次呢？
 *
 * @author cheng
 *         2018/2/1 23:12
 */
public class Exercise14 {
    static class MaxPQ<Key extends Comparable<Key>> {
        private Key[] keys;
        private int count;
        public int exchanges;

        public MaxPQ(int n) {
            keys = (Key[]) new Comparable[n + 1];
        }

        public void insert(Key key) {
            keys[++count] = key;
            swim(count);
        }

        public Key delMax() {
            Key max = keys[1];
            // 使用 key[count] 作为哨兵
            // 先减小keys的大小，不删除该值
            keys[1] = keys[count--];
            sink(1);
            exchanges++;

            // 避免对象游离
            keys[count + 1] = null;
            return max;
        }

        private void sink(int k) {
            while (k * 2 <= count) {
                int j = k * 2;
                // 使用 key[count] 作为哨兵
                if (less(j, j + 1)) j++;
                if (less(j, k)) break;
                exch(k, j);
                k = j;
            }
        }

        private void swim(int k) {
            while (k > 1 && less(k / 2, k)) {
                exch(k / 2, k);
                k /= 2;
            }
        }

        private void exch(int i, int j) {
            exchanges++;
            Key temp = keys[i];
            keys[i] = keys[j];
            keys[j] = temp;
        }

        private boolean less(int i, int j) {
            return keys[i].compareTo(keys[j]) < 0;
        }

        public boolean isEmpty() {
            return count == 0;
        }
    }

    public static void construct3ExchangesHeap() {
        int N = 15;
        while (true) {
            MaxPQ<Integer> pq = new MaxPQ<>(N);
            for (Integer i : Integers(0, N - 1)) {
                pq.insert(i);
            }

            Integer[] copy = new Integer[N + 1];
            System.arraycopy(pq.keys, 0, copy, 0, N + 1);

            Integer t = pq.delMax();
            if (pq.exchanges < 4) {
                System.out.println("删掉" + t + "的交换次数：" + pq.exchanges);
                System.out.println(Arrays.toString(copy));
                break;
            }
        }
    }

    public static void construct5ExchangesHeap() {
        int N = 15;
        while (true) {
            MaxPQ<Integer> pq = new MaxPQ<>(N);
            for (Integer i : Integers(0, N - 1)) {
                pq.insert(i);
            }

            Integer[] copy = new Integer[N + 1];
            System.arraycopy(pq.keys, 0, copy, 0, N + 1);

            // 连续删 2 次
            Integer t = pq.delMax();
            Integer t1 = pq.delMax();

            if (pq.exchanges < 6) {
                System.out.println("删掉" + t + "、" + t1 + "的交换次数：" + pq.exchanges);
                System.out.println(Arrays.toString(copy));
                break;
            }
        }
    }

    public static void construct8ExchangesHeap() {
        int N = 15;
        while (true) {
            MaxPQ<Integer> pq = new MaxPQ<>(N);
            for (Integer i : Integers(0, N - 1)) {

                pq.insert(i);
            }

            Integer[] copy = new Integer[N + 1];
            System.arraycopy(pq.keys, 0, copy, 0, N + 1);

            // 连续删 3 次
            Integer t = pq.delMax();
            Integer t1 = pq.delMax();
            Integer t2 = pq.delMax();
            if (pq.exchanges < 9) {
                System.out.println("删掉" + t + "、" + t1 + "、" + t2 + "的交换次数：" + pq.exchanges);
                System.out.println(Arrays.toString(copy));
                break;
            }
        }
    }

    public static void main(String[] args) {
        // 删除一个最大元素的最少操作
        construct3ExchangesHeap();
        // 删除两个最大元素的最少操作

        construct5ExchangesHeap();
        // 删除三个最大元素的最少操作
        construct8ExchangesHeap();

        /*
         * 删掉14的交换次数：3
         * [null, 14, 13, 12, 6, 4, 10, 11, 3, 2, 1, 0, 5, 8, 7, 9]
         * 删掉14、13的交换次数：5
         *
         * [null, 14, 13, 12, 8, 6, 10, 11, 5, 0, 3, 1, 7, 4, 2, 9]
         *
         * 删掉14、13、12的交换次数：8
         * [null, 14, 12, 13, 4, 8, 11, 10, 3, 0, 6, 1, 2, 5, 9, 7]
         */
    }
}

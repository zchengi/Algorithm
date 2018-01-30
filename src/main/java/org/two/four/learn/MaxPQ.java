package org.two.four.learn;

import org.tool.SortTestHelper;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 算法2.6 基于堆的优先队列
 *
 * @author cheng
 *         2018/1/29 14:14
 */
public class MaxPQ<Key> implements Iterable<Key> {

    /**
     * 基于堆的完全二叉树
     */
    private Key[] pq;

    /**
     * 存储pq[1..n]中，pq[0]不使用，元素个数
     */
    private int n;

    /**
     * 可选的比较器
     */
    private Comparator<Key> comparator;

    /**
     * 初始化一个空优先队列
     */
    public MaxPQ() {
        this(1);
    }

    /**
     * 初始化一个给定容量的空优先队列
     */
    public MaxPQ(int initCapacity) {
        pq = (Key[]) new Object[initCapacity + 1];
        n = 0;
    }

    /**
     * 初始化一个给定容量的空优先队列及其比较器
     */
    public MaxPQ(int initCapacity, Comparator<Key> comparator) {
        pq = (Key[]) new Object[initCapacity + 1];
        n = 0;
        this.comparator = comparator;
    }

    /**
     * 使用给定的比较器初始化空优先级队列。
     */
    public MaxPQ(Comparator comparator) {
        this(1, comparator);
    }

    /**
     * 用 keys 数组初始化空优先队列。
     * 使用基于sink()的堆构造，花费时间与 keys.length 成比例。
     */
    public MaxPQ(Key[] keys) {
        n = keys.length;
        pq = (Key[]) new Object[n + 1];

        System.arraycopy(keys, 0, pq, 1, n);
        for (int k = n / 2; k >= 1; k--) {
            sink(k);
        }
        assert isMaxHeap();
    }


    public void insert(Key key) {
        if (n == pq.length - 1) {
            resize(pq.length * 2);
        }
        pq[++n] = key;
        swim(n);
        assert isMaxHeap();
    }


    public Key delMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("Priority queue underflow.");
        }

        // 从根结点得到最大的元素
        Key max = pq[1];
        // 将其和最后一个结点交换
        exch(1, n--);
        // 防止对象游离
        pq[n + 1] = null;

        if (n > 0 && n == (pq.length - 1) / 4) {
            resize((pq.length - 1) / 2);
        }

        // 恢复堆的有序性
        sink(1);
        assert isMaxHeap();
        return max;
    }

    /**
     * 由下至上的堆有序化（上浮）
     */
    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    /**
     * 由上至下的堆有序化（下沉）
     */
    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(j, j + 1)) {
                j++;
            }
            if (!less(k, j)) {
                break;
            }
            exch(k, j);
            k = j;
        }
    }

    private void resize(int newCapacity) {
        assert newCapacity > n;

        Key[] temp = (Key[]) new Object[newCapacity];
        System.arraycopy(pq, 1, temp, 1, n);

        pq = temp;
    }

    /**
     * 返回优先队列中最大的键
     */
    public Key max() {
        if (isEmpty()) {
            throw new NoSuchElementException("Priority queue underflow");
        }
        return pq[1];
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    private boolean less(int i, int j) {
        if (comparator == null) {
            return ((Comparable<Key>) pq[i]).compareTo(pq[j]) < 0;
        } else {
            return comparator.compare(pq[i], pq[j]) < 0;
        }
    }

    private void exch(int i, int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    /**
     * 是否是二叉堆
     */
    private boolean isMaxHeap() {
        return isMaxHeap(1);
    }

    /**
     * 递归判断 k 是否在子树中值最大
     */
    private boolean isMaxHeap(int k) {
        if (k > n) return true;

        int left = 2 * k;
        int right = (2 * k) + 1;
        if (left <= n && less(k, left)) return false;
        if (right <= n && less(k, right)) return false;

        return isMaxHeap(left) && isMaxHeap(right);
    }


    @Override
    public Iterator<Key> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Key> {
        private MaxPQ<Key> copy;

        public HeapIterator() {
            if (comparator == null) {
                copy = new MaxPQ<>(size());
            } else {
                copy = new MaxPQ<>(size(), comparator);
            }
            for (int i = 1; i <= n; i++) {
                copy.insert(pq[i]);
            }
        }

        @Override
        public Key next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return copy.delMax();
        }

        @Override
        public boolean hasNext() {
            return !copy.isEmpty();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        MaxPQ<Integer> pq = new MaxPQ<>();
        int n = 20;
        Integer[] arr = SortTestHelper.generateRandomArray(n, 0, 100);
        for (Integer item : arr) {
            pq.insert(item);
        }

        for (Integer item : pq) {
            System.out.print(item + " ");
        }
    }
}

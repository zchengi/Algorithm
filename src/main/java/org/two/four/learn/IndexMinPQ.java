package org.two.four.learn;

import edu.princeton.cs.algs4.StdOut;
import org.tool.SortTestHelper;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 2.4.4.6 关联索引的泛型优先队列
 * 从小到大
 * @author cheng
 *         2018/1/29 16:23
 */
public class IndexMinPQ<Key extends Comparable<Key>> implements Iterable<Integer> {

    /**
     * pq上的最大元素数量
     */
    private int maxN;

    /**
     * pq上的元素数量
     */
    private int n;

    /**
     * 记录优先队列的索引的数组（基于1）
     */
    private int[] pq;

    /**
     * 将pq颠倒的数组 ：  qp[pq[i]] = pq[qp[i]] = i
     * 也就是将 pq的索引作为值，值作为索引
     * 不存在索引的值为 -1；
     */
    private int[] qp;

    /**
     * 优先队列的元素
     */
    private Key[] keys;

    public IndexMinPQ(int maxN) {
        if (maxN < 0) {
            throw new IllegalArgumentException("非法参数!");
        }
        this.maxN = maxN;
        n = 0;
        keys = (Key[]) new Comparable[maxN + 1];

        pq = new int[maxN + 1];
        qp = new int[maxN + 1];
        for (int i = 0; i <= maxN; i++) {
            qp[i] = -1;
        }

    }

    /**
     * 根据索引插入key
     */
    public void insert(int i, Key key) {
        if (i < 0 || i > maxN) {
            throw new IllegalArgumentException("非法参数!");
        }
        if (contains(i)) {
            throw new IllegalArgumentException("该索引已被使用!");
        }

        n++;
        qp[i] = n;
        pq[n] = i;
        keys[i] = key;
        swim(n);
    }

    /**
     * 根据索引删除key
     */
    public void delete(int i) {
        if (n == 0) {
            throw new NoSuchElementException("优先队列下溢出!");
        }
        if (contains(i)) {
            throw new IllegalArgumentException("该索引不存在!");
        }
        int index = qp[i];
        exch(index, n--);
        swim(index);
        sink(index);
        keys[i] = null;
        qp[i] = -1;
    }

    /**
     * 删除最小key并返回索引
     */
    public int delMin() {
        if (n == 0) {
            throw new NoSuchElementException("优先队列下溢出!");
        }
        int min = pq[1];
        exch(1, n--);
        sink(1);

        assert min == pq[n + 1];
        qp[min] = -1;
        keys[min] = null;
        pq[n + 1] = -1;
        return min;
    }

    /**
     * 返回最小key，即值
     */
    public Key minKey() {
        if (n == 0) {
            throw new NoSuchElementException("优先队列下溢出!");
        }
        return keys[pq[1]];
    }

    /**
     * 根据索引返回key
     */
    public Key keyOf(int i) {
        if (n == 0) {
            throw new NoSuchElementException("优先队列下溢出!");
        }
        if (contains(i)) {
            throw new IllegalArgumentException("该索引不存在!");
        }
        return keys[i];
    }

    /**
     * 返回与最小key关联的索引
     */
    public int minIndex() {
        if (n == 0) {
            throw new NoSuchElementException("优先队列下溢出!");
        }
        return pq[1];
    }

    public void change(int i, Key key) {
        changeKey(i, key);
    }

    /**
     * 根据索引修改key
     */
    public void changeKey(int i, Key key) {
        if (i < 0 || i >= maxN) {
            throw new IllegalArgumentException("非法参数!");
        }
        if (!contains(i)) {
            throw new NoSuchElementException("该索引不存在!");
        }
        keys[i] = key;
        swim(qp[i]);
        sink(qp[i]);
    }

    /**
     * 根据索引和新key减小当前key
     */
    public void decreaseKey(int i, Key key) {
        if (i < 0 || i >= maxN) {
            throw new IllegalArgumentException("非法参数!");
        }
        if (!contains(i)) {
            throw new NoSuchElementException("该索引不存在!");
        }

        if (keys[i].compareTo(key) <= 0) {
            throw new IllegalArgumentException("当前索引的值小于给定的值!");
        }
        keys[i] = key;
        swim(qp[i]);
    }

    /**
     * 根据索引和新key增大当前key
     */
    public void increaseKey(int i, Key key) {
        if (i < 0 || i >= maxN) {
            throw new IllegalArgumentException("非法参数!");
        }
        if (!contains(i)) {
            throw new NoSuchElementException("该索引不存在!");
        }

        if (keys[i].compareTo(key) >= 0) {
            throw new IllegalArgumentException("当前索引的值小于给定的值!");
        }
        keys[i] = key;
        sink(qp[i]);
    }

    /**
     * 是否有索引为i的元素
     */
    public boolean contains(int i) {
        if (i < 0 || i > maxN) {
            throw new IllegalArgumentException("非法参数!");
        }
        return qp[i] != -1;
    }

    private void swim(int k) {
        while (k > 1 && greater(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && greater(j, j + 1)) {
                j++;
            }
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    private void exch(int i, int j) {
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    /**
     * 辅助函数
     */
    public boolean greater(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Integer> {

        private IndexMinPQ<Key> copy;

        public HeapIterator() {
            copy = new IndexMinPQ<>(pq.length - 1);
            for (int i = 1; i <= n; i++) {
                copy.insert(pq[i], keys[pq[i]]);
            }
        }

        @Override
        public boolean hasNext() {
            return !copy.isEmpty();
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return copy.delMin();
        }

        @Override
        public void remove() {
        }
    }


    public static void main(String[] args) {
        int n = 20;
        Integer[] arr = SortTestHelper.generateNearlyOrderedArray(n, 50);

        IndexMinPQ<Integer> pq = new IndexMinPQ<>(arr.length);
        for (int i = 0; i < arr.length; i++) {
            pq.insert(i, arr[i]);
        }

        // 删除和打印每个key
        while (!pq.isEmpty()) {
            int i = pq.delMin();
            System.out.print(i + ":" + arr[i] + " ");
        }
        System.out.println(" ");

        // 重新插入相同的数字
        for (int i = 0; i < arr.length; i++) {
            pq.insert(i, arr[i]);
        }

        // 使用迭代器打印每个key
        for (int i : pq) {
            StdOut.print(i + ":" + arr[i]+" ");
        }
        while (!pq.isEmpty()) {
            pq.delMin();
        }
    }
}

package org.cheng.day8;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

/**
 * 1.3.35
 * 随机队列。
 * 使用动态数组表示数据.
 * <p>
 * -----------------------------------------------------
 * public class RandomQueue<Item> implements Iterable<Item>
 * -----------------------------------------------------
 * RandomQueue()            创建一条空的随机队列
 * boolean isEmpty()        队列是否为空
 * void enqueue(Item item)  添加一个元素
 * Item dequeue()           删除并随机返回一个元素（取样且不放回）
 * Item sample()            随机返回一个元素但不删除它（取样且放回）
 * -----------------------------------------------------
 *
 * @author cheng
 *         2017/10/23 13:25
 */
public class RandomQueue<Item> implements Iterable<Item> {

    @SuppressWarnings("unchecked")
    private Item[] a = (Item[]) new Object[1];

    private int N;

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    @SuppressWarnings("unchecked")
    private void resize(int cap) {
        Item[] tmp = (Item[]) new Object[cap];
        for (int i = 0; i < N; i++) {
            tmp[i] = a[i];
        }
        a = tmp;
    }

    public void enqueue(Item item) {
        if (N == a.length) {
            resize(2 * a.length);
        }
        a[N++] = item;
    }

    public Item dequeue() {
        if (isEmpty()) {
            return null;
        }
        int index = StdRandom.uniform(N);
        Item indexTmp = a[index];
        a[index] = a[N - 1];
        a[N - 1] = indexTmp;
        Item temp = a[--N];
        a[N] = null;
        if (N == a.length / 4) {
            resize(a.length / 2);
        }
        return temp;
    }

    public Item sample() {
        if (isEmpty()) {
            return null;
        }
        int index = StdRandom.uniform(N);
        return a[index];
    }

    /**
     * 1.3.36
     * 随机迭代器
     * 随机返回队列中的所有元素
     */
    public Iterator<Item> iterator() {
        return new ReverseIterator();
    }

    private class ReverseIterator implements Iterator<Item> {

        private int i = N;

        @Override
        public boolean hasNext() {
            return N > 0;
        }

        @Override
        public Item next() {
            return a[--N];
        }

        @Override
        public void remove() {
        }
    }

    public static void main(String[] args) {
        RandomQueue<Integer> queue = new RandomQueue<Integer>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        System.out.println(queue.sample());
        System.out.println(queue.sample());
        System.out.println(queue.sample());
    }
}



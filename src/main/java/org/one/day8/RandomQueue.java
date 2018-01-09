package org.one.day8;

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

    private int size;

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    @SuppressWarnings("unchecked")
    private void resize(int cap) {
        Item[] tmp = (Item[]) new Object[cap];
        for (int i = 0; i < size; i++) {
            tmp[i] = a[i];
        }
        a = tmp;
    }

    public void enqueue(Item item) {
        if (size == a.length) {
            resize(2 * a.length);
        }
        a[size++] = item;
    }

    public Item dequeue() {
        if (isEmpty()) {
            return null;
        }
        int index = StdRandom.uniform(size);
        Item indexTmp = a[index];
        a[index] = a[size - 1];
        a[size - 1] = indexTmp;
        Item temp = a[--size];
        a[size] = null;
        if (size == a.length / 4) {
            resize(a.length / 2);
        }
        return temp;
    }

    public Item sample() {
        if (isEmpty()) {
            return null;
        }
        int index = StdRandom.uniform(0, size);
        return a[index];
    }

    /**
     * 1.3.36
     * 随机迭代器
     * 随机返回队列中的所有元素
     */
    public Iterator<Item> iterator() {
        return new RandomQueueIterator();
    }

    private class RandomQueueIterator implements Iterator<Item> {

        private int index;

        private Item[] iteratorArrays;

        @SuppressWarnings("unchecked")
        private RandomQueueIterator() {
            iteratorArrays = (Item[]) new Object[size];
            for (int i = 0; i < size; i++) {
                iteratorArrays[i] = a[i];
            }
            StdRandom.shuffle(iteratorArrays);
            index = 0;
        }


        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public Item next() {
            return iteratorArrays[index++];
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
        for (Integer i : queue) {
            System.out.print(i + " ");
        }
    }
}



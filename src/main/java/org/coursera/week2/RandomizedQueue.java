package org.coursera.week2;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author cheng
 *         2018/1/9 15:56
 *         <p>
 *         Programming Assignment 2: Deques and Randomized Queues
 *         题目2：随机队列 Randomized Queue
 *         -分析：
 *         该队列每次移除的元素是随机的，性能要求提到迭代器的next方法必须是常数时间，很容易发现链表不
 *         满足该需求，所以改用数组实现。
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] rqArrays;

    private int size;

    public RandomizedQueue() {
        rqArrays = (Item[]) new Object[1];
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void resize(int cap) {
        Item[] temp = (Item[]) new Object[cap];
        for (int i = 0; i < size; i++) {
            temp[i] = rqArrays[i];
        }
        rqArrays = temp;
    }

    public void enqueue(Item item) {
        validate(item);
        rqArrays[size++] = item;
        if (size == rqArrays.length) {
            resize(2 * size);
        }
    }

    public Item dequeue() {
        // 随机选取一个位置，将这个位置的元素与队列末尾的元素交换，
        // 即dequeue末尾元素时就达到随机remove元素的目的。
        if (size == 0) {
            throw new NoSuchElementException("这个队列是空的!");
        }
        int r = StdRandom.uniform(0, size);
        size--;
        Item delItem = rqArrays[r];
        rqArrays[r] = rqArrays[size];
        rqArrays[size] = null;
        if (size > 0 && size == rqArrays.length / 4) {
            resize(rqArrays.length / 2);
        }
        return delItem;
    }

    public Item sample() {
        if (size == 0) {
            throw new NoSuchElementException("这个队列是空的!");
        }
        return rqArrays[StdRandom.uniform(0, size)];
    }

    private void validate(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("该元素不能为空!");
        }
    }

    @Override
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item> {
        // 记录遍历的次数
        private int rank;
        // 两个迭代器必须相互独立，并且自己有自己的随机顺序
        private Item[] iteratorArrays;

        public RandomIterator() {
            rank = size;
            iteratorArrays = (Item[]) new Object[rank];
            for (int i = 0; i < size; i++) {
                iteratorArrays[i] = rqArrays[i];
            }
        }

        @Override
        public boolean hasNext() {
            return (rank > 0);
        }

        @Override
        public Item next() {
            if (rank == 0) {
                throw new NoSuchElementException("没有更多元素!");
            }
            // 随机选取一个位置的元素返回
            int r = StdRandom.uniform(0, rank);
            rank--;
            Item item = iteratorArrays[r];
            iteratorArrays[r] = iteratorArrays[rank];
            // 将已经遍历过的元素放到当前队列末尾，这样下次迭代就不会被选到
            iteratorArrays[rank] = item;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("不支持该操作!");
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        rq.enqueue("a");
        rq.enqueue("b");
        rq.enqueue("c");
        rq.enqueue("d");
        rq.enqueue("e");
        rq.enqueue("f");
        rq.enqueue("g");
        System.out.println(rq.dequeue());
        for (String s : rq) {
            System.out.print(s + " ");
        }
        System.out.println();
        for (String s : rq) {
            System.out.print(s + " ");
        }
    }

}

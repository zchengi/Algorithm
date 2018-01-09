package org.one.day8;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

/**
 * 1.3.34
 * 随机背包。
 * 用数组保存所有元素并在迭代器的构造函数中随机打乱它们的顺序。
 * <p>
 * -----------------------------------------------------
 * public class RandomBag<Item> implements Iterable<Item>
 * -----------------------------------------------------
 * RandomBag()          创建一个空随机背包
 * boolean isEmpty()    背包是否为空
 * int size()           背包中的元素数量
 * void add(Item item)  添加一个元素
 * -----------------------------------------------------
 *
 * @author cheng
 *         2017/10/23 13:28
 */
public class RandomBag<Item> implements Iterable<Item> {

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
    public void resize(int cap) {
        Item[] temp = (Item[]) new Object[cap];
        for (int i = 0; i < size; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    public void add(Item item) {
        if (size == a.length) {
            resize(2 * size);
        }
        a[size++] = item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item> {

        int[] randomArray = randomInt(size);
        int N = size;

        @Override
        public boolean hasNext() {
            return size > 0;
        }

        @Override
        public Item next() {
            return a[randomArray[--size]];
        }

        @Override
        public void remove() {
        }
    }

    private int[] randomInt(int N) {
        int[] tmp = new int[N];
        for (int i = 0; i < N; i++) {
            tmp[i] = i;
        }
        for (int i = N; i > 0; i--) {
            int index = StdRandom.uniform(i);
            int temp = tmp[index];
            tmp[index] = tmp[i - 1];
            tmp[i - 1] = temp;
        }
        return tmp;
    }

    public static void main(String[] args) {
        RandomBag<Integer> randomBag = new RandomBag<Integer>();
        randomBag.add(1);
        randomBag.add(2);
        randomBag.add(3);
        randomBag.add(4);
        for (int i : randomBag) {
            System.out.println(i);
        }
    }
}

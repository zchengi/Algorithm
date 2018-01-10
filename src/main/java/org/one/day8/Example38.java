package org.one.day8;

import java.util.Iterator;

/**
 * 1.3.38 删除第k个元素
 * 用链表实现该数据类型
 * <p>
 * --------------------------------------------
 * public class GeneralizedQueue<Item>
 * --------------------------------------------
 * GeneralizedQueue()       创建一条空队列
 * boolean isEmpty()        队列是否为空
 * void insert(Item x)      添加一个元素
 * Item delete(int k)       删除并返回最早插入的第k个元素
 * --------------------------------------------
 *
 * @author cheng
 *         2018/1/10 19:43
 */
@SuppressWarnings("unchecked")
public class Example38<Item> implements Iterable<Item> {

    private Item[] a;

    private int size;

    class Node {
        Node node;
        Item item;
    }

    public Example38() {
        a = (Item[]) new Object[1];
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void insert(Item x) {
        if (size == a.length) {
            resize(size * 2);
        }
        a[size++] = x;
    }

    private void resize(int newSize) {
        Item[] temp = (Item[]) new Object[newSize];
        System.arraycopy(a, 0, temp, 0, a.length);
        a = temp;
    }

    public Item delete(int k) {
        if (k > size || k < 0) {
            return null;
        }
        if (size == a.length / 4) {
            resize(a.length / 2);
        }
        Item target = a[--k];
        Item[] temp = (Item[]) new Object[size];
        for (int i = 0; i < size; i++) {
            if (i < k) {
                temp[i] = a[i];
            } else {
                temp[i] = a[i + 1];
            }
        }
        a = temp;
        a[--size] = null;
        return target;
    }

    @Override
    public Iterator<Item> iterator() {
        return new GeneralizedQueueIterator();
    }

    private class GeneralizedQueueIterator implements Iterator<Item> {

        private Item[] temp;
        private int index;

        public GeneralizedQueueIterator() {
            temp = (Item[]) new Object[size];
            System.arraycopy(a, 0, temp, 0, size);

            index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public Item next() {
            return temp[index++];
        }

        @Override
        public void remove() {
        }
    }

    public static void main(String[] args) {
        Example38<String> ex = new Example38<String>();
        ex.insert("a");
        ex.insert("b");
        ex.insert("c");
        ex.insert("d");
        ex.insert("e");
        System.out.println(ex.delete(5));
        for (String s : ex) {
            System.out.print(s + " ");
        }
    }
}

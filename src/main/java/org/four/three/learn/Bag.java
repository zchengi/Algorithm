package org.four.three.learn;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 背包：实现邻接表的数据结构
 * <p>
 * 添加新的边或遍历任意顶点的所有相邻顶点的时间复杂度：O(1)
 *
 * @author cheng
 *         2018/3/15 15:16
 */
public class Bag<Item> implements Iterable<Item> {

    private Node<Item> first;
    private int count;

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    public Bag() {
        first = null;
        count = 0;
    }

    public void add(Item item) {

        Node<Item> oldFirst = first;
        first = new Node<>();
        first.item = item;
        first.next = oldFirst;
        count++;
    }


    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {

        private Node<Item> current;

        public ListIterator() {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("Bag underflow!");

            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return count;
    }

    public static void main(String[] args) {

        Bag<Integer> bag = new Bag<>();
        Integer[] arr = {2, 4, 5, 6, 1, 3, 3};
        for (Integer i : arr) {
            bag.add(i);
        }

        for (Integer i : bag) {
            System.out.print(i + " ");
        }
    }
}

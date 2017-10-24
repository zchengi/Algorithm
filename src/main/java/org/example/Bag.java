package org.example;

import java.util.Iterator;

/**
 * 算法 1.4 背包
 *
 * @author one
 *         2017/10/20 13:51
 */
public class Bag<Item> implements Iterable<Item> {

    private Node first;
    private int N;

    private class Node {
        Item item;
        Node next;
    }

    public void add(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        N++;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
        }
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return N;
    }
}

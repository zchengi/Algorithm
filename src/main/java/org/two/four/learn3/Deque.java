package org.two.four.learn3;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 双向队列（double queue）
 *
 * @author cheng
 *         2018/2/6 17:37
 */
public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int size;

    private class Node {
        Item item;
        Node prev;
        Node next;
    }

    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("param must be not null!");
        Node newNode = new Node();
        newNode.item = item;
        newNode.prev = null;
        // 空队列
        if (size == 0) {
            first = last = newNode;
            newNode.next = null;
        } else {
            newNode.next = first;
            first.prev = newNode;
            first = newNode;
        }
        size++;
    }

    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("param must be not null!");
        Node newNode = new Node();
        newNode.item = item;
        newNode.next = null;
        // 空队列情况
        if (size == 0) {
            first = last = newNode;
            newNode.next = null;
        } else {
            newNode.prev = last;
            last.next = newNode;
            last = newNode;
        }
        size++;
    }

    public Item removeFirst() {
        if (isEmpty()) throw new NullPointerException("Deque overflow!");

        Item item = first.item;
        if (size == 1) {
            first = null;
            last = null;
        } else {
            first = first.next;
            // 防止内存泄漏
            first.prev.next = null;
            first.prev.item = null;
            first.prev = null;
        }
        size--;
        return item;
    }

    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Deque overflow!");

        Item item = last.item;
        if (size == 1) {
            first = null;
            last = null;
        } else {
            last = last.prev;
            last.next.prev = null;
            last.next.item = null;
            last.next = null;
        }
        size--;
        return item;
    }

    public Item peek() {
        Node temp = first;
        return (temp == null) ? null : temp.item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (current == null) {
                throw new NoSuchElementException("Deque overflow!");
            }
            Item temp = current.item;
            current = current.next;
            return temp;
        }

        @Override
        public void remove() {
        }
    }

    public static void main(String[] args) {
        Deque<String> dq = new Deque<>();
        dq.addFirst("A");
        dq.addFirst("B");
        dq.addFirst("C");
        dq.addLast("Q");
        dq.addLast("E");
        dq.addLast("D");
        for (String s : dq) {
            System.out.println(s);
        }
        StdOut.println("remove first" + dq.removeFirst());
        StdOut.println("remove last" + dq.removeLast());
        for (String s : dq) {
            System.out.println(s);
        }
    }
}

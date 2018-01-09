package org.one.day7;

import java.util.Iterator;

/**
 * 1.3.29
 * 用环形链表实现Queue。
 * 环形链表也是一条链表，只是没有任何结点链接为空，且只要链表非空则last.next的值就为first。
 * 只能使用一个Node类型的实例变量(last)。
 *
 * @author cheng
 *         2017/10/22 12:23
 */
public class CircleQueue<Item> implements Iterable<Item> {

    private Node last;

    private class Node {
        private Item item;
        private Node next;
    }

    public CircleQueue() {
        last = null;
    }

    public boolean isEmpty() {
        return last == null;
    }

    public Item dequeue() {
        if (isEmpty()) {
            return null;
        }
        Item item = last.next.item;
        if (last.next == last) {
            last = null;
        } else {
            last.next = last.next.next;
        }
        return item;
    }

    public void enqueue(Item item) {
        Node node = new Node();
        node.item = item;
        if (last == null) {
            last = node;
            node.next = node;
        } else {
            node.next = last.next;
            last.next = node;
            last = node;
        }
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iter();
    }

    private class Iter implements Iterator<Item> {

        private Node first;
        private boolean one;

        public Iter() {
            if (last == null) {
                first = null;
            } else {
                first = last.next;
                one = (last == last.next);
            }
        }

        @Override
        public boolean hasNext() {
            if (last == last.next) {
                if (one) {
                    one = false;
                    return true;
                } else {
                    return false;
                }
            } else {
                return first != null;
            }
        }

        @Override
        public Item next() {
            Item item;
            if (last == last.next) {
                first = null;
                item = last.item;
            } else {
                item = first.item;
                first = first.next;
                if (first == last.next) {
                    first = null;
                }
            }
            return item;
        }

        @Override
        public void remove() {
        }
    }

    public static void main(String[] args) {
        CircleQueue<String> queue = new CircleQueue<String>();
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
        queue.enqueue("d");
        queue.enqueue("e");
        for (String s : queue) {
            System.out.println(s);
        }
        System.out.println("dequeue: ");
        String s;
        while ((s = queue.dequeue()) != null) {
            System.out.println(s);
        }
    }
}

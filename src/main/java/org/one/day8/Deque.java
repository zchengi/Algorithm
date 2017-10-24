package org.one.day8;

import java.util.Iterator;

/**
 * 1.3.33
 * 一个双向队列和栈或队列累死，但它同时支持在两端添加或删除元素。
 * Deque能够存储一组元素并支持如下API。
 * 使用一个动态数组调整实现这份API的 ResizingArrayDeque类。
 * <p>
 * -----------------------------------------------------
 * public class Deque<Item> implements Iterable<Item>
 * -----------------------------------------------------
 * Deque()                       创建空双向队列
 * boolean isEmpty()             双向队列是否为空
 * int size()                    双向队列中元素的数量
 * void pushLeft(Item item)      向左端添加一个新元素
 * void pushRight(Item item)     向右端添加一个新元素
 * Item popLeft()                从左羰删除一个元素
 * Item popRight()               从右羰删除一个元素
 * -----------------------------------------------------
 *
 * @author one
 *         2017/10/23 12:25
 */
public class Deque<Item> implements Iterable<Item> {

    private Node head = null;
    private Node tail = null;
    private int size = 0;

    private class Node {
        public Item item;
        public Node prev;
        public Node next;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return size;
    }

    public void pushLeft(Item item) {
        Node node = new Node();
        node.item = item;
        node.prev = null;
        if (isEmpty()) {
            head = tail = node;
            node.next = null;
        } else {
            head.prev = node;
            node.next = head;
            head = node;
        }
        size++;
    }

    public void pushRight(Item item) {
        Node node = new Node();
        node.item = item;
        node.next = null;
        if (isEmpty()) {
            head = tail = node;
            node.prev = null;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        size++;
    }

    public Item popLeft() {
        if (isEmpty()) {
            return null;
        } else {
            Item e = head.item;
            if (size() == 1) {
                head = tail = null;
            } else {
                head = head.next;
                head.prev.next = null;
                head.prev = null;
            }
            size--;
            return e;
        }
    }

    public Item popRight() {
        if (isEmpty()) {
            return null;
        } else {
            Item e = tail.item;
            if (size() == 1) {
                head = tail = null;
            } else {
                tail = tail.prev;
                tail.next.prev = null;
                tail.next = null;
            }
            size--;
            return e;
        }
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iter();
    }

    private class Iter implements Iterator<Item> {

        private Node current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item e = current.item;
            current = current.next;
            return e;
        }

        @Override
        public void remove() {
        }
    }

    public static void main(String[] args) {
        // Deque<String> deque = new Deque<String>();
        ResizingArrayDeque<String> deque = new ResizingArrayDeque<String>();
        deque.pushLeft("c");
        deque.pushLeft("b");
        deque.pushRight("d");
        deque.pushRight("e");
        System.out.println("deque size: " + deque.size());
        for (String s : deque) {
            System.out.print(s+" ");
        }

        System.out.println("\rpop up from right: ");
        while (!deque.isEmpty()) {
            System.out.println(deque.popRight());
        }

        deque.pushLeft("c");
        deque.pushLeft("b");
        deque.pushRight("d");
        deque.pushRight("e");
        System.out.println("pop up from left: ");
        while (!deque.isEmpty()) {
            System.out.println(deque.popLeft());
        }
    }

}

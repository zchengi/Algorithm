package org.coursera.week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author cheng
 *         2018/1/9 14:51
 *         <p>
 *         Programming Assignment 2: Deques and Randomized Queues
 *         题目1：双端队列 Deque
 *         -分析
 *         要求可以同时从头尾移除元素，那么该队列内部采用链表更合适
 */
public class Deque<Item> implements Iterable<Item> {

    /**
     * 8 bytes
     */
    private Node first;
    /**
     * 8 bytes
     */
    private Node last;
    /**
     * 4 bytes
     */
    private int size;

    /**
     * 16 bytes对象开销 + 8 bytes内部类额外开销 +8+8+8=48 bytes,n个节点就是 48n
     */
    private class Node {
        // 前一个节点的引用
        private Node preNode;
        private Item item;
        // 后一个节点的引用
        private Node nextNode;
    }

    /**
     * 构造一个空的双端队列
     */
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    /**
     * 判断是否为空
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 返回deque上的项目数量
     */
    public int size() {
        return size;
    }

    /**
     * 将元素添加到前面
     */
    public void addFirst(Item item) {
        validate(item);
        Node newNode = new Node();
        newNode.item = item;
        newNode.preNode = null;
        // 空队列情况
        if (size == 0) {
            first = last = newNode;
            newNode.nextNode = null;
        } else {
            newNode.nextNode = first;
            first.preNode = newNode;
            first = newNode;
        }
        size++;
    }

    /**
     * 将元素添加到后面
     */
    public void addLast(Item item) {
        validate(item);
        Node newNode = new Node();
        newNode.item = item;
        newNode.nextNode = null;
        // 空队列情况
        if (size == 0) {
            first = last = newNode;
            newNode.preNode = null;
        } else {
            newNode.preNode = last;
            last.nextNode = newNode;
            last = newNode;
        }
        size++;
    }

    /**
     * 从前面删除并返回元素
     */
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("这个队列是空的!");
        }
        Item returnItem = first.item;
        if (size == 1) {
            first = null;
            last = null;
        } else {
            first = first.nextNode;
            first.preNode.nextNode = null;
            first.preNode.item = null;
            first.preNode = null;
        }
        size--;
        return returnItem;
    }

    /**
     * 从后面删除并返回元素
     */
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("这个队列是空的!");
        }
        Item returnItem = last.item;
        if (size == 1) {
            first = null;
            last = null;
        } else {
            last = last.preNode;
            last.nextNode.preNode = null;
            last.nextNode.item = null;
            last.nextNode = null;
        }
        size--;
        return returnItem;
    }

    /**
     * 检查元素是否合法
     */
    private void validate(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("不能为空!");
        }
    }

    /**
     * 返回一个按顺序从头到尾排序的迭代器
     */
    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (current == null) {
                throw new NoSuchElementException("没有更多的元素!");
            }
            Item item = current.item;
            current = current.nextNode;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("不支持此操作!");
        }
    }

    public static void main(String[] args) {
        Deque<String> deque = new Deque<String>();
        deque.addFirst("a");
        deque.addFirst("b");
        deque.addFirst("c");
        deque.addFirst("d");
        deque.addFirst("e");
        System.out.println(deque.size);
        for (String s : deque) {
            System.out.println(s);
        }
        System.out.println("---");
        System.out.println(deque.removeFirst());
        System.out.println(deque.removeLast());
        System.out.println("---");
        deque.addLast("f");
        for (String s : deque) {
            System.out.println(s);
        }
    }
}

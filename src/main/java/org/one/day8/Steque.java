package org.one.day8;

import java.util.Iterator;

/**
 * 1.3.32
 * 一个以栈为目标的队列,是一种支持push、pop和enqueue操作的数据类型。
 * 为这种抽象数据类型定义一份API并给出一份基于链表的实现。
 * <p>
 * -----------------------------------------------------
 * public class Steque<Item> implements Iterable<Item>
 * -----------------------------------------------------
 * boolean isEmpty()            判断是否为空
 * void push(Item e)            添加一个元素到头部
 * Item pop()                   从头部删除一个元素
 * void enqueue(Item e)         添加一个元素到尾部
 * -----------------------------------------------------
 *
 * @author cheng
 *         2017/10/23 12:09
 */
public class Steque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;

    private class Node {
        public Item item;
        public Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void enqueue(Item e) {
        Node node = new Node();
        node.item = e;
        node.next = null;
        if (isEmpty()) {
            first = node;
            last = node;
        } else {
            last.next = node;
            last = node;
        }
    }

    public void push(Item e) {
        Node node = new Node();
        node.item = e;
        if (isEmpty()) {
            first = node;
            last = node;
            node.next = null;
        } else {
            node.next = first;
            first = node;
        }
    }

    public Item pop() {
        if (isEmpty()) {
            return null;
        } else {
            Item e = first.item;
            first = first.next;
            return e;
        }
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iter();
    }

    private class Iter implements Iterator<Item> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current.next != null;
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
        Steque<String> steque = new Steque<String>();
        steque.enqueue("d");
        steque.enqueue("e");
        steque.enqueue("f");
        steque.push("c");
        steque.push("b");
        steque.push("a");
        System.out.println("Steque is:");
        Iterator<String> iterator = steque.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("pop up:");
        while (!steque.isEmpty()) {
            System.out.println(steque.pop());
        }
    }
}

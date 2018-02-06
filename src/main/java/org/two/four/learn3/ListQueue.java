package org.two.four.learn3;

import java.util.NoSuchElementException;

/**
 * 链表队列
 *
 * @author cheng
 *         2018/2/6 16:21
 */
public class ListQueue<T> {

    private Node first;
    private Node last;
    private int count;

    private class Node {
        Node next;
        T item;
    }

    public ListQueue() {
        first = null;
        last = null;
        count = 0;
    }

    public void enqueue(T item) {
        Node newNode = new Node();
        newNode.item = item;
        if (isEmpty()) {
            first = newNode;
            last = first;
        } else {
            last.next = newNode;
            last = newNode;
        }
        count++;
    }

    public T dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue overflow!");
        T item = first.item;
        first = first.next;
        if (isEmpty()) last = null;
        count--;
        return item;
    }

    public T peek() {
        Node temp = first;
        return (temp == null) ? null : temp.item;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int size() {
        return count;
    }

    public static void main(String[] args) {
        ListQueue<Integer> queue = new ListQueue<>();
        queue.enqueue(1);
        queue.enqueue(1);
        queue.enqueue(1);
        System.out.println(queue.dequeue());
    }
}

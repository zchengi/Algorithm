package org.cheng.example;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 算法 1.3 先进先出队列
 *
 * @author cheng
 *         2017/10/20 12:13
 */
public class Queue<Item> implements Iterable<Item> {

    /**
     * 指向最早添加的结点的链接
     */
    private Node first;
    /**
     * 指向最近添加的结点的链接
     */
    private Node last;
    /**
     * 队列中的元素数量
     */
    private int n;


    /**
     * 定义了结点的嵌套类
     */
    private class Node {
        Item item;
        Node next;
    }

    public Queue() {
        first = null;
        last = null;
        n = 0;
    }

    /**
     * 向表尾添加元素
     */
    public void enqueue(Item item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        n++;
    }

    /**
     * 从表头删除元素
     */
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }
        Item item = first.item;
        first = first.next;
        if (isEmpty()) {
            last = null;
        }
        n--;
        return item;
    }

    /**
     * 重写toString
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }

    /**
     * 判断是否为空
     */
    public boolean isEmpty() {
        // 或 n==0
        return first == null;
    }

    /**
     * 获得大小
     */
    public int size() {
        return n;
    }

    @Override
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<Item> {

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

    public static void main(String[] args) {
        //创建一个队列并操作字符串入列或出列
        Queue<String> queue = new Queue<String>();
        while (!StdIn.isEmpty()) {
            // to be or not to - be - - that - - - is
            String item = StdIn.readString();
            if (!"-".equals(item)) {
                queue.enqueue(item);
            } else if (!queue.isEmpty()) {
                StdOut.print(queue.dequeue() + " ");
            }
        }
        StdOut.println("(" + queue.size() + " left on queue)");
    }
}

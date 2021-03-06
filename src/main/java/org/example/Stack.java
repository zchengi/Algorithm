package org.example;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 算法 1.2 下压堆栈（链表实现）
 *
 * @author one
 *         2017/10/20 11:27
 */
public class Stack<Item> implements Iterable<Item> {

    /**
     * 栈顶（最近添加的元素）
     */
    private Node first;

    /**
     * 元素数量
     */
    private int N;

    /**
     * 定义了结点的嵌套类
     */
    public class Node {
        Item item;
        Node next;

        public String toString() {
            return "item:" + item;
        }
    }

    /**
     * 向栈顶添加元素
     */
    public void push(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        N++;
    }

    /**
     * 从栈顶删除元素
     */
    public Item pop() {
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }

    /**
     * 返回栈中最近添加的元素，但不弹出
     */
    public Item peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        return first.item;
    }

    /**
     * 1.3.30
     * 接受一条链表的首结点作为参数，（破坏性地）将链表反转并返回结果链表的首结点。
     * 递归解答： 假设链表有N个结点，我们先递归颠倒最后N-1个结点，然后小心地将链表
     * 中的首结点插入到结果链表的末端。
     *
     * @param first 原首结点
     * @return 现首结点
     */
    public Node reverse(Node first) {
        if (first == null) {
            return null;
        }
        if (first.next == null) {
            return first;
        }
        Node second = first.next;
        Node rest = reverse(second);
        second.next = first;
        first.next = null;
        return rest;
        //2.迭代解答
        /*Node first   = x;
        Node reverse = null;
        while (first != null)
        {
            Node second = first.next;
            first.next  = reverse;
            reverse     = first;
            first       = second;
        }
        return reverse;*/
    }

    /**
     * 获取首结点
     */
    public Node getFirst() {
        return first;
    }

    /**
     * 设置首结点
     */
    public void setFirst(Node first) {
        this.first = first;
    }
    @Override
    public Iterator<Item> iterator() {
        return new StackIterator();
    }

    private class StackIterator implements Iterator<Item> {

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

    /**
     * 是否为空
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * 获取大小
     */
    public int size() {
        return N;
    }

    public static void main(String[] args) {

        Stack<String> s = new Stack<String>();
        while (!StdIn.isEmpty()) {
            // a b c
            // a b c - d - f
            String item = StdIn.readString();
            if (!"-".equals(item)) {
                s.push(item);
            } else if (!s.isEmpty()) {
                StdOut.printf(s.pop() + " ");
            }
        }
        StdOut.println("(" + s.size() + " left on stack)");
        System.out.println(s.reverse(s.first));
    }
}

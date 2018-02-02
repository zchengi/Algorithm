package org.coursera.week3;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

/**
 * Test3 Shuffling a linked list.
 * <p>
 * 分析：此题要求的是对单向链表进行随机排序，可以先实现一个链表递归，再改造为随机排序。
 *
 * @author cheng
 *         2018/2/2 16:32
 */
public class ShuffleLinkedList<T extends Comparable<T>> implements Iterable<T> {

    private Node first;
    private Node last;
    private int n;

    private class Node {
        T element;
        Node next;
    }

    public ShuffleLinkedList() {
        first = null;
        last = null;
        n = 0;
    }

    public void add(T t) {
        Node node = new Node();
        node.element = t;
        node.next = null;
        if (first == null && last == null) {
            first = node;
            last = node;
        } else if (first != null && first == last) {
            first.next = node;
            last = node;
        } else {
            last.next = node;
            last = node;
        }
        n++;
    }

    public void mergeSort() {
        first = sort(first);
    }

    private Node sort(Node head) {
        if (head == null || head.next == null) return head;

        Node slow = head;
        Node fast = head;
        // 获取中间结点
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        Node left = head;
        Node right = slow.next;
        // 将左右链表分开
        slow.next = null;
        right = sort(right);
        return merge(left, right);
    }

    private Node merge(Node left, Node right) {
        // 需要耗费logn的额外空间
        Node aux = new Node();
        Node l = left;
        Node r = right;
        Node current = aux;
        while (l != null && r != null) {
            int random = StdRandom.uniform(2);
            // 如果随机数为0，则选取左侧元素
            if (random == 0) {
                current.next = l;
                current = current.next;
                l = l.next;
            } else {
                // 如果随机数为1，则选取右侧元素
                current.next = r;
                current = current.next;
                r = r.next;
            }
        }

        // 如果左侧没有遍历完，将其连接到current后
        if (l != null) current.next = l;
        // 如果右侧没有遍历完，将其连接到current后
        if (r != null) current.next = r;
        // 返回归并好的链表
        return aux.next;
    }

    private boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<T> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T temp = current.element;
            current = current.next;
            return temp;
        }

        @Override
        public void remove() {

        }
    }

    @Override
    public String toString() {
        Iterator<T> iterator = iterator();
        StringBuilder ret = new StringBuilder(iterator.next().toString());
        while (iterator.hasNext()) {
            ret.append(", ").append(iterator.next().toString());
        }
        return ret.toString();
    }

    public static void main(String[] args) {
        ShuffleLinkedList<Integer> shuffleLinkedList = new ShuffleLinkedList<>();
        shuffleLinkedList.add(1);
        shuffleLinkedList.add(2);
        shuffleLinkedList.add(11);
        shuffleLinkedList.add(22);
        shuffleLinkedList.add(5);
        shuffleLinkedList.add(78);
        shuffleLinkedList.add(7);
        shuffleLinkedList.add(34);
        System.out.println(shuffleLinkedList);
        shuffleLinkedList.mergeSort();
        System.out.println(shuffleLinkedList);
    }
}

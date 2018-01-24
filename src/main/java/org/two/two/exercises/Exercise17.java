package org.two.two.exercises;


import edu.princeton.cs.algs4.StdIn;

import java.util.Iterator;

/**
 * 2.2.17 链表排序。实现对链表的自然排序（这是将链表排序的最佳方法，因为它不需要额外的空间，
 * 且运行时间是线性对数级别的）。
 *
 * @author cheng
 *         2018/1/20 22:12
 */
public class Exercise17<Item extends Comparable> implements Iterable<Item> {

    private Node first;

    class Node {
        Node next;
        Item item;
    }


    public Node getFirst() {
        return first;
    }

    public void push(Item item) {
        if (first == null) {
            first = new Node();
            first.item = item;
            return;
        }
        Node temp = first;
        Node current = new Node();
        current.item = item;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = current;
    }

    /**
     * 快速排序实现链表排序
     */
    public void quickSort(Node head) {

        Node tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        quickSort(head, tail);
    }

    private void quickSort(Node head, Node tail) {
        if (head == null || head == tail) return;

        Node middle = partition(head, tail);
        quickSort(head, middle);
        quickSort(middle.next, tail);
    }

    private Node partition(Node head, Node tail) {
        if (head == null || head == tail) return head;

        Item v = head.item;
        Node res = head;
        Node current = head.next;
        while (current != null) {
            if (current.item.compareTo(v) < 0) {
                res = res.next;
                Item temp = res.item;
                res.item = current.item;
                current.item = temp;
            }
            current = current.next;
        }

        head.item = res.item;
        res.item = v;
        return res;
    }

    /**
     * 归并排序实现链表排序
     */
    public Node mergeSort(Node head) {
        // 空链表或者只有单个结点
        if (head == null || head.next == null) return head;

        // 寻找中间结点
        Node middle = getMiddle(head);
        // 把链表分为两半
        Node sHalf = middle.next;
        middle.next = null;

        // 递归合并
        Node temp1 = mergeSort(head);
        Node temp2 = mergeSort(sHalf);
        return merge(temp1, temp2);
    }

    /**
     * 合并两个子链表
     */
    private Node merge(Node nodeList1, Node nodeList2) {
        Node header = new Node();
        Node current = header;

        while (nodeList1 != null && nodeList2 != null) {
            if (nodeList1.item.compareTo(nodeList2.item) <= 0) {
                current.next = nodeList1;
                // 重新赋值给current 这时current指向header.next
                current = current.next;
                nodeList1 = nodeList1.next;
            } else {
                current.next = nodeList2;
                current = current.next;
                nodeList2 = nodeList2.next;
            }
        }

        while (nodeList1 != null) {
            current.next = nodeList1;
            current = current.next;
            nodeList1 = nodeList1.next;
        }
        while (nodeList2 != null) {
            current.next = nodeList2;
            current = current.next;
            nodeList2 = nodeList2.next;
        }
        return header.next;
    }

    /**
     * 使用快慢指针寻找中间结点
     */
    private Node getMiddle(Node head) {

        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    @Override
    public Iterator<Item> iterator() {
        return new NodeIterator();
    }

    private class NodeIterator implements Iterator<Item> {

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

    // 归并排序实现：
    // 单链表与数组相比只能顺序访问每个元素，因此在使用二路归并排序时关键在于找到链表的中间结点将链表一分为二：
    // 可以利用快慢指针同时遍历单链表，当步长为2的指针指向链表最后一个结点或者最后一个结点的下一个结点时，
    // 步长为1的指针即指向链表的中间结点。然后是两个有序单链表的合并问题。时间复杂度为O(N*logN)，空间复杂度为O(1)。
    public static void main(String[] args) {
        Exercise17<Integer> stack = new Exercise17<>();
        while (!StdIn.isEmpty()) {
            // 0 9 5 2 3 6 4 1 7 8 234 32 253 34 2
            stack.push(StdIn.readInt());
        }

        stack.quickSort(stack.getFirst());
        for (Integer item : stack) {
            System.out.print(item + " ");
        }
    }
}

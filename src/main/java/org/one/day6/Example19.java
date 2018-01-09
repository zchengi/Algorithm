package org.one.day6;

/**
 * 给出一段代码，删除链表的尾结点，其中链表的首结点为first。
 *
 * @author cheng
 *         2017/10/21 13:53
 */
public class Example19 {
    public class Node {
        int item;
        Node next;
    }

    private Node first;

    public void deleteLastNode() {
        Node current = first;
        if (current == null) {
            return;
        }
        Node next = current.next;
        if (next == null) {
            first = null;
            return;
        }
        while (next.next != null) {
            current = next;
            next = next.next;
        }
        current.next = null;
    }
}

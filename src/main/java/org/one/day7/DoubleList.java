package org.one.day7;

/**
 * 1.3.31
 * 实现一个嵌套类双向链表
 *
 * @author cheng
 *         2017/10/22 14:33
 */
public class DoubleList<Item> {

    private DoubleNode<Item> first;
    private DoubleNode<Item> last;

    public static class DoubleNode<E> {
        public E item;
        public DoubleNode<E> next;
        public DoubleNode<E> prev;
    }

    public DoubleList() {
        first = null;
        last = null;
    }

    /**
     * 在表头插入结点
     */
    public static <T> void insertAsFirst(DoubleList<T> dl, T e) {
        DoubleNode<T> node = new DoubleNode<T>();
        node.item = e;
        node.prev = null;
        node.next = dl.first;
        if (dl.first == null) {
            dl.last = node;
        } else {
            dl.first.prev = node;
        }
        dl.first = node;
    }

    /**
     * 在表尾插入结点
     */
    public static <T> void insertAsLast(DoubleList<T> dl, T e) {
        DoubleNode<T> node = new DoubleNode<T>();
        node.item = e;
        node.prev = dl.last;
        node.next = null;
        if (dl.last == null) {
            dl.first = node;
        } else {
            dl.last.next = node;
        }
        dl.last = node;
    }

    /**
     * 在表头删除结点
     */
    public static <T> void deleteFirst(DoubleList<T> dl) {
        if (dl.first == null) {
            return;
        }
        if (dl.first == dl.last) {
            dl.first = null;
            dl.last = null;
        } else {
            dl.first = dl.first.next;
            dl.first.prev.next = null;
            dl.first.prev = null;
        }
    }

    /**
     * 在表尾删除结点
     */
    public static <T> void deleteLast(DoubleList<T> dl) {
        if (dl.last == null) {
            return;
        }
        if (dl.last == dl.first) {
            dl.first = null;
            dl.last = null;
        } else {
            dl.last = dl.last.prev;
            dl.last.next.prev = null;
            dl.last.next = null;
        }
    }


    /**
     * 在指定结点之前插入新结点
     */
    public static <T> void insertBefore(DoubleList<T> dl, DoubleNode<T> node, T e) {
        if (node == null) {
            return;
        }
        if (dl.first == node) {
            insertAsFirst(dl, e);
        } else {
            DoubleNode<T> newNode = new DoubleNode<T>();
            newNode.item = e;
            newNode.prev = node.prev;
            newNode.next = node;
            node.prev.next = newNode;
            node.prev = newNode;
        }
    }


    /**
     * 在指定结点之后插入新结点
     */
    public static <T> void insertAfter(DoubleList<T> dl, DoubleNode<T> node, T e) {
        if (node == null) {
            return;
        }
        if (dl.last == node) {
            insertAsLast(dl, e);
        } else {
            DoubleNode<T> newNode = new DoubleNode<T>();
            newNode.item = e;
            newNode.prev = node;
            newNode.next = node.next;
            node.next.prev = newNode;
            node.next = newNode;
        }
    }

    /**
     * 删除指定结点
     */
    public static <T> void deleteNode(DoubleList<T> dl, DoubleNode<T> node) {
        if (node == null) {
            return;
        } else if (node == dl.first) {
            deleteFirst(dl);
        } else if (node == dl.last) {
            deleteLast(dl);
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.prev = null;
            node.next = null;
        }
    }

    /**
     * 从链表中查找
     */
    public static <T> DoubleNode<T> search(DoubleList<T> dl, T e) {
        DoubleNode<T> node = dl.first;
        while (node != null) {
            if (node.item.equals(e)) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

    /**
     * 打印链表所有结点的值
     */
    public static <T> void print(DoubleList<T> dl) {
        DoubleNode<T> node = dl.first;
        while (node != null) {
            System.out.println(node.item);
            node = node.next;
        }
    }
}

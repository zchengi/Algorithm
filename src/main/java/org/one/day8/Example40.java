package org.one.day8;

import java.awt.dnd.InvalidDnDOperationException;
import java.util.Iterator;

/**
 * 1.3.40 前移编码
 * 从标准输入读取一串字符，使用链表保存这些字符并删除重复字符。当你读取一个从未见过的字符时，
 * 将他插入表头。当你读取一个重复的字符时，将它从链表中删除并在此插入表头。MoveToFront 这个程序实现了著名
 * 的前移编码策略，这种策略假设最近访问过的元素很可能会再次访问，因此可以用于缓存、数据压缩等许多场景。
 *
 * @author cheng
 *         2018/1/10 21:17
 */
public class Example40<Item> implements Iterable<Item> {

    private Node<Item> first;
    private int count;

    private class Node<T> {
        public Node<T> next;
        public T item;
    }

    /**
     * 建立一个前移编码组
     */
    public Example40() {
        this.first = null;
        this.count = 0;
    }

    /**
     * 检查编码组是否为空
     */
    public boolean isEmpty() {
        return this.first == null;
    }

    /**
     * 前移编码插入
     */
    public void insert(Item item) {
        Node<Item> temp = Find(item);
        if (temp == null) {
            temp = new Node<>();
            temp.item = item;
            temp.next = this.first;
            this.first = temp;
            this.count++;
        } else if (this.count != 1) {
            // Node<Item> target = temp.next;
            // 使用回调 替换注释代码
            Item repeat = temp.next.item;
            temp.next = temp.next.next;
            // target.next = this.first;
            // this.first = target;

            // 使用回调 替换注释代码
            insert(repeat);
        }
    }

    /**
     * 找到给出元素的前一个结点
     */
    private Node<Item> Find(Item item) {
        // 查看集合是否为空
        if (isEmpty()) {
            return null;
        }
        // 获取当前首结点
        Node<Item> current = this.first;
        // 如果首结点的下一个节点不为空则循环
        while (current.next != null) {
            // 如果当前结点的下一个结点值等于要插入的值，则返回当前结点
            if (current.next.item.equals(item)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    /**
     * 查找第一个元素
     */
    public Item peek() {
        if (isEmpty()) {
            throw new InvalidDnDOperationException("集合为空!");
        }
        return this.first.item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new MoveToFontIterator();
    }

    private class MoveToFontIterator implements Iterator<Item> {

        private Node<Item> current = first;

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
        Example40<Integer> moveToFront = new Example40<>();
        for (int i = 0; i < 10; i++) {
            moveToFront.insert(i);
        }
        moveToFront.insert(5);
        System.out.println(moveToFront.peek());
        moveToFront.insert(8);
        System.out.println(moveToFront.peek());
        for (Integer num : moveToFront) {
            System.out.print(num + " ");
        }
    }
}

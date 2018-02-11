package org.three.one.learn;

import org.example.Queue;

/**
 * 算法3.1 顺序查找（基于无序链表）
 *
 * @author cheng
 *         2018/2/5 13:28
 */
public class SequentialSearchST<Key, Value> {

    /**
     * 链表首结点
     */
    private Node first;

    /**
     * 结点数量
     */
    private int count;

    /**
     * 链表结点的定义
     */
    private class Node {
        Key key;
        Value value;
        Node next;

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public SequentialSearchST() {
    }

    /**
     * 查找给定的键，找到则更新其值，否则在表中新建结点
     */
    public void put(Key key, Value value) {
        if (key == null) throw new IllegalArgumentException("key mush not be null!");
        if (value == null) {
            delete(key);
            return;
        }

        for (Node current = first; current != null; current = current.next) {
            // 命中，更新数据
            if (key.equals(current.key)) {
                current.value = value;
                return;
            }
        }
        // 未命中，新建结点
        first = new Node(key, value, first);
        count++;
    }

    /**
     * 查找给定的键，返回相关联的值
     */
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("key must be not null!");
        for (Node current = first; current != null; current = current.next) {
            // 命中
            if (key.equals(current.key)) {
                return current.value;
            }
        }
        // 未命中
        return null;
    }

    /**
     * 删除给定的键的结点
     */
    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("key mush not be null!");

        first = delete(first, key);
    }

    /**
     * 删除链表从给定结点开始的一个指定键的结点，返回删除后的符号表
     * 警告：如果符号表很大，函数调用堆栈太大
     */
    public Node delete(Node x, Key key) {
        if (x == null) return null;
        if (key.equals(x.key)) {
            count--;
            return x.next;
        }
        x.next = delete(x.next, key);
        return x;
    }

    /**
     * 键key在表中是否有对应的值
     */
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("key must be not null!");
        return get(key) != null;
    }

    /**
     * 表是否为空
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * 返回表中键值对的数量
     */
    public int size() {
        return count;
    }

    /**
     * 表中所有键的集合
     */
    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();
        for (Node current = first; current != null; current = current.next) {
            queue.enqueue(current.key);
        }
        return queue;
    }


    public static void main(String[] args) {
        SequentialSearchST<String, Integer> st = new SequentialSearchST<>();
        String[] strings = {"w", "q", "x", "f", "gad", "as", "gw", "rtg", "zvz"};
        for (int i = 0; i < strings.length; i++) {
            st.put(strings[i], i);
        }

        for (String key : st.keys()) {
            System.out.println(key + " " + st.get(key));
        }
    }
}

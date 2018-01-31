package org.two.four.exercises;

import edu.princeton.cs.algs4.StdRandom;
import org.one.day9.StopWatch;

import java.util.NoSuchElementException;

/**
 * 2.4.3 用以下数据结构实现优先队列，支持插入元素和删除最大元素的操作：无序数组、有序数组、无序链表
 * 和链表。将你的四种实现中每种操作在最坏情况下的运行时间上下限制成一张表格。
 * 由于实现的不同，结果可能不同！
 * <p>
 * 未实现：无序链表 有序链表；
 *
 * @author cheng
 *         2018/1/31 15:24
 */
public class Exercise3 {
    interface PQOperation<Key extends Comparable<Key>> {
        void insert(Key key);

        Key delMax();

        boolean isOrderedImp();

        boolean isEmpty();
    }

    /**
     * 无序数组
     */
    static class UnorderedArrayMaxPQ<Key extends Comparable<Key>> implements PQOperation<Key> {
        private int count;
        private Key[] keys;

        public UnorderedArrayMaxPQ(int capacity) {
            count = 0;
            keys = (Key[]) new Comparable[capacity];
        }

        @Override
        public void insert(Key key) {
            assert count < keys.length : "数组越界";
            keys[count++] = key;
        }

        @Override
        public Key delMax() {
            int max = 0;
            for (int i = 1; i < count; i++) {
                if (keys[max].compareTo(keys[i]) < 0) {
                    max = i;
                }
            }
            exch(max, count - 1);
            return keys[--count];
        }

        private void exch(int i, int j) {
            Key swap = keys[i];
            keys[i] = keys[j];
            keys[j] = swap;
        }

        @Override
        public boolean isOrderedImp() {
            return false;
        }

        @Override
        public boolean isEmpty() {
            return count == 0;
        }
    }

    /**
     * 有序数组
     */
    static class OrderedArrayMaxPQ<Key extends Comparable<Key>> implements PQOperation<Key> {
        private Key[] keys;
        private int count;

        public OrderedArrayMaxPQ(int capacity) {
            count = 0;
            keys = (Key[]) new Comparable[capacity];
        }

        @Override
        public void insert(Key key) {
            int i = count - 1;
            while (i >= 0 && less(key, keys[i])) {
                keys[i + 1] = keys[i];
                i--;
            }
            keys[i + 1] = key;
            count++;
        }

        @Override
        public Key delMax() {
            return keys[--count];
        }

        @Override
        public boolean isOrderedImp() {
            return true;
        }

        @Override
        public boolean isEmpty() {
            return count == 0;
        }

        private boolean less(Key v, Key w) {
            return v.compareTo(w) < 0;
        }
    }

    /*
    * 无序链表
    */
    static class UnorderedList<Key extends Comparable<Key>> implements PQOperation<Key> {
        private class Node {
            Node next;
            Key key;

            Node() {
            }

            Node(Key key) {
                this.key = key;
            }
        }

        private int size;
        private Node header = new Node();
        private Node cur = header;

        public boolean isOrderedImp() {
            return false;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void insert(Key key) {
            ++size;
            cur.next = new Node(key);
            cur = cur.next;
        }

        public Key delMax() {
            if (isEmpty())
                throw new NoSuchElementException("priority queue underflow");
            Node cur = header.next, recorder = header;
            Key max = cur.key;
            while (cur.next != null) {
                if (cur.next.key.compareTo(max) > 0) {
                    recorder = cur;
                    max = cur.next.key;
                }
                cur = cur.next;
            }
            --size;
            recorder.next.key = null;
            recorder.next = recorder.next.next;
            return max;
        }
    }

    /*
    * 有序链表
    */
    static class OrderedList<Key extends Comparable<Key>> implements PQOperation<Key> {
        private class Node {
            Node next;
            Key key;

            Node() {
            }

            Node(Key key) {
                this.key = key;
            }

            Node insertAfter(Key key) {
                Node n = new Node(key);
                n.next = next;
                next = n;
                return n;
            }
        }

        private Node header = new Node();
        private int size;

        public boolean isOrderedImp() {
            return true;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void insert(Key key) {
            Node cur = header;
            while (cur.next != null) {
                if (cur.next.key.compareTo(key) > 0) break;
                cur = cur.next;
            }
            ++size;
            cur.insertAfter(key);
        }

        public Key delMax() {
            if (isEmpty())
                throw new NoSuchElementException("priority queue underflow");
            --size;
            Node cur = header, pre = header;
            while ((cur = cur.next).next != null) ;
            while (pre.next != cur) pre = pre.next;
            Key max = cur.key;
            cur.key = null;
            pre.next = null;
            return max;
        }
    }

    /**
     * 最差情况测试
     */
    public static double timeOfTheWorstCase(PQOperation<Integer> pq) {
        if (pq.isOrderedImp()) {
            StopWatch timer = new StopWatch();
            for (int i = 100000; i > 0; i--) {
                pq.insert(i);
            }
            while (!pq.isEmpty()) {
                pq.delMax();
            }
            return timer.elapsedTime();
        } else {
            StopWatch timer = new StopWatch();
            for (int i = 0; i < 100000; i++) {
                pq.insert(i);
            }
            while (!pq.isEmpty()) {
                pq.delMax();
            }
            return timer.elapsedTime();
        }
    }

    public static void currentTest(PQOperation<Integer> pq) {
        for (int i = 0; i < 20; i++) {
            pq.insert(StdRandom.uniform(20));
        }
        while (!pq.isEmpty()) {
            System.out.print(pq.delMax() + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        UnorderedArrayMaxPQ<Integer> unorderedArray = new UnorderedArrayMaxPQ<>(20);
        OrderedArrayMaxPQ<Integer> orderedArray = new OrderedArrayMaxPQ<>(20);
        UnorderedList<Integer> unorderedList = new UnorderedList<>();
        OrderedList<Integer> orderedList = new OrderedList<>();

        currentTest(unorderedArray);
        currentTest(orderedArray);
        currentTest(unorderedList);
        currentTest(orderedList);


        unorderedArray = new UnorderedArrayMaxPQ<>(100000);
        orderedArray = new OrderedArrayMaxPQ<>(100001);
        unorderedList = new UnorderedList<>();
        orderedList = new OrderedList<>();

        System.out.println("最差情况下：");
        System.out.println("无序数组：" + timeOfTheWorstCase(unorderedArray));
        System.out.println("有序数组：" + timeOfTheWorstCase(orderedArray));
        System.out.println("无序链表：" + timeOfTheWorstCase(unorderedList));
        System.out.println("有序链表：" + timeOfTheWorstCase(orderedList));

        /*
         * 最差情况下：
         * 无序数组：10.141
         * 有序数组：9.088
         * 无序链表：22.306
         * 有序链表：42.743
         */
    }
}

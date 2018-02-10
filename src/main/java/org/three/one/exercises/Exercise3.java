package org.three.one.exercises;

import org.example.Queue;

import java.util.NoSuchElementException;

/**
 * 3.1.3 开发一个符号表的实现OrderedSequentialSearchST，使用有序链表来实现我们的有序符号表API。
 *
 * @author cheng
 *         2018/2/10 14:04
 */
public class Exercise3 {
    static class OrderedSequentialSearchST<Key extends Comparable<Key>, Value> {
        private Node header = new Node();
        private int count;
        private Key max;
        private Key min;

        private class Node {
            Key key;
            Value value;
            Node next;

            public Node() {
            }

            public Node(Key key, Value value, Node next) {
                this.key = key;
                this.value = value;
                this.next = next;
            }
        }

        public void put(Key key, Value value) {
            if (key == null) throw new IllegalArgumentException("key can not be null!");
            if (value == null) {
                delete(key);
                return;
            }

            Node pre = header;
            Node current = header.next;
            while (current != null && current.key.compareTo(key) < 0) {
                pre = current;
                current = current.next;
            }

            if (current != null && current.key.compareTo(key) == 0) {
                current.value = value;
            } else {
                pre.next = new Node(key, value, pre.next);
                count++;
            }

            // 更新最大最小值
            if (max == null || key.compareTo(max) > 0) max = key;
            if (min == null || key.compareTo(min) < 0) min = key;
        }

        public Value get(Key key) {
            if (key == null) throw new IllegalArgumentException("key can not be null!");
            for (Node current = header.next; current != null; current = current.next) {
                if (key.compareTo(current.key) == 0) {
                    return current.value;
                }
            }
            return null;
        }

        private void delete(Key key) {
            if (key == null) throw new IllegalArgumentException("key can not be null!");
            if (isEmpty()) throw new NoSuchElementException("Symbol table underflow!");

            Node current = header.next;
            Node pre = header;
            while (current != null && key.compareTo(current.key) != 0) {
                pre = current;
                current = current.next;
            }

            if (current == null) return;
            count--;
            if (current.key == min) min = current.next.key;

            if (current.key == max) max = pre.key;

            pre.next = pre.next.next;
            current.key = null;
            current.next = null;
            current.value = null;
        }

        public Key max() {
            return max;
        }

        public Key min() {
            return min;
        }

        public int rank(Key key) {
            if (key == null) throw new IllegalArgumentException("key can not be null!");
            int i = 0;
            Node current = header.next;
            while (current != null && current.key.compareTo(key) <= 0) {
                current = current.next;
                ++i;
            }
            return i;
        }

        public Key floor(Key key) {
            if (key == null) throw new IllegalArgumentException("key can not be null!");
            if (isEmpty()) return null;

            Node pre = header;
            for (Node current = header.next; current != null; current = current.next) {
                if (current.key.compareTo(key) <= 0) pre = current;
                else break;
            }
            if (pre == null) return null;
            return pre.key;
        }

        public Key ceil(Key key) {
            if (key == null) throw new IllegalArgumentException("key can not be null!");
            if (isEmpty()) return null;

            Node current;
            for (current = header.next; current != null; current = current.next) {
                if (current.key.compareTo(key) >= 0) break;
            }
            if (current == null) return null;
            return current.key;
        }

        public Key select(int k) {
            if (isEmpty()) return null;

            Node current = header;
            while (k-- > 0 && current != null) {
                current = current.next;
            }
            return current == null ? null : current.key;
        }

        public void deleteMax() {
            if (isEmpty()) return;
            delete(max);
        }

        public void deleteMin() {
            if (isEmpty()) return;
            delete(min);
        }

        public Iterable<Key> keys() {
            if (isEmpty()) return null;
            return keys(min, max);
        }

        public Iterable<Key> keys(Key lo, Key hi) {
            if (isEmpty()) return null;
            if (lo.compareTo(hi) > 0 || isEmpty()) return null;

            Queue<Key> queue = new Queue<>();

            Node x = header.next;
            while (x != null && x.key.compareTo(lo) < 0) {
                x = x.next;
            }
            if (x == null) return null;

            Node cur = header.next, pre = header;
            while (cur != null && cur.key.compareTo(hi) <= 0) {
                cur = cur.next;
                pre = pre.next;
            }

            while (x != null && x.key.compareTo(pre.key) <= 0) {
                queue.enqueue(x.key);
                x = x.next;
            }

            return queue;
        }

        public boolean contains(Key key) {
            if (key == null) throw new IllegalArgumentException("key can not be null!");
            return get(key) != null;
        }

        public int size() {
            return count;
        }

        public int size(Key lo, Key hi) {
            if (lo.compareTo(hi) > 0 || isEmpty()) return 0;
            Node x = header.next;

            int i = 0;
            while (x != null && x.key.compareTo(lo) < 0) {
                x = x.next;
            }
            if (x == null) return -1;

            Node cur = header.next, pre = header;
            while (cur != null && cur.key.compareTo(hi) <= 0) {
                cur = cur.next;
                pre = pre.next;
            }

            while (x != null && x.key.compareTo(pre.key) <= 0) {
                i++;
                x = x.next;
            }
            return i;
        }

        public boolean isEmpty() {
            return count == 0;
        }
    }

    public static void main(String[] args) {
        OrderedSequentialSearchST<Integer, String> st = new OrderedSequentialSearchST<>();

        st.put(14, "H");
        st.put(4, "C");
        st.put(34, "R");
        st.put(32, "Q");
        st.put(6, "D");
        st.put(2, "B");
        st.put(16, "I");

        st.deleteMin();
        st.deleteMax();
        System.out.println("在 [2,34] 范围内的表键值对：");
        for (Integer i : st.keys(2, 34)) {
            System.out.println(i + " " + st.get(i));
        }
        System.out.println("最大key：" + st.min() + "，最小key：" + st.max());
        System.out.println("在 [2,34] 范围内的键值对个数：" + st.size(2, 34));
        System.out.println("是否包含键 14：" + st.contains(14));
        int key = st.rank(4);
        System.out.println("找到键为32的键在符号表中的位置：" + key);
        key = st.select(5);
        System.out.println("选择第5个键值对：" + key + " " + st.get(key));
        key = st.floor(16);
        System.out.println("键小于等于 16 的第一个键值对：" + key + " " + st.get(key));
        key = st.ceil(14);
        System.out.println("键大于等于 14 的第一个键值对：" + key + " " + st.get(key));
    }
}

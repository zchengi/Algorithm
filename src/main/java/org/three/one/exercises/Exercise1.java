package org.three.one.exercises;

import org.example.Queue;

/**
 * 3.1.1 编写一个程序，创建一张符号表并建立字母成绩和数值分数的对应关系，如下表所示。
 * 从标准输入读取一系列字母成绩，计算并打印GPA（字母成绩对应的分数的平均值）。
 *
 *  A+  |   A  |  A-  |  B+  |  B   |  B-  |  C+  |  C   |  C-  |  D   |  F
 * ---------------------------------------------------------------------------
 * 4.33 | 4.00 | 3.00 | 3.67 | 3.00 | 2.67 | 2.33 | 2.00 | 1.67 | 1.00 | 0.00
 *
 * @author cheng
 *         2018/2/10 11:43
 */
public class Exercise1 {
    static class SymbolTable<Key, Value> {

        private Node first;
        private int count;

        private class Node {
            Key key;
            Value value;
            Node next;

            Node(Key key, Value value, Node next) {
                this.key = key;
                this.value = value;
                this.next = next;
            }
        }

        public SymbolTable() {
        }

        public void put(Key key, Value value) {
            if (key == null) throw new IllegalArgumentException("key can not be null!");
            if (value == null) {
                delete(key);
                return;
            }

            for (Node current = first; current != null; current = current.next) {
                if (key.equals(current.key)) {
                    current.value = value;
                    return;
                }
            }

            first = new Node(key, value, first);
            count++;
        }

        public Value get(Key key) {
            if (key == null) throw new IllegalArgumentException("key can not be null!");
            for (Node current = first; current != null; current = current.next) {
                if (key.equals(current.key)) {
                    return current.value;
                }
            }
            return null;
        }

        public void delete(Key key) {
            if (key == null) throw new IllegalArgumentException("key can not be null!");
            first = delete(first, key);
        }

        private Node delete(Node current, Key key) {
            if (current == null) return null;
            if (key.equals(current.key)) {
                count--;
                return current.next;
            }
            current.next = delete(current.next, key);
            return current;
        }

        public boolean contains(Key key) {
            if (key == null) throw new IllegalArgumentException("key can not be null!");
            return get(key) != null;
        }

        public int size() {
            return count;
        }

        public boolean isEmpty() {
            return count == 0;
        }

        public Iterable<Key> keys() {
            Queue<Key> queue = new Queue<>();
            for (Node current = first; current != null; current = current.next) {
                queue.enqueue(current.key);
            }
            return queue;
        }
    }

    public static void main(String[] args) {
        SymbolTable<String, Double> st = new SymbolTable<>();
        st.put("A+", 4.33);
        st.put("A", 4.00);
        st.put("A-", 3.67);
        st.put("B+", 3.33);
        st.put("B", 3.00);
        st.put("B-", 2.67);
        st.put("C+", 2.33);
        st.put("C", 2.00);
        st.put("C-", 1.67);
        st.put("D", 1.00);
        st.put("F", 0.00);

        double sum = 0;
        String[] grade = {"A", "C-", "D", "B+", "A+", "A-", "C+", "B-"};
        for (String s : grade) {
            sum += st.get(s);
        }
        double gpa = sum / grade.length;
        System.out.printf(" GPA 为：%.2f", gpa);
    }
}

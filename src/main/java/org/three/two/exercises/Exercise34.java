package org.three.two.exercises;

import java.util.NoSuchElementException;

/**
 * 3.2.34 线性符号表。你的目标是实现一个号扩展的符号表ThreadedST，支持以下两个运行时间为常数的操作：
 * Key next(Key key)，key的下一个键（若key为最大键则返回空）
 * Key prev(Key key)，key的上一个键（若key为最小键则返回空）
 * 要做到这一点需要在结点中增加pred和succ两个变量来保存结点的前趋和后继结点，
 * 并相应的修改put()、deleteMin()、deleteMax()和delete()方法来维护这两个变量。
 *
 * @author cheng
 *         2018/2/27 11:53
 */
public class Exercise34 {
    private static class ThreadedST<Key extends Comparable<Key>, Value> {
        private Node root, hot;

        private class Node {
            private Key key;
            private Value value;
            int size;
            Node left, right;
            Node pred, succ;

            public Node(Key key, Value value) {
                this.key = key;
                this.value = value;
                this.size = 1;
            }

            public Node insertLC(Key key, Value value) {
                return left = new Node(key, value);
            }

            public Node insertRC(Key key, Value value) {
                return right = new Node(key, value);
            }
        }

        public ThreadedST() {
        }

        public void put(Key key, Value value) {
            if (key == null) throw new IllegalArgumentException("Called put() with a null key!");
            if (isEmpty()) {
                root = new Node(key, value);
                return;
            }

            Node node = get(root, key);
            if (node != null) {
                node.value = value;
                return;
            }

            int cmp = key.compareTo(hot.key);
            if (cmp < 0) {
                Node current = hot.insertLC(key, value);

                // 设置直接后继
                current.succ = hot;
                Node hotPred = hot.pred;
                hot.pred = current;

                // 设置直接前趋
                current.pred = hotPred;
                if (hotPred != null) hotPred.succ = current;
            } else {
                Node cur = hot.insertRC(key, value);

                // 设置直接前趋
                cur.pred = hot;
                Node hotSucc = hot.succ;
                hot.succ = cur;

                // 设置直接后继
                cur.succ = hotSucc;
                if (hotSucc != null) hotSucc.pred = cur;
            }
        }

        public Value get(Key key) {
            if (isEmpty()) return null;

            Node node = get(root, key);
            return node == null ? null : node.value;
        }

        private Node get(Node node, Key key) {
            hot = null;

            while (node != null) {
                int cmp = key.compareTo(node.key);

                if (cmp == 0) return node;
                hot = node;
                node = cmp < 0 ? node.left : node.right;
            }
            return null;
        }

        public void delete(Key key) {
            root = delete(root, key);
        }

        private Node delete(Node node, Key key) {
            if (node == null) return null;

            int cmp = key.compareTo(node.key);

            if (cmp < 0) node.left = delete(node.left, key);
            if (cmp > 0) node.right = delete(node.right, key);
            else {
                if (node.left == null) {
                    if (node.pred != null) node.pred.succ = node.succ;
                    if (node.succ != null) node.succ.pred = node.pred;
                    return node.right;
                }
                if (node.right == null) {
                    if (node.pred != null) node.pred.succ = node.succ;
                    if (node.succ != null) node.succ.pred = node.pred;
                    return node.left;
                }

                Node temp = node;
                node = min(node.right);
                node.right = deleteMin(temp.right);
                node.left = temp.left;

                temp.pred.succ = temp.succ;
                temp.succ.pred = temp.pred;
            }
            return node;
        }

        public void deleteMin() {
            root = deleteMin(root);
        }

        private Node deleteMin(Node node) {
            if (node.left == null) return node.right;
            node.left = deleteMin(node.left);
            return node;
        }

        public void deleteMax() {
            root = deleteMax(root);
        }

        private Node deleteMax(Node node) {
            while (node.right == null) {
                if (node.succ != null) node.succ.pred = node.pred;
                if (node.pred != null) node.pred.succ = node.succ;
                return node.left;
            }

            node.right = deleteMax(node.right);
            return node;
        }

        public Key min() {
            if (isEmpty()) throw new NoSuchElementException("Called min() with empty Symbol table!");

            return min(root).key;
        }

        private Node min(Node x) {
            if (x.left == null) return x;
            return min(x.left);
        }

        public boolean isEmpty() {
            return root == null;
        }
    }

    public static void main(String[] args) {
        ThreadedST<String, Integer> tst = new ThreadedST<>();
        tst.put("d", 1);
        tst.put("c", 1);
        tst.put("e", 1);
        tst.put("b", 1);
        tst.put("f", 1);
        tst.put("a", 1);
    }
}

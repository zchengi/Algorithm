package org.three.two.exercises;

import edu.princeton.cs.algs4.StdRandom;
import org.tool.ArrayGenerator;

/**
 * 3.2.21 为二叉查找树添加一个randomKey()方法来在和树高成正比的时间内从符号表中随机返回一个键。
 *
 * @author cheng
 *         2018/2/26 20:08
 */
public class Exercise21 {
    private static class BST<Key extends Comparable<Key>, Value> {
        private Node root;

        private class Node {
            private Key key;
            private Value value;
            private Node left, right;
            private int size;

            public Node(Key key, Value value, int size) {
                this.key = key;
                this.value = value;
                this.size = size;
            }
        }

        public void put(Key key, Value value) {
            if (key == null) throw new IllegalArgumentException("Called put() with a null key!");

            root = put(root, key, value);
        }

        private Node put(Node x, Key key, Value value) {
            if (x == null) return new Node(key, value, 1);

            int cmp = key.compareTo(x.key);
            if (cmp < 0) x.left = put(x.left, key, value);
            else if (cmp > 0) x.right = put(x.right, key, value);
            else x.value = value;

            x.size = 1 + size(x.left) + size(x.right);
            return x;
        }

        public Value get(Key key) {
            return get(root, key);
        }

        private Value get(Node x, Key key) {
            if (key == null) throw new IllegalArgumentException("Called get() with a null key!");
            if (x == null) return null;

            int cmp = key.compareTo(x.key);
            if (cmp < 0) return get(x.left, key);
            else if (cmp > 0) return get(x.right, key);
            else return x.value;
        }

        public int rank(Key key) {
            if (key == null) throw new IllegalArgumentException("Called rank() with a null key!");

            return rank(key, root);
        }

        private int rank(Key key, Node x) {
            if (x == null) return 0;

            int cmp = key.compareTo(x.key);
            if (cmp < 0) return rank(key, x.left);
            if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
            else return size(x.left);
        }

        public boolean contains(Key key) {
            if (key == null) throw new IllegalArgumentException("Called contains() with a null key!");

            return get(key) != null;
        }

        public int size() {
            return size(root);
        }

        private int size(Node x) {
            if (x == null) return 0;
            else return x.size;
        }

        public int size(Key lo, Key hi) {
            if (lo == null) throw new IllegalArgumentException("First argument to size() is null!");
            if (hi == null) throw new IllegalArgumentException("Second argument to size() is null!");

            if (lo.compareTo(hi) > 0) return 0;
            if (contains(hi)) return 1 + rank(hi) - rank(lo);
            else return rank(hi) - rank(lo);
        }

        private Key randomKey() {
            if (root == null) return null;

            int h = (int) (Math.log(size()) / Math.log(2));

            Node node = root;

            int steps = StdRandom.uniform(h + 1);

            while (steps-- > 0) {
                boolean left = StdRandom.bernoulli();
                if (left) {
                    if (node.left == null) return node.key;
                    node = node.left;
                } else {
                    if (node.right == null) return node.key;
                    node = node.right;
                }
            }
            return node.key;
        }
    }

    public static void main(String[] args) {
        String[] alps = ArrayGenerator.Alphbets.random(30);

        BST<String, Integer> bst = new BST<>();
        for (int i = 0; i < alps.length; i++) {
            bst.put(alps[i], i);
        }

        // 随机抽取
        for (int i = 0; i < 10; i++) {
            String key = bst.randomKey();
            System.out.println(key + " " + bst.get(key));
        }
    }
}

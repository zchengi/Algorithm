package org.three.two.exercises;

/**
 * 3.2.7 为二叉查找树添加一个avgCompares()来计算一棵给定树中的一次随机命中查找平均所需的比较次数
 * （即树的内部路径长度除以树的大小再加1）。实现的两种方案：
 * 一种使用递归（用时为线性级别，所需空间和树高成正比），
 * 一种模仿size()在每个结点中添加一个变量（所需空间为线性级别，查询耗时为常数）。
 *
 * 分析：使用递归的方式计算树的内部路径长度；
 *
 * @author cheng
 *         2018/2/16 12:43
 */
public class Exercise7 {
    static class BST<Key extends Comparable<Key>, Value> {
        private Node root;

        private class Node {
            private Key key;
            private Value value;
            private Node left, right;
            private int size, height;

            public Node(Key key, Value value) {
                this.key = key;
                this.value = value;
                this.size = 1;
            }
        }

        public int size() {
            return size(root);
        }

        private int size(Node x) {
            return x == null ? 0 : x.size;
        }

        public int height() {
            return height(root);
        }

        private int height(Node x) {
            return x == null ? -1 : x.height;
        }

        public void put(Key key, Value value) {
            root = put(root, key, value);
        }

        private Node put(Node x, Key key, Value value) {
            if (x == null) return new Node(key, value);

            int cmp = key.compareTo(x.key);
            if (cmp < 0) x.left = put(x.left, key, value);
            if (cmp > 0) x.right = put(x.right, key, value);
            else x.value = value;

            x.size = 1 + size(x.left) + size(x.right);
            x.height = 1 + Math.max(height(x.left), height(x.right));
            return x;
        }

        public int avgCompares() {
            return internalPathLength() / size() + 1;
        }

        private int internalPathLength() {
            return internalPathLength(root, 0);
        }

        private int internalPathLength(Node x, int depth) {
            if (x == null) return 0;
            int curLength = depth;
            depth += internalPathLength(x.left, curLength + 1);
            depth += internalPathLength(x.right, curLength + 1);
            return depth;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            appendString(builder, root);
            return builder.toString();
        }

        private void appendString(StringBuilder builder, Node x) {
            if (x == null) return;
            appendString(builder, x.left);
            builder.append(String.format("{ %3s %3s size = %3d height = %3d }\n", x.key, x.value, x.size, x.height));
            appendString(builder, x.right);
        }
    }

    public static void main(String[] args) {
        BST<Integer, Integer> bst = new BST<>();
        bst.put(4, 0);
        bst.put(2, 0);
        bst.put(6, 0);
        bst.put(1, 0);
        bst.put(3, 0);
        bst.put(5, 0);
        bst.put(7, 0);

        System.out.println(bst);
        System.out.println("二叉树内部路径长度：" + bst.internalPathLength());
        System.out.println("随机命中查找平均所需比较次数：" + bst.avgCompares());
    }
}

package org.three.three.exercises;

import org.example.Queue;
import org.tool.SortTestHelper;

import java.util.Arrays;

/**
 * 3.3.22 找出一组键的序列使得用它顺序构造的二叉查找树比用它的顺序构造的红黑树的高度更低，
 * 或者证明这样的序列不存在。
 *
 * @author cheng
 *         2018/3/5 14:55
 */
public class Exercise22 {
    static class BSTree<Key extends Comparable<Key>, Value> {

        private Node root;

        private class Node {
            int size;
            Key key;
            Value value;
            Node left, right;

            Node(Key key, Value value) {
                this.key = key;
                this.value = value;
                size = 1;
            }

            public String toString() {
                return String.format("%5s%5d\n", key, size);
            }
        }

        public boolean isEmpty() {
            return root == null;
        }

        public int height() {
            return height(root);
        }

        private int height(Node node) {
            if (node == null) return -1;
            return 1 + Math.max(height(node.left), height(node.right));
        }

        public int size() {
            return size(root);
        }

        private int size(Node node) {
            return node == null ? 0 : node.size;
        }

        public void put(Key key, Value value) {
            // 假设不会有重复键
            root = put(root, key, value);
        }

        private Node put(Node node, Key key, Value value) {
            if (node == null) return new Node(key, value);

            int cmp = key.compareTo(node.key);

            if (cmp < 0) node.left = put(node.left, key, value);
            if (cmp > 0) node.right = put(node.right, key, value);
            else node.value = value;

            node.size = 1 + size(node.right) + size(node.left);
            return node;
        }

        public String toString() {
            if (isEmpty()) return "Empty Tree.";

            StringBuilder builder = new StringBuilder();
            Queue<Node> queue = new Queue<>();
            queue.enqueue(root);

            Node node;
            while (!queue.isEmpty()) {
                node = queue.dequeue();
                builder.append(node);
                if (node.left != null) queue.enqueue(node.left);
                if (node.right != null) queue.enqueue(node.right);
            }

            return builder.toString();
        }
    }

    static class RBTree<Key extends Comparable<Key>, Value> {

        private static final boolean RED = true;
        private static final boolean BLACK = false;

        private Node root;

        private class Node {
            Key key;
            Value value;
            int size;
            Node left, right;
            boolean color;

            public Node(Key key, Value value) {
                this.key = key;
                this.value = value;
                size = 1;
                color = RED;
            }

            @Override
            public String toString() {
                return String.format("%5s%5s%5d\n", key, color ? "红" : "黑", size);
            }
        }

        public boolean isEmpty() {
            return root == null;
        }

        public int height() {
            return height(root);
        }

        private int height(Node node) {
            if (node == null) return -1;
            return 1 + Math.max(height(node.left), height(node.right));
        }

        public int size() {
            return size(root);
        }

        private int size(Node node) {
            return node == null ? 0 : node.size;
        }

        private boolean isRed(Node node) {
            return node != null && node.color;
        }

        private Node rotateLeft(Node h) {
            Node node = h.right;
            h.right = node.left;
            node.left = h;
            node.color = h.color;
            h.color = RED;
            node.size = h.size;
            h.size = 1 + size(h.left) + size(h.right);
            return node;
        }

        private Node rotateRight(Node h) {
            Node node = h.left;
            h.left = node.right;
            node.right = h;
            node.color = h.color;
            h.color = RED;
            node.size = h.size;
            h.size = 1 + size(h.left) + size(h.right);
            return node;
        }

        private void flipColors(Node h) {
            h.color = !h.color;
            h.left.color = !h.left.color;
            h.right.color = !h.right.color;
        }

        public void put(Key key, Value value) {
            root = put(root, key, value);
            root.color = BLACK;
        }

        private Node put(Node node, Key key, Value value) {
            if (node == null) return new Node(key, value);

            int cmp = key.compareTo(node.key);

            if (cmp < 0) node.left = put(node.left, key, value);
            if (cmp > 0) node.right = put(node.right, key, value);
            else node.value = value;

            if (!isRed(node.left) && isRed(node.right)) node = rotateLeft(node);
            if (isRed(node.left) && isRed(node.left.left)) node = rotateRight(node);
            if (isRed(node.left) && isRed(node.right)) flipColors(node);

            node.size = 1 + size(node.left) + size(node.right);
            return node;
        }

        public String toString() {
            if (isEmpty()) return "Empty Tree.";

            StringBuilder builder = new StringBuilder();
            Queue<Node> queue = new Queue<>();
            queue.enqueue(root);

            Node node;
            while (!queue.isEmpty()) {
                node = queue.dequeue();
                builder.append(node);
                if (node.left != null) queue.enqueue(node.left);
                if (node.right != null) queue.enqueue(node.right);
            }

            return builder.toString();
        }
    }

    public static void main(String[] args) {

        while (true) {
            int n = 10;
            Integer[] arr = SortTestHelper.generateNearlyOrderedArray(n, 20);

            RBTree<Integer, Integer> rbt = new RBTree<>();
            BSTree<Integer, Integer> bst = new BSTree<>();

            for (Integer i : arr) {
                rbt.put(i, i);
                bst.put(i, i);
            }

            if (bst.height() < rbt.height()) {
                System.out.println("顺序插入数组: " + Arrays.toString(arr));
                System.out.println("红黑树高度：" + rbt.height());
                System.out.println(rbt);
                System.out.println("二叉查找树高度：" + bst.height());
                break;
            }
        }
        /*
         *
         * 构造的图像见 FlowChart 的 3.3.22.jpg
         *
         * 顺序插入数组: [6, 7, 2, 0, 4, 1, 5, 8, 9, 3]
         *
         * 红黑树高度：4
         *
         *     6    黑   10
         *     4    黑    6
         *     8    黑    3
         *     2    红    4
         *     5    黑    1
         *     7    黑    1
         *     9    黑    1
         *     1    黑    2
         *     3    黑    1
         *     0    红    1
         *
         * 二叉查找树高度：3
         *
         */
    }
}
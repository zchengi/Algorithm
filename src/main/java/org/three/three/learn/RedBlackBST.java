package org.three.three.learn;

import org.example.Queue;

import java.util.NoSuchElementException;

/**
 * 红黑二叉查找树（红黑树）
 *
 * @author cheng
 *         2018/2/28 13:19
 */
public class RedBlackBST<Key extends Comparable<Key>, Value> {

    private static final boolean RED = true;
    private static final boolean BlACK = false;

    private Node root;

    private class Node {
        private Key key;
        private Value value;
        private Node left, right;

        // 父链接的颜色
        private boolean color;

        private int size;

        public Node(Key key, Value value, boolean color, int size) {
            this.key = key;
            this.value = value;
            this.color = color;
            this.size = size;
        }
    }

    public RedBlackBST() {
    }

    public void put(Key key, Value value) {
        if (key == null) throw new IllegalArgumentException("First argument to put() is null!");
        if (value == null) {
            // delete(key);
            return;
        }

        root = put(root, key, value);
        root.color = BlACK;
        // assert check()
    }

    /**
     * 将键值对插入以h为根的子树中
     */
    private Node put(Node h, Key key, Value value) {
        if (h == null) return new Node(key, value, RED, 1);

        int cmp = key.compareTo(h.key);

        if (cmp < 0) h.left = put(h.left, key, value);
        else if (cmp > 0) h.right = put(h.right, key, value);
        else h.value = value;

        // 将任意含有红色右链接的3-结点（或临时的4-结点）向左旋转
        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        // 将临时的4-结点中两条连续红链接中的上层链接 向右旋转
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        // 进行颜色转换并将红链接在树中向上传递
        if (isRed(h.left) && isRed(h.right)) flipColors(h);

        h.size = 1 + size(h.left) + size(h.right);
        return h;
    }

    /**
     * 删除最小键
     */
    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("BST underflow!");

        // 如果两个子孩子都为黑色，则将root设为红色
        if (!isRed(root.left) && !isRed(root.right)) root.color = RED;

        root = deleteMin(root);
        if (!isEmpty()) root.color = BlACK;
        // assert check()
    }

    private Node deleteMin(Node node) {
        if (node.left == null) return null;

        if (!isRed(node.left) && !isRed(node.left.left)) {
            node = moveRedLeft(node);
        }

        node.left = deleteMin(node.left);
        return balance(node);
    }

    /**
     * 判断结点 node 是否为红色
     */
    private boolean isRed(Node node) {
        // return node == null ? false : node.color==RED;
        return node != null && node.color == RED;
    }

    /**
     * 左旋转h的右链接
     */
    private Node rotateLeft(Node h) {
        // assert (h != null) && isRed(h.right);

        Node node = h.right;
        h.right = node.left;
        node.left = h;

        node.color = h.color;
        node.left.color = RED;

        node.size = h.size;
        h.size = 1 + size(h.left) + size(h.right);

        return node;
    }

    /**
     * 右旋转h的左链接
     */
    private Node rotateRight(Node h) {
        // assert (h != null) && isRed(h.right);

        Node node = h.left;
        h.left = node.right;
        node.right = h;

        node.color = h.color;
        node.right.color = RED;

        node.size = h.size;
        h.size = 1 + size(h.left) + size(h.right);

        return node;
    }

    /**
     * 颜色转换
     * 翻转h结点即其两个孩子的颜色
     * 只能出现两种情况：
     * a. h:RED     h.left:BLACK   h.right:BLACK
     * b. h:BLACK   h.left:RED     h.right:RED
     */
    private void flipColors(Node h) {
        // h must have opposite color of its two children
        // assert (h != null) && (h.left != null) && (h.right != null);
        // assert (!isRed(h) && isRed(h.left) && isRed(h.right))
        //         || (isRed(h) && !isRed(h.left) && !isRed(h.left));

        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    /**
     * 假设node是红色，node.left和node.left.left都是黑色，将node.left或其中一个孩子设为红色。
     */
    private Node moveRedLeft(Node node) {
        // assert (node != null);
        // assert isRed(node) && !isRed(node.left) && !isRed(node.left.left)

        flipColors(node);
        if (isRed(node.right.left)) {
            node.right = rotateRight(node.right);
            node = rotateLeft(node);
            flipColors(node);
        }

        return node;
    }

    /**
     * 假设node是红色，node.right和node.right.left都是黑色，将node.right或其中一个孩子变成红色。
     */
    private Node moveRedRight(Node node) {
        // assert (node != null);
        // assert isRed(node) && !isRed(node.left) && !isRed(node.left.left)

        flipColors(node);
        if (isRed(node.left.left)) {
            node = rotateRight(node);
            flipColors(node);
        }

        return node;
    }

    /**
     * 恢复红黑树的平衡
     */
    private Node balance(Node node) {
        // assert node != null;

        if (isRed(node.right)) node = rotateLeft(node);
        if (isRed(node.left) && isRed(node.left.left)) node = rotateRight(node);
        if (isRed(node.left) && isRed(node.right)) flipColors(node);

        node.size = 1 + size(node.left) + size(node.right);

        return node;
    }

    private int size() {
        return size(root);
    }

    private int size(Node node) {
        return node == null ? 0 : node.size;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("Called min() with empty Symbol table!");

        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }

    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("Called max() with empty Symbol table!");

        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        else return max(x.right);
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("First argument to keys() is null!");
        if (hi == null) throw new IllegalArgumentException("Second argument to keys() is null!");

        Queue<Key> queue = new Queue<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) return;

        int cmpLo = lo.compareTo(x.key);
        int cmpHi = hi.compareTo(x.key);

        if (cmpLo < 0) keys(x.left, queue, lo, hi);
        if (cmpLo <= 0 && cmpHi >= 0) queue.enqueue(x.key);
        if (cmpHi > 0) keys(x.right, queue, lo, hi);
    }

    public static void main(String[] args) {
        String[] words = {"S", "E", "A", "R", "C", "H", "X", "M", "P", "L"};
        RedBlackBST<String, Integer> bst = new RedBlackBST<>();

        for (int i = 0; i < words.length; i++) {
            bst.put(words[i], i);
        }

        for (String letter : bst.keys()) {
            System.out.println(letter);
        }
    }
}

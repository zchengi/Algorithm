package org.three.three.learn;

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

    private int size() {
        return size(root);
    }

    private int size(Node node) {
        return node == null ? 0 : node.size;
    }

    public static void main(String[] args) {
        String[] words = {"S", "E", "A", "R", "C", "H", "X", "M", "P", "L"};
        RedBlackBST<String, Integer> bst = new RedBlackBST<>();

        for (int i = 0; i < words.length; i++) {
            bst.put(words[i], i);
        }
    }
}

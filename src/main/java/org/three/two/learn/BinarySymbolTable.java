package org.three.two.learn;

import org.example.Queue;

import java.util.NoSuchElementException;

/**
 * 算法3.3 基于二叉查找树的符号表
 *
 * 二叉树的每个结点都含有一个Comparable的键（以及相关联的值），
 * 且每个结点的键都大于其左子树中任意结点的键而小于右子树的任意结点的键。
 *
 * @author cheng
 *         2018/2/13 11:12
 */
public class BinarySymbolTable<Key extends Comparable<Key>, Value> {

    /**
     * 二叉查找树的根节点
     */
    private Node root;

    private class Node {
        // 键
        private Key key;
        // 值
        private Value value;
        // 指向子树的链接
        private Node left, right;
        // 以该结点为根的子树中的结点总数（自己 + 左子树结点总数 + 右子树结点总数）
        private int size;

        public Node(Key key, Value value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }

    public BinarySymbolTable() {
    }

    public void put(Key key, Value value) {
        if (key == null) throw new IllegalArgumentException("Called put() with a null key!");
        if (value == null) {
            delete(key);
            return;
        }

        // 查找key，找到则更新它的值，否则为它创建一个新的结点
        root = put(root, key, value);
        assert check();
    }

    private Node put(Node x, Key key, Value value) {
        // 如果key存在于以x为根结点的子树中则更新它的值；
        // 否则将以key和value为键值对的新结点插入到该子树中；
        if (x == null) return new Node(key, value, 1);

        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, value);
        else if (cmp > 0) x.right = put(x.right, key, value);
        else x.value = value;

        // 左子数结点总数 + 右子数结点总数 + 根结点
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    private Value get(Node x, Key key) {
        if (key == null) throw new IllegalArgumentException("Called get() with a null key!");
        // 在以x为根节点的子树中查找并返回Key所对应的值；
        // 如果找不到则返回null
        if (x == null) return null;

        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.value;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("Called delete() with a null key!");

        root = delete(root, key);
        assert check();
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;

        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.left == null) return x.right;
            if (x.right == null) return x.left;

            Node temp = x;
            x = min(temp.right);
            x.right = deleteMin(temp.right);
            x.left = temp.left;
        }

        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    public void deleteMin() {
        if (root == null) throw new NoSuchElementException("Symbol table underflow!");

        root = deleteMin(root);
        assert check();
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);

        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    public void deleteMax() {
        if (root == null) throw new NoSuchElementException("Symbol table underflow!");

        deleteMax(root);
        assert check();
    }

    private Node deleteMax(Node x) {
        if (x.right == null) return x.left;
        x.right = deleteMax(x.right);

        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    public Key select(int k) {
        if (k < 0 || k >= size()) {
            throw new NoSuchElementException("Called select with invalid argument: " + k + "!");
        }

        Node x = select(root, k);
        return x.key;
    }

    public Node select(Node x, int k) {
        if (x == null) return null;

        int t = size(x.left);
        if (k < t) return select(x.left, k);
        // k - t - 1：减去左子树结点数，和当前的一个结点，从右子树查找
        if (k > t) return select(x.right, k - t - 1);
        else return x;
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

    public Key floor(Key key) {
        if (key == null) throw new IllegalArgumentException("Called floor() with a null key!");
        if (isEmpty()) throw new NoSuchElementException("Called floor() with empty symbol table!");

        Node x = floor(root, key);
        if (x == null) return null;
        else return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) return null;

        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        // 如果 key 小于当前结点，则floor(key) 一定在左子树
        if (cmp < 0) return floor(x.left, key);

        // 如果 key 大于当前结点，则floor(key) 只可能在右子树，或者就是当前结点
        Node temp = floor(x.right, key);
        if (temp != null) return temp;
        else return x;
    }

    public Key ceiling(Key key) {
        if (key == null) throw new IllegalArgumentException("Called ceiling() with a null key!");
        if (isEmpty()) throw new NoSuchElementException("Called ceiling() with empty symbol table!");

        Node x = ceiling(root, key);
        if (x == null) return null;
        else return x.key;
    }

    private Node ceiling(Node x, Key key) {
        if (x == null) return null;

        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp > 0) return ceiling(x.right, key);

        Node temp = ceiling(x.left, key);
        if (temp != null) return temp;
        else return x;
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

    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("Called contains() with a null key!");

        return get(key) != null;
    }

    public boolean isEmpty() {
        return size() == 0;
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

    public int height() {
        return height(root);
    }

    private int height(Node x) {
        if (x == null) return -1;
        return 1 + Math.max(height(x.left), height(x.right));
    }

    /**
     * 用于deBug： key 按顺序排列在BST中的 keys
     */
    public Iterable<Key> levelOrder() {
        Queue<Key> keys = new Queue<>();
        Queue<Node> queue = new Queue<>();

        queue.enqueue(root);
        while (!queue.isEmpty()) {
            Node x = queue.dequeue();
            if (x == null) continue;

            keys.enqueue(x.key);
            queue.enqueue(x.left);
            queue.enqueue(x.right);
        }
        return keys;
    }

    private boolean check() {
        // 不是对称的顺序
        if (!isBST()) System.out.println("Not is symmetric order!");
        if (!isSizeConsistent()) System.out.println("Subtree counts not consistent!");
        if (!isRankConsistent()) System.out.println("Ranks not consistent!");

        return isBST() && isSizeConsistent() && isRankConsistent();
    }

    private boolean isBST() {
        return isBST(root, null, null);
    }

    private boolean isBST(Node x, Key min, Key max) {
        if (x == null) return true;
        if (min != null && x.key.compareTo(min) <= 0) return false;
        if (max != null && x.key.compareTo(max) >= 0) return false;

        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    }

    private boolean isSizeConsistent() {
        return isSizeConsistent(root);
    }

    private boolean isSizeConsistent(Node x) {
        if (x == null) return true;
        if (x.size != 1 + size(x.left) + size(x.right)) return false;

        return isSizeConsistent(x.left) && isSizeConsistent(x.right);
    }

    private boolean isRankConsistent() {
        for (int i = 0; i < size(); i++) {
            if (i != rank(select(i))) return false;
        }
        for (Key key : keys()) {
            if (!key.equals(select(rank(key)))) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        BinarySymbolTable<Integer, String> bst = new BinarySymbolTable<>();
        bst.put(4, "4");
        bst.put(1, "1");
        bst.put(2, "2");
        bst.put(5, "5");
        bst.put(9, "9");
        bst.put(6, "6");
        bst.put(3, "3");

        for (Integer i : bst.levelOrder()) {
            System.out.println(i + " " + bst.get(i));
        }

        System.out.println();

        for (Integer i : bst.keys()) {
            System.out.println(i + " " + bst.get(i));
        }
    }
}

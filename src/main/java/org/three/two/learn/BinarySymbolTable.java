package org.three.two.learn;

import java.util.NoSuchElementException;

/**
 * 算法3.3 基于二叉查找树的符号表
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
        private int n;

        public Node(Key key, Value value, int n) {
            this.key = key;
            this.value = value;
            this.n = n;
        }

    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (key == null) throw new IllegalArgumentException("called get() with a null key!");
        // 在以x为根节点的子树中查找并返回Key所对应的值；
        // 如果找不到则返回null
        if (x == null) return null;

        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.value;
    }

    public void put(Key key, Value value) {
        if (key == null) throw new IllegalArgumentException("called put() with a null key!");
        if (value == null) {
            delete(key);
            return;
        }
        // 查找key，找到则更新它的值，否则为它创建一个新的结点
        root = put(root, key, value);
    }

    private Node put(Node x, Key key, Value value) {
        // 如果key存在于以x为根结点的子树中则更新它的值；
        // 否则将以key和value为键值对的新结点插入到该子树中；
        if (x == null) return new Node(key, value, 1);

        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, value);
        else if (cmp > 0) x.right = put(x.right, key, value);
        else x.value = value;

        // 左子数结点总数 + 右子数结点总数 + 自己
        x.n = size(x.left) + size(x.right) + 1;
        return x;
    }

    private void delete(Key key) {

    }

    private Key floor(Key key) {
        if (key == null) throw new IllegalArgumentException("called floor() with a null key!");
        if (isEmpty()) throw new NoSuchElementException("called floor() with empty symbol table!");
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

        // 如果 key 大于当前结点，则floor(key) 可能只能在右子树，或者就是当前结点
        Node temp = floor(x.right, key);
        if (temp != null) return temp;
        else return x;
    }

    private Key ceiling(Key key) {
        if (key == null) throw new IllegalArgumentException("called ceiling() with a null key!");
        if (isEmpty()) throw new NoSuchElementException("called ceiling() with empty symbol table!");
        return null;
    }

    private Key max() {
        if (isEmpty()) throw new NoSuchElementException("called max() with empty symbol table!");
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        else return max(x.right);
    }

    private Key min() {
        if (isEmpty()) throw new NoSuchElementException("called min() with empty symbol table!");
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    private int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        else return x.n;
    }

    public static void main(String[] args) {
        BinarySymbolTable<Integer, String> bst = new BinarySymbolTable<>();
        bst.put(1, "1");
        bst.put(4, "4");
        bst.put(2, "2");
        bst.put(5, "5");
        bst.put(9, "9");
        bst.put(6, "6");
    }
}

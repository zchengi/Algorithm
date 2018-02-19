package org.three.two.learn2;

/**
 * 二分搜索树
 *
 * @author cheng
 *         2018/2/18 13:50
 */
public class BinarySearchTree<Key extends Comparable<Key>, Value> {

    private Node root;
    private int count;

    private class Node {
        private Key key;
        private Value value;
        private Node left, right;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            this.left = this.right = null;
        }
    }

    public BinarySearchTree() {
        root = null;
        count = 0;
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public void insert(Key key, Value value) {
        if (key == null) throw new IllegalArgumentException("called insert() with a null key!");
        if (value == null) {
            // TODO
        }
        root = insert(root, key, value);
    }

    /**
     * 向以node为根的二叉搜索树中，插入结点(key,value)
     * 返回插入新结点后的二叉搜索树的根
     */
    private Node insert(Node node, Key key, Value value) {
        if (node == null) {
            count++;
            return new Node(key, value);
        }

        int cmp = node.key.compareTo(key);
        if (cmp > 0) node.left = insert(node.left, key, value);
        else if (cmp < 0) node.right = insert(node.right, key, value);
        else node.value = value;

        return node;
    }

    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("Called contains() with a null key!");

        return search(key) != null;
    }

    public Value search(Key key) {
        return search(root, key);
    }

    private Value search(Node node, Key key) {
        if (key == null) throw new IllegalArgumentException("Called search() with a null key!");
        if (node == null) return null;

        int cmp = node.key.compareTo(key);
        if (cmp > 0) return search(node.left, key);
        else if (cmp < 0) return search(node.right, key);
        else return node.value;
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<>();
        bst.insert(1, 1);
        bst.insert(2, 2);
        bst.insert(3, 3);
        bst.insert(4, 4);
        bst.insert(5, 5);
        bst.insert(3, 33);

        System.out.println(bst.contains(3));
        System.out.println(bst.search(3));
    }
}

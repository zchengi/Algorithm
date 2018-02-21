package org.three.two.learn2;

import java.util.LinkedList;

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

    public void removeMin() {
        if (root != null) {
            root = removeMin(root);
        }
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            count--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    public void removeMax() {
        if (root != null) {
            root = removeMax(root);
        }
    }

    private Node removeMax(Node node) {
        if (node.right == null) {
            Node rightNode = node.left;
            node.left = null;
            count--;
            return rightNode;
        }
        node.right = removeMax(node.right);
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

    public Key minimum() {
        assert count != 0;

        return minimum(root).key;
    }

    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    public Key maximum() {
        assert count != 0;

        return maximum(root).key;
    }

    private Node maximum(Node node) {
        if (node.right == null) {
            return node;
        }
        return maximum(node.right);
    }

    /**
     * 层序遍历
     */
    public void levelOrder() {
        // 使用LinkedList作为队列
        LinkedList<Node> list = new LinkedList<>();
        list.add(root);

        while (!list.isEmpty()) {
            Node node = list.remove();
            System.out.println(node.key);

            if (node.left != null) {
                list.add(node.left);
            }
            if (node.right != null) {
                list.add(node.right);
            }
        }
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node node) {
        if (node != null) {
            System.out.println(node.key);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    /**
     * 中序遍历
     */
    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.println(node.key);
            inOrder(node.right);
        }
    }

    /**
     * 后续遍历
     */
    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.println(node.key);
        }
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<>();
        bst.insert(3, 3);
        bst.insert(1, 1);
        bst.insert(2, 2);
        bst.insert(4, 4);
        bst.insert(5, 5);
        bst.insert(3, 33);

        System.out.println(bst.contains(3));
        System.out.println(bst.search(3));

        bst.removeMin();
        bst.removeMax();
    }
}

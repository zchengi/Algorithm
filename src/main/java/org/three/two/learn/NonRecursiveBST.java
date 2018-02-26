package org.three.two.learn;

import org.example.Queue;
import org.example.Stack;

import java.util.NoSuchElementException;

/**
 * 非递归的二叉查找树
 *
 * @author cheng
 *         2018/2/26 16:39
 */
public class NonRecursiveBST<Key extends Comparable<Key>, Value> {

    private Node root;

    private class Node {

        private Key key;
        private Value value;
        private Node left, right;
        private int size;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            size = 1;
        }
    }

    public void put(Key key, Value value) {
        if (key == null) throw new IllegalArgumentException("Called put() with a null key!");
        if (value == null) {
            // TODO
            //delete(key);
            return;
        }

        Node newNode = new Node(key, value);
        if (isEmpty()) {
            root = newNode;
            return;
        }

        Node current = root;
        Node parent = null;
        while (current != null) {
            parent = current;
            int cmp = key.compareTo(current.key);

            if (cmp < 0) current = current.left;
            else if (cmp > 0) current = current.right;
            else {
                current.value = value;
                parent = null;
                break;
            }
        }

        if (parent == null) return;

        if (key.compareTo(parent.key) < 0) parent.left = newNode;
        else parent.right = newNode;

        // 更新size值
        updateSize(newNode);
    }

    private void updateSize(Node node) {
        Stack<Node> stack = new Stack<>();

        Node current = root;
        while (current != null) {
            stack.push(current);
            int cmp = node.key.compareTo(current.key);

            if (cmp == 0) break;
            if (cmp < 0) current = current.left;
            else current = current.right;
        }

        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            pop.size = 1 + size(pop.left) + size(pop.right);
        }
    }

    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("Called get() with a null key!");
        if (root == null) return null;

        Node current = root;
        while (current != null) {
            int cmp = key.compareTo(current.key);

            if (cmp > 0) current = current.right;
            else if (cmp < 0) current = current.left;
            else return current.value;
        }
        return null;
    }

    public Key floor(Key key) {
        if (key == null) throw new IllegalArgumentException("Called floor() with a null key!");
        if (root == null) throw new NoSuchElementException("Called floor() with empty symbol table!");

        Node temp = null;
        Node current = root;

        while (current != null) {
            int cmp = key.compareTo(current.key);

            if (cmp == 0) return current.key;
            else if (cmp < 0) current = current.left;
            else {
                temp = current;
                current = current.right;
            }
        }

        if (temp != null) return temp.key;
        else if (current == null) return null;
        else return current.key;
    }

    public Key ceiling(Key key) {
        if (key == null) throw new IllegalArgumentException("Called floor() with a null key!");
        if (root == null) throw new NoSuchElementException("Called floor() with empty symbol table!");

        Node temp = null;
        Node current = root;

        while (current != null) {
            int cmp = key.compareTo(current.key);

            if (cmp == 0) return current.key;
            else if (cmp > 0) current = current.right;
            else {
                temp = current;
                current = current.left;
            }
        }

        if (temp != null) return temp.key;
        else if (current == null) return null;
        else return current.key;
    }

    public int rank(Key key) {
        if (key == null) throw new IllegalArgumentException("Called rank() with a null key!");
        if (root == null) return 0;

        int count = 0;
        Node current = root;

        while (current != null) {
            int cmp = key.compareTo(current.key);

            if (cmp == 0) return count + size(current.left);
            else if (cmp < 0) current = current.left;
            else {
                count += size(current.left) + 1;
                current = current.right;
            }
        }
        return count;
    }

    public Key select(int k) {
        if (k < 0 || k >= size()) {
            throw new NoSuchElementException("Called select with invalid argument: " + k + "!");
        }
        if (root == null) return null;

        Node current = root;

        while (current != null) {
            int ls = size(current.left);

            if (k < ls) current = current.left;
            else if (k > ls) {
                k = k - 1 - ls;
                current = current.right;
            } else return current.key;
        }
        return null;
    }

    public Key min() {
        if (isEmpty()) return null;

        Node current = root;
        while (current != null) {
            if (current.left != null) current = current.left;
            else return current.key;
        }
        return null;
    }

    public Key max() {
        if (isEmpty()) return null;

        Node current = root;
        while (current != null) {
            if (current.right != null) current = current.right;
            else return current.key;
        }
        return null;
    }

    public Iterable<Key> keys() {
        Stack<Node> stack = new Stack<>();
        Queue<Key> queue = new Queue<>();

        Node current = root;
        while (current != null || !stack.isEmpty()) {
            if (current != null) {
                stack.push(current);
                current = current.left;
            } else {
                current = stack.pop();
                queue.enqueue(current.key);
                current = current.right;
            }
        }
        return queue;
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

    public static void main(String[] args) {
        NonRecursiveBST<Integer, Integer> bst = new NonRecursiveBST<>();
        bst.put(3, 3);
        bst.put(1, 1);
        bst.put(2, 2);
        bst.put(4, 4);
        bst.put(5, 5);
        bst.put(3, 33);

        for (Integer i : bst.keys()) {
            System.out.println(i + " " + bst.get(i));
        }

        System.out.println("最小值的键: " + bst.min() + ", 最大值的键: " + bst.max());
        System.out.println("floor(1) = " + bst.floor(1));
        System.out.println("ceiling(5) = " + bst.ceiling(5));
        System.out.println("select(4) = " + bst.select(4));
        System.out.println("rank(6) = " + bst.rank(6));
    }
}

package org.three.two.learn2;

/**
 * 使用迭代构造二分搜索树
 *
 * @author cheng
 *         2018/2/18 14:04
 */
public class BinarySearchTreeLoop<Key extends Comparable<Key>, Value> {

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

    public BinarySearchTreeLoop() {
        root = null;
        count = 0;
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * 使用迭代迭代向 以node为根的二叉搜索树中，插入结点(key,value)
     */
    public void insert(Key key, Value value) {
        if (key == null) throw new IllegalArgumentException("Called insert() with a null key!");
        if (value == null) {
            // TODO
            return;
        }

        Node newNode = new Node(key, value);
        if (root == null) {
            root = newNode;
            count++;
            return;
        }

        Node current = root;
        Node parent = null;

        while (current != null) {
            parent = current;
            int cmp = current.key.compareTo(key);

            if (cmp > 0) current = current.left;
            else if (cmp < 0) {
                current = current.right;
            } else {
                current.value = value;
                parent = null;
                break;
            }
        }

        if (parent == null) return;

        if (parent.key.compareTo(key) < 0) parent.right = newNode;
        else parent.left = newNode;
        count++;
    }

    public static void main(String[] args) {
        BinarySearchTreeLoop<Integer, Integer> bst = new BinarySearchTreeLoop<>();
        bst.insert(3, 3);
        bst.insert(1, 1);
        bst.insert(5, 5);
        bst.insert(4, 4);
        bst.insert(2, 2);
        bst.insert(3, 33);
    }
}


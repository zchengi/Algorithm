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
            remove(key);
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

            // 当前结点大于要插入的结点
            if (cmp > 0) current = current.left;
            else if (cmp < 0) {
                // 当前结点小于要插入的结点
                current = current.right;
            } else {
                // 当前结点就是要插入的结点
                current.value = value;
                parent = null;
                break;
            }
        }

        // 如果存在要插入的结点，父节点为空
        if (parent == null) return;

        if (parent.key.compareTo(key) < 0) {
            // 如果要父节点key小于要插入结点的key，则插入到父节点的右子树
            parent.right = newNode;
        } else {
            // 如果要父节点key大于要插入结点的key，则插入到父节点的左子树
            parent.left = newNode;
        }
        count++;
    }

    public void remove(Key key) {
        if (root == null) return;
        // TODO
    }

    public void removeMin() {
        // TODO
        Node current = root;
        Node parent;
        while (current != null) {
            if (current.left == null) {
                Node rightNode = current.right;
                current.right = null;
                count--;

            }
        }
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


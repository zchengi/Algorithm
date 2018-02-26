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

        public Node(Node node) {
            this.key = node.key;
            this.value = node.value;
            this.left = node.left;
            this.right = node.right;
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
        if (key == null) throw new IllegalArgumentException("Called insert() with a null key!");
        if (value == null) {
            remove(key);
            return;
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

    public void remove(Key key) {
        root = remove(root, key);
    }

    /**
     * 删除掉以node为根的二分搜索树中的键值为key的结点，递归算法
     * 返回删除结点后的新的二分搜素树的根
     */
    private Node remove(Node node, Key key) {
        if (node == null) return null;

        int cmp = node.key.compareTo(key);

        if (cmp > 0) {
            node.left = remove(node.left, key);
            return node;
        } else if (cmp < 0) {
            node.right = remove(node.right, key);
            return node;
        } else {
            // key == node.key

            // 待删除结点左子树为空的情况
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                count--;
                return rightNode;
            }

            // 待删除结点右子树为空的情况
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                count--;
                return leftNode;
            }

            // 待删除结点左右子树不为空的情况

            // 找到比待删除结点大的最小结点，即待删除结点右子树的最小结点
            // 用这个结点顶替待删除结点的位置
            Node successor = new Node(minimum(node.right));
            count++;

            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;
            count--;

            return successor;
        }
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

    public Key floor(Key key) {
        if (count == 0 || key.compareTo(minimum()) < 0) return null;

        Node floorNode = floor(root, key);
        return floorNode.key;
    }

    private Node floor(Node node, Key key) {
        if (node == null) return null;

        int cmp = node.key.compareTo(key);

        // 如果node的key值和要寻找的key值相等
        // 则node本身就是key的floor结点
        if (cmp == 0) return node;

        // 如果node的key值比要寻找的key值大
        // 则要寻找的key的floor结点一定在node的左子树中
        if (cmp > 0) return floor(node.left, key);

        // 如果node的key值比要寻找的key值小
        // 则node有可能是key的floor结点，也有可能不是（存在比node.key大但是小于key的其余结点）
        // 需要尝试向node的左子树寻找一下
        Node tempNode = floor(node.right, key);
        if (tempNode != null) {
            return tempNode;
        }
        return node;
    }

    public Key ceil(Key key) {
        if (count == 0 || key.compareTo(maximum()) > 0) return null;

        Node ceilNode = ceil(root, key);
        return ceilNode.key;
    }

    private Node ceil(Node node, Key key) {
        if (node == null) return null;

        int cmp = node.key.compareTo(key);

        if (cmp == 0) return node;

        if (cmp < 0) return ceil(node.right, key);

        Node tempNode = ceil(node.left, key);
        if (tempNode != null) {
            return tempNode;
        }

        return node;

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
        bst.remove(3);
        System.out.println(bst.ceil(4));
        System.out.println(bst.floor(2));
    }
}

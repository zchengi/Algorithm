package org.two.four.exercises;

import org.tool.SortTestHelper;

import java.util.NoSuchElementException;

/**
 * 2.4.24 使用链接的优先队列。用堆有序的二叉树实现一个优先队列，但使用链表解结构代替数组。
 * 每个结点都需要三个链接：两个向下，一个向上，你的实现即使在无法预知队列大小的情况下
 * 也能保证优先队列的基本操作所需的时间为对数级别。
 *
 * @author cheng
 *         2018/2/6 0:48
 */
public class Exercise24 {
    static class maxPQ<Item extends Comparable<Item>> {
        class Node {
            Node left;
            Node right;
            Node parent;
            int height;
            Item item;

            public Node(Item item) {
                this.item = item;
            }
        }

        Node root = null;

        public void insert(Item item) {
            root = insert(root, item);
        }

        private Node insert(Node n, Item item) {
            if (n == null) return new Node(item);
            if (item.compareTo(n.item) > 0) {
                Item temp = n.item;
                n.item = item;
                item = temp;
            }

            int lh = getHeight(n.left);
            int rh = getHeight(n.right);

            if (lh <= rh) {
                n.left = insert(n.left, item);
                n.left.parent = n;
            } else {
                n.right = insert(n.right, item);
                n.right.parent = n;
            }
            n.height = 1 + maxHeight(n.left, n.right);
            return n;
        }

        public Item delMax() {
            if (isEmpty()) {
                throw new NoSuchElementException("priority queue is empty!");
            }
            Node last = getLast();
            Node parent = last.parent;
            if (parent == null) {
                Item max = root.item;
                root.item = null;
                root = null;
                return max;
            }
            Item max = root.item;
            root.item = last.item;
            // 取出末尾结点，同时更新该路径上所有结点的高度
            if (isLeft(last)) {
                last.parent.left = null;
            } else {
                last.parent.right = null;
            }
            while (parent != null) {
                parent.height = 1 + maxHeight(parent.left, parent.right);
                parent = parent.parent;
            }

            // 下沉
            Node current = root;
            while (hasChild(current)) {
                Node m = maxNode(current.left, current.right);
                if (current.item.compareTo(m.item) >= 0) break;
                Item temp = current.item;
                current.item = m.item;
                m.item = temp;
                if (m == current.right) {
                    current = current.right;
                } else {
                    current = current.left;
                }
            }
            return max;
        }

        private Node getLast() {
            if (root == null) return null;
            Node current = root;
            while (hasChild(current)) {
                if (getHeight(current.left) > getHeight(current.right)) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }
            return current;
        }

        private int maxHeight(Node n1, Node n2) {
            return getHeight(n1) > getHeight(n2) ? getHeight(n1) : getHeight(n2);
        }

        private Node maxNode(Node n1, Node n2) {
            if (n1 == null) return n2;
            if (n2 == null) return n1;
            return n1.item.compareTo(n2.item) > 0 ? n1 : n2;
        }

        private int getHeight(Node n) {
            return n == null ? -1 : n.height;
        }

        private boolean isLeft(Node n) {
            return n.parent.left == n;
        }

        private boolean hasChild(Node n) {
            return n.left != null || n.right != null;
        }

        public boolean isEmpty() {
            return root == null;
        }
    }

    public static void main(String[] args) {
        maxPQ<Integer> maxPQ = new maxPQ<>();
        int n = 20;
        Integer[] arr = SortTestHelper.generateNearlyOrderedArray(n, 100);
        for (Integer i : arr) {
            maxPQ.insert(i);
        }
        while (!maxPQ.isEmpty()) {
            System.out.println(maxPQ.delMax());
        }
    }
}

package org.two.four.learn3;

/**
 * 链接式优先队列（最大堆）
 *
 * @author cheng
 *         2018/2/6 18:36
 */
public class ListMaxPQ<Item extends Comparable<Item>> {

    /**
     * 二叉堆根结点
     */
    private Node root;
    /**
     * 双向队列
     */
    private Deque<Node> deque = new Deque<>();

    private int size;

    private class Node {
        Item item;
        Node parent;
        Node lChild;
        Node rChild;
    }

    public ListMaxPQ() {
        root = new Node();
        root.item = null;
        root.parent = null;
        root.lChild = null;
        root.rChild = null;
        deque.addFirst(root);
        size = 0;
    }

    public void insert(Item item) {
        if (isEmpty()) {
            root.item = item;
            size++;
            return;
        }
        // 新建结点
        Node newNode = new Node();
        newNode.item = item;
        while (!deque.isEmpty()) {
            // 取队头结点但不出队
            Node temp = deque.peek();
            if (temp.lChild == null) {
                // 建立双向链接
                temp.lChild = newNode;
                newNode.parent = temp;
                // 左孩子插入deque队尾
                deque.addLast(temp.lChild);
                size++;
                // 新结点上游
                swim(newNode);
                // 仅插入左孩子的结点不出队，之后插入右孩子
                return;
            }
            if (temp.rChild == null) {
                // 建立双向队列
                temp.rChild = newNode;
                newNode.parent = temp;
                // 插入右孩子向队尾
                deque.addLast(temp.rChild);
                // 插入右孩子后结点处理完毕，出队
                deque.removeFirst();
                size++;
                // 新结点上游
                swim(newNode);
                return;
            }
        }
    }

    public Item delMax() {
        if (size == 0) return null;
        else if (size == 1) {
            size--;
            return root.item;
        }
        // 根结点即最大结点
        Item max = root.item;
        // 删除deque队尾（完全二叉树的末尾结点）
        Node last = deque.removeLast();
        // 如果是右孩子则将父结点恢复到deque中
        if (last.parent.rChild == last) {
            deque.addFirst(last.parent);
        }
        // 交换末尾结点和根结点
        exch(last, root);

        // 以下操作是删除末尾结点的任何链接，作为垃圾被回收
        if (last.parent.lChild == last) {
            last.parent.lChild = null;
        } else {
            last.parent.rChild = null;
        }

        last.parent = null;
        last = null;
        size--;
        // 根结点下沉
        sink(root);
        return max;
    }

    private void swim(Node node) {
        while (node.parent != null && less(node.parent, node)) {
            exch(node.parent, node);
            node = node.parent;
        }
    }

    private void sink(Node node) {
        while (node.lChild != null) {
            Node temp = node.lChild;
            if (node.rChild != null && less(temp, node.rChild)) {
                temp = node.rChild;
            }
            if (!less(node, temp)) break;
            exch(temp, node);
            node = temp;
        }
    }

    private boolean less(Node v, Node w) {
        return v.item.compareTo(w.item) < 0;
    }

    private void exch(Node a, Node b) {
        Item temp = a.item;
        a.item = b.item;
        b.item = temp;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void show() {
        // 按照广度优先顺序遍历
        // 层次信息队列
        ListQueue<Node> queue = new ListQueue<>();

        queue.enqueue(root);
        System.out.println("基于链表的优先队列： ");
        while (!queue.isEmpty()) {
            // 取出根结点
            Node temp = queue.dequeue();
            System.out.print(temp.item + " ");
            // 分别将左右孩子入队，成为下一层结点
            if (temp.lChild != null) {
                queue.enqueue(temp.lChild);
            }
            if (temp.rChild != null) {
                queue.enqueue(temp.rChild);
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ListMaxPQ<Integer> listMaxPQ = new ListMaxPQ<>();
        for (int i = 0; i < 20; i++) {
            listMaxPQ.insert((int) (Math.random() * 50) + 1);
        }
        listMaxPQ.show();
        System.out.println("删除最大元素：");
        while (!listMaxPQ.isEmpty()) {
            System.out.print(listMaxPQ.delMax() + " ");
        }
    }
}

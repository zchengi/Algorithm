package org.two.four.learn3;

/**
 * 使用链表构建完全二叉树
 *
 * @author cheng
 *         2018/2/6 16:21
 */
public class CompleteBiTree<T> {

    /**
     * 二叉树的根结点
     */
    private Node root;

    /**
     * 层次信息的队列
     */
    private ListQueue<Node> queue;

    private int count;

    private class Node {

        T item;
        Node lChild;
        Node rChild;
    }


    public CompleteBiTree() {
        // 将根结点入队
        queue.enqueue(root);
        count = 0;
    }

    public void insert(T item) {
        if (isEmpty()) {
            root.item = item;
            count++;
            return;
        }

        // 新建结点
        Node newNode = new Node();
        newNode.item = item;
        if (!queue.isEmpty()) {
            // 取出队头结点但不出队
            Node temp = queue.peek();
            if (temp.lChild == null) {
                temp.lChild = newNode;
                count++;
                // 仅有左孩子的结点并不出队，之后插入其右孩子
            } else if (temp.rChild == null) {
                temp.rChild = newNode;
                // 插入右孩子后结点处理完毕，让其出队
                queue.dequeue();
                // 将左右孩子入队，为下一层结点
                queue.enqueue(temp.lChild);
                queue.enqueue(temp.rChild);
                count++;
            }
        }
    }

    /**
     * 按照广度优先的顺序遍历
     */
    public void show() {
        // 层次信息的队列
        ListQueue<Node> queue = new ListQueue<>();
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            // 根结点出队
            Node t = queue.dequeue();
            System.out.print(t.item + " ");
            // 分别将左右孩子入队，成为下一层结点
            if (t.lChild != null) {
                queue.enqueue(t.lChild);
            }
            if (t.rChild != null) {
                queue.enqueue(t.rChild);
            }
        }
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int size() {
        return count;
    }

    public static void main(String[] args) {
        CompleteBiTree<Integer> tree = new CompleteBiTree<>();
        for (int i = 0; i < 10; i++) {
            tree.insert(i);
        }
        tree.show();
    }
}

package org.three.two.learn2;

/**
 * 顺序查找表
 *
 * @author cheng
 *         2018/2/19 12:59
 */
public class SST<Key extends Comparable<Key>, Value> {

    private Node head;
    private int count;

    private class Node {
        private Key key;
        private Value value;
        private Node next;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            next = null;
        }
    }

    public SST() {
        head = null;
        count = 0;
    }

    public void insert(Key key, Value value) {

        // 查找整个顺序表，看是否存在同样大小的key
        Node node = head;
        while (node != null) {
            // 若在顺序表中找到了同样大小的key的结点
            // 则在当前结点不需要插入，将该key所对应的value更新后返回
            if (key.compareTo(node.key) == 0) {
                node.value = value;
                return;
            }
            node = node.next;
        }

        // 若顺序表中没有同样大小的key，则创建新结点，将新结点直接插在表头
        Node newNode = new Node(key, value);
        newNode.next = head;
        head = newNode;
        count++;
    }

    public void remove(Key key) {
        if (head == null) return;

        // 思考：对于链表，可以使用什么技术不去特殊处理头结点的特殊情况？

        // 如果待删除的结点就是头结点，则需要特殊处理
        if (key.compareTo(head.key) == 0) {
            Node delNode = head;
            head = head.next;
            delNode.next = null;
            count--;
            return;
        }

        Node node = head;
        while (node.next != null && node.next.key.compareTo(key) != 0) {
            node = node.next;

            if (node.next != null) {
                Node delNode = node.next;
                node.next = delNode.next;
                delNode.next = null;
                count--;
                return;
            }
        }
    }

    public Value search(Key key) {
        Node node = head;

        while (node != null) {
            if (key.compareTo(node.key) == 0) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    public boolean contains(Key key) {
        return search(key) != null;
    }


    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

}


package org.two.two.exercises;

import edu.princeton.cs.algs4.StdRandom;

/**
 * 2.2.18 打乱链表。实现一个分治算法，使用线性对数级别的时候和对数级别的额外空间随即打乱一条链表。
 *
 * @author cheng
 *         2018/1/20 22:31
 */
public class Exercise18 {
    static class Node<T extends Comparable<T>> {
        Node<T> next;
        T item;

        Node() {
        }

        Node(T item, Node<T> next) {
            this.item = item;
            this.next = next;
        }

        Node<T> insertAfter(T item) {
            Node<T> n = new Node<>(item, next);
            next = n;
            return n;
        }

        Node<T> removeFirst() {
            item = null;
            return next;
        }

        void forwardPrint() {
            Node<T> current = this;
            System.out.print(current.item + " ");
            while ((current = current.next) != null) {
                System.out.print(current.item + " ");
            }
            System.out.println();
        }

        int forwardCount() {
            Node<T> current = this;
            int count = 1;
            while ((current = current.next) != null) {
                count++;
            }
            return count;
        }

        static Node<Integer> list(int n) {
            Node<Integer> header = new Node<>();
            Node<Integer> temp = header;
            for (int i = 0; i < n; i++) {
                temp = temp.insertAfter(i);
            }
            return header.next;
        }

        public static <T extends Comparable<T>> Node<T> nextOfMid(Node<T> head) {
            Node<T> fast = head;
            Node<T> slow = head;
            while (true) {
                fast = fast.next;
                if (fast == null) break;
                fast = fast.next;
                if (fast == null) break;
                slow = slow.next;
            }
            Node<T> next = slow.next;
            slow.next = null;
            return next;
        }

        public static <T extends Comparable<T>> Node<T> merge(Node<T> head) {
            if (head == null || head.next == null) return head;
            Node<T> head1 = head;
            Node<T> head2 = nextOfMid(head);
            head1 = merge(head1);
            head2 = merge(head2);
            return mergeShuffle(head1, head2);
        }

        private static <T extends Comparable<T>> Node<T> mergeShuffle(Node<T> list1, Node<T> list2) {
            int count1 = list1.forwardCount();
            int count2 = list2.forwardCount();
            Node<T> header = new Node<>();
            Node<T> temp = header;

            while (count1 != 0 || count2 != 0) {
                if (count1 == 0) {
                    temp.insertAfter(list2.item);
                    list2 = list2.removeFirst();
                    --count2;
                } else if (count2 == 0) {
                    temp.insertAfter(list1.item);
                    list1 = list1.removeFirst();
                    --count1;
                } else if (less(count1, count2)) {
                    temp.insertAfter(list1.item);
                    list1 = list1.removeFirst();
                    --count1;
                } else {
                    temp.insertAfter(list2.item);
                    list2 = list2.removeFirst();
                    --count2;
                }
            }
            return header.next;
        }

        public static boolean less(int count1, int count2) {
            return StdRandom.uniform() < (count1 * 1.0) / (count1 + count2);
        }

        public static void main(String[] args) {
            Node<Integer> list = Node.list(20);
            System.out.println("---------- 置乱前 ----------");
            list.forwardPrint();

            list = merge(list);
            System.out.println("---------- 置乱后 ----------");
            list.forwardPrint();
        }
    }
}


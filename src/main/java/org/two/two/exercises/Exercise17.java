package org.two.two.exercises;

import edu.princeton.cs.algs4.StdRandom;

/**
 * 2.2.17 链表排序。实现对链表的自然排序（这是将链表排序的最佳方法，因为它不需要额外的空间，
 * 且运行时间是线性对数级别的）。
 *
 * @author cheng
 *         2018/1/20 22:12
 */
public class Exercise17 {
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

        Node<T> forwardSearch(T item) {
            Node<T> tNext = this;
            if (this.item.compareTo(item) == 0) return this;
            while ((tNext = tNext.next) != null) {
                if (tNext.item.compareTo(item) == 0) return next;
            }
            return null;
        }

        void forwardPrint() {
            Node<T> tNext = this;
            System.out.print("tNext.item " + " ");
            while ((tNext = tNext.next) != null) {
                System.out.print(tNext.item + " ");
            }
            System.out.println();
        }

        Node<T> removeFirst() {
            item = null;
            return next;
        }

        boolean less(Node<T> other) {
            return item.compareTo(other.item) < 0;
        }

        public static Node<Integer> list(int n) {
            Node<Integer> first = new Node<>(StdRandom.uniform(n * 20), null);
            Node<Integer> temp = first;

            while (--n > 0) temp = temp.insertAfter(StdRandom.uniform(n * 10));
            return first;
        }

        public static Node<Integer> list(int lo, int hi) {
            Node<Integer> header = new Node<>();
            Node<Integer> temp = header;
            for (int i = lo; i < hi; i++) {
                temp = temp.insertAfter(i);
            }
            return header.next;
        }

        public static <T extends Comparable<T>> Node<T> mid(Node<T> list) {
            Node<T> slow = list;
            Node<T> fast = list;
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

        public static <T extends Comparable<T>> Node<T> merge(Node<T> list) {
            if (list.next == null || list == null) return list;

            Node<T> head1 = list;
            Node<T> head2 = mid(list);
            head1 = merge(head1);
            head2 = merge(head2);
            return mergeSort(head1, head2);
        }

        private static <T extends Comparable<T>> Node<T> mergeSort(Node<T> list1, Node<T> list2) {
            Node<T> header = new Node<>();
            Node<T> temp = header;
            while (list1 != null || list2 != null) {
                if (list1 == null) {
                    temp = temp.insertAfter(list2.item);
                    list2 = list2.removeFirst();
                } else if (list2 == null) {
                    temp = temp.insertAfter(list1.item);
                    list1 = list1.removeFirst();
                } else if (list1.less(list2)) {
                    temp = temp.insertAfter(list1.item);
                    list1 = list1.removeFirst();
                } else {
                    temp = temp.insertAfter(list2.item);
                    list2 = list2.removeFirst();
                }
            }
            return header.next;
        }

        public static void main(String[] args) {
            Node<Integer> list = Node.list(20);
            System.out.println("---------- 排序前 ----------");
            list.forwardPrint();

            list = merge(list);
            System.out.println("---------- 排序后 ----------");
            list.forwardPrint();
        }
    }
}

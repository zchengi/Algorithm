package org.two.two.exercises;

import org.tool.ArrayGenerator;

/**
 * 2.2.14 归并有序的队列。编写一个静态方法，将两个有序的队列作为参数，返回一个归并后的有序队列。
 *
 * @author cheng
 *         2018/1/19 17:09
 */
public class Exercise14 {
    public static class Queue {
        private static int counter = 0;
        private final int id = counter++;
        private int[] items = new int[1];
        private int head, tail, size;

        void resize(int newSize) {
            int[] newItems = new int[newSize];
            int current = head, k = 0;
            do {
                newItems[k++] = items[current];
                current = (current + 1) % items.length;
            } while (current != tail);
            head = 0;
            tail = size;
            items = newItems;
        }

        int peek() {
            if (isEmpty()) return 0;
            return items[head];
        }

        boolean isEmpty() {
            return size == 0;
        }

        void enqueue(int item) {
            if (size == items.length) resize(size * 2);
            ++size;

            items[tail] = item;
            tail = (tail + 1) % items.length;
        }

        int dequeue() {
            if (isEmpty()) {
                throw new RuntimeException("dequeue from a empty queue!");
            }
            int del = items[head];
            items[head] = 0;
            head = (head + 1) % items.length;

            --size;
            if (size > 0 && size == items.length / 4) {
                resize(items.length / 2);
            }
            return del;
        }

        /**
         * 出列排队
         */
        private int[] topTwo() {
            int top = items[head];
            int topNext = items[(head + 1) % items.length];
            return new int[]{top, topNext};
        }

        private void makeTopEnqueueAgain() {
            enqueue(dequeue());
        }

        private void exchangeTopTwo() {
            int i = head, j = (head + 1) % items.length;
            int temp = items[i];
            items[i] = items[j];
            items[j] = temp;
        }

        private boolean isSorted() {
            if (isEmpty()) return false;
            if (size < 2) return true;
            int current = head, next;
            while ((next = (current + 1) % items.length) != tail) {
                if (items[current] > items[next]) return false;
                else current = next;
            }
            return true;
        }

        void dequeueSort() {
            while (!isSorted()) {
                int k = size;
                while (--k > 0) {
                    if (topTwo()[0] > topTwo()[1]) {
                        exchangeTopTwo();
                    }
                    makeTopEnqueueAgain();
                }
                makeTopEnqueueAgain();
            }
        }

        void print() {
            if (isEmpty()) System.out.printf("[Queue %d] Empty", id);
            int current = head;
            System.out.printf("[Queue %d] ", id);
            System.out.printf("items[current]" + " ");
            while ((current = (current + 1) % items.length) != tail) {
                System.out.print(items[current] + " ");
            }
            System.out.println();
        }

        public String toString() {
            if (isEmpty()) return "Empty";
            StringBuilder sb = new StringBuilder();
            for (int item : items) sb.append(String.format("%4s", item == 0 ? "-" : item + ""));
            sb.append(String.format("              head = %d tail = %d size = %d", head, tail, size));
            return sb.toString();
        }

        public static Queue mergeTwoSortedQueue(Queue q1, Queue q2) {
            if (!q1.isSorted() || !q2.isSorted()) {
                throw new IllegalArgumentException("q1 or q2 is not in order!");
            }
            Queue newQueue = new Queue();
            while (!q1.isEmpty() || !q2.isEmpty()) {
                if (q1.isEmpty()) newQueue.enqueue(q2.dequeue());
                else if (q2.isEmpty()) newQueue.enqueue(q1.dequeue());
                else if (q1.peek() < q2.peek()) newQueue.enqueue(q1.dequeue());
                else newQueue.enqueue(q2.dequeue());
            }
            return newQueue;
        }

        public static Queue randomQueue(int lo, int hi) {
            Queue queue = new Queue();
            for (int i : ArrayGenerator.ints(lo, hi)) {
                queue.enqueue(i);
            }
            return queue;
        }
    }

    public static void main(String[] args) {
        Queue q1 = Queue.randomQueue(3, 8);
        Queue q2 = Queue.randomQueue(10, 20);
        q1.print();
        q2.print();
        q1.dequeueSort();
        q2.dequeueSort();

        System.out.println("\n-------- 归并 q1 和 q2 --------");
        Queue mergeQueue = Queue.mergeTwoSortedQueue(q1, q2);
        mergeQueue.print();
    }
}

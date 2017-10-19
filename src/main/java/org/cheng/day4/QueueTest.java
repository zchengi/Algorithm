package org.cheng.day4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

import java.util.Arrays;

/**
 * 先进先出队列的用例
 * @author cheng
 *         2017/10/19 16:05
 */
public class QueueTest {
    public static int[] readInts(String name) {
        In in = new In(name);
        Queue<Integer> queue = new Queue<Integer>();
        while (!in.isEmpty()) {
            queue.enqueue(in.readInt());
        }
        int n = queue.size();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = queue.dequeue();
        }
        return a;
    }

    public static void main(String[] args) {
        int[] arrays = readInts("src/main/java/org/cheng/day4/number.txt");
        System.out.println(Arrays.toString(arrays));
    }
}

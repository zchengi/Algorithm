package org.two.five.exercises;

import org.two.four.learn2.MinHeap;


/**
 * 2.5.12 调度。编写一段程序SPT.java，从标准输入中读取任务名称和所需的运行时间，
 * 用2.5.4.3节所述的最短处理时间优先的原则打印出一份调度计划，使得任务完成的平均时间最小。
 *
 * @author cheng
 *         2018/2/3 16:16
 */
public class Exercise12 {
    static class Task implements Comparable {
        private final String name;
        private final double time;

        public Task(String name, double time) {
            this.name = name;
            this.time = time;
        }

        @Override
        public String toString() {
            return String.format("(%s %.2f)", name, time);
        }

        @Override
        public int compareTo(Object obj) {
            if (obj instanceof Task) {
                Task that = (Task) obj;
                return this.time < that.time ? -1 : this.time > that.time ? 1 : 0;
            }
            return -1;
        }

        public double getTime() {
            return time;
        }
    }

    public static void main(String[] args) {
        MinHeap<Task> minHeap = new MinHeap<>();
        int n = 20;
        for (int i = 0; i < n; i++) {
            double time = Math.round(Math.random() * 10000) / 100;
            Task task = new Task("Task-" + String.valueOf(i), time);
            minHeap.insert(task);
        }
        while (!minHeap.isEmpty()) {
            System.out.println(minHeap.extractMin());
        }
    }
}

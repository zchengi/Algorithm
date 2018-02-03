package org.two.five.exercises;

import org.two.four.learn2.MaxHeap;
import org.two.four.learn2.MinHeap;

import static org.two.five.exercises.Exercise12.Task;

/**
 * 2.5.13 负载均衡。编写一段程序LPT.java，接受一个整数M作为命令行参数，
 * 从标准输入中读取任务的名称和所需的运行时间，用2.5.4.3.节所述的最长处理时间优先原则，
 * 打印出一份调度计划，将所有任务分配给M个处理器并使得所有任务完成的所需总时间最少。
 *
 * 分析：也就是将 n 个任务较平均的分成 M 份；
 *
 * @author cheng
 *         2018/2/3 17:19
 */
public class Exercise13 {
    static class Processor implements Comparable<Object> {
        private final int id;
        private double totalTime;

        public Processor(int id) {
            this.id = id;
        }

        public void addTask(Task t) {
            totalTime += t.getTime();
        }

        @Override
        public String toString() {
            return String.format("(Processor-%d  %.2f)", id, totalTime);
        }

        @Override
        public int compareTo(Object obj) {
            if (obj instanceof Processor) {
                Processor that = (Processor) obj;
                return this.totalTime < that.totalTime ? -1 : this.totalTime > that.totalTime ? 1 : 0;
            }
            return -1;
        }
    }

    public static void main(String[] args) {
        int n = 20;

        // 创建n个任务，按照耗时降序排列
        Task[] tasks = new Task[n];
        for (int i = 0; i < n; i++) {
            double time = Math.round(Math.random() * 10000) / 100;
            Task task = new Task("Task-" + String.valueOf(i), time);
            tasks[i] = task;
        }
        MaxHeap<Task> maxHeap = new MaxHeap<>(tasks);
        for (int i = 0; i < n; i++) {
            tasks[i] = maxHeap.extractMax();
        }

        int m = 8;
        // 创建由堆负责调度的M个处理器
        MinHeap<Processor> processorMinHeap = new MinHeap<>();
        for (int i = 1; i <= m; i++) {
            processorMinHeap.insert(new Processor(i));
        }

        System.out.println("开始执行任务：");
        for (int i = 0; i < n; i++) {
            // 把当前最耗时的任务分配给最闲的那个处理器
            Processor min = processorMinHeap.extractMin();
            min.addTask(tasks[i]);

            // 重新安排任务调度
            processorMinHeap.insert(min);
            System.out.println( min + " 执行任务 " + tasks[i]);
        }
        System.out.println("任务执行完毕!");
    }
}

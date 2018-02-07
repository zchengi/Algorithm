package org.two.five.exercises;

import org.two.three.learn.Quick2Ways;

/**
 * 2.5.20 空闲时间。假设有一台计算机能够并行处理N个任务。编写一段程序并给定一系列任务的起始时间和结束时间，
 * 找出这台机器最长的空闲时间和最长的繁忙时间。
 *
 * 分析：编写一个 Task 类型，实现comparable，保存任务的起始时间、结束时间、运行时间；
 * 最长繁忙时间就是 在这段任务中持续运行最长的时间
 * 最长空闲时间就是 在这段任务中停止运行最长的时间
 * @author cheng
 *         2018/2/7 0:17
 */
public class Exercise20 {
    static class Task implements Comparable<Task> {
        private final int id;
        private final String start;
        private final String end;
        private final long startTime;
        private final long endTime;
        private final long operationHours;

        public Task(int id, String start, String end) {
            this.id = id;
            if (!start.matches("\\d+:\\d+") || !end.matches("\\d+:\\d+")) {
                throw new IllegalArgumentException("Illegal time format!");
            }
            this.start = start;
            this.end = end;
            String[] startSplit = start.split(":");
            String[] endSplit = end.split(":");

            // 起始时
            int startH = Integer.parseInt(startSplit[0]);
            int startM = Integer.parseInt(startSplit[1]);
            int endH = Integer.parseInt(endSplit[0]);
            int endM = Integer.parseInt(endSplit[1]);

            if (startH < 0 || startH >= 24 || startM < 0 || startM >= 60
                    || endH < 0 || endH >= 24 || endM < 0 || endM >= 60) {
                throw new IllegalArgumentException("Illegal time format!");
            }

            // 起始时间
            startTime = startH * 60 + startM;
            // 结束时间
            endTime = endH * 60 + endM;
            // 运行时间
            operationHours = endTime - startTime;
            if (operationHours <= 0) throw new IllegalArgumentException("Start time larger than end time!");
        }

        @Override
        public int compareTo(Task that) {
            // 当前起始时间小于比较起始时间，即当前先运行
            // 返回 -1 排序时候 -1 < 0  相当于返回true
            if (this.startTime < that.startTime) return -1;
            else if (this.startTime > that.startTime) return 1;
                // 如果起始时间相同，结束时间大的排在后面
            else if (this.operationHours < that.operationHours) return -1;
            else if (this.operationHours > that.operationHours) return 1;
            else return 0;
        }

        @Override
        public String toString() {
            return String.format("Task -%2d \t%5s - %5s", id, start, end);
        }

        public long getStartTime() {
            return startTime;
        }


        public long getEndTime() {
            return endTime;
        }

        public long getOperationHours() {
            return operationHours;
        }
    }

    public static void main(String[] args) {
        Task[] tasks = {
                new Task(1, "11:30", "11:55"),
                new Task(2, "11:00", "13:00"),
                new Task(3, "12:05", "12:50"),
                new Task(4, "12:30", "14:20"),
                new Task(5, "12:12", "13:50"),
                new Task(6, "8:20", "9:40"),
                new Task(7, "8:10", "8:30"),
                new Task(8, "9:00", "9:27"),
                new Task(9, "9:00", "9:26"),
                new Task(10, "9:10", "9:20"),
                new Task(11, "8:10", "8:20"),
                new Task(12, "8:20", "8:25"),
                new Task(13, "8:20", "9:10"),
                new Task(14, "8:40", "10:00"),
                new Task(15, "8:30", "9:45"),
                new Task(16, "9:00", "9:29"),
                new Task(17, "9:00", "9:28"),
                new Task(18, "10:10", "13:40"),
        };

        Quick2Ways.sort(tasks);
        for (Task task : tasks) System.out.println(task);

        // 最长繁忙时间
        long theLongestPeakTime = tasks[0].getOperationHours();
        long tempStartTime = tasks[0].getStartTime();
        long tempEndTime = tasks[0].getEndTime();
        Task current;

        for (int i = 1; i < tasks.length; i++) {
            current = tasks[i];
            // 如果 当前任务起始时间 <= 临时任务结束时间
            if (current.getStartTime() <= tempEndTime) {
                // 如果 当前任务的结束时间 - 临时任务结束时间 > 最长繁忙时间
                if (current.getEndTime() - tempStartTime > theLongestPeakTime) {
                    // 最长繁忙时间 = 当前任务结束时间 - 临时任务起始时间
                    theLongestPeakTime = current.getEndTime() - tempStartTime;
                    // 将 临时任务结束时间 更新为 当前任务的结束时间
                    // 因为在这一段时间内程序都在运行
                    tempEndTime = current.getEndTime();
                }
            } else {
                // 如果 最长繁忙时间 <= 当前任务运行时间
                if (theLongestPeakTime <= current.getOperationHours()) {
                    // 最长繁忙时间 = 当前任务运行时间
                    theLongestPeakTime = current.getOperationHours();
                    // 临时任务起始时间 = 当前任务起始时间
                    tempStartTime = current.getStartTime();
                    // 临时任务结束时间 = 当前任务结束时间
                    tempEndTime = current.getEndTime();
                }
            }
        }

        // 最长空闲时间
        long theLongestRunningTime = 0;
        // 临时任务结束时间
        tempEndTime = tasks[0].getEndTime();

        for (int i = 1; i < tasks.length; i++) {
            current = tasks[i];
            // 如果 当前任务起始时间 - 临时任务结束时间 > 最长空闲时间
            if (current.getStartTime() - tempEndTime > theLongestRunningTime) {
                // 最长空闲时间 = 当前任务起始时间 - 临时任务结束时间
                theLongestRunningTime = current.getStartTime() - tempEndTime;
            }
            // 如果 当前任务结束时间 > 临时任务结束时间
            if (current.getEndTime() > tempEndTime) {
                // 临时任务结束时间 = 当前任务结束时间
                tempEndTime = current.getEndTime();
            }
        }

        System.out.println("最长繁忙时间为： " + theLongestPeakTime + " 分钟");
        System.out.println("最长空闲时间为： " + theLongestRunningTime + " 分钟 ");
    }
}

package org.two.three.exercises;

import org.one.day9.StopWatch;
import org.tool.ArrayGenerator;

/**
 * 2.3.20 非递归的快速排序。实现一个非递归的快速排序，使用一个循环来将弹出栈的子数组切分并将结果子数组重新压入栈。
 * 注意：先将较大的子数组压入栈，这样就可以保证栈最多只会有lgN个元素。
 *
 * @author cheng
 *         2018/1/27 22:32
 */
public class Exercise20 {

    static class Stack {
        private static int[] items = new int[1];
        private static int size;

        void resize(int newSize) {
            int[] newItems = new int[newSize];
            System.arraycopy(items, 0, newItems, 0, size);
            items = newItems;
        }

        boolean isEmpty() {
            return size == 0;
        }

        Stack push(int item) {
            if (size == items.length) {
                resize(size * 2);
            }

            items[size++] = item;
            return this;
        }

        int pop() {
            if (size == 0) {
                throw new RuntimeException("Stack is empty!");
            }

            int temp = items[--size];
            if (size > 0 && size == items.length / 4) {
                resize(items.length / 2);
            }
            return temp;
        }
    }

    public static double sort(int[] arr) {
        StopWatch timer = new StopWatch();
        Stack stack = new Stack();
        stack.push(0).push(arr.length - 1);
        sort(arr, stack);
        return timer.elapsedTime();
    }

    private static void sort(int[] arr, Stack stack) {
        while (!stack.isEmpty()) {
            // 栈顶
            int hi = stack.pop();
            // 栈底
            int lo = stack.pop();

            // 入栈范围的大小判断避免了在这里写 if (lo >= hi) continue;
            int i = lo;
            int j = hi + 1;
            int v = arr[lo];
            while (true) {
                while (i < hi && arr[++i] < v) ;
                while (arr[--j] > v) ;
                if (i >= j) break;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
            int temp = arr[j];
            arr[j] = arr[lo];
            arr[lo] = temp;
            // 如果是单元素或者范围是负数，就过，免得在此初入栈引起的扩容和缩容操作；
            if (j + 1 < hi) {
                // 先放右子数组
                stack.push(j + 1).push(hi);
            }
            if (lo < j - 1) {
                // 再放左子数组
                stack.push(lo).push(j - 1);
            }
        }
    }

    public static double _sort(int[] arr) {
        StopWatch timer = new StopWatch();

        int n = arr.length;
        _sort(arr, 0, n - 1);

        return timer.elapsedTime();
    }

    private static void _sort(int[] arr, int lo, int hi) {
        if (lo >= hi) return;

        int i = lo;
        int j = hi + 1;
        int v = arr[lo];

        while (true) {
            while (i < hi && arr[++i] < v) ;
            while (arr[--j] > v) ;
            if (i >= j) break;

            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        int temp = arr[lo];
        arr[lo] = arr[j];
        arr[j] = temp;

        _sort(arr, lo, j - 1);
        _sort(arr, j + 1, hi);
    }


    public static void main(String[] args) {

        double time1 = 0, time2 = 0;
        for (int i = 0; i < 20; i++) {
            int n = 1000000;
            int[] arr = ArrayGenerator.ints(0, n);
            int[] copy = ArrayGenerator.copy(arr);

            time2 += _sort(copy);
            time1 += sort(arr);

        }
        System.out.println("非递归 耗时： " + time1);
        System.out.println("递归 耗时： " + time2);

        // 数据量100万，测试次数10次，总时间如下，非递归速度快一点点。

        // 非递归 耗时： 2.8249999999999997
        // 递归 耗时： 2.7580000000000005

        // 非递归 耗时： 3.028
        // 递归 耗时： 3.053

        // 非递归 耗时： 2.6620000000000004
        // 递归 耗时： 2.893

        // 非递归 耗时： 2.4810000000000008
        // 递归 耗时： 2.868
    }
}

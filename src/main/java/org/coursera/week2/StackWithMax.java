package org.coursera.week2;

import java.util.Arrays;

/**
 * @author cheng
 *         2018/1/9 13:39
 *         <p>
 *         Test2： Stack with max
 *         -分析：
 *         该题目要求在实现正常stack的push和pop操作外，还需要实现返回maximum的操作；
 *         显然一个数组是不够的，需要额外的数组maximums[]来从小到大存储stack的值，
 *         需要maximum值就返回maximums[n-1]，可满足要求。
 */
public class StackWithMax {

    /**
     * 存放数组
     */
    public double[] items;

    /**
     * 存放最大值的数组
     */
    public double[] maximums;

    private int n;
    private int cap;

    public StackWithMax() {
        n = 0;
        cap = 2;
        items = new double[cap];
        maximums = new double[cap];
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void push(double item) {
        if (n == 0) {
            maximums[0] = item;
        } else {
            int i;
            int j;
            // 这个循环用来找出item在maximum数组中应该放置的位置
            for (i = n - 1; i >= 0; i--) {
                if (item > maximums[i]) {
                    break;
                }
            }
            // 将位置i以后的元素都往后挪一个位置
            for (j = n - 1; j > i; j--) {
                // 循环结束后j指向i
                maximums[j + 1] = maximums[j];
            }
            // j+1 就是item应该放置的位置
            maximums[j + 1] = item;
        }
        items[n++] = item;
        if (n == cap) {
            resize(2 * cap);
        }
    }

    public double pop() {
        double item = items[--n];
        if (n > 0 && n == cap / 4) {
            resize(cap / 2);
        }
        return item;
    }

    public double maximum() {
        return maximums[n - 1];
    }

    private void resize(int cap) {
        double[] t1 = new double[cap];
        double[] t2 = new double[cap];
        for (int i = 0; i < n; i++) {
            t1[i] = items[i];
            t2[i] = maximums[i];
        }
        items = t1;
        maximums = t2;
        this.cap = cap;
    }

    public static void main(String[] args) {
        StackWithMax stack = new StackWithMax();
        stack.push(9);
        stack.push(8);
        stack.push(11);
        stack.push(0);
        stack.push(-9.9);
        stack.push(88);
        System.out.println(Arrays.toString(stack.items));
        System.out.println(Arrays.toString(stack.maximums));
        System.out.println(stack.pop());
        System.out.println(stack.maximum());
    }
}

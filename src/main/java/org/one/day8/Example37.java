package org.one.day8;

import org.example.Queue;

/**
 * 1.3.337 Josephus问题
 *
 * @author cheng
 *         2018/1/9 21:55
 *         -分析：
 *         利用队列的性质：先进先出
 *         将不被删除的元素出列再入列，再删除应该删除的元素。
 *         使用数学归纳法，总结出递推公式
 *         f[1]=0;
 *         f[i]=(f[i-1]+m)%i; (i>1)
 */
public class Example37 {
    public static void main(String[] args) {
        int n = 7;
        int m = 2;
        Queue<Integer> queue = new Queue<>();
        for (int i = 0; i < n; i++) {
            queue.enqueue(i);
        }
        while (!queue.isEmpty()) {
            for (int i = 0; i < m - 1; i++) {
                queue.enqueue(queue.dequeue());
            }
            System.out.print(queue.dequeue() + " ");
        }
    }
}

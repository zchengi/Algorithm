package org.one.day6;

import edu.princeton.cs.algs4.StdIn;
import org.example.Queue;

/**
 * 1.3.15 编写一个Queue的用例，接受一个命令行参数k并打印出标准输入中的倒数第k个字符串。
 * 假设标准输入中至少有k个字符串。
 *
 * @author one
 *         2017/10/21 13:16
 */
public class Example15 {
    public static void main(String[] args) {
        int k = StdIn.readInt();
        Queue<String> queue = new Queue<String>();
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
        queue.enqueue("d");
        queue.enqueue("f");
        System.out.println(queue);
        while (k < queue.size()) {
            queue.dequeue();
        }
        System.out.println(queue.dequeue());
    }
}

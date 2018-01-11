package org.one.day8;

import org.example.Queue;

/**
 * 1.3.41 复制队列
 * 编写一个新的构造函数，使以下代码：
 * Queue r=new Queue(q);
 * 得到的r指向队列q的一个新的独立的副本。可以对q或r进行任意入列或出列操作
 * 但它们不会相互影响。提示：从q中取出所有元素再将它们插入q和r。
 *
 * @author cheng
 *         2018/1/11 14:30
 */
public class Example41<Item> extends Queue<Item> {
    public Queue<Item> reversal(Queue<Item> queue) {

        Queue<Item> reversal = new Queue<>();
        for (Item item : queue) {
            reversal.enqueue(item);
        }
        return reversal;
    }

    public static void main(String[] args) {
        Queue<String> queue = new Queue<>();
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");

        Example41<String> ex = new Example41<>();
        Queue<String> reversal = ex.reversal(queue);
        for (String s : queue) {
            System.out.print(s + " ");
        }
        System.out.println("\n-------");
        for (String s : reversal) {
            System.out.print(s + " ");
        }
    }

}

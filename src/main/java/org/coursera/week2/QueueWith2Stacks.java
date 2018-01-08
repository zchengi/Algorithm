package org.coursera.week2;

import org.example.Stack;

/**
 * @author cheng
 *         2018/1/8 20:53
 *         <p>
 *         Test1: Queue with two stacks
 *         -题目： 使用栈实现队列的所有操作。
 */
public class QueueWith2Stacks<Item> {

    private Stack<Item> inStack = new Stack<>();
    private Stack<Item> outStack = new Stack<>();

    public boolean isEmpty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }

    public int size() {
        return inStack.size() + outStack.size();
    }

    public void enqueue(Item item) {
        inStack.push(item);
    }

    public Item dequeue() {
        if (outStack.isEmpty()) {
            if (inStack.isEmpty()) {
                return null;
            }
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
        return outStack.pop();
    }

    public static void main(String[] args) {
        QueueWith2Stacks<String> queue = new QueueWith2Stacks<>();
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
        System.out.println(queue.dequeue());
        queue.enqueue("1");
        System.out.println(queue.dequeue());
        queue.enqueue("2");
        System.out.println(queue.dequeue());
    }
}

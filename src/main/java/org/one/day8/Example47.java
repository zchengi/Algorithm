package org.one.day8;

import org.example.Stack;

/**
 * 1.3.47 可连接的队列、栈或者steque
 * 为队列、栈或者steque 添加一个能够(破坏性地)连接两个同类对象的额外操作catenation。
 *
 * @author cheng
 *         2018/1/12 13:37
 */
public class Example47<Item> extends Stack<Item> {

    /**
     * 可连接的栈
     */
    public void catenation(Stack<Item> that) {
        for (Item item : that) {
            this.push(item);
        }
    }

//    // steque
//    public void catenation(Steque<Item> that) {
//        for (Item item : that) {
//            this.enqueue(item);
//        }
//    }

//    // 对列
//    public void catenation(Queue<Item> that) {
//        for (Item item : that) {
//            this.enqueue(item);
//        }
//    }
}

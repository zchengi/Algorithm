package org.cheng.day7;

import org.cheng.example.Stack;

/**
 * 1.3.30
 * 接受一条链表的首结点作为参数，（破坏性地）将链表反转并返回结果链表的首结点。
 *
 * @author cheng
 *         2017/10/22 13:03
 */
public class Example30 {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<String>();
        stack.push("a");
        stack.push("b");
        stack.push("c");
        stack.push("d");
        stack.push("e");
        for (String s : stack) {
            System.out.print(s + " ");
        }
        stack.reverse();
        for (String s : stack) {
            System.out.print(s + " ");
        }
    }
}

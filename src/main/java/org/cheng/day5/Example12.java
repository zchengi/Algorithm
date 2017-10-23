package org.cheng.day5;

import org.cheng.example.Stack;

import java.util.Iterator;

/**
 * 1.3.12
 * 接受一个字符串栈作为参数并返回该栈的一个副本
 *
 * @author cheng
 *         2017/10/21 11:33
 */
public class Example12 {
    public static Stack<String> copy(Stack<String> stack) {
        Iterator<String> iterator = stack.iterator();
        Stack<String> temp = new Stack<String>();
        Stack<String> result = new Stack<String>();
        while (iterator.hasNext()) {
            temp.push(iterator.next());
        }
        while (!temp.isEmpty()) {
            result.push(temp.pop());
        }
        temp = null;
        return result;
    }

    public static void main(String[] args) {
        Stack<String> stack = new Stack<String>();
        stack.push("d");
        stack.push("c");
        stack.push("b");
        stack.push("a");
        Stack<String> copy = Example12.copy(stack);
        copy.push("ff");
        for (String s : copy) {
            System.out.print(s + " ");
        }
        for (String s : stack) {
            System.out.print(s + " ");
        }
    }
}

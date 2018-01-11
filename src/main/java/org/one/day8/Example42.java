package org.one.day8;

import org.example.Stack;

/**
 * 1.3.42 复制栈
 * 编写一个新的构造函数，使以下代码
 * Stack<Item> t = new Stack<Item>(s);
 * 得到的t指向栈s的一个新的独立的副本。
 *
 * @author cheng
 *         2018/1/11 15:10
 */
public class Example42<Item> extends Stack<Item> {
    public Stack<Item> copy(Stack<Item> stack) {
        Stack<Item> copy = new Stack<>();
        Stack<Item> temp= new Stack<>();
        for (Item item : stack) {
            temp.push(item);
        }
        for (Item item : temp) {
            copy.push(item);
        }
        return copy;
    }

    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.push("a");
        stack.push("b");
        stack.push("c");
        for (String s : stack) {
            System.out.print(s+" ");
        }
        Example42<String> ex = new Example42<>();
        Stack<String> copy = ex.copy(stack);
        System.out.println("\n------");
        for (String s : copy) {
            System.out.print(s+" ");
        }
    }
}

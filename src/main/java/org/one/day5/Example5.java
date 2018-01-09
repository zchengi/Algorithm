package org.one.day5;

import org.example.Stack;

/**
 * 当N为50，下面这段代码会打印出什么？
 * 从较高的抽象层描述给定正整数N时这段代码的行为:
 * 这段代码打印出了N的二进制表示(当N为50时打印110010)
 *
 * @author cheng
 *         2017/10/20 21:24
 */
public class Example5 {
    public static void main(String[] args) {
        int N = 50;
        Stack<Integer> stack = new Stack<Integer>();
        while (N > 0) {
            stack.push(N % 2);
            N = N / 2;
        }
        for (int d : stack) {
            System.out.print(d);
        }
    }
}

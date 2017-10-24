package org.one.day5;

import edu.princeton.cs.algs4.StdIn;
import org.example.Stack;

/**
 * 1.3.11
 * 从标准输入中得到一个后续表达式，求值并打印结果。
 *
 * @author one
 *         2017/10/21 11:00
 */
public class EvaluatePostfix {
    public static void main(String[] args) {
        Stack<Double> stack = new Stack<Double>();
        while (!StdIn.isEmpty()) {
            // 1 2 3 + 4 5 * * +
            String str = StdIn.readString();
            switch (str) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-":
                    // 因为出栈原因
                    stack.push(-stack.pop() + stack.pop());
                    break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "/":
                    // 因为出栈原因，这里需要将分子分母交换位置
                    stack.push(1 / stack.pop() * stack.pop());
                    break;
                default:
                    stack.push(Double.parseDouble(str));
            }
        }
        System.out.println(stack.pop());
    }
}

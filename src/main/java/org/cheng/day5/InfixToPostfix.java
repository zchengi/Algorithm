package org.cheng.day5;

import edu.princeton.cs.algs4.StdIn;
import org.cheng.example.Stack;

/**
 * 1.3.10
 * 编写一个过滤器，将算数表达式由中序表达式转为后序表达式。
 *
 * @author cheng
 *         2017/10/20 22:36
 */
public class InfixToPostfix {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<String>();
        while (!StdIn.isEmpty()) {
            // ( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )
            // ( ( 1 + sqrt ( 5.0 ) ) / 2.0 )
            String s = StdIn.readString();
            switch (s) {
                case "+":
                case "-":
                case "*":
                case "/":
                    stack.push(s);
                    break;
                case "(":
                    break;
                case ")":
                    System.out.print(stack.pop() + " ");
                    break;
                default:
                    System.out.print(s + " ");
            }
        }
        System.out.println();
    }
}
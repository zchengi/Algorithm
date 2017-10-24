package org.one.day4;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;


/**
 * Stack的简单用例
 *
 * @author one
 *         2017/10/19 16:30
 */
public class Reverse {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<Integer>();
        while (!StdIn.isEmpty()) {
            stack.push(StdIn.readInt());
        }
        System.out.println(stack);
    }
}

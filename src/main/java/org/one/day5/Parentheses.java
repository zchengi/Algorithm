package org.one.day5;

import edu.princeton.cs.algs4.StdIn;
import org.example.Stack;

/**
 * Example5 4
 * 从标准准输入中读取一个文本流并使用栈判定其中的括号是否配对完整。
 *
 * @author cheng
 *         2017/10/20 15:16
 */
public class Parentheses {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<String>();
        //{{}{}{}{}{}{}{}{}{}}()[][][]
        String str = StdIn.readString();
        String[] inputs = str.split("");
        for (String input : inputs) {
            if ("{".equals(input) || "[".equals(input) || "(".equals(input)) {
                stack.push(input);
            } else if (!stack.isEmpty()) {
                if ("}".equals(input)) {
                    if (!"{".equals(stack.pop())) {
                        System.out.println(false);
                        return;
                    }
                } else if ("]".equals(input)) {
                    if (!"[".equals(stack.pop())) {
                        System.out.println(false);
                        return;
                    }
                }else if (")".equals(input)) {
                    if (!"(".equals(stack.pop())) {
                        System.out.println(false);
                        return;
                    }
                }
            }
        }
        System.out.println(stack.isEmpty());
    }
}

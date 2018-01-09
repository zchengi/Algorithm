package org.one.day4;

import edu.princeton.cs.algs4.StdIn;

import java.util.Stack;

/**
 * Dijkstra的双栈算术表达式求值算法
 *
 * @author cheng
 *         2017/10/19 17:15
 */
public class Evaluate {
    public static void main(String[] args) {
        Stack<String> ops = new Stack<String>();
        Stack<Double> values = new Stack<Double>();

        // ( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )
        // ( ( 1 + sqrt ( 5.0 ) ) / 2.0 )
        while (!StdIn.isEmpty()) {
            //读取字符，如果是运算符则压入栈
            String strs = StdIn.readString();
            if ("(".equals(strs)) {
            } else if ("+".equals(strs)) {
                ops.push(strs);
            } else if ("-".equals(strs)) {
                ops.push(strs);
            } else if ("*".equals(strs)) {
                ops.push(strs);
            } else if ("/".equals(strs)) {
                ops.push(strs);
            } else if ("sqrt".equals(strs)) {
                ops.push(strs);
            } else if (")".equals(strs)) {
                //如果字符为")"，弹出运算符和操作数，计算结果并压入栈
                String op = ops.pop();
                double v = values.pop();

                if ("+".equals(op)) {
                    v = values.pop() + v;
                } else if ("-".equals(op)) {
                    v = values.pop() - v;
                } else if ("*".equals(op)) {
                    v = values.pop() * v;
                } else if ("/".equals(op)) {
                    v = values.pop() / v;
                } else if ("sqrt".equals(op)) {
                    v = Math.sqrt(v);
                }
                values.push(v);
            } else {
                //如果字符既非运算符也不是括号，将它作为double值压入栈
                values.push(Double.parseDouble(strs));
            }
        }
        System.out.println(values.pop());
    }
}

package org.cheng.day5;

import edu.princeton.cs.algs4.StdIn;
import org.cheng.example.Stack;

/**
 * 编写一段程序，从标准输入得到一个缺少左括号的表达式并打印出补全括号之后的中序表达式。
 * 每个字符用空格隔开。
 * 例如,给定输入：1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )
 * 应该输出：( ( 1 + 2 ) * ( ( 3 - 4 ) * 5 - 6 ) ) )
 *
 * @author cheng
 *         2017/10/20 21:49
 */
public class Example9 {
    public static void main(String[] args) {

        Stack<String> options = new Stack<String>();
        Stack<String> values = new Stack<String>();

        while (!StdIn.isEmpty()) {
            String str = StdIn.readString();
            if ("+".equals(str) || "-".equals(str) || "*".equals(str) || "/".equals(str)) {
                options.push(str);
            } else if (")".equals(str)) {
                if (options.isEmpty()) {
                    return;
                } else {
                    String temp = values.pop();
                    values.push("( " + values.pop() + " " + options.pop() + " " + temp + " )");
                }
            } else {
                values.push(str);
            }
        }
        System.out.println(values.peek());
    }
}

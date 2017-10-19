package org.cheng.day4;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 一种表示固定容量字符串栈的抽象数据类型
 *
 * @author cheng
 *         2017/10/19 19:48
 */
public class FixedCapacityStackOfStrings {
    /**
     * 保存栈数组
     */
    private String[] a;
    /**
     * 数组大小
     */
    private int n;

    /**
     * 创建一个容量为cap的空栈
     *
     * @param cap 容器大小
     */
    public FixedCapacityStackOfStrings(int cap) {
        a = new String[cap];
    }

    /**
     * 判断栈是否为空
     *
     * @return true:空;false:不为空
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * 返回栈中字符串的数量
     *
     * @return 数量
     */
    public int size() {
        return n;
    }

    /**
     * 添加一个字符串
     *
     * @param item 字符串
     */
    public void push(String item) {
        a[n++] = item;
    }

    /**
     * 删除最近添加的字符串
     * a[--n] 表示最近添加的元素，然后再重新为该索引赋值
     * @return 删除的字符串
     */
    public String pop() {
        return a[--n];
    }

    public static void main(String[] args) {

        FixedCapacityStackOfStrings s = new FixedCapacityStackOfStrings(100);
        while (!StdIn.isEmpty()) {
            // a b c
            // a b c -
            // a b c - -
            String item = StdIn.readString();
            // '-'表示删除最近添加的字符串
            if (!"-".equals(item)) {
                s.push(item);
            } else if (!s.isEmpty()) {
                StdOut.print(s.pop() + " ");
            }
        }
        StdOut.println("(" + s.size() + " left on stack)");
    }
}

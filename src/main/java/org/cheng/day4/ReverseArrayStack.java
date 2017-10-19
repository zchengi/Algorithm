package org.cheng.day4;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/**
 * 一种表示泛型不定容栈的抽象数据类型
 * @author cheng
 *         2017/10/19 20:16
 */
public class ReverseArrayStack<Item> implements Iterable<Item>{

    /**
     * 栈元素
     */
    private Item[] a = (Item[]) new Object[1];
    /**
     * 元素数量
     */
    private int n=0;

    /**
     * 将元素压入栈顶
     *
     * @param item 字符串
     */
    public void push(Item item) {
        // 如果数组a满了，则扩充为其原大小的2倍
        if (n==a.length) {
            resize(2 * a.length);
        }
        a[n++] = item;
    }

    /**
     * 从栈顶删除元素
     * a[--n] 表示最近添加的元素，然后再重新为该索引赋值
     * @return 删除的字符串
     */
    public Item pop() {
        Item item = a[--n];
        // 避免对象游离
        a[n] = null;
        if (n > 0 && n == a.length / 4) {
            resize(a.length/2);
        }
        return item;
    }

    /**
     * 动态调整数组大小
     * @param max 栈大小
     */
    public void resize(int max) {
        //将大小为n<=max的栈移动到一个新的大小为max的数组中
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < n; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    /**
     * 迭代方法
     * @return 迭代器
     */
    public Iterator<Item> iterator() {
        return new ReverseArrayStackIterator();
    }

    /**
     * 迭代器具体实现
     */
    private class ReverseArrayStackIterator implements Iterator<Item>{
        //支持后进先出的迭代
        private int i = n;

        @Override
        public boolean hasNext() {
            return i>0;
        }

        @Override
        public Item next() {
            return a[--i];
        }

        @Override
        public void remove() {
        }
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

    public static void main(String[] args) {

         ReverseArrayStack<String> s = new ReverseArrayStack<String>();
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
        // 迭代输出数组中的值
        for (String str : s) {
            System.out.println(str);
        }
    }
}

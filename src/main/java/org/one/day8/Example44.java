package org.one.day8;

import org.example.Stack;

import java.util.Iterator;

/**
 * 1.3.44 文本编辑器的缓冲区
 * 提示：使用两个栈
 * ---------------------------------------------
 * public class Buffer
 * ---------------------------------------------
 * Buffer               创建一块空的缓存区
 * void insert(char c)  在光标位置插入字符c
 * char delete()        删除并返回光标位置的字符
 * void left(int k)     将光标向左移动k个位置
 * void right(int k)    将光标向右移动k个位置
 * int size()           缓冲区中的字符数量
 * ---------------------------------------------
 *
 * @author cheng
 *         2018/1/11 15:23
 */
public class Example44 implements Iterable {

    /**
     * 光标前面的字符
     */
    private Stack<Character> front;
    /**
     * 光标后面的字符
     */
    private Stack<Character> after;

    public Example44() {
        front = new Stack<>();
        after = new Stack<>();
    }

    public int size() {
        return front.size() + after.size();
    }

    public void insert(char c) {
        front.push(c);
    }

    public char delete() {
        return front.pop();
    }

    public void left(int k) {
        if (k < front.size()) {
            for (int i = 0; i < k; i++) {
                after.push(front.pop());
            }
        }
    }

    public void right(int k) {
        if (k <= after.size()) {
            for (int i = 0; i < k; i++) {
                front.push(after.pop());
            }
        }
    }

    @Override
    public Iterator iterator() {
        return new BufferIterator();
    }

    private class BufferIterator implements Iterator {

        Iterator<Character> iterator;

        public BufferIterator() {
            Example42<Character> ex = new Example42<>();
            Stack<Character> temp = ex.copy(after);
            for (Character item : front) {
                temp.push(item);
            }
            iterator = temp.iterator();
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public Object next() {
            return iterator.next();
        }

        @Override
        public void remove() {
        }
    }

    public static void main(String[] args) {

        Example44 buffer = new Example44();
        String s = "Hellow word!";
        for (int i = 0; i < s.length(); i++) {
            buffer.insert(s.charAt(i));
        }
        for (Object o : buffer) {
            System.out.print(o);
        }
        System.out.println();

        buffer.left(6);
        System.out.println(buffer.delete());
        for (Object o : buffer) {
            System.out.print(o);
        }
        System.out.println();

        buffer.right(4);
        buffer.insert('l');
        for (Object o : buffer) {
            System.out.print(o);
        }
    }
}

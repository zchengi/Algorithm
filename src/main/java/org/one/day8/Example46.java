package org.one.day8;

/**
 * 1.3.46 栈可生成性问题中禁止出现的排列。(没懂)
 * 若三元组(a,b,c)中 a < b < c 且c最先被弹出，a第二，b第三(c和a以及b之间可以间隔其他整数)，
 * 那么当且仅当排列中不含这样的三元组时(如上题所述的)栈才可能生成它。
 * 分析：
 * 题意即禁止栈中出现 a < b < c 的排列
 * 所以对每个要入栈的元素进行判断，如果栈中有两个比他小的元素则禁止入栈(因为c最后入栈)
 * ---判断栈中的两个最小的元素：
 * ----- 1 在每次push的时候全部遍历一遍，时间复杂度为O(n)
 * ----- 2 类中维护两个变量min1，min2，分别表示最小，次小，那么每次push的时候更新这两个变量，
 * -----   时间复杂度为O(1)，在每次pop的时候更新这两个变量，有：1.把min，min2其中之一pop()出去，
 * -----   那么stack中最小的变量变了，需要重新遍历找出这两个值，时间复杂度为O(n)。2.并没有pop()
 * -----   出去，无需遍历。
 *
 * @author cheng
 *         2018/1/12 13:37
 */
public class Example46<Item extends Comparable<Item>> {
    // <Item extends Comparable<Item>>意思是泛型继承了comparable比较方法

    private Node first;
    private int n;

    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    public void push(Item item) {
        if (check(item)) {
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.next = oldFirst;
            n++;
        }
    }

    private boolean check(Item item) {
        if (n < 2) {
            return true;
        }
        Node current = first;
        Item min1;
        Item min2;
        if (current.item.compareTo(current.next.item) < 0) {
            min1 = current.item;
            min2 = current.next.item;
        } else {
            min1 = current.next.item;
            min2 = current.item;
        }

        for (current = current.next.next; current != null; current = current.next) {
            if (current.item.compareTo(min2) < 0) {
                if (current.item.compareTo(min1) > 0) {
                    min2 = current.item;
                } else {
                    min2 = min1;
                    min1 = current.item;
                }
            }
        }
        return min2.compareTo(item) <= 0;
    }

    public Item peek() {
        return n == 0 ? null : first.item;
    }

    public Item pop() {
        Item item = first.item;
        first = first.next;
        n--;
        return item;
    }

    public static void main(String[] args) {
        Example46<Integer> stack = new Example46<>();
        stack.push(0);
        stack.push(2);
        stack.push(1);
        stack.push(3);
        stack.push(1);
        stack.push(2);
        System.out.println(stack.peek());
    }
}

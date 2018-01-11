package org.one.day8;

import org.example.Stack;

import java.util.Arrays;

/**
 * 1.3.45 栈的可生成性
 * 假设我们的栈测试用例将会进行一系列混合的入栈和出栈的操作，序列中的整数0,1,···,N-1(按此先后顺序排列)表示入栈操作，
 * N个减号表示出栈操作。设计一个算法，判断给定的混合序列是否会使数组向下溢出（你所使用的空间与N无关，即不能用某种
 * 数据结构存储所有的整数）。设计一个线性时间的算法判定我们的测试用例能否产生某个给定的排列（这取决于出栈操作指令
 * 的出现位置）。
 * 解答：除非对于某个整数k，前k次出栈操作会在前k次入栈操作前完成，否则栈不会向下溢出。如果某个排列可以产生，那么
 * 它产生的方式一定是唯一：如果输出排列中的下一个整数在栈顶，则将它弹出，否则将它压入栈中。
 *
 * @author cheng
 *         2018/1/11 15:24
 */
public class Example45 {

    /**
     * 判断是否会出现下溢出
     */
    public static boolean problemA(String[] seq) {
        // 表示栈的大小
        int count = 0;
        for (String item : seq) {
            if (item.equals("-")) {
                count--;
            } else {
                count++;
            }
            if (count < 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 返回一系列操作顺序，seq是出栈顺序
     */
    public static String[] problemB(String[] seq) {
        Stack<String> stack = new Stack<>();
        String[] ans = new String[2 * seq.length];
        // stack的大小
        int n = 0;
        // 数组大小
        int p = 0;
        stack.push(String.valueOf(n));
        ans[p++] = String.valueOf(n);
        n++;
        for (String str : seq) {
            while (n < seq.length && !stack.peek().equals(str)) {
                stack.push(String.valueOf(n));
                ans[p++] = String.valueOf(n);
                n++;
            }
            if (!stack.peek().equals(str)) {
                return null;
            } else {
                stack.pop();
                ans[p++] = "-";
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String[] seq = new String[]{"0", "1", "-", "-", "3", "-", "-"};
        System.out.println(problemA(seq));
        // 输出排列
        String[] seq1 = new String[]{"2", "5", "6", "7", "4", "8", "9", "3", "1", "0"};
        System.out.println(problemA(seq1));
        for (String i : problemB(seq1)) {
            System.out.print(i + " ");
        }
        System.out.println();
        // "4", "6", "8", "7", "5", "3", "2", "9", "1", "0"
        String[] seq2 = new String[]{"4", "6", "8", "7", "5", "3", "2", "9", "0", "1"};
        System.out.println(Arrays.toString(problemB(seq2)));
    }
}

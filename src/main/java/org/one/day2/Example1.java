package org.one.day2;

import edu.princeton.cs.algs4.StdIn;

import java.util.Arrays;

/**
 * @author cheng
 *         2017/10/17 12:28
 */
public class Example1 {
    public static void main(String[] args) {
        /*
         * 以空白字符为分隔符从StdIn中创建一个字符串数据
         * ctrl+d结束输入
         */
        String input = StdIn.readAll();
        String[] words = input.split("\\s+");
        System.out.println(Arrays.toString(words));

        System.out.println(isSorted(new String[]{"a", "b"}));
        System.out.println(isSorted(new String[]{"a", "b", "a"}));
    }

    /**
     * 检查一个字符串数组中的元素是否已按照字母表顺序排列
     *
     * @param a 检查的字符串数组
     * @return 是否排序正确
     */
    private static boolean isSorted(String[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i - 1].compareTo(a[i]) > 0) {
                return false;
            }
        }
        return true;
    }
}

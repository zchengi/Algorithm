package org.two.one.exercises;

import java.util.Arrays;

/**
 * 2.1.16 验证。编写一个check()方法，调用sort()对任意数组排序。如果排序成功而且数组中的
 * 所有对象均没有被修改则返回true，否则返回false。不要假设sort()只能通过exch()来移动数据，
 * 可以信任并使用Arrays.sort()。
 *
 * @author cheng
 *         2018/1/18 14:25
 */
public class Exercise16 {
    public static boolean check(Comparable[] a) {
        Comparable[] copy = new Comparable[a.length];
        System.arraycopy(a, 0, copy, 0, a.length);
        Arrays.sort(a);
        for (int i = 0; i < a.length; i++) {
            if (a[i] != copy[i]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[]{1, 2, 3, 2};
        System.out.println(check(a));
    }
}

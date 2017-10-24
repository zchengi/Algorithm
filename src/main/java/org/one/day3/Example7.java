package org.one.day3;

/**
 * 以下递归函数的返回值是什么？
 *
 * @author one
 *         2017/10/18 21:26
 */
public class Example7 {
    public static String mystery(String s) {
        int n = s.length();
        if (n <= 1) {
            return s;
        }
        String a = s.substring(0, n / 2);
        String b = s.substring(n / 2, n);
        return mystery(b) + mystery(a);
    }

    public static String reverseString(String s) {
        if (s.isEmpty()) {
            return s;
        }
        return reverseString(s.substring(1)) + s.charAt(0);
    }

    public static void main(String[] args) {
        String s = "abcde";
        String mystery = mystery(s);
        System.out.println(mystery);
        System.out.println(reverseString(s));
    }
}

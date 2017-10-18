package org.cheng.day3;

/**
 * 这段代码会输出什么？
 *
 * @author cheng
 *         2017/10/18 19:18
 */
public class Example4 {
    public static void main(String[] args) {
        String str1 = "hello";
        String str2 = str1;
        String str3 = "hello";
        str1 = "world";
        System.out.println(str1);
        System.out.println(str2);
    }
}
/*
分析：
1.将str1指向常量池->"hello"
2.将str2同样指向->"hello"
3.将str1重新赋值->"world"
即str2的依然指向"hello",str1改变指向
 */
/*
String a = new String("aa")，代表在堆内存中，创建了一个字符串对象，变量a指向该对象，而该对象又指向在常量池中的字符串常量。
而String a = "aa"代表直接由变量a指向常量池中的字符串，省去了中间的堆内存中的对象，因为new对象时，都会在堆中创建对象。
 */
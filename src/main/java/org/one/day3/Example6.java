package org.one.day3;

import edu.princeton.cs.algs4.StdIn;

/**
 * 如果字符串s中的字符循环任意移动位置之后能够得到另一个字符串t，那么s就被称为t的回环变味。
 * 例如：ACTGACG就是TGACGAC的一个回环变位，反之亦然。
 * 判断这个条件在基因组序列的研究中是很重要的。
 * 编写一个程序检查两个给定的字符串s和t是否互为回环变位。
 * 提示：答案只需要一行用到indexOf()、length()和字符串连接的代码。
 *
 * @author one
 *         2017/10/18 21:03
 */
public class Example6 {
    public static void main(String[] args) {
        String s = StdIn.readString();
        String t = StdIn.readString();
        //将字符串s再连接到s的末尾再判断t是否在s中出现过
        //s.concat(s).contains(t)等价于s.concat(s).indexOf(t)>=0
        if (s.length() == t.length() && s.concat(s).contains(t)) {
            System.out.println(s + " is the circular rotation of " + t);
        } else {
            System.out.println(s + " is not the circular rotation of " + t);
        }
    }
}

package org.two.four.exercises;

/**
 * 2.4.9 给出 A B C D E 五个元素可能构造出来的所有堆，
 * 然后给出 A A A B B 这五个元素可能构造出来的所有堆。
 *
 * @author cheng
 *         2018/1/31 18:06
 */
public class Exercise9 {

    /*
     * 我们知道，由 A B C D E 构造出来的堆只有三层，并且根结点一定是 E
     * 第二层可能是 B C E ，第三次可能是 A B C
     * E  D C   A B
     * E  D C   B A
     * E  D B   A C
     * E  D B   C A
     * E  D A   B C
     * E  D A   C B
     *
     * E  C D   A B
     * E  C D   B A
     *
     * 对于序列 A A A B B 构造堆也是三层 ，并且根结点一定是 B
     * 第二层一定是 A B，第三次一定是 A
     * B  B A   A A
     * B  A B   A A
     */
}

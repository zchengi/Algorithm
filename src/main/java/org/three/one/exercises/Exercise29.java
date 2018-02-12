package org.three.one.exercises;

import org.three.one.learn.BinarySearchST;

/**
 * 3.1.29 测试用例。编写一段测试代码TestBinarySearch.java用来测试正文中min()、max()、floor()、
 * ceiling()、select()、rank()、deleteMin()、deleteMax()和keys()的实现。
 * 可以参考3.1.3.1节的索引用例，添加代码使其在适当的情况下接受更多的命令行参数。
 *
 * @author cheng
 *         2018/2/12 21:26
 */
public class Exercise29 {
    public static void main(String[] args) {
        BinarySearchST<String, Integer> st = new BinarySearchST<>();

        // b c d e f
        for (int i = 0; i < 20; i++) {
            st.put(String.valueOf((char) ('b' + (int) (Math.random() * 20))), i);
        }

        st.delete("b");
        st.deleteMin();
        st.deleteMax();

        System.out.println("符号表最小键值对：" + st.min() + " " + st.get(st.min()));
        System.out.println("符号表最大键值对：" + st.max() + " " + st.get(st.max()));
        System.out.println("符号表排名为0的键：" + st.select(0));
        System.out.println("对字母a向上取整：" + st.ceiling("a"));
        System.out.println("对字母g向下取整：" + st.floor("g"));
        System.out.println("符号表是否包含字母d：" + st.contains("d"));
        System.out.println("符号表大小：" + st.size() + "，是否为空：" + st.isEmpty());
        System.out.println("符号表的键是否有序：" + st.check());

        System.out.println("循环输出符号表剩余键值对：");
        Iterable<String> keys = st.keys();
        for (String key : keys) {
            System.out.print(key + " " + st.get(key) + "\0\0\0\0");
        }
    }
}

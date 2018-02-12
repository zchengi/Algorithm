package org.three.one.exercises;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import org.three.one.learn.BinarySearchST;

/**
 * 3.1.26 基于字典表的统计。修改FrequencyCounter，接受一个字典文件作为参数，
 * 统计标准输入出现在字典中的单词的频率，并将单词和频率打印为两张表格，一张
 * 按照频率高低排序，一张按照字典顺序排序。
 *
 * 分析：按照频率高低排序：先找出所有单词并记录出现的次数，然后将键值交换位置，
 * 再逆序输出交换位置后的符号表，就是从高到低排序。
 *
 * @author cheng
 *         2018/2/12 20:37
 */
public class Exercise26 {
    public static <K extends Comparable<K>, V> void sortByFrequency(String name, String[] words) {
        BinarySearchST<String, Integer> st = new BinarySearchST<>();
        In in = new In("src/main/java/org/three/one/text/" + name);

        for (String word : words) {
            st.put(word, 0);
        }

        while (!in.isEmpty()) {
            String word = in.readString();
            if (st.contains(word)) {
                st.put(word, st.get(word) + 1);
            }
        }

        BinarySearchST<Integer, String> reverseST = new BinarySearchST<>();
        for (String word : st.keys()) {
            reverseST.put(st.get(word), word);
        }

        Stack<Integer> stack = new Stack<>();
        for (Integer count : reverseST.keys()) {
            stack.push(count);
        }
        while (!stack.isEmpty()) {
            Integer count = stack.pop();
            System.out.println(reverseST.get(count) + "的出现频率：" + count);
        }
    }

    public static <K extends Comparable<K>, V> void sortByDictionaryTableOrder(String name, String[] words) {
        BinarySearchST<String, Integer> st = new BinarySearchST<>();
        In in = new In("src/main/java/org/three/one/text/" + name);

        for (String word : words) {
            st.put(word, 0);
        }

        while (!in.isEmpty()) {
            String word = in.readString();
            if (st.contains(word)) {
                st.put(word, st.get(word) + 1);
            }
        }

        for (String key : st.keys()) {
            System.out.println(key + "的出现频率：" + st.get(key));
        }
    }

    public static void main(String[] args) {
        String name = "tale.txt";
        String words[] = {"the", "yourself", "your", "yielding"};

        System.out.println("按照出现频率排序：");
        sortByFrequency(name, words);

        System.out.println("按照字典表顺序排序：");
        sortByDictionaryTableOrder(name, words);
    }
}

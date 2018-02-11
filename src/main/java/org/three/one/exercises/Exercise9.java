package org.three.one.exercises;

import edu.princeton.cs.algs4.In;
import org.three.one.learn.BinarySearchST;

/**
 * 3.1.9 在FrequencyCounter中添加追踪put()方法的最后一次调用的代码。
 * 打印出最后插入的那个单词以及在此之前之前总共从输入中处理了多少个单词。
 * 用你的程序处理tale.txt中长度分别大于等于1、8和10的单词。
 *
 * @author cheng
 *         2018/2/11 12:36
 */
public class Exercise9 {
    public static void putTrack(int minLength) {
        BinarySearchST<String, Integer> st = new BinarySearchST<>();
        In in = new In("src/main/java/org/three/one/text/tale.txt");
        int lastPutCount = 0;
        String lastPutWord = null;
        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() < minLength) continue;

            if (!st.contains(word)) st.put(word, 1);
            else st.put(word, st.get(word) + 1);
            lastPutCount++;
            lastPutWord = word;
        }
        System.out.println("最后一次调用put()的单词：" + lastPutWord
                + "，在之前总共处理了：" + (lastPutCount - 1) + " 个单词");
    }

    public static void main(String[] args) {

        putTrack(1);
        putTrack(8);
        putTrack(10);
    }
}

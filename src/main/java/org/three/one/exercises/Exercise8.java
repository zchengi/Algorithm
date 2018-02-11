package org.three.one.exercises;

import edu.princeton.cs.algs4.In;
import org.three.one.learn.BinarySearchST;

/**
 * 3.1.8 在《双城记》中，使用频率最高的长度大于等于10的单词是什么？
 *
 * @author cheng
 *         2018/2/11 12:30
 */
public class Exercise8 {
    public static void main(String[] args) {
        In in = new In("src/main/java/org/three/one/text/tale.txt");
        BinarySearchST<String, Integer> st = new BinarySearchST<>();
        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() < 10) continue;
            if (st.contains(word)) st.put(word, st.get(word) + 1);
            else st.put(word, 1);
        }
        String maxWord = "";
        int max = 0;
        for (String word : st.keys()) {
            if (st.get(word) > max) {
                maxWord = word;
                max = st.get(word);
            }
        }
        System.out.println("使用频率最高的长度大于等于10的单词是： " + maxWord + "，出现了 " + max + "次");
    }
}

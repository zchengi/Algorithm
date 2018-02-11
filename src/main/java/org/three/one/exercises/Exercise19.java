package org.three.one.exercises;

import edu.princeton.cs.algs4.In;
import org.example.Queue;
import org.three.one.learn.BinarySearchST;

/**
 * 3.1.19 修改FrequencyCounter，打印出现频率最高的所有单词，而非其中之一。
 * 提示：请使用Queue。
 *
 * @author cheng
 *         2018/2/11 22:46
 */
public class Exercise19 {
    public static Queue<String> showMaxWord(String name, int minLength) {
        BinarySearchST<String, Integer> st = new BinarySearchST<>();
        In in = new In("src/main/java/org/three/one/text/" + name);

        while (!in.isEmpty()) {
            // 构造符号表并统计频率
            String word = in.readString();
            // 忽略较短的单词
            if (word.length() < minLength) continue;
            if (!st.contains(word)) st.put(word, 1);
            else st.put(word, st.get(word) + 1);
        }

        // 找出出现频率最高的单词
        int maxCount = 0;
        for (String word : st.keys()) {
            if (st.get(word) > maxCount) {
                maxCount = st.get(word);
            }
        }
        Queue<String> queue = new Queue<>();
        for (String word : st.keys()) {
            if (st.get(word) == maxCount) {
                queue.enqueue(word);
            }
        }
        return queue;
    }

    public static void main(String[] args) {
        Queue<String> queue = showMaxWord("words.txt", 0);
        for (String word : queue) {
            System.out.println(word);
        }
    }
}

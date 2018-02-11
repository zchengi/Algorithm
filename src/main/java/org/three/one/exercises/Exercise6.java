package org.three.one.exercises;

import edu.princeton.cs.algs4.In;
import org.three.one.learn.BinarySearchST;

/**
 * 3.1.6 用输入中的单词总数W和不同单词总数D的函数给出FrequencyCounter调用
 * put()和get()方法的次数。
 *
 * @author cheng
 *         2018/2/11 11:37
 */
public class Exercise6 {
    public static void main(String[] args) {
        // 单次总数 W  不同单词总数 D
        int W = 0;
        int D = 0;
        In in = new In("src/main/java/org/three/one/text/tale.txt");
        BinarySearchST<String, Integer> st = new BinarySearchST<>();
        int minLength = 8;

        while (!in.isEmpty()) {
            String word = in.readString();
            // 忽略较短的单词
            if (word.length() < minLength) continue;

            // get： W 次
            if (st.contains(word)) {
                // put： W - D 次
                // get： W - D 次
                st.put(word, st.get(word) + 1);
            } else {
                // put： D 次
                st.put(word, 1);
                D++;
            }
            W++;
        }

        String max = "";
        // put： 1 次
        st.put(max, 0);
        for (String word : st.keys()) {
            // get： 2 * D 次
            if (st.get(word) > st.get(max)) {
                max = word;
            }
        }
        // get： 1 次
        System.out.println(max + " " + st.get(max));

        System.out.println("单词总数： " + W + "，不同单词数： " + D);

        /*
         *
         * get 总次数： W + W - D + 2 * D + 1 = 2W + D + 1
         *
         * put 总次数： W - D + D + 1 = W + 1
         *
         */
    }
}

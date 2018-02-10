package org.three.one.learn;

import edu.princeton.cs.algs4.In;

/**
 * 3.1.3.2 性能测试用例
 * 从标准输入中得到一列字符串并记录每个字符串出现次数（长度至少达到指定的阀值），
 * 然后遍历所有键找出出现频率最高的键。
 * 这是一种字典。
 *
 * @author cheng
 *         2018/2/5 12:12
 */
public class FrequencyCounter {
    public static void showMaxWord(String name, int minLength) {
        BinarySearchST<String, Integer> st = new BinarySearchST<>();
        In in = new In("src/main/java/org/three/one/text/"+name);
        in.isEmpty();
        while (!in.isEmpty()) {
            // 构造符号表并统计频率
            String word = in.readString();
            // 忽略较短的单词
            if (word.length() < minLength) continue;
            if (!st.contains(word)) st.put(word, 1);
            else st.put(word, st.get(word) + 1);
        }

        // 找出出现频率最高的单词
        String max = "";
        st.put(max, 0);
        for (String word : st.keys()) {
            if (st.get(word) > st.get(max)) {
                max = word;
            }
        }
        System.out.println(max + " " + st.get(max));
    }

    public static void main(String[] args) {
        // 最小键长 1             8         10
        // 文件名   tinyTale.txt  tale.txt  leipzig1M.txt
        showMaxWord("tinyTale.txt",1);
        showMaxWord("tale.txt",8);
        showMaxWord("leipzig1M.txt",10);
    }
}

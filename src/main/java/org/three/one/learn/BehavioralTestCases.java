package org.three.one.learn;

import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;

/**
 * 3.1.3.1 行为测试用例
 *
 * @author cheng
 *         2018/2/5 12:07
 */
public class BehavioralTestCases {
    public static void main(String[] args) {
        ST<String, Integer> st = new ST<>();
        // S E A R C H E HX A M P L E
        for (int i = 0; !StdIn.isEmpty(); i++) {
            st.put(StdIn.readString(), i);
        }

        for (String key : st.keys()) {
            System.out.println(key + " " + st.get(key));
        }
    }
}

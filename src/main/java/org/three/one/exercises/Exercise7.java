package org.three.one.exercises;

import org.three.one.learn.BinarySearchST;
import org.tool.SortTestHelper;

/**
 * 3.1.7 对于N = 10、10^2、10^3、10^4、10^5和10^6，在N个小于1000的随机非负整数中FrequencyCounter平均能够找到多少个不同的键？
 *
 * @author cheng
 *         2018/2/11 12:02
 */
public class Exercise7 {
    public static void main(String[] args) {
        for (int n = 10, j = 1; j <= 6; n *= 10, j++) {
            BinarySearchST<Integer, Integer> st = new BinarySearchST<>();
            for (Integer i : SortTestHelper.generateRandomArray(n, 0, 999)) {
                if (st.contains(i)) {
                    st.put(i, st.get(i) + 1);
                } else {
                    st.put(i, 1);
                }
            }
            System.out.println("规模：" + n + "，总共能找到：" + st.size() + "个不同的键");
        }
    }
}

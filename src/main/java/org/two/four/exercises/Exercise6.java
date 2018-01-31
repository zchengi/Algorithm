package org.two.four.exercises;

import org.two.four.learn2.MaxHeap;

/**
 * 3.4.6 按照联系2.4.1的规则，用顺序 P R I O * R * * I * T * Y * * * Q U E * * * U * E
 * 操作一个初始为空的面向最大元素的堆，给出每次操作后堆的内容。
 *
 * @author cheng
 *         2018/1/31 17:19
 */
public class Exercise6 {
    public static void main(String[] args) {
        String str = "P R I O * R * * I * T * Y * * * Q U E * * * U * E";
        String[] arr = str.split("\\s");
        MaxHeap<String> maxHeap = new MaxHeap<>(10);
        MaxHeap<String> copy;

        for (String item : arr) {
            if ("*".equals(item)) {
                maxHeap.extractMax();
            } else {
                maxHeap.insert(item);
            }
            maxHeap.show();
        }
    }
}

package org.two.four.exercises;

import org.two.four.learn2.MaxHeap;

/**
 * 2.4.1 用序列 P R I O * R * * I * T * Y * * * Q U E * * * U * E
 * （字母表示插入元素，*表示删除最大元素）操作一个优先队列。
 * 给出每次删除最大元素返回的字符。
 *
 * @author cheng
 *         2018/1/31 14:55
 */
public class Exercise1 {
    public static void main(String[] args) {
        String str = "P R I O * R * * I * T * Y * * * Q U E * * * U * E";
        String[] arr = str.split("\\s");
        MaxHeap<String> maxHeap = new MaxHeap<>(10);

        for (String item : arr) {
            if (item.equals("*")) {
                System.out.print(maxHeap.extractMax() + " ");
            } else {
                maxHeap.insert(item);
            }
        }
    }
}

package org.two.four.exercises;

import org.two.four.learn2.MaxHeap;

/**
 * 2.4.5 将 E A S Y Q U E S T I O N 顺序插入一个面向最大元素的堆中，给出结果。
 *
 * @author cheng
 *         2018/1/31 17:16
 */
public class Exercise5 {
    public static void main(String[] args) {
        String str = "E A S Y Q U E S T I O N";
        String[] arr = str.split("\\s");
        MaxHeap<String> maxHeap = new MaxHeap<>(arr);
        while (!maxHeap.isEmpty()) {
            System.out.print(maxHeap.extractMax() + " ");
        }
    }
}

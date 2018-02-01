package org.two.five.exercises;

import org.tool.ArrayGenerator;

/**
 * 2.5.7 用select()找出N个元素中的最小值平均大约需要多少次数组比较？
 * 即找索引为 0 的值
 *
 * @author cheng
 *         2018/2/1 20:53
 */
public class Exercise7 {
    public static void main(String[] args) {
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            int[] arr = ArrayGenerator.ints(100, 5, 1000);
            Exercise6.quickSelect2(arr, 1);
            sum += Exercise6.compares;
        }
        System.out.println("平均比较次数为：" + sum / 100);
    }
}

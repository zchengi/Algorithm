package org.coursera.week1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author cheng
 *         2018/1/8 12:02
 *         <p>
 *         Test4: 2-sum 线性级别
 *         -分析：
 *         要实现3-sum复杂度为N^2，要先把2-sum复杂度实现为n，这里使用map实现(也可以用set)。
 *         将数组中的数依次添加到map中的键中，当数组中当前数的取反后，取反值存在于map的键中，则
 *         表示当前值与取反值均存在于数组中，count++。
 */
public class TwoSumLinear {
    public static int count(int[] a) {
        // 满足 2-sum 的对数
        int count = 0;
        // 数组大小
        int n = a.length;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            if (map.get(a[i]) == null) {
                map.put(a[i], i);
            }
            Integer negIndex = map.get(-a[i]);
            if (negIndex != null && negIndex != i) {
                System.out.println("a[" + negIndex + "]=" + (-a[i]) + "和a[" + i + "]=" + a[i]);
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] a = {30, -40, -20, -10, 40, 0, 10, 5};
        System.out.println(Arrays.toString(a));
        System.out.println(count(a));
    }
}

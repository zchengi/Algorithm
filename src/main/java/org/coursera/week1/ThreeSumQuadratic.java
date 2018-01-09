package org.coursera.week1;

import edu.princeton.cs.algs4.In;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author cheng
 *         2018/1/8 14:40
 *         <p>
 *         Test4: 3-sum 平方级别
 *         -分析：
 *         可以先将数组排序，然后结合写过的2sum线性实现方法即可。
 */
public class ThreeSumQuadratic {
    public static int count(int[] a, int target) {
        // 数组从大到小排序，以便于之后使用有序数组的性质简化运算
        Arrays.sort(a);
        // System.out.println(Arrays.toString(a));
        // System.out.println("target=" + target);

        int count = 0;
        int n = a.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            // 以数组的值作为key，index作为value
            map.put(a[i], i);
        }
        // i不会超过n-2
        for (int i = 0; i < n - 1; i++) {
            // j从i+1开始统计，不会超过n-1
            for (int j = i + 1; j < n; j++) {
                // 因为排好序了，将最开始的两个数相加 a[i] + a[j]
                int smallValue = a[i] + a[j];
                // 当 a[i] + a[j] > target 时就没有计算的必要了，因为后续的查找就会重复。(两个数相加为正，之后大于这两个数的数也为正)
                if (smallValue > target) {
                    break;
                }
                // 计算出对应的数值较大的value
                int bigValue = target - smallValue;
                Integer bigIndex = map.get(bigValue);
                if (bigIndex != null && bigIndex > i && bigIndex > j) {
                   // System.out.println("[" + i + "]=" + a[i] + ",[" + j + "]" + a[j]
                   //         + ",[" + bigIndex + "]" + (bigValue));
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] a = new In("src/main/java/org/coursera/week1/8Kints.txt").readAllInts();
        // int[] a = {30, -40, -20, -10, 40, 0, 10, 5};
        // 计算数组a中，随机三个数相加为0的组数
        System.out.println(count(a, 0));
    }
}










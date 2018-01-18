package org.coursera.week2;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

/**
 * Test6 Dutch national flag
 * 题目：本题的限制条件n calls to color()意味着每个元素最多只能访问一次，也就是要求在遍历一次所有元素的情况下完成排序。
 * 遍历结束时红色球要全部在数组的左侧，白色球全部在中间，蓝色球全部在右侧，整个数组将被分成三部分
 *
 * @author cheng
 *         2018/1/16 15:09
 */
public class DutchNationalFlag {

    private static final int RED = 0;
    private static final int WHITE = 1;
    private static final int BLUE = 2;
    private int n;
    private int[] buckets;
    private int callColorNum = 0;
    private int callSwapNum = 0;

    public DutchNationalFlag(int[] buckets) {
        this.n = buckets.length;
        this.buckets = buckets;
    }

    public void sort() {
        int lo = 0;
        int current = 0;
        int hi = n - 1;
        while (current <= hi) {
            switch (color(current)) {
                case RED:
                    if (current != lo) {
                        swap(current, lo);
                    }
                    current++;
                    lo++;
                    break;
                case WHITE:
                    current++;
                    break;
                case BLUE:
                    swap(hi, current);
                    hi--;
                    break;
            }
        }
    }

    private void swap(int i, int j) {
        callSwapNum++;
        int temp = buckets[i];
        buckets[i] = buckets[j];
        buckets[j] = temp;
    }

    private int color(int i) {
        callColorNum++;
        return buckets[i];
    }

    public static void main(String[] args) {
        int n = 10;
        int[] buckets = new int[n];
        for (int i = 0; i < n; i++) {
            buckets[i] = StdRandom.uniform(3);
        }
        System.out.println(Arrays.toString(buckets));
        DutchNationalFlag dnf = new DutchNationalFlag(buckets);
        //dnf.sort();
        dnf.quickSort3way();
        System.out.println("after sort call color()=+" + dnf.callColorNum
                + "times, call swap()=" + dnf.callSwapNum + "times");
        System.out.println(Arrays.toString(buckets));
    }

    /**
     * 3way-quick sort
     * 更简单清晰的做法
     */
    public void quickSort3way() {
        int lt = 0;
        int gt = n - 1;
        int current = 0;
        while (current <= gt) {
                    int num = color(current);
                    if (num < WHITE) {
                        swap(lt++, current++);
                    } else if (num > WHITE) {
                        swap(current, gt--);
                    } else {
                        current++;
            }
        }
    }
}

package org.two.one.exercises;

import org.two.one.learn.Shell;

/**
 * 2.1.14 出列排序。说说你会如何将一副扑克牌排序，限制条件是只能查看最上面的两张牌，
 * 交换最上面的两张牌，或是将最上面的一张牌放到这摞牌的最下面。
 * <p>
 * 思路：模仿冒泡排序
 * 冒泡排序是移动i(即数组指针)，而我们可以通过移动数组来近似实现。
 * 我们可以固定交换前两个，移动数组。
 * <p>
 * 即进行一系列队列操作，每次出列再入列的项都是小的项，最终达到从小到大排序。
 *
 * @author cheng
 *         2018/1/18 13:07
 */
public class Exercise14 extends Shell {
    public static void sortX(Comparable[] a) {
        int n = a.length;
        while (!isSorted(a)) {
            for (int i = 0; i < n - 1; i++) {
                if (less(a[1], a[0])) {
                    // 第一张和第二张交换
                    exch(a, 0, 1);
                }
                for (int j = 0; j < n - 1; j++) {
                    // 第一张放到最后
                    exch(a, j, j + 1);
                }
            }
            // 此时 第一张为最大的一张
            for (int j = 0; j < n - 1; j++) {
                // 第一张放到最后
                exch(a, j, j + 1);
            }
        }
    }

    public static void main(String[] args) {
        int n = 52;
        Integer[] cards = new Integer[n];
        for (int i = 0; i < 4; i++) {
            for (int j = i * 13; j < 13 + i * 13; j++) {
                cards[j] = i;
            }
        }
        //cards = new Integer[]{1, 3, 1, 0, 2, 0, 2, 3};
        // StdRandom.shuffle(cards);
        sortX(cards);
        show(cards);
    }
}

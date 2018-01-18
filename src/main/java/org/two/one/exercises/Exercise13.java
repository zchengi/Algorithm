package org.two.one.exercises;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

/**
 * 2.1.13 纸牌排序。说说你会如何将一副扑克牌按花色排序（花色顺序是黑桃、红桃、梅花和方片），
 * 限制条件是所有牌都是背面朝上排成一列，而你一次只能翻看两张或者交换两张牌（保持背面朝上）。
 *
 * @author cheng
 *         2018/1/18 11:01
 */
public class Exercise13 {
    /*
     * 这个问题其实就是如何排序 1111111111111 2222222222222 3333333333333 4444444444444 这个序列
     * 这里将 0 1 2 3 分别代表 黑桃 红桃 梅花 方片
     */
    private static final int spades = 0;
    private static final int hearts = 1;
    private static final int clubs = 2;
    private static final int diamonds = 3;

    /**
     * 纸牌数
     */
    private int n;

    /**
     * 纸牌数组
     */
    private int[] cards;

    /**
     * 数组查询次数
     */
    private int callCardNum = 0;

    /**
     * 交换次数
     */
    private int callSwapNum = 0;

    public Exercise13(int[] cards) {
        this.cards = cards;
        this.n = cards.length;
    }

    public void sort() {
        int lo = 0;
        int current = 0;
        int hi = n - 1;
        while (current <= hi) {
            switch (card(current)) {
                case spades:
                    if (current != lo) {
                        swap(current, lo);
                    }
                    lo++;
                    current++;
                    break;
                case diamonds:
                    swap(current, hi);
                    hi--;
                    break;
                default:
                    current++;
                    break;
            }
        }
        int lo2 = 13;
        int current2 = 13;
        int hi2 = 38;
        while (current2 <= hi2) {
            switch (card(current2)) {
                case hearts:
                    if (current2 != lo2) {
                        swap(current2, lo2);
                    }
                    lo2++;
                    current2++;
                    break;
                case clubs:
                    swap(current2, hi2);
                    hi2--;
                    break;
                default:
                    current2++;
                    break;
            }
        }
    }

    private void swap(int i, int j) {
        callSwapNum++;
        int temp = cards[i];
        cards[i] = cards[j];
        cards[j] = temp;
    }

    private int card(int num) {
        callCardNum++;
        return cards[num];
    }

    public static void main(String[] args) {
        int n = 52;
        int[] cards = new int[n];
        for (int i = 0; i < 4; i++) {
            for (int j = i * 13; j < 13 + i * 13; j++) {
                cards[j] = i;
            }
        }
        StdRandom.shuffle(cards);
        System.out.println(Arrays.toString(cards));
        Exercise13 ex = new Exercise13(cards);
        ex.sort();
        System.out.println("after sort call card() =+" + ex.callCardNum
                + "times, call swap() =" + ex.callSwapNum + "times");
        System.out.println(Arrays.toString(cards));
    }
}

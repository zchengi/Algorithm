package org.cheng.day8;

import edu.princeton.cs.algs4.StdRandom;

/**
 * 1.3.35
 * 在桥牌中发牌（每人13张）
 *
 * @author cheng
 *         2017/10/23 14:13
 */
public class Card {

    private String name;

    private String flowerColor;

    public Card() {
        String[] nget = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        String[] fget = {"红桃", "黑桃", "梅花", "棉花"};
        int n = StdRandom.uniform(13);
        int f = StdRandom.uniform(4);
        name = nget[n];
        flowerColor = fget[f];
    }

    @Override
    public String toString() {
        return flowerColor + name;
    }

    public static void main(String[] args) {
        RandomQueue<Card> rd = new RandomQueue<Card>();
        for (int i = 0; i < 13; i++) {
            rd.enqueue(new Card());
        }
        for (int i = 0; i < 13; i++) {
            if (StdRandom.bernoulli()) {
                System.out.println(rd.dequeue());
            } else {
                System.out.println(rd.sample());
            }
        }
        System.out.println(rd.size());
    }
}

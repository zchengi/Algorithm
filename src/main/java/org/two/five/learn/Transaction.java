package org.two.five.learn;

import java.util.Comparator;
import java.util.Date;

/**
 * 使用comparator的插入排序
 *
 * @author cheng
 *         2018/2/1 15:06
 */
public class Transaction {
    private final String who = "";
    private final Date when = null;
    private final double amount = 0;

    public static class WhoOrder implements Comparator<Transaction> {
        @Override
        public int compare(Transaction v, Transaction w) {
            return v.who.compareTo(w.who);
        }

    }

    public static class whenOrder implements Comparator<Transaction> {
        @Override
        public int compare(Transaction v, Transaction w) {
            return v.when.compareTo(w.when);
        }

    }

    public static class HowMuchOrder implements Comparator<Transaction> {
        @Override
        public int compare(Transaction v, Transaction w) {
            if (v.amount < w.amount) return -1;
            else if (v.amount > w.amount) return +1;
            return 0;
        }

    }
}

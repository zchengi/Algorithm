package org.two.one.exercises;

import java.util.regex.Pattern;

/**
 * 2.1.21 可比较的交易。用我们的Date类（见2.1.1.4节）作为模版扩展你的Transaction类（见练习1.2.13），
 * 实现Comparable接口，使交易能够按照金额排序。
 *
 * @author cheng
 *         2018/1/19 14:45
 */
public class Exercise21 {

    /**
     * Transaction类
     */
    static class Transaction implements Comparable<Transaction> {

        private final double amount;
        private final String date;
        private final String name;
        private final String info;

        public Transaction(String info) {
            String[] InfoSplit = info.split("\\s+");
            if (InfoSplit.length != 3) {
                throw new IllegalArgumentException("arguments invalid!");
            }

            date = InfoSplit[1];
            if (!date.matches("\\d{1,4}-\\d{1,2}-\\d{1,2}")) {
                throw new IllegalArgumentException("date format invalid!");
            }

            name = InfoSplit[2];
            if (Pattern.compile("\\d").matcher(name).find()) {
                throw new IllegalArgumentException("name cannot contain digit!");
            }

            if (!InfoSplit[0].matches("[1-9]\\d*(\\.\\d+)?")) {
                throw new IllegalArgumentException("money format invalid!");
            }
            amount = Double.parseDouble(InfoSplit[0]);

            this.info = info;
        }

        @Override
        public String toString() {
            return info;
        }

        @Override
        public int compareTo(Transaction that) {
            return amount < that.amount ? -1 : amount > that.amount ? 1 : 0;
        }
    }

    public static void main(String[] args) {
        Transaction t1 = new Transaction("200.23 1994-5-2 lisi"),
                t2 = new Transaction("342 2010-10-7 zhangsan"),
                t3 = new Transaction("100 2017-9-27 wangwu"),
                t4 = new Transaction("342 1879-2-28 zhaoliu");
        System.out.println(t1.compareTo(t2));
        System.out.println(t1.compareTo(t3));
        System.out.println(t1.compareTo(t4));
        System.out.println(t2.compareTo(t3));
        System.out.println(t2.compareTo(t4));
        System.out.println(t3.compareTo(t4));
    }
}

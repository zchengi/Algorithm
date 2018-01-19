package org.two.one.exercises;

import org.two.one.exercises.Exercise21.Transaction;
import org.two.one.learn.Shell;

/**
 * 2.1.22 交易测试用例。编写一个SortTransaction类，在静态方法main()中从标准输入
 * 读取一些列交易，将它们排序并在标准输出中打印结果（请见练习1.3.17）。
 *
 * @author cheng
 *         2018/1/19 15:08
 */
public class Exercise22 {
    public static void main(String[] args) {
        Transaction t1 = new Transaction("200.23 1994-5-2 lisi"),
                t2 = new Transaction("342 2010-10-7 zhangsan"),
                t3 = new Transaction("100 2017-9-27 wangwu"),
                t4 = new Transaction("342 1879-2-28 zhaoliu"),
                t5 = new Transaction("500.3 2011-6-30 lisi"),
                t6 = new Transaction("500 2012-5-21 zhangsan"),
                t7 = new Transaction("400 2013-5-20 zhaoliu");
        Transaction[] transactions = new Transaction[] {t1, t2, t3, t4, t5, t6, t7};

        Shell.sort(transactions);
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }
}

package org.two.five.exercises;

/**
 * 2.5.3 找出下面这段账单余额Balance类的实现代码的错误。
 * 为什么compareTo()方法对Comparable接口的实现有缺陷。说明如何休正这个问题。
 *
 * @author cheng
 *         2018/2/1 17:13
 */
public class Exercise3 {
    /**
     * 该实现违背了Comparable接口需满足传递性的要求：
     * 假设 a.compareTo(b) 和 b.compareTo(c) 返回值都为0，
     * 但是 a.compareTo(c) 不为0,可能为1也可能为-1；
     *
     * 将加减值设置的更小一些，可以避免这个问题。
     */
    class Balance implements Comparable<Balance> {
        private double amount;

        @Override
        public int compareTo(Balance that) {
            if (this.amount < that.amount - 0.005) return -1;
            if (this.amount > that.amount + 0.005) return +1;
            return 0;
        }
    }
}


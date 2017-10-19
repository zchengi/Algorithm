package org.cheng.day3;

/**
 * Exercise 1.2.16 & 1.2.17
 * 为有理数实现一个不可变数据类型，支持加减乘除操作。
 * 使用欧几里德算法来保证分子和分母没有公因子。
 * 添加断言来防止溢出。
 *
 * @author cheng
 *         2017/10/19 11:31
 */
public class Rational {
    /**
     * 分子
     */
    private int numerator;
    /**
     * 分母
     */
    private int denominator;

    /**
     * int类型最大值
     */
    private static final int MAX = 2147483647;
    /**
     * int类型最小值
     */
    private static final int MIN = -2147483647;

    public Rational(int numerator, int denominator) throws ArithmeticException {
        if (denominator == 0) {
            throw new ArithmeticException("分母为零");
        }
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }
        /**
         * 公约数
         */
        int commonDivisor = euclid(numerator, denominator);

        this.numerator = numerator / commonDivisor;
        this.denominator = denominator / commonDivisor;
    }

    /**
     * 欧几里德算法
     */
    private int euclid(int p, int q) {
        if (p == 0 || q == 0) {
            return 1;
        }
        p = Math.abs(p);
        q = Math.abs(q);
        if (p < q) {
            int t = p;
            p = q;
            q = t;
        }
        if (p % q == 0) {
            return q;
        } else {
            return euclid(q, p % q);
        }
    }

    /**
     * 计算和
     */
    public Rational plus(Rational b) {
        assert isPlusOverflow(numerator * b.denominator, b.numerator * denominator) : "Plus overflow";
        assert isTimeOverflow(denominator, b.denominator) : "Times overflow";
        return new Rational(numerator * b.denominator + b.numerator * denominator, denominator * b.denominator);
    }

    /**
     * 计算差
     */
    public Rational minus(Rational b) {
        return new Rational(numerator * b.denominator - b.numerator * denominator, denominator * b.denominator);
    }

    /**
     * 计算积
     */
    public Rational times(Rational b) {
        assert isTimeOverflow(numerator, b.numerator) : "Times overflow";
        assert isTimeOverflow(denominator, b.denominator) : "Times overflow";
        return new Rational(numerator * b.numerator, denominator * b.denominator);
    }

    /**
     * 计算商
     */
    public Rational divides(Rational b) {
        return new Rational(numerator * b.denominator, denominator * b.numerator);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Rational that = (Rational) obj;
        return !(this.numerator != that.numerator || this.denominator != that.denominator);
    }

    @Override
    public String toString() {
        if (Math.abs(numerator) % Math.abs(denominator) == 0) {
            return String.valueOf(numerator / denominator);
        } else {
            return numerator + "/" + denominator;
        }
    }

    /**
     * 判断是否溢出
     */
    private boolean isPlusOverflow(int a, int b) {
        return a >= 0 ? a + b < MAX : a + b > MIN;
    }

    private boolean isTimeOverflow(int a, int b) {
        if (a < 0) {
            a = -a;
        }
        if (b < 0) {
            b = -b;
        }
        if (a == 0 || b == 0) {
            return false;
        } else {
            return a * b < MAX;
        }
    }

    public static void main(String[] args) {
        int numerator =2;
        int denominator = 1;
        Rational a = new Rational(numerator, denominator);
        System.out.println(a);
        numerator = 3;
        denominator = 1;
        Rational b = new Rational(numerator, denominator);

        System.out.println("a plus b: "+a.plus(b));
        System.out.println("a minus b: "+a.minus(b));
        System.out.println("a times b: "+a.times(b));
        System.out.println("a divides b: "+a.divides(b));
        System.out.println("a equals b: "+a.equals(b));
    }
}

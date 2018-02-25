package org.unionfind;

/**
 * 测试用
 *
 * @author cheng
 *         2018/2/25 11:53
 */
public class Pair<A, B> {
    private A a;
    private B b;

    public Pair(A a, B b) {
        this.a = a;
        this.b = b;
    }

    public A a() {
        return a;
    }

    public B b() {
        return b;
    }
}

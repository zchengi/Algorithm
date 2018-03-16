package org.four.four.learn2;

/**
 * 数据类型：边
 *
 * @author cheng
 *         2018/3/14 15:12
 */
public class Edge<Weight extends Number & Comparable> implements Comparable<Edge> {

    /**
     * 边的两个端点
     */
    private int a, b;

    /**
     * 边的权值
     */
    private Weight weight;

    public Edge(int a, int b, Weight weight) {
        this.a = a;
        this.b = b;
        this.weight = weight;
    }

    public Edge(Edge<Weight> e) {
        this.a = e.a;
        this.b = e.b;
        this.weight = e.weight;
    }

    public int other(int x) {
        if (x != a && x != b) {
            throw new IllegalArgumentException("Called other() with a invalid argument.");
        }

        return x == a ? b : a;
    }

    @Override
    public String toString() {
        return "" + a + "-" + b + ": " + weight;
    }

    @Override
    public int compareTo(Edge that) {
        if (this.weight.compareTo(that.wt()) < 0) {
            return -1;
        } else if (this.weight.compareTo(that.wt()) > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * 返回第一个顶点
     */
    public int v() {
        return a;
    }

    /**
     * 返回第二个顶点
     */
    public int w() {
        return b;
    }

    /**
     * 返回权值
     */
    public Weight wt() {
        return weight;
    }
}

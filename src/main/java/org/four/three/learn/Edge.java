package org.four.three.learn;

/**
 * 算法4.3节 加权边的API
 * **********************************************************************
 * public class Edge implement Comparable<Edge>
 * **********************************************************************
 * Edge (int v, int w, double weight)   用于初始化的构造函数
 * double weight()                      边的权重
 * int either()                         边两端的顶点之一
 * int other(int v)                     另一个顶点
 * int compareTo(Edge that)             将这条边与that比较
 * String toString()                    对象的字符串表示
 * **********************************************************************
 *
 * @author cheng
 *         2018/3/15 15:16
 */
public class Edge implements Comparable<Edge> {

    /**
     * 顶点之一
     */
    private final int v;

    /**
     * 另一个顶点
     */
    private final int w;

    /**
     * 权重
     */
    private final double weight;

    public Edge(int v, int w, double weight) {
        if (v < 0) {
            throw new IllegalArgumentException("Vertex index must be a nonegative integer.");
        }
        if (w < 0) {
            throw new IllegalArgumentException("Vertex index must be a nonegative integer.");
        }
        if (Double.isNaN(weight)) {
            throw new IllegalArgumentException("Weight is NaN.");
        }

        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double weight() {
        return weight;
    }

    public int either() {
        return v;
    }

    public int other(int vertex) {
        if (vertex == v) return w;
        else if (vertex == w) return v;
        else throw new IllegalArgumentException("Illegal endpoint.");
    }

    @Override
    public int compareTo(Edge that) {
        return Double.compare(this.weight, that.weight);
    }

    public String toString() {
        return String.format("%d-%d %.5f", v, w, weight);
    }

    public static void main(String[] args) {

        Edge edge = new Edge(12, 34, 5.22);
        System.out.println(edge);
    }
}

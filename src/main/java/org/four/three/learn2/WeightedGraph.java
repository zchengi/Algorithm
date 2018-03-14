package org.four.three.learn2;

/**
 * 加权图的接口
 *
 * @author cheng
 *         2018/3/14 15:43
 */
public interface WeightedGraph<Weight extends Number & Comparable> {

    int V();

    int E();

    void addEdge(Edge<Weight> ed);

    boolean hasEdge(int v, int w);

    void show();

    Iterable<Edge<Weight>> adj(int v);
}

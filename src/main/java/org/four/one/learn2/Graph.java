package org.four.one.learn2;

/**
 * 图的接口
 *
 * @author cheng
 *         2018/3/7 12:56
 */
public interface Graph {

    void addEdge(int v, int w);

    Iterable<Integer> adj(int v);

    void show();

    boolean hasEdge(int v, int w);

    int V();

    int E();
}

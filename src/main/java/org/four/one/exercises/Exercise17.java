package org.four.one.exercises;

/**
 * 4.1.16 图的周长为图中最短环的长度。如果是无环图，则它的周长为无穷大。为GraphProperties添加一个方法girth()，
 * 返回图的周长。提示：在每个顶点都进行广度优先搜索。含有s的最小环为s到某个顶点v的最短路径加上从v返回到s的边。
 *
 * @author cheng
 *         2018/3/12 15:10
 */
public class Exercise17 {
    /*
     *
     * 本题是求无向图的最小环，解法为：
     * 无向图的环最少有三个点，所以需要增加一部分求最小环；
     * 枚举中间点k，在用其更新最短路前，先找最小环，令1<=i<j<k，即k点必定不在i,j的最短路上，
     * 则这个环中至少有三个点，这个环的权值为：dis[i][j]+g[i][k]+g[k][j]
     *
     * 见 examples 的 GraphProperties
     *
     */
}

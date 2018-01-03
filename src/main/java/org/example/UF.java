package org.example;

import edu.princeton.cs.algs4.StdIn;

/**
 * 算法1.5 union-find的实现
 *
 * @author cheng
 *         2018/1/3 14:47
 */
public class UF {

    /**
     * 分量id（以触点作为索引）
     */
    private int[] id;
    /**
     * 分量数量
     */
    private int count;

    /**
     * 以整数标识（0到N-1）初始化N个触点
     *
     * @param N 分量数量
     */
    public UF(int N) {
        //初始化分量id数组
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    /**
     * 连通分量的数量
     */
    public int count() {
        return count;
    }

    /**
     * 如果p与q存在于同一个分量中则返回true
     */
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * p（0到N-1）所在的分量的标识符
     */
    public int find(int p) {
        return 0;
    }

    /**
     * 在p和q之间添加一条连接
     */
    public void union(int p, int q) {

    }

    public static void main(String[] args) {
        //解决由StdIn得到的动态连通性问题
        //读取触点数量
        int N = StdIn.readInt();
        //初始化N个分量
        UF uf = new UF(N);
        while (!StdIn.isEmpty()) {
            //读取整数对
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            // 如果已经连同则忽略
            if (uf.connected(p, q)) {
                continue;
            }
            //归并分量
            uf.union(p, q);
            //打印连接
            System.out.println(p + " " + q);
        }
        System.out.println(uf.count() + " components");
    }
}

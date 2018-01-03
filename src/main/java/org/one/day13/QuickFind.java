package org.one.day13;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 实现1：quick-find算法
 * 结论：
 * quick-find算法一般无法处理大型问题，因为对于每一次输入unio()都需要扫描整个id[]数组。
 *
 * @author cheng
 *         2018/1/3 15:18
 */
public class QuickFind {
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
    public QuickFind(int N) {
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
        return id[p];
    }

    /**
     * 在p和q之间添加一条连接
     */
    public void union(int p, int q) {
        // 将p和q归并到相同的分量中
        int pID = find(p);
        int qID = find(q);

        // 如果p和q已经在相同的分量之中则不需要采取任何行动
        if (pID == qID) {
            return;
        }
        // 将p的分量重命名为q的名称
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pID) {
                id[i] = qID;
            }
        }
        count--;
    }

    public static void main(String[] args) {
        In in = new In("src/main/java/org/one/day13/largeUF.txt");
        int N = in.readInt();
        QuickFind qf = new QuickFind(N);
        while (!in.isEmpty()) {
            int p = in.readInt();
            int q = in.readInt();
            if (qf.connected(p, q)) {
                continue;
            }
            qf.union(p, q);
            System.out.println(p + " " + q);
        }
        StdOut.println(qf.count() + " components");
    }
}

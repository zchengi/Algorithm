package org.one.day13;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * quick-union算法
 *
 * @author cheng
 *         2018/1/3 16:00
 */
public class QuickUnion {
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
    public QuickUnion(int N) {
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
     * 随着链接到达根触点
     */
    public int find(int p) {
        // 找出分量的名称
        while (p != id[p]) {
            p = id[p];
        }
        return p;
    }

    /**
     * 在p和q之间添加一条连接
     * 只需要修改一个链接
     */
    public void union(int p, int q) {
        // 将p和q的根节点统一
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }
        id[pRoot] = qRoot;
        count--;
    }

    public static void main(String[] args) {
        In in = new In("src/main/java/org/one/day13/tinyUF.txt");
        int N = in.readInt();
        QuickUnion qu = new QuickUnion(N);
        while (!in.isEmpty()) {
            int p = in.readInt();
            int q = in.readInt();
            if (qu.connected(p, q)) {
                continue;
            }
            qu.union(p, q);
            System.out.println(p + " " + q);
        }
        StdOut.println(qu.count() + " components");
    }
}

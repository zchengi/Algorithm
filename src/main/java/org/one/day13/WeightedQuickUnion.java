package org.one.day13;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * union-find算法的实现（加权quick-union算法）
 *
 * @author cheng
 *         2018/1/3 16:46
 */
public class WeightedQuickUnion {
    /**
     * 父链接数组（由触点索引）
     */
    private int[] id;
    /**
     * （由触点索引的）各个根节点所对应的分量的大小
     */
    private int[] sz;
    /**
     * 连通分量的数量
     */
    private int count;

    public WeightedQuickUnion(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            sz[i] = 1;
        }
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int find(int p) {
        // 跟随链接找到根节点
        while (p != id[p]) {
            p = id[p];
        }
        return p;
    }

    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        if (i == j) {
            return;
        }
        // 将小树的根节点连接到大树的根节点
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }
        count--;
    }

    public static void main(String[] args) {
        In in = new In("src/main/java/org/one/day13/largeUF.txt");
        int N = in.readInt();
        WeightedQuickUnion wqu = new WeightedQuickUnion(N);
        while (!in.isEmpty()) {
            int p = in.readInt();
            int q = in.readInt();
            if (wqu.connected(p, q)) {
                continue;
            }
            wqu.union(p, q);
            System.out.println(p + " " + q);
        }
        StdOut.println(wqu.count() + " components");
    }
}


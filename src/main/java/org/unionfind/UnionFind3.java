package org.unionfind;

/**
 * 第三版 Union-Find
 * 优化 quick-union
 * 将小树连接到大树上
 *
 * @author cheng
 *         2018/2/25 11:10
 */
public class UnionFind3 implements UF {
    /**
     * 表示第一个元素所指向的父结点
     */
    private int[] parent;
    /**
     * sz[i]表示以i为根的集合中的元素个数
     */
    private int[] sz;

    private int count;

    public UnionFind3(int count) {
        this.count = count;
        parent = new int[count];
        sz = new int[count];

        for (int i = 0; i < count; i++) {
            parent[i] = i;
            sz[i] = 1;
        }
    }

    /**
     * 查找过程，查找元素p所对应的集合编号
     * O(h)复杂度，h为树的高度
     */
    public int find(int p) {
        if (p < 0 || p >= count) {
            throw new IllegalArgumentException("Called find() with invalid argument: " + p + "!");
        }

        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
    }

    /**
     * 查看元素p和元素q是否所属于一个集合
     * O(h)复杂度，h为树的高度
     */
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * 合并元素p和元素q所属的集合
     * O(h)复杂度，h为树的高度
     */
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) return;

        // 根据两个元素所在树的元素个数不同判断合并方向
        // 将元素个数少的集合合并到元素个数多的集合上
        if (sz[pRoot] < sz[qRoot]) {
            parent[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        } else {
            parent[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
    }
}


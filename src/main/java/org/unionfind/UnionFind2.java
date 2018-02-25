package org.unionfind;

/**
 * 第二版 Union-Find
 * quick-union
 *
 * @author cheng
 *         2018/2/25 10:40
 */
public class UnionFind2 implements UF {
    /**
     * 表示第一个元素所指向的父结点
     */
    private int[] parent;

    private int count;

    public UnionFind2(int count) {
        this.count = count;
        parent = new int[count];

        for (int i = 0; i < count; i++) {
            parent[i] = i;
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

        parent[pRoot] = qRoot;
    }
}

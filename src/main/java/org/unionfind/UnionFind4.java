package org.unionfind;

/**
 * 第四版 Union-Find
 * 优化 quick-union
 * 将树低的连接到树高的
 *
 * @author cheng
 *         2018/2/25 11:10
 */
public class UnionFind4 implements UF {
    /**
     * 表示第一个元素所指向的父结点
     */
    private int[] parent;
    /**
     * rank[i]表示以i为根的集合中所表示的树的层数
     */
    private int[] rank;

    private int count;

    public UnionFind4(int count) {
        this.count = count;
        parent = new int[count];
        rank = new int[count];

        for (int i = 0; i < count; i++) {
            parent[i] = i;
            rank[i] = 1;
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

        // 根据两个元素所在集合是层数判断合并方向
        // 将树低的合并到树高的上
        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;
        } else if (rank[pRoot] > rank[qRoot]) {
            parent[qRoot] = pRoot;
        } else {
            // 此时树高一样，树高增加了 1
            parent[pRoot] = qRoot;
            rank[qRoot] += 1;
        }
    }
}


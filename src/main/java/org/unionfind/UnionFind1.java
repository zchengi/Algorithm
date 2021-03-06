package org.unionfind;

/**
 * 第一版 Union-Find
 * 本质上就是一个数组
 * quick-find
 *
 * @author cheng
 *         2018/2/24 12:34
 */
public class UnionFind1 implements UF {

    private int[] id;

    private int count;

    public UnionFind1(int count) {
        this.count = count;
        id = new int[count];
        // 初始化，每一个id[i]指向自己，没有合并的元素
        for (int i = 0; i < count; i++) {
            id[i] = i;
        }
    }

    /**
     * 查找过程，查找元素p所对应的集合编号
     * O(1)复杂度
     */
    public int find(int p) {
        if (p < 0 || p >= count) {
            throw new IllegalArgumentException("Called find() with invalid argument: " + p + "!");
        }

        return id[p];
    }

    /**
     * 查看元素p和元素q是否所属于一个集合
     * O(1)复杂度
     */
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * 合并元素p和元素q所属的集合
     * O(n)复杂度
     */
    public void unionElements(int p, int q) {
        int pID = find(p);
        int qID = find(q);

        if (pID == qID) return;

        // 合并过程需要遍历一遍所有元素，将两个元素的所属集合编号合并
        for (int i = 0; i < count; i++) {
            if (id[i] == pID) id[i] = qID;
        }
    }
}

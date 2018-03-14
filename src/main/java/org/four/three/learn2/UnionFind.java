package org.four.three.learn2;

/**
 * Union-Find
 *
 * @author cheng
 *         2018/3/14 21:52
 */
public class UnionFind {

    private int[] rank;
    private int[] parent;
    private int count;

    public UnionFind(int count) {
        rank = new int[count];
        parent = new int[count];
        this.count = count;

        for (int i = 0; i < count; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public void unionElements(int p, int q) {

        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) {
            return;
        }

        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;
        } else if (rank[qRoot] < rank[pRoot]) {
            parent[qRoot] = pRoot;
        } else {
            parent[pRoot] = qRoot;
            rank[pRoot]++;
        }
    }

    public int find(int p) {
        assert p >= 0 && p < count;

        // path compression 1
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }

        return p;
    }

    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }


}

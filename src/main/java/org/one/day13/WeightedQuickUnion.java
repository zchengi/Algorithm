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
    private int[] parent;
    /**
     * （由触点索引的）各个根节点所对应的分量的大小
     */
    private int[] size;
    /**
     * 连通分量的数量
     */
    private int count;

    public WeightedQuickUnion(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("连通分量数必须大于一！");
        }
        count = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int find(int p) {
        validate(p);
        // 跟随链接找到根节点
        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
    }

    // validate that p is a valid index
    private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n-1));
        }
    }

    public void union(int p, int q) {
        //先判断父链接根节点是否相同
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
            return;
        }
        // 如果不同，将小树的根节点连接到大树的根节点，更新分量大小
        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        } else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
        count--;
    }

    public static void main(String[] args) {
        In in = new In("src/main/java/org/one/day13/largeUF.txt");
        int n = in.readInt();
        WeightedQuickUnion wqu = new WeightedQuickUnion(n);
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


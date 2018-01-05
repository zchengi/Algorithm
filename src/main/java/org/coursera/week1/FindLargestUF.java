package org.coursera.week1;

import edu.princeton.cs.algs4.StdIn;

/**
 * @author cheng
 *         2018/1/5 20:48
 *         <p>
 *         Test2： Union-find with specific canonical element
 *         -题目：
 *         Add a method find() to the union-find data type so that find(i)
 *         returns the largest element in the connected component containing i.
 *         The operations, union(), connected(), and find() should all take logarithmic time or better.
 *         -分析：
 *         要求是find()方法查找的根是子集中最大的元素。
 *         用两个子集中较大的root作为合并后的root就可以了。
 */
public class FindLargestUF {

    private int[] parent;
    private int count;

    public FindLargestUF(int n) {
        count = n;
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int find(int p) {
        while (p != parent[p]) {
            p = parent[p];
        }
        //
        System.out.println(p);
        return p;
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        System.out.println("find(" + p + ")=" + rootP + ",find(" + q + ")=" + rootQ);
        if (rootP == rootQ) {
            return;
        } else if (rootP<rootQ) {
            parent[rootP] = rootQ;
        } else {
            parent[rootQ] = rootP;
        }
        count--;
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        FindLargestUF uf = new FindLargestUF(n);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q)) {
                continue;
            }
            uf.union(p, q);
            System.out.println("link points:" + p + " " + q);
        }
        System.out.println(uf.count+" components");
    }
}

package org.coursera.week1;

/**
 * @author cheng
 *         2018/1/5 21:21
 *         <p>
 *         Test3: Successor with delete
 *         -题目：
 *         Given a set of n integers S = {0,1,…,N-1}and a sequence of requests of the following form:
 *         ·Remove x from S(从S中删除x)
 *         ·Find the successor of x: the smallest y in S such thaty>=x(找到一个满足y>=x的最小的y)
 *         design a data type so that all operations(except construction)
 *         take logarithmic time or better in the worst case.
 *         -分析：
 *         题目要求有一个按0~n-1的顺序排列的S数组,从S中删除任意的x，然后调用getSuccessor(x)方法，
 *         返回一个y，这个y是剩余还在S中的满足y>=x的最小的数。
 *         实际上就是把所有remove的数做了union，root为子集中最大值，那么getSuccessor(x)方法
 *         实际就是获取remove数中的最大值+1。
 */
public class Successor {

    private int num;
    private int[] id;
    private boolean[] isRemove;

    public Successor(int n) {
        num = n;
        id = new int[n];
        isRemove = new boolean[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
            isRemove[i] = false;
        }
    }

    public int find(int p) {
        while (p != id[p]) {
            p = id[p];
        }
        return p;
    }

    public void union(int p, int q) {
        // 此处的union取较大的根
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
            return;
        } else if (rootP < rootQ) {
            id[rootP] = rootQ;
        } else {
            id[rootQ] = rootP;
        }
    }

    public void remove(int x) {
        isRemove[x] = true;
        // 判断相邻节点是否也被remove，如果是则union
        if (x > 0 && isRemove[x - 1]) {
            union(x, x - 1);
        }
        if (x < num - 1 && isRemove[x + 1]) {
            union(x, x + 1);
        }
    }

    public int getSuccessor(int x) {
        if (x < 0 || x > num - 1) {
            throw new IllegalArgumentException("访问越界！");
        } else if (isRemove[x]) {
            if (find(x) + 1 > num - 1) {
                // x以及大于x的数都被remove了，返回-1
                return -1;
            } else {
                // 所有remove数集中的最大值+1，就是Successor
                return find(x) + 1;
            }
        }
        // x未被remove，则返回自身
        return x;
    }

    public static void main(String[] args) {
        Successor successor = new Successor(10);
        successor.remove(2);
        System.out.println("the successor is: " + successor.getSuccessor(4));
        successor.remove(6);
        System.out.println("the successor is: " + successor.getSuccessor(6));
        successor.remove(5);
        System.out.println("the successor is: " + successor.getSuccessor(5));
        successor.remove(9);
        System.out.println("the successor is: " + successor.getSuccessor(9));
    }
}

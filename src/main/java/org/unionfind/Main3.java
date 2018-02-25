package org.unionfind;

/**
 * 迭代和递归实现两种路径压缩的区别
 *
 * @author cheng
 *         2018/2/25 12:08
 */

public class Main3 {
    public static void main(String[] args) {
        // 为了能够方便地看出两种路径压缩算法的不同，这里只使用5个元素的并查集进行实验
        int n = 5;

        UnionFind5 uf5 = new UnionFind5(n);
        UnionFind6 uf6 = new UnionFind6(n);

        // 将并查集初始设置为如下情况
        //            0
        //           /
        //          1
        //         /
        //        2
        //       /
        //      3
        //     /
        //    4
        for (int i = 1; i < n; i++) {
            uf5.parent[i] = i - 1;
            uf6.parent[i] = i - 1;
        }

        // 然后，对两个并查集调用find(4)操作
        uf5.find(n - 1);
        uf6.find(n - 1);

        // 通过show, 可以看出, 使用迭代的路径压缩, 我们的并查集变成这个样子:
        //     0
        //    / \
        //   1   2
        //      / \
        //     3   4
        System.out.println("UF implements Path Compression by recursion: ");
        uf5.show();

        System.out.println();

        // 使用递归的路径压缩, 我们的并查集变成这个样子:
        //     0
        //  / / \ \
        // 1 2   3 4
        System.out.println("UF implements Path Compression without recursion:");
        uf6.show();

    }
}

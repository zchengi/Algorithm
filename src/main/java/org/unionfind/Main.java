package org.unionfind;

/**
 * 测试类
 *
 * @author cheng
 *         2018/2/24 14:32
 */
public class Main {
    public static void main(String[] args) {
        int n = 1000000;

        // 虽然isConnected()只需要O(1)的时间，但由于union()操作需要O(n)的时间
        // 所以总体测试过程的算法复杂度是O(n^2)的
        // UnionFindTestHelper.testUF(new UnionFind1(n), n);

        // 对于UF2来说, 其时间性能是O(n*h)的, h为并查集表达的树的最大高度
        // 这里严格来讲, h和logn没有关系, 不过大家可以简单这么理解
        // 我们后续内容会对h进行优化, 总体而言, 这个h是远小于n的
        // 所以我们实现的UF2测试结果远远好于UF1, n越大越明显
        // UnionFindTestHelper.testUF(new UnionFind2(n), n);

        // 对于UF3来说，其时间性能依然是O(n*h)的，h为并查集表达的树的最大高度
        // 但由于UF3能更高概率的保证树的平衡，所以性能更优
        UnionFindTestHelper.testUF(new UnionFind3(n), n);

        // UF4虽然相对UF3进行了优化，但优化的地方出现的情况较少，
        // 所以性能更优表现的不明显，甚至在一些数据下性能会更差
        UnionFindTestHelper.testUF(new UnionFind4(n), n);

        // UF5使用路径压缩优化UF4，两种方式：1：迭代；2：递归
        UnionFindTestHelper.testUF(new UnionFind5(n), n);
    }
}

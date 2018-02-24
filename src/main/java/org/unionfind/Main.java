package org.unionfind;

/**
 * 测试类
 *
 * @author cheng
 *         2018/2/24 14:32
 */
public class Main {
    public static void main(String[] args) {
        int n = 10000;

        // 虽然isConnected()只需要O(1)的时间，但由于union()操作需要O(n)的时间
        // 所以总体测试过程的算法复杂度是O(n^2)的
        UnionFindTestHelper.testUF1(n);
    }
}

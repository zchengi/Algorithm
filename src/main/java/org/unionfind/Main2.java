package org.unionfind;

/**
 * 使用相同的测试数据测试UF的执行效率
 * <p>
 * 对比UF1,UF2,UF3,UF4,UF5,UF6的时间性能
 *
 * @author cheng
 *         2018/2/25 11:59
 */
public class Main2 {
    public static void main(String[] args) {
        int n = 5000000;

        // 生成unionElements的测试用例
        Pair<Integer, Integer>[] unionTest = new Pair[n];
        for (int i = 0; i < n; i++) {
            int a = (int) (Math.random() * n);
            int b = (int) (Math.random() * n);
            unionTest[i] = new Pair<>(a, b);
        }

        // 生成isConnected的测试用例
        Pair<Integer, Integer>[] connectTest = new Pair[n];
        for (int i = 0; i < n; i++) {
            int a = (int) (Math.random() * n);
            int b = (int) (Math.random() * n);
            connectTest[i] = new Pair<>(a, b);
        }

        // 测试 UF1 ~ UF6

        // 百万数据对于UF1和UF2来说太慢了，不测试

        // UnionFindTestHelper.testUF2(new UnionFind1(n),unionTest,connectTest);

        // UnionFindTestHelper.testUF2(new UnionFind2(n),unionTest,connectTest);

        UnionFindTestHelper.testUF2(new UnionFind3(n), unionTest, connectTest);

        UnionFindTestHelper.testUF2(new UnionFind4(n), unionTest, connectTest);

        UnionFindTestHelper.testUF2(new UnionFind5(n), unionTest, connectTest);

        UnionFindTestHelper.testUF2(new UnionFind6(n), unionTest, connectTest);
    }
    /*
     *
     * UnionFind3 with 5000000 unionElements ops, 5000000 isConnected ops, 1514ms
     * UnionFind4 with 5000000 unionElements ops, 5000000 isConnected ops, 1627ms
     * UnionFind5 with 5000000 unionElements ops, 5000000 isConnected ops, 1137ms
     * UnionFind6 with 5000000 unionElements ops, 5000000 isConnected ops, 1636ms
     *
     */
}

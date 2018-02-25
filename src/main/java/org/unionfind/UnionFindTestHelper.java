package org.unionfind;

/**
 * 测试并查集
 *
 * @author cheng
 *         2018/2/24 14:31
 */
public class UnionFindTestHelper {

    /**
     * 测试并查集，测试元素个数为n
     */
    public static void testUF(UF uf, int n) {

        long startTime = System.currentTimeMillis();

        // 进行n次操作，每次随机选择两个元素进行合并操作
        for (int i = 0; i < n; i++) {
            int a = (int) (Math.random() * n);
            int b = (int) (Math.random() * n);
            uf.unionElements(a, b);
        }

        // 进行n次操作，每次随机选择两个元素，查询他们是否同属于一个集合
        for (int i = 0; i < n; i++) {
            int a = (int) (Math.random() * n);
            int b = (int) (Math.random() * n);
            uf.isConnected(a, b);
        }

        long endTime = System.currentTimeMillis();

        // 打印输出对这个2n个操作的耗时
        System.out.println(uf.getClass().getSimpleName() + ", "
                + 2 * n + " ops, " + (endTime - startTime) + "ms");
    }

    /**
     * 使用相同的测试数据测试UF的执行效率
     */
    public static void testUF2(UF uf, Pair<Integer, Integer>[] unionTest, Pair<Integer, Integer>[] connectTest) {

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < unionTest.length; i++) {
            int a = unionTest[i].a();
            int b = unionTest[i].b();
            uf.unionElements(a, b);
        }

        for (int i = 0; i < connectTest.length; i++) {
            int a = connectTest[i].a();
            int b = connectTest[i].b();
            uf.isConnected(a, b);
        }

        long endTime = System.currentTimeMillis();

        System.out.println(uf.getClass().getSimpleName() + " with " + unionTest.length + " unionElements ops, "
                + connectTest.length + " isConnected ops, "
                + (endTime - startTime) + "ms");
    }
}

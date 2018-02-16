package org.three.two.exercises;

/**
 * 3.2.8 编写一个optCompares(),接受一个整型参数N并计算一颗最优（完美二叉树）
 * 二叉查找树中的一次随机查找命中平均所需的比较次数，如果树中的链接数量为2的幂，
 * 那么所有链接都应该在同一层，否则则分布在最底部的两层中。
 *
 * @author cheng
 *         2018/2/16 13:05
 */
public class Exercise8 {
    public static int optCompares(int n) {
        // 计算有多少层
        int sum = 0;
        int levels = 0;
        while (true) {
            sum += 1 << levels;
            if (sum > n) break;
            levels++;
        }
        sum -= 1 << levels;
        System.out.println(levels + " " + sum);

        // 计算内部路径长度
        int internal = 0;
        for (int i = 0; i < levels; i++) {
            internal += i * (1 << i);
        }
        internal += (n - sum) * levels;
        return internal / n;
    }

    public static void main(String[] args) {
        int n = 100;
        for (int i = 2; i < n; i++) {
            System.out.println("规模为 " + i + " 的满二叉树随机查找命中的平均比较次数为： " + optCompares(n));
        }
    }
}

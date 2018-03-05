package org.three.three.exercises;

/**
 * 3.3.20 计算一棵大小为 N 且完美平衡的二叉查找树的内部路径长度，其中N为2的幂减1。
 *
 * @author cheng
 *         2018/3/5 14:46
 */
public class Exercise20 {
    public static int ipl(int n) {
        int h = (int) (Math.log(n + 1) / Math.log(2));
        int sum = 0;
        for (int i = 0; i < h; i++) {
            sum += i * (1 << i);
        }
        return sum;
    }

    public static void main(String[] args) {
        int n = 63;
        System.out.println("大小为： " + n + " 内部路径长度：" + ipl(n));
    }
}

package org.two.three.exercises;

/**
 * 2.3.13 在最佳、平均和最坏情况下，快速排序的递归深度分别是多少？
 * 这决定了系统为了追踪递归所需的栈的大小。在最坏情况下保证递归深度
 * 为数组大小的对数级别的方法请见联系2.3.20。
 *
 * @author cheng
 *         2018/1/25 23:16
 */
public class Exercise13 {

    /*
     * 最坏情况是 N - 1
     * 比如对于升序序列，降序序列来说，每次切分都会得到一个大小为 0 的数组，一个大小为 N-1 的数组
     *
     * 最佳情况是logN
     *
     * 比如所有元素都相同的情况下，每次切分差不多刚好分割成相等长度的两个子数组
     * 所以递归深度约等于 logN
     *
     * 平均情况是 NlogN
     */

}
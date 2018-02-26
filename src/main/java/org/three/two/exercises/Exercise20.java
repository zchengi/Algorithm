package org.three.two.exercises;

/**
 * 3.2.20 请证明：对于含有N个结点的二叉查找树，接受两个参数的size()方法所需的运行时间最多为
 * 树高的倍数加上查找范围内的键的数量。
 *
 * @author cheng
 *         2018/2/26 20:05
 */
public class Exercise20 {
    /*
     *
     * rank(K lo) 方法只会自根结点向lo结点靠拢，访问的结点不会小于lo
     * rank(K hi) 方法只会自根结点向hi结点靠拢，访问的结点不会大于hi
     *
     * 因为 rank(k lo) 和 rank(k hi) 两次方法调用了 lo ~ hi 内的所有结点
     *
     * 同时在访问过程中，rank() 的递归调用深度最多不高过树高，
     * 所以size(K lo, K hi) 方法所需运行时间最多为树高的倍数加上查找范围内的键的数量。
     *
     */
}

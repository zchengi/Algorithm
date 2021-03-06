package org.two.four.exercises;

/**
 * 2.4.20 证明：基于下沉的堆构造方法使用的比较次数小于2N，交换次数小于N。
 *
 * @author cheng
 *         2018/2/2 21:00
 */
public class Exercise20 {
    /*
     *
     * 只需证明基于下沉的堆的交换次数为 n即可，因为比较的次数最多是交换的两倍。
     * 假设有一个完全二叉堆，高度为h：
     *
     * 我们将树中结点的高度定位为以该结点为根的子树的高度;一个高度为 k 的键在下沉时最多与下面的 k 个键交换;
     * 由于在高度 k 处有 2h - k 个结点，所以交换的总数是：
     *
     * h+2(h-1)+4(h-2)+8(h-3)+...+2^h(0) = 2^(h+1)-h-2 = n-(h+1) <= n
     *
     * 因为比较次数是交换次数的两倍（在交换前会进行 less(j, j + 1) 和 less(k, j) 两次比较）
     * 所以最坏情况下比较次数少于 2N
     *
     */
}

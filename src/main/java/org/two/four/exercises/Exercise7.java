package org.two.four.exercises;

/**
 * 2.4.7 在堆中，最大的元素一定在位置1上，第二大的元素一定在位置2或者3上。
 * 对于一个大小为31的堆，给出第k大的元素可能出现的位置和不可能出现的位置，
 * 其中k = 2、3、4(设元素值不重复) 。
 *
 * @author cheng
 *         2018/1/31 17:42
 */
public class Exercise7 {
    /*
     * 脑补一棵二叉树的图
     *
     * k = 2 第二大的位置只可能出现在索引为 2, 3 的位置上，其他位置都不可能出现
     * k = 3 第三大的位置可能出现在第二层的 2, 3 结点中，也可能作为第二大结点的子结点出现在第三层，但不可能出现在第四层以及往后的位置
     * k = 4 第四大可能作为根结点的子结点出现在第二层，也可能作为第三大或者第二大的子结点出现在第三层，也可能作为第三大的子结点出现在第四层，但不可能出现在第五层以及往后的位置
     *
     *
     * 综上所述，第二大可能出现的位置是 [2, 3]
     * 第三大可能出现的位置是 [2, 7]
     * 第四大可能出现的位置是 [2, 15]
     */
}

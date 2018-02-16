package org.three.two.exercises;

/**
 * 3.2.4 假设某颗二叉查找树的所有键均为1至10的整数，而我们要查找5。
 * 以下哪个不可能是键的检查序列？
 *
 * @author cheng
 *         2018/2/15 15:09
 */
public class Exercise4 {
    /*
     * 检查序列：从首结点开始，判断与 当前根结点 的大小，小于 在左子树查找，大于 在右子树查找，等于 已经找到；
     *
     *
     * a. 10，9，8，7，6，5              可能；
     * b. 4，10，8，7，5，3              不可能，因为查找到 5 之后，不可能再查找 3；
     * c. 1，10，2，9，3，8，4，7，6，5   可能；
     * d. 2，7，3，8，4，5               不可能，因为查到 7 的时候， 5 < 7 检查 7 的左子树，而 8 是 7 的右子树，不可能检查到；
     * e. 1，2，10，4，8，5              可能；
     *
     */
}

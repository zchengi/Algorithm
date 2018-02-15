package org.three.two.exercises;

/**
 * 3.2.3 给出 A X C S E R H 的五种能够构造出最优二叉查找树的排列。
 *
 * @author cheng
 *         2018/2/15 13:55
 */
public class Exercise3 {
    /*
     *
     * A X C S E R H 共7个结点，最优情况是三层满二叉树；
     *
     * 因为根节点左右必须各为3个结点，所以根节点必定是 H；
     * 根节点的子结点必须是 C 和 S，才能构造出三层满二叉树；
     *
     * 所以排列情况如下：
     *
     * H C E A S R X
     * H C E A S X R
     * H C A E S R X
     * H C A E S X R
     * H S R X C A E
     * H S R X C E A
     * H S X R C A E
     * H S X R C E A
     *
     */
}

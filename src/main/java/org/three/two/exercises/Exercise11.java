package org.three.two.exercises;

/**
 * 3.2.11 高度为N且含有N个结点的二叉树能有多少种形状？
 * 使用N个不同的键能有多少种不同的方式构造一颗高度为N的二叉查找树？（参考练习3.2.2）
 *
 * @author cheng
 *         2018/2/26 11:58
 */
public class Exercise11 {
    /*
     *
     * 显然每一层只能有一个结点，因为高度为N且含有N个结点；
     *
     * 因为第一层是唯一的，下面n-1层的每一层都有左右孩子两种情况；
     *
     * 一共 n-1 个结点，就是 2^(n-1) 种形状；
     *
     */
}

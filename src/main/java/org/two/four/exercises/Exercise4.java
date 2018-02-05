package org.two.four.exercises;

/**
 * 2.4.4 一个按降序排列的数组也是一个面向最大元素的堆吗？
 *
 * @author cheng
 *         2018/1/31 17:12
 */
public class Exercise4 {

    /*
     * 显然是最大堆。
     * 满足每个结点都小于它的父结点；
     *                 10
     *          9               8
     *      7       6       5       4
     *    3   2       1
     *
     * 可以看出，延每个叶结点从底向上都是一条非递减的路径；
     *
     */
}
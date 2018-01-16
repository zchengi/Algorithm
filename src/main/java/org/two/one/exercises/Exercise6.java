package org.two.one.exercises;

/**
 * 2.1.6 在所有主键都相同时，选择排序和插入排序谁更快？
 *
 * @author cheng
 *         2018/1/16 20:52
 */
public class Exercise6 {
    // 插入排序更快
    /*
     * 选择排序无论如何都需要n+(n-1)+(n-2)+...+1=n^2/2次比较
     * 插入排序在这种情况下只需要进行n次比较。（所有主键都相同==数组已排序）
     */
}

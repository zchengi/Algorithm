package org.two.five.exercises;

/**
 * 2.5.5 说明为什么选择排序是不稳定的。
 *
 * @author cheng
 *         2018/2/1 19:24
 */
public class Exercise5 {

    /*
     * 稳定性是指排序算法对于相等的元素，在排序后，原来靠前的元素依然靠前；
     * 相对元素的相对位置没有改变；
     *
     *
     * 因为选择排序每次循环找出当前的最小值，然后与最前面的值交换；
     * 很有可能破坏相对位置已经有序元素，即破坏稳定性，如：
     *
     *
     * 对于 A1 B1 B2 A2  (1,2只是标记，元素为 A B)
     *
     * 选择排序后： A1 A2 B2 B1
     *
     * 显然，破坏了稳定性;
     */
}
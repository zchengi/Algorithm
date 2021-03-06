package org.two.three.exercises;

/**
 * 2.3.15 螺丝和螺帽。（G.J.E.Rawlins）假设有N个螺丝和N个螺帽混在一堆，你需要快速将它们配对。
 * 一个螺丝只会匹配一个螺帽，一个螺帽也只会匹配应该螺丝。你可以试着把一个螺丝和应该螺帽拧在一起
 * 看看谁大了，但不能直接比较两个螺丝或者两个螺帽。给出一个解决这个问题的有效方法。
 *
 * @author cheng
 *         2018/1/26 16:09
 */
public class Exercise15 {
    /*
     * 假设螺丝是 A0 A1 A2 ...
     * 假设螺帽是 B0 B1 B2 ...
     *
     * step1：把螺丝螺帽分成两堆,如：
     * 有 A8 A2 A5 A6 A4 A2 A1 A3 A9 A0
     * 有 B1 B8 B0 B3 B5 B2 B9 B0 B1 B7
     *
     * 现在取出螺丝A8，将螺帽分为大于小于A8的两堆，同时找出了B8
     *
     * A8 与 B8 配对成
     *
     * 此时螺帽成为如下两堆：
     * B1 B0 B3 B5 B2 B0 B1 B7    B9
     * 此时用找出来的 B8 把螺丝分成大于和小于 B8 的两堆
     * A2 A5 A6 A4 A2 A1 A3 A0    A9
     *
     * A9 和 B9 配对成功
     *
     * 取出螺丝A2，将螺帽分成大于和小于A2的两堆，同时找出了B2
     *
     * A2 和 B2 配对成功
     *
     * B1 B0     B3 B5 B0 B1 B7
     * 此时用找出来的 B2 把螺丝分成大于和小于 B2 的两堆
     * A0 A1     A5 A6 A4 A2 A3
     *
     *
     * 重复上述步骤，直到得到所有匹配
     */
}

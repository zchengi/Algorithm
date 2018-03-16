package org.four.three.exercises;

/**
 * 4.3.15 给定一副加权图G以及它的最小生成树。向G中添加一条边e，
 * 如何在与V成正比的时间内找到新图的最小生成树。
 *
 * @author cheng
 *         2018/3/16 14:13
 */
public class Exercise15 {
    /*
     *
     * 1.先判断这条边是否小于最小生成树中的最大边，
     * 如果不小于则旧的最小生成树就是新的最小生成树；
     * 否则删除连接这条边的两个顶点，
     * 然后再分别在两个顶点所相连的边中找到权重最小的边加入最小生成树。
     *
     * 2.添加一条边e，必定形成一个环，找出这个环中的最大边，然后删除，即可。
     *
     */
}

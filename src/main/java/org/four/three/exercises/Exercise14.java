package org.four.three.exercises;

/**
 * 4.3.14 给出一副加权图G以及它的最小生成树。从G中删去一条边且G仍然是连通的，
 * 如何在与E成正比的时间内找到新图的最小生成树。
 *
 * @author cheng
 *         2018/3/16 14:13
 */
public class Exercise14 {
    /*
     *
     * 如果这条边不属于最小生成树的边，那么旧的最小生成树就是新的最小生成树；
     * 否则，从最小生成树中删除连接这条变的两个顶点，
     * 然后再分别在两个顶点所相连的边中找到权重最小的边加入最小生成树。
     *
     */
}

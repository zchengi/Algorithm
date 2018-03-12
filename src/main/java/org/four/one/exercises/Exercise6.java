package org.four.one.exercises;

import org.four.one.learn.Graph;

/**
 * 4.1.6 有一张含有四个顶点的图，其中的边为 0-1、 1-2 、 2-3 和 3-0。给出一种邻接表数组，
 * 无论以任何顺序调用addEdge()来添加这些边都无法创建它。
 *
 * @author cheng
 *         2018/3/12 10:37
 */
public class Exercise6 {
    /*
     *
     * 四个顶点：0，1，2，3；四条边 0-1、 1-2 、 2-3 和 3-0；
     * 邻接表数组： 见 FlowChart 的 4.1.6.jpg
     *
     */
    public static void main(String[] args) {

        int V = 4;
        int E = 4;
        Graph G = new Graph(4);
        
        // 给出的邻接表数组
        G.addEdge(0, 1);
        G.addEdge(0, 2);
        G.addEdge(1, 2);
        System.out.println(G);
    }
}

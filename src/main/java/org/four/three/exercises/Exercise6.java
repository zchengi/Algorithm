package org.four.three.exercises;

import org.four.three.learn.Edge;
import org.four.three.learn.EdgeWeightGraph;
import org.four.three.learn.KruskalMST;

/**
 * 4.3.6  从tinyEWG.txt中（请见图4.3.1）删去顶点7并给出加权图的最小生成树。
 *
 * @author cheng
 *         2018/3/16 13:26
 */
public class Exercise6 {
    /*
     *
     * 删去顶点7也意味着删去了与顶点7相连的边
     * 加权图变为：
     * 7
     * 11
     * 4 5 0.35
     * 1 5 0.32
     * 0 4 0.38
     * 2 3 0.17
     * 0 2 0.26
     * 1 2 0.36
     * 1 3 0.29
     * 6 2 0.40
     * 3 6 0.52
     * 6 0 0.58
     * 6 4 0.93
     *
     */
    public static void main(String[] args) {

        String filename = "src/main/java/org/four/three/text/exercise6.txt";
        EdgeWeightGraph G = new EdgeWeightGraph(filename);
        KruskalMST mst = new KruskalMST(G);

        for (Edge e : mst.edges()) {
            System.out.println(e);
        }
        System.out.printf("%.5f\n", mst.weight());
    }
}

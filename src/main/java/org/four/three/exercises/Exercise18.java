package org.four.three.exercises;

import org.four.three.learn.Edge;
import org.four.three.learn.EdgeWeightGraph;
import org.four.three.learn.KruskalMST;

/**
 * 4.3.18 给出使用延时Prim算法、即时Prim算法和Kruskal算法在计算练习4.3.6中的图的最小生成树过程中的轨迹。
 * <p>
 * 轨迹略 ··· ···
 *
 * @author cheng
 *         2018/3/16 14:27
 */
public class Exercise18 {
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

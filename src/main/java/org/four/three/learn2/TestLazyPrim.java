package org.four.three.learn2;

import java.util.Vector;

/**
 * 测试 Lazy Prim 最小生成树算法
 *
 * @author cheng
 *         2018/3/14 17:02
 */
public class TestLazyPrim {
    public static void main(String[] args) {

        String filename = "src/main/java/org/four/three/text/testG1.txt";
        int V = 8;

        SparseWeightedGraph<Double> g = new SparseWeightedGraph<>(V, false);
        ReadWeightedGraph readGraph = new ReadWeightedGraph(g, filename);

        // Test Lazy Prim MST
        System.out.println("Test Lazy Prim MST: ");
        LazyPrimMST<Double> lazyPrimMST = new LazyPrimMST<Double>(g);
        Vector<Edge<Double>> mst = lazyPrimMST.mstEdges();
        for (int i = 0; i < mst.size(); i++) {
            System.out.println(mst.elementAt(i));
        }
        System.out.println("The MST weight is: " + lazyPrimMST.result());
    }
}

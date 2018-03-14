package org.four.three.learn2;

/**
 * 比较Lazy Prim, Prim和Kruskal的时间性能
 *
 * -可以看出使用 索引堆实现的Prim 算法优于 Lazy Prim 算法
 *
 * -相比 Kruskal算法，Prim算法性能更优一点
 * -但是由于 Kruskal算法实现简单，所以对于一些小的图也可以用于快速实现
 * @author cheng
 *         2018/3/14 21:33
 */
public class CompareMST {
    public static void main(String[] args) {

        String filename1 = "src/main/java/org/four/three/text/testG1.txt";
        int V1 = 8;

        String filename2 = "src/main/java/org/four/three/text/testG2.txt";
        int V2 = 250;

        String filename3 = "src/main/java/org/four/three/text/testG3.txt";
        int V3 = 1000;

        String filename4 = "src/main/java/org/four/three/text/testG4.txt";
        int V4 = 10000;

        // 文件读取
        SparseWeightedGraph<Double> g1 = new SparseWeightedGraph<Double>(V1, false);
        ReadWeightedGraph readGraph1 = new ReadWeightedGraph(g1, filename1);
        System.out.println(filename1 + " load successfully.");

        SparseWeightedGraph<Double> g2 = new SparseWeightedGraph<Double>(V2, false);
        ReadWeightedGraph readGraph2 = new ReadWeightedGraph(g2, filename2);
        System.out.println(filename2 + " load successfully.");

        SparseWeightedGraph<Double> g3 = new SparseWeightedGraph<Double>(V3, false);
        ReadWeightedGraph readGraph3 = new ReadWeightedGraph(g3, filename3);
        System.out.println(filename3 + " load successfully.");

        SparseWeightedGraph<Double> g4 = new SparseWeightedGraph<Double>(V4, false);
        ReadWeightedGraph readGraph4 = new ReadWeightedGraph(g4, filename4);
        System.out.println(filename4 + " load successfully.");

        System.out.println();

        long startTime, endTime;

        // Test Lazy Prim MST
        System.out.println("Test Lazy Prim MST:");

        startTime = System.currentTimeMillis();
        LazyPrimMST<Double> lazyPrimMST1 = new LazyPrimMST<Double>(g1);
        endTime = System.currentTimeMillis();
        System.out.println("Test for G1: " + (endTime - startTime) + "ms.");

        startTime = System.currentTimeMillis();
        LazyPrimMST<Double> lazyPrimMST2 = new LazyPrimMST<Double>(g2);
        endTime = System.currentTimeMillis();
        System.out.println("Test for G2: " + (endTime - startTime) + "ms.");

        startTime = System.currentTimeMillis();
        LazyPrimMST<Double> lazyPrimMST3 = new LazyPrimMST<Double>(g3);
        endTime = System.currentTimeMillis();
        System.out.println("Test for G3: " + (endTime - startTime) + "ms.");

        startTime = System.currentTimeMillis();
        LazyPrimMST<Double> lazyPrimMST4 = new LazyPrimMST<Double>(g4);
        endTime = System.currentTimeMillis();
        System.out.println("Test for G4: " + (endTime - startTime) + "ms.");

        System.out.println();

        // Test Prim MST
        System.out.println("Test Prim MST:");

        startTime = System.currentTimeMillis();
        PrimMST<Double> primMST1 = new PrimMST<Double>(g1);
        endTime = System.currentTimeMillis();
        System.out.println("Test for G1: " + (endTime - startTime) + "ms.");

        startTime = System.currentTimeMillis();
        PrimMST<Double> primMST2 = new PrimMST<Double>(g2);
        endTime = System.currentTimeMillis();
        System.out.println("Test for G2: " + (endTime - startTime) + "ms.");

        startTime = System.currentTimeMillis();
        PrimMST<Double> primMST3 = new PrimMST<Double>(g3);
        endTime = System.currentTimeMillis();
        System.out.println("Test for G3: " + (endTime - startTime) + "ms.");

        startTime = System.currentTimeMillis();
        PrimMST<Double> primMST4 = new PrimMST<Double>(g4);
        endTime = System.currentTimeMillis();
        System.out.println("Test for G4: " + (endTime - startTime) + "ms.");

        System.out.println();

        // Test Kruskal MST
        System.out.println("Test Kruskal MST");

        startTime = System.currentTimeMillis();
        KruskalMST<Double> kruskalMST1 = new KruskalMST<Double>(g1);
        endTime = System.currentTimeMillis();
        System.out.println("Test for G1: " + (endTime - startTime) + "ms.");

        startTime = System.currentTimeMillis();
        KruskalMST<Double> kruskalMST2 = new KruskalMST<Double>(g2);
        endTime = System.currentTimeMillis();
        System.out.println("Test for G2: " + (endTime - startTime) + "ms.");

        startTime = System.currentTimeMillis();
        KruskalMST<Double> kruskalMST3 = new KruskalMST<Double>(g3);
        endTime = System.currentTimeMillis();
        System.out.println("Test for G3: " + (endTime - startTime) + "ms.");

        startTime = System.currentTimeMillis();
        KruskalMST<Double> kruskalMST4 = new KruskalMST<Double>(g4);
        endTime = System.currentTimeMillis();
        System.out.println("Test for G4: " + (endTime - startTime) + "ms.");
        /*
        *
        * Test Lazy Prim MST:
        * Test for G1: 2ms.
        * Test for G2: 17ms.
        * Test for G3: 56ms.
        * Test for G4: 4123ms.
        *
        * Test Prim MST:
        * Test for G1: 2ms.
        * Test for G2: 1ms.
        * Test for G3: 10ms.
        * Test for G4: 29ms.
        *
        *
        * Test Kruskal MST
        * Test for G1: 1ms.
        * Test for G2: 2ms.
        * Test for G3: 14ms.
        * Test for G4: 58ms.
        *
        */
    }
}

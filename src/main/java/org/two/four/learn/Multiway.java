package org.two.four.learn;

import edu.princeton.cs.algs4.In;

/**
 * 2.4.4.7 使用优先队列的多向归并
 *
 * @author cheng
 *         2018/1/29 19:13
 */
public class Multiway {
    public static void merge(In[] streams) {
        int n = streams.length;
        IndexMinPQ<String> pq = new IndexMinPQ<>(n);
        System.out.println(n);
        // 从三个输入流中分别读取一个字符串
        for (int i = 0; i < n; i++) {
            if (!streams[i].isEmpty()) {
                pq.insert(i, streams[i].readString());
            }
        }

        // 每次运行打印并输出pq中最小的字符串，然后在此索引添加新的字符串，直到所有输入流为空
        while (!pq.isEmpty()) {
            // 打印最小字符串
            System.out.print(pq.minKey() + " ");

            // i的取值只能是 [0,n-1]
            int i = pq.delMin();

            // 判断当前输入流是否为空，如果不为空，将该字符添加到刚刚删除的索引位置。
            if (!streams[i].isEmpty()) {
                pq.insert(i, streams[i].readString());
            }
        }
    }

    public static void main(String[] args) {
        int n = args.length;
        In[] streams = new In[n];
        for (int i = 0; i < n; i++) {
            streams[i] = new In(args[i]);
        }
        merge(streams);

        // java Multiway m1.txt m2.txt m3.txt
        // 或者在idea运行设置中添加参数：
        /*
         * src\main\java\org\two\four\learn\m1.txt
         * src\main\java\org\two\four\learn\m2.txt
         * src\main\java\org\two\four\learn\m3.txt
         */

        // A B C F G I I Z
        // B D H P Q Q
        // A B E F J N
    }
}

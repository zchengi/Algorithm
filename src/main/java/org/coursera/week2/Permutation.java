package org.coursera.week2;

import edu.princeton.cs.algs4.StdIn;

/**
 * @author cheng
 *         2018/1/9 16:33
 *         <p>
 *         Programming Assignment 2: Deques and Randomized Queues
 *         题目3：排列组合类Permutation
 *         -分析：
 *         要做的是读取一个k，并且从加载的输入文件内容中选取k个String进行展示，故采用RandomizedQueue。
 *         将文件中的所有stirng放入RandomizedQueue中其size为n，如果只放k个string的话，randomizedQueue
 *         的size就会是k，目前只实现了size==n。size=k的方法未实现。
 */
public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        //8 AA BB BB BB BB BB CC CC
        int k = StdIn.readInt();
        while (!StdIn.isEmpty()) {
            rq.enqueue(StdIn.readString());
        }
        if (k < rq.size()) {
            while (k > 0) {
                System.out.println(rq.dequeue());
                k--;
            }
        } else {
            System.out.println("需要太多的输出!");
        }
    }
}

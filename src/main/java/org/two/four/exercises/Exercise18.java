package org.two.four.exercises;

import org.two.four.learn.MaxPQ;

/**
 * 2.4 18 在MaxPQ中，如果一个用例使用 insert() 插入了一个比队列中所有元素都大的新元素，
 * 随后立即调用delMax()。假设没有重复元素，此时的堆进和进行这些操作之前的堆完全相同吗？
 * 进行两次 insert() 操作接两次delMax操作呢？（第一次插入一个比队列中所有元素都大的元素，
 * 第二次插入一个更大的元素）
 *
 *
 *
 * @author cheng
 *         2018/2/2 20:40
 */
public class Exercise18 {
    /*
     *
     * 插入一次和删除一次操作后的堆和进行操作之前的堆完全相同；
     * 插入最大元素会改变堆的顺序，删除会还原堆的位置，举例如下：
     *
     * 初始堆：   3        插入最大元素后的堆：     4        删除最大元素后的堆：      3
     *        2      1                        3       1                         2      1  （1和2的位置没有改变）
     *                                    2
     *
     *
     * 先插入两次再删除两次同理，优先队列也不会改变位置；
     *
     */

    public static void main(String[] args) {
        MaxPQ<Integer> maxPQ = new MaxPQ<>(12);
        maxPQ.insert(11);
        maxPQ.insert(10);
        maxPQ.insert(9);
        maxPQ.insert(8);
        maxPQ.insert(7);
        maxPQ.insert(6);
        maxPQ.insert(5);
        maxPQ.insert(4);
        maxPQ.insert(3);
        maxPQ.insert(2);
        maxPQ.insert(1);
        maxPQ.insert(12);
        maxPQ.insert(13);
        maxPQ.delMax();
        maxPQ.delMax();
    }
}

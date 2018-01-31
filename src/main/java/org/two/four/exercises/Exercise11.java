package org.two.four.exercises;

/**
 * 2.4.11 如果你的应用场景中有大量的插入元素的操作，但只有若干删除最大元素操作，
 * 哪种优先队列的实现方法更有效：堆、无序数组、有序数组？
 *
 * @author cheng
 *         2018/1/31 18:26
 */
public class Exercise11 {
    /*
     * 数据结构   插入元素    删除最大元素
     * 有序数组     O(n)         O(1)
     * 无序数组     O(1)         O(n)
     *   堆       O(logn)      O(logn)
     *
     * 由图显然得出结论：
     * 有大量的插入元素的操作，但只有若干删除最大元素操作：
     *      无序数组最有效，插入元素的时间是恒定的；
     *      Unordered array. Insert is constant time.
     * 有大量的找出最大元素的操作，但插入元素和删除最大元素操作相对较少：
     *      有序数组最有效，找出最大值的时间是恒定的；
     *      Ordered array. Find the maximum is constant time.
     */
}

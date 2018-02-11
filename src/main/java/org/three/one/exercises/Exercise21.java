package org.three.one.exercises;

/**
 * 3.1.21 内存使用。基于1.4节中的假设，对于N对键值比较BinarySearchST和SequentialSearchST的内存使用情况。
 * 不需要记录键值本身用的内存，只统计它们的引用。对于BinarySearchST，假设数组大小可以动态调整，
 * 数组中被占用的空间比例为 25% ~ 100%。
 *
 * @author cheng
 *         2018/2/11 23:03
 */
public class Exercise21 {
    /*
     *
     * SequentialSearchST：
     * key + value + 对象 = 8 + 8 + 16 = 32 字节
     * 即对于 N 对键值对，使用了 32N 字节的内存；
     *
     * BinarySearchST：
     * key + value + 对象 + 数组扩容 = 8 + 8 + 16 + (16N ~ 64N) = (16N + 32) ~ (64N + 32) 字节
     * 即对于 N 对键值对，使用了 (16N + 32) ~ (64N + 32) 字节的内存
     *
     */
}

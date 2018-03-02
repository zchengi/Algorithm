package org.three.two.exercises;

/**
 * 3.2.27 内存使用。基于1.4节的假设，对于N对键值比较二叉查找树和BinarySearchST以及SequentialSearchST的内存使用情况。
 * 用图精确描述一棵以String为键、Integer为值的二叉查找树（比如FrequencyCounter构造的那种）的内存使用情况，
 * 然后估计FrequencyCounter在使用二叉查找树处理《双城记》时树的内存使用情况（精确到字节）。
 *
 * @author cheng
 *         2018/2/27 10:56
 */
public class Exercise27 {
    /*
     *
     * SequentialSearchST 每对键值用一个Node结点表示：
     * 一个引用占8个字节， N 对不同的键值总共占用 = (16 + 8 + 8) * N = 32N 字节
     *
     * BinarySearchST 用一个数组表示键，一个数组表示值：
     * 当涉及到数组扩容时，数组大小范围为 N ~ 4N
     * 所以总共占用内存 32 + 16N ~ 32 + 64N 字节
     *
     * BinarySearchTree 每对键值由一个Node结点表示：
     * 每个Node结点为了维护自身的树结构，至少要由两个引用，分别表示左右孩子
     * 一个 Node 结点至少有 16 + 8 + 8 + 8 + 8 = 48 字节
     * 所以 N 对不同的键值共占用 = 48N 字节
     *
     *
     */
}

package org.three.one.exercises;

/**
 * 3.1.13 对于一个会随机混合进行10^3次put()和10^6次get()操作的应用程序，
 * 你会使用本节中的哪一种符号表的实现？说明理由。
 *
 * @author cheng
 *         2018/2/11 14:13
 */
public class Exercise13 {

    /*
     *
     * get() 操作次数远远大于 put() 操作次数
     *
     * 使用有序数组（二分查找）的符号表，因为 get() 操作时间复杂度为 O(lgn)
     * 无序链表（顺序查找）、有序链表（顺序查找）、无序数组（顺序查找）的符号表，get() 操作时间复杂度为 o(n)
     *
     */
}

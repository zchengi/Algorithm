package org.three.one.exercises;

/**
 * 3.1.28 有序的插入。修改BinarySearchST，使得插入一个比当前所有键都大的键只需要常数时间
 * （这个在构造符号表有序地使用put()插入键值对就只需要线性时间了）。
 *
 * @author cheng
 *         2018/2/12 21:20
 */
public class Exercise28 {
    /*
     *
     * 为BinarySearchST添加一个 max 成员，记录最大键，
     * 每次put判断插入键是否大于 max，大于 max 的话直接判断是否需要扩容然后插入符号表。
     *
     */
}
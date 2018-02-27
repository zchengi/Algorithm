package org.three.two.exercises;

/**
 * 3.2.28 缓存。修改二叉查找树的实现，将最近访问的结点Node保存在一个变量中，
 * 这样get()或put()再次访问同一个键时就只需要常数时间了（参考练习3.1.25）。
 *
 * @author cheng
 *         2018/2/27 11:07
 */
public class Exercise28 {
    /*
     *
     * 声明一个保存当前使用键的缓存cache，
     * 在put()时，更新当前键为cache；
     * 在每次get()时，先判断该键是否与cache相等，
     * 相等直接返回，不相等再查找，更新cache的值。
     *
     */
}

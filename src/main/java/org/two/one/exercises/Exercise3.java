package org.two.one.exercises;

/**
 * 2.1.3 构造一个含有N个元素的数组，使选择排序运行过程中 a[j] < a[min]（由此min会不断更新）成功的次数最大。
 *
 * @author cheng
 *         2018/1/16 20:30
 */
public class Exercise3 {
    // 分析： 构造一个逆序数组即可
    /*
     * 如： 9 8 7 6 5 4 3 2 1
     * i=0 条件满足8次，1和9交换 1 8 7 6 5 4 3 2 9
     * i=1 条件满足6次，2和8交换 1 2 7 6 5 4 3 8 9
     * i=2 条件满足4次，3和7交换 1 2 3 6 5 4 7 8 9
     * i=3 条件满足2次，4和6交换 1 2 3 4 5 6 7 8 9
     * 一共满足 8+6+4+2=20次
     */
}

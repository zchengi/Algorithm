package org.one.day14;

import java.util.Arrays;

/**
 * 1.4.22 仅用加减实现的二分查找（难）
 * 编写一个程序，给定一个含有N个不同的int值的按照升序排列的数组，判断它是否含有给定的整数。只能使用加法和
 * 减法以及常数的额外内存空间。程序的运行时间在最差情况下应该和logN成正比。
 * 分析： 用斐波那契代替2的幂（二分法）进行查找。用两个变量保存Fk和Fk-1并在[i,i+Fk-2]之间查找。在每一步中，
 * 使用减法计算Fk-2，检查i+Fk-2处的元素，并根据搜索结果将搜索范围变为[i,i+Fk-2]或者是[i+Fk-2,i+Fk+2+Fk-1]。
 * （k所在的表达式都是下标）
 * <p>
 * 介绍：斐波那契搜索就是在二分查找的基础上根据斐波那契数列进行分割的。在斐波那契数列找一个等于略大于查找表中
 * 元素个数的数F[n]，将原查找表扩展为长度为Fn，完成后进行斐波那契分割，即F[n]个元素分割为前半部分F[n-1]个元素，
 * 后半部分F[n-2]个元素，找出要查找的元素在那一部分并递归，直到找到。
 *
 * @author cheng
 *         2018/1/14 18:00
 */
public class Example22 {

    private final int FI_SIZE = 20;

    public int fibonacciSearch(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        } else {
            int length = array.length;
            // 制造一个长度为10的斐波那契数列
            int[] fb = makeFbArray(FI_SIZE);
            int k = 0;
            // 找出数据的长度在斐波那契数列(减1)中的位置，将决定如何拆分
            while (length > fb[k] - 1) {
                k++;
            }
            // 构造一个长度为fb[k] - 1的新数列
            int[] temp = Arrays.copyOf(array, fb[k] - 1);
            for (int i = length; i < temp.length; i++) {
                if (i >= length) {
                    temp[i] = array[length - 1];
                }
            }
            int low = 0;
            int high = array.length - 1;
            while (low <= high) {
                int middle = low + fb[k - 1] - 1;
                if (temp[middle] > target) {
                    high = middle - 1;
                    k = k - 1;
                } else if (temp[middle] < target) {
                    low = middle + 1;
                    k = k - 2;
                } else {
                    if (middle <= high) {
                        // 若相等则说明mid即为查找到的位置
                        return middle;
                    } else {
                        // middle的值已经大于high，进入扩展数组的填充补分，即最后一个数就是要查找的数
                        return high;
                    }
                }
            }
            return -1;
        }
    }

    private static int[] makeFbArray(int length) {
        int[] array = null;
        if (length > 2) {
            array = new int[length];
            array[0] = 1;
            array[1] = 1;
            for (int i = 2; i < length; i++) {
                array[i] = array[i - 1] + array[i - 2];
            }
        }
        return array;
    }

    public static void main(String[] args) {
        int[] array = {1, 5, 15, 22, 25, 31, 39, 42, 47, 49, 59, 68, 88, 88,
                88, 88, 88};
        Example22 ex = new Example22();
        System.out.println("result: " + ex.fibonacciSearch(array, 31));
    }
}

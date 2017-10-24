package org.one.day3;

import java.util.Arrays;

/**
 * 二分查找
 * 用于在整数集合中进行查找的一种抽象数据类型
 *
 * @author one
 *         2017/10/18 14:54
 */
public class StaticSetOfInts {
    /**
     * 查询数组
     */
    private int[] a;

    /**
     * @param keys 整数的数组
     * @throws IllegalArgumentException 如果数组包含重复的整数
     */
    public StaticSetOfInts(int[] keys) {

        //保护性复制
        a = new int[keys.length];
        System.arraycopy(keys, 0, a, 0, keys.length);

        //排序
        Arrays.sort(a);

        //检查是否有重复整数
        for (int i = 1; i < a.length; i++) {
            if (a[i] == a[i - 1]) {
                throw new IllegalArgumentException("参数数组包含重复的键");
            }
        }
    }

    /**
     * 检查数组中是否包含key
     *
     * @param key 查找的键
     * @return 包含:true;不包含:false
     */
    public boolean contains(int key) {
        return rank(key) != -1;
    }

    /**
     * 二分查找的实现
     *
     * @param key 查找的键
     * @return 找到:返回该值在数组中出现的位置;未找到:返回-1
     */
    public int rank(int key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key > a[mid]) {
                lo = mid + 1;
            } else if (key < a[mid]) {
                hi = mid - 1;
            } else if (key == a[mid]) {
                return mid;
            }
        }
        return -1;
    }
}

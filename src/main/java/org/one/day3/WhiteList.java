package org.one.day3;

/**
 * 白名单检测用例（使用二分查找）
 *
 * @author cheng
 *         2017/10/18 15:20
 */
public class WhiteList {
    public static void main(String[] args) {
        int[] whiteList = {1, 2, 3, 4, 5, 6, 34, 7, 8, 9, 12, 234, 323, 121, 23, 412, 41, 32, 13};
        StaticSetOfInts set = new StaticSetOfInts(whiteList);
        int key = 3;
        if (set.contains(key)) {
            System.out.println(key);
        }
    }
}

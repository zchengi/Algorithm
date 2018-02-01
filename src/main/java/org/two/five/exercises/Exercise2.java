package org.two.five.exercises;

import org.two.one.learn.Insertion;

/**
 * 2.5.2 编写一段程序，从标准输入读入一列单词并打印出其中所有由两个单词组成的组合词。
 * 例如，如果输入单词为after、和thought和afterthought，那么afterthought就是一个组合词。
 * 效率很低.....
 *
 * @author cheng
 *         2018/2/1 16:21
 */
public class Exercise2 {

    public Exercise2() {
    }

    public static void quickSort(String[] arr) {
        int n = arr.length;
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(String[] arr, int lo, int hi) {
        if (hi - lo <= 15) {
            Insertion.sort(arr, lo, hi);
            return;
        }

        int p = partition(arr, lo, hi);
        quickSort(arr, lo, p - 1);
        quickSort(arr, p + 1, hi);
    }

    private static int partition(String[] arr, int lo, int hi) {
        if (hi - lo <= 15) {
            Insertion.sort(arr, lo, hi);
        }

        // 将 lo 与随机位置交换  随机性
        exch(arr, lo, (int) (Math.random() * (hi - lo + 1)) + lo);
        String v = arr[lo];
        int i = lo;
        int j = hi + 1;
        while (true) {
            while (i <= hi && less(arr[i], v)) i++;
            while (j >= lo + 1 && less(v, arr[j])) j++;
            if (i > j) break;
            exch(arr, i++, j--);
        }

        exch(arr, lo, j);
        return j;
    }

    /**
     * 打印所有组合词
     */
    public static void printCompound(String[] arr) {
        quickSort(arr);
        for (String str : arr)
            if (isCompound(arr, str)) {
                System.out.println(str);
            }
    }

    /**
     * 判断 str 是否是组合词
     */
    private static boolean isCompound(String[] arr, String str) {
        for (int i = 1; i < str.length(); i++) {
            String sub = str.substring(0, i);
            if (rank(arr, sub) < 0) continue;
            sub = str.substring(i, str.length());
            if (rank(arr, sub) >= 0) return true;
        }
        return false;
    }

    /**
     * 查找key是否存在与字符串数组中
     */
    private static int rank(String[] arr, String key) {
        int lo = 0;
        int hi = arr.length - 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (less(arr[mid], key)) {
                lo = mid + 1;
            } else if (less(key, arr[mid])) {
                hi = mid - 1;
            } else return mid;
        }
        return -1;
    }

    private static void exch(String[] arr, int i, int j) {
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static boolean greater(String str1, String str2) {
        return str1.compareTo(str2) > 0;
    }

    private static boolean less(String str1, String str2) {
        return str1.compareTo(str2) < 0;
    }

    private static boolean equals(String str1, String str2) {
        return str1.compareTo(str2) == 0;
    }


    public static void main(String[] args) {
        String[] str = {"c", "d", "w", "f", "z", "f", "a", "b", "o", "bo", "ob","fa","fc"};
        printCompound(str);
    }
}

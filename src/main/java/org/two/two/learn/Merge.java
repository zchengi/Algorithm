package org.two.two.learn;

import org.two.one.learn.Insertion;

/**
 * 算法2.4 自顶向下的归并排序
 * <p>
 * 证明：如果它能够将两个子数组排序，它就能够通过归并两个子数组来将整个数组排序。
 * <p>
 * 要对子数组a[lo...hi]进行排序，先将它分为a[lo...mid] 和 a[mid+1...hi]两部分，
 * 分别通过递归调用将它们单独排序，最后将有序的字数组归并为最终的结果。
 *
 * @author cheng
 *         2018/1/17 12:26
 */
public class Merge {

    /**
     * 归并所需的辅助数组
     */
    private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        // 一次性分配内存空间
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    /**
     * 自顶向下归并排序
     */
    private static void sort(Comparable[] a, int lo, int hi) {
        //
        if (hi - lo <= 10) {
            Insertion.sort(a);
            return;
        }

        // 将数组a[lo...hi]排序
        if (hi <= lo) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        // 将左半边排序
        sort(a, lo, mid);
        // 将右半边排序
        sort(a, mid + 1, hi);

        if (less(a[mid], a[mid + 1])) return;  //优化2 判断归并前是否已经有序

        // 归并结果
        merge(a, lo, mid, hi);
    }

    /**
     * 2.2.3 自底向上的归并排序
     * 分析：自底向上的归并排序会多次遍历整个数组，根据子数组大小进行两两归并。子数组的大小sz的初始值为1，
     * 每次加倍。最后一个子数组的大小只有在数组大小是sz的偶数倍的时候才会等于sz（否则它会比sz小）。
     */
    public static void sortBU(Comparable[] a) {
        // 进行lgN次两两合并
        int n = a.length;
        aux = new Comparable[n];
        // sz：子数组大小
        for (int sz = 1; sz < n; sz += sz) {
            // lo：子数组索引
            for (int lo = 0; lo < n - sz; lo += sz + sz) {
                merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, n - 1
                ));
            }
        }
    }

    /**
     * 原地归并的抽象方法
     */
    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        // 将a[lo...mid] 和 a[mid+1...hi] 归并
        int i = lo, j = mid + 1;
        // 将a[lo...hi] 复制到 aux[lo...hi]
        System.arraycopy(a, lo, aux, lo, hi + 1 - lo);
        // 归并回到a[lo...hi]
        for (int k = lo; k <= hi; k++) {
            // 左半边用尽（取右半边元素）
            if (i > mid) a[k] = aux[j++];
                // 右半边用尽（取左半边元素）
            else if (j > hi) a[k] = aux[i++];
                // 右半边的当前元素小于左半边的当前元素（取右半边元素）
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
                // 右半边的当前元素大于左半边的当前元素（取左半边元素）
            else a[k] = aux[i++];
        }
    }


    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void show(Comparable[] a) {
        for (Comparable item : a) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        String[] a = {"M", "E", "R", "G", "E", "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        sortBU(a);
        show(a);
    }
}

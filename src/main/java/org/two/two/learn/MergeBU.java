package org.two.two.learn;


import org.tool.SortTestHelper;
import org.two.one.learn.InsertionFaster;

/**
 * 2.2.3 自底向上的归并排序
 * 分析：自底向上的归并排序会多次遍历整个数组，根据子数组大小进行两两归并。子数组的大小sz的初始值为1，
 * 每次加倍。最后一个子数组的大小只有在数组大小是sz的偶数倍的时候才会等于sz（否则它会比sz小）。
 *
 * @author cheng
 *         2018/1/17 12:26
 */
public class MergeBU {

    /**
     * 归并所需的辅助数组
     */
    private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        // 一次性分配内存空间
        aux = new Comparable[a.length];
        sortBU(a);
    }

    private static void sortBU(Comparable[] a) {

        // 进行lgN次两两合并
        int n = a.length;

        // Merge Sort Bottom Up 无优化版本
//        for (int sz = 1; sz < n; sz += sz) {
//            for (int lo = 0; lo < n - sz; lo += sz + sz) {
//                // 对 a[i...i+sz-1] 和 a[i+sz...i+2*sz-1] 进行归并
//                merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, n - 1));
//            }
//        }

        // Merge Sort Bottom Up 优化
        // 对于小数组，使用插入排序优化
        for (int i = 0; i < n; i += 16) {
            InsertionFaster.sort(a, i, Math.min(i + 15, n - 1));
        }

        for (int sz = 16; sz < n; sz += sz) {
            for (int lo = 0; lo < n - sz; lo += sz + sz) {
                // 对于a[mid] <= a[mid+1]的情况，不进行merge
                if (a[lo + sz - 1].compareTo(a[lo + sz]) > 0) {
                    merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, n - 1));
                }
            }
        }

    }

    /**
     * 原地归并的抽象方法
     */
    private static void merge(Comparable[] a, int lo, int mid, int hi) {

        // 将a[lo...hi] 复制到 aux[lo...hi]
        System.arraycopy(a, lo, aux, lo, hi + 1 - lo);

        // 将a[lo...mid] 和 a[mid+1...hi] 归并
        int i = lo, j = mid + 1;

        // 归并回到a[lo...hi]
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
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

        // Merge Sort BU 也是一个O(nlogn)复杂度的算法，虽然只使用两重for循环
        // 所以，Merge Sort BU也可以在1秒之内轻松处理100万数量级的数据
        // 注意：不要轻易根据循环层数判断算法的复杂度，Merge Sort BU就是一个反例
        Integer[] arr = SortTestHelper.generateRandomArray(1000000, 1, 5000000);
        SortTestHelper.testSort("org.two.two.learn.MergeBU", arr);
    }
}

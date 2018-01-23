package org.two.two.learn;

import org.tool.SortTestHelper;
import org.two.one.learn.InsertionFaster;

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
        // 优化1 对于小规模数组，使用插入排序
        if (hi - lo <= 15) {
            InsertionFaster.sort(a, lo, hi);
            return;
        }
       /* if (hi <= lo) {
            return;
        }*/

        // 将数组a[lo...hi]排序
        int mid = lo + (hi - lo) / 2;
        // 将左半边排序
        sort(a, lo, mid);
        // 将右半边排序
        sort(a, mid + 1, hi);

        // 优化2 对于a[mid] <= a[mid+1]的情况，不进行merge
        // 对于近乎有序的数组非常有效,但是对于一般情况,有一定的性能损失
        if (less(a[mid], a[mid + 1])) return;

        // 归并结果
        merge(a, lo, mid, hi);
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
                // 左半边元素已经全部处理完毕（取右半边元素）
                a[k] = aux[j++];
            } else if (j > hi) {
                // 右半边元素已经全部处理完毕（取左半边元素）
                a[k] = aux[i++];
            } else if (less(aux[j], aux[i])) {
                // 右半边的当前元素 < 左半边的当前元素（取右半边元素）
                a[k] = aux[j++];
            } else {
                // 右半边的当前元素 > 左半边的当前元素（取左半边元素）
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

        // Merge Sort是我学习的第一个O(nlogn)复杂度的算法
        // 可以在1秒之内轻松处理10万数量级的数据
        // 注意：不要尝试使用SelectionSort，InsertionSort或者BubbleSort处理100万级的数字局
        // 否则，你就见识到了O(n^2)的算法和O(nlogn)算法的本质差异

        String[] a = {"M", "E", "R", "G", "E", "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        sort(a);
        show(a);

        Integer[] arr = SortTestHelper.generateRandomArray(1000000, 1, 5000000);
        SortTestHelper.testSort("org.two.two.learn.Merge", arr);
    }
}

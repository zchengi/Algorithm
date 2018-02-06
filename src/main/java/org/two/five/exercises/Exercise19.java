package org.two.five.exercises;

import java.util.Arrays;

import static org.tool.ArrayGenerator.ints;
import static org.tool.ArrayGenerator.print;

/**
 * 2.5.19 Kendall tau距离。编写一段程序KendallTau.java，在线性对数时间内计算两组排列之间的Kendall tau距离。
 * <p>
 * 分析：Kendall tau距离就是两个排列之间的逆序数，它反应了两个排列的相似程度。
 * 使用归并排序，它可以使得算法变成线性对数量级。在将两个有序的排列归并在一起时，
 * 前子数组首元素如果小于后子数组首元素，则逆序数为 0，反之，逆序数为前子数组当前的元素个数。
 *
 * @author cheng
 *         2018/2/6 23:30
 */
public class Exercise19 {
    public static long count(int[] arr) {
        int n = arr.length;
        int[] aux = Arrays.copyOf(arr, n);
        return mergeSortCount(arr, aux, 0, n - 1);
    }

    private static long mergeSortCount(int[] a, int[] aux, int lo, int hi) {
        if (hi <= lo) return 0;

        int mid = (hi - lo) / 2 + lo;
        long inversions = 0;
        inversions += mergeSortCount(a, aux, lo, mid);
        inversions += mergeSortCount(a, aux, mid + 1, hi);
        inversions += mergeCount(a, aux, lo, mid, hi);

        return inversions;
    }

    private static long mergeCount(int[] a, int[] aux, int lo, int mid, int hi) {
        System.arraycopy(a, 0, aux, 0, hi + 1);

        int i = lo;
        int j = mid + 1;
        long inversions = 0;

        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (aux[j] < aux[i]) {
                a[k] = aux[j++];
                inversions += mid - i + 1;
            } else a[k] = aux[i++];

        }
        return inversions;
    }

    /**
     * KendallTau遵守的约定是 a b 长度一致，且 a 中所有元素都能在 b 中找到；
     * 所以 a 和 b 是 0~ n-1 的不同置换
     */
    public static long KendallTau(int[] a, int[] b) {
        int[] aIndex = new int[a.length];
        int[] aIndexMappedByb = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            aIndex[a[i]] = i;
        }
        for (int i = 0; i < a.length; i++) {
            aIndexMappedByb[i] = aIndex[b[i]];
        }
        return count(aIndexMappedByb);
    }

    public static void main(String[] args) {
        int[] a = ints(0, 5);
        int[] b = ints(0, 5);
        System.out.print("a: ");
        print(a);
        System.out.print("b: ");
        print(b);
        System.out.println("Kendall tau distance: " + KendallTau(a, b));
    }
}

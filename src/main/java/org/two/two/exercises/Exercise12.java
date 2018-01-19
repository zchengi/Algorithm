package org.two.two.exercises;

import org.tool.ArrayGenerator;

/**
 * 2.2.12 次线性的额外空间。用大小M将数组分为N/M块（简单起见，设M是N的约数）。
 * 实现一个归并方法，使之所需的额外空间减少到max(M,M/N):
 * (i)可以先将一个块看做一个元素，将块的第一个元素作为块的主键，用选择排序将块排序；
 * (ii)遍历数组，将第一块和第二块归并，完成后将第二块和第三块归并，等等。
 *
 * @author cheng
 *         2018/1/19 16:12
 */
public class Exercise12 {

    public static void divideIntoMSizeBlockSort(int[] a, int m) {
        int n = a.length;
        if (n % m != 0) {
            throw new IllegalArgumentException("m必须是数组的约数!");
        }
        int block = n / m;
        for (int i = 0, lo = i * m, hi = (i + 1) * m - 1;
             i < block; i++, lo = i * m, hi = (i + 1) * m - 1) {
            selection(a, lo, hi);
        }
        for (int i = 0; i < block-1; i++) {
            mergeSort(a, 0, (i + 1) * m - 1, (i + 2) * m - 1);
        }
    }

    private static void mergeSort(int[] a, int lo, int mid, int hi) {
        int[] aux = new int[hi - lo + 1];
        System.arraycopy(a, lo, aux, lo, hi + 1 - lo);
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (aux[j] < aux[i]) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }

    private static void selection(int[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            int min = i;
            for (int j = i + 1; j <= hi; j++) {
                if (a[j] < a[min]) min = j;
            }
            if (min != i) {
                int temp = a[i];
                a[i] = a[min];
                a[min] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] a = ArrayGenerator.ints(21);
        divideIntoMSizeBlockSort(a, 7);
        ArrayGenerator.print(a);
    }
}

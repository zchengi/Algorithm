package org.two.two.exercises;

/**
 * 2.2.10 快速归并。实现一个merge()方法，按降序将a[]的后半部分复制到aux[]，然后将其
 * 归并回a[]中。这样就可以去掉内循环中检测某半边是否用尽的代码。
 * 注意：这样的排序产生的结果是不稳定的。
 *
 * @author cheng
 *         2018/1/18 18:38
 */
public class Exercise10 {

    private static Comparable[] aux;


    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }


    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        int i = lo, j = hi;
        for (int k = 0; k <= mid; k++) {
            aux[k] = a[k];
        }
        for (int k = mid + 1; k <= hi; k++) {
            aux[k] = a[hi + mid + 1 - k];
        }
        for (int k = lo; k <= hi; k++) {
            if (less(aux[j], aux[i])) {
                a[k] = aux[j--];
            } else {
                a[k] = aux[i++];
            }
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void show(Comparable[] a) {
        for (Comparable item : a) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        String[] a = {"a", "s", "f", "t", "w", "v", "b"};
        sort(a);
        show(a);
    }
}

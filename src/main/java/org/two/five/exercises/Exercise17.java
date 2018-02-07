package org.two.five.exercises;

import org.tool.SortTestHelper;

import java.util.Arrays;

/**
 * 算法2.5.17 检测稳定性。扩展练习2.1.16中的check()方法，对指定数组调用sort()，
 * 如果排序结果是稳定的则返回true，否则返回false。不要假设sort()只会使用exch()移动数据。
 * <p>
 * 分析：在排序中添加一个索引数组，用索引排序数组，返回排序后的索引。根据稳定性的定义可以得到：
 * 如果两个元素值相等，那么索引在前的元素排序后索引依然在前，则证明是稳定排序，否则是不稳定排序。
 * 所以根据排序后的索引，遍历数组，如果有两个相等的值，则判断这两个相等的值的索引：如果索引数组中
 * 排在前面的索引值大于后面的索引值，则证明是不稳定排序，否则是稳定排序。例如：
 *
 * 索  引：            0  1  2  3
 * 原数组：            2  2  2  1
 * 不稳定排序的数组：   3  0  2  1
 * 稳定排序的索引：     3  0  1  2
 *
 * @author cheng
 *         2018/2/4 23:25
 */
public class Exercise17 {
    /**
     * 排序稳定性检测
     */
    public static boolean check(Comparable[] arr, int[] indexes) {
        for (int i = 1; i < indexes.length; i++) {
            if (arr[indexes[i]].compareTo(arr[indexes[i - 1]]) == 0) {
                if (indexes[i] < indexes[i - 1]) return false;
            }
        }
        return true;
    }

    /**
     * 选择排序
     */
    public static int[] selection(Comparable[] arr) {
        int n = arr.length;
        int[] indexes = new int[n];
        for (int i = 0; i < n; i++) {
            indexes[i] = i;
        }

        for (int i = 0; i < n; i++) {
            Comparable min = arr[indexes[i]];
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                if (arr[indexes[j]].compareTo(min) < 0) {
                    min = arr[indexes[j]];
                    minIndex = j;
                }
            }
            int temp = indexes[i];
            indexes[i] = indexes[minIndex];
            indexes[minIndex] = temp;
        }
        return indexes;
    }

    /**
     * 插入排序
     */
    public static int[] insertion(Comparable[] arr) {
        int[] indexes = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            indexes[i] = i;
        }
        for (int i = 0; i < arr.length; i++) {
            Comparable temp = arr[indexes[i]];
            int j;
            int indexTemp = indexes[i];
            for (j = i; j > 0 && temp.compareTo(arr[indexes[j - 1]]) < 0; j--) {
                indexes[j] = indexes[j - 1];
            }
            indexes[j] = indexTemp;
        }
        return indexes;
    }

    /**
     * 冒泡排序
     */
    public static int[] bubble(Comparable[] arr) {
        int n = arr.length;
        int[] indexes = new int[n];
        for (int i = 0; i < n; i++) {
            indexes[i] = i;
        }

        boolean swapped = true;
        while (swapped) {
            swapped = false;
            for (int i = 1; i < n; i++) {
                if (arr[indexes[i - 1]].compareTo(arr[indexes[i]]) > 0) {
                    int temp = indexes[i - 1];
                    indexes[i - 1] = indexes[i];
                    indexes[i] = temp;
                    swapped = true;
                }
            }
            n--;
        }
        return indexes;
    }

    /**
     * 希尔排序
     */
    public static int[] shell(Comparable[] arr) {
        int n = arr.length;
        int[] indexes = new int[n];
        for (int i = 0; i < n; i++) {
            indexes[i] = i;
        }

        int h = 0;
        while (h < n / 3) h = h * 3 + 1;

        while (h >= 1) {
            for (int i = h; i < n; i++) {
                Comparable temp = arr[indexes[i]];
                int j;
                int index = indexes[i];
                for (j = i; j >= h && temp.compareTo(arr[indexes[j - h]]) < 0; j -= h) {
                    indexes[j] = indexes[j - h];
                }
                indexes[j] = index;
            }
            h /= 3;
        }
        return indexes;
    }

    /**
     * 归并排序
     */
    public static int[] merge(Comparable[] arr) {
        int n = arr.length;
        int[] indexes = new int[n];
        int[] aux = new int[n];
        for (int i = 0; i < n; i++) {
            indexes[i] = i;
        }
        merge(arr, indexes, aux, 0, n - 1);
        return indexes;
    }

    private static void merge(Comparable[] arr, int[] indexes, int[] aux, int lo, int hi) {
        if (lo >= hi) return;

        int mid = (hi - lo) / 2 + lo;
        merge(arr, indexes, aux, lo, mid);
        merge(arr, indexes, aux, mid + 1, hi);
        if (arr[indexes[mid]].compareTo(arr[indexes[mid + 1]]) < 0) {
            return;
        }
        mergeSort(arr, indexes, aux, lo, mid, hi);
    }

    private static void mergeSort(Comparable[] arr, int[] indexes, int[] aux, int lo, int mid, int hi) {
        System.arraycopy(indexes, lo, aux, lo, hi + 1 - lo);

        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) indexes[k] = aux[j++];
            else if (j > hi) indexes[k] = aux[i++];
            else if (arr[aux[i]].compareTo(arr[aux[j]]) <= 0) indexes[k] = aux[i++];
            else indexes[k] = aux[j++];
        }
    }

    /**
     * 快速排序
     */
    public static int[] quick(Comparable[] arr) {
        int n = arr.length;
        int[] indexes = new int[n];
        for (int i = 0; i < n; i++) {
            indexes[i] = i;
        }
        quick(arr, indexes, 0, n - 1);
        return indexes;
    }

    private static void quick(Comparable[] arr, int[] indexes, int lo, int hi) {
        if (lo >= hi) return;

        int p = pattern(arr, indexes, lo, hi);
        quick(arr, indexes, lo, p - 1);
        quick(arr, indexes, p + 1, hi);
    }

    private static int pattern(Comparable[] arr, int[] indexes, int lo, int hi) {
        Comparable v = arr[indexes[lo]];
        int i = lo + 1;
        int j = hi;
        while (true) {
            while (i <= hi && less(arr[indexes[i]], v)) i++;
            while (j >= lo + 1 && less(v, arr[indexes[j]])) j--;
            if (i >= j) break;
            swap(indexes, i++, j--);
        }
        swap(indexes, lo, j);
        return j;
    }

    private static void swap(int[] indexes, int i, int j) {
        int temp = indexes[i];
        indexes[i] = indexes[j];
        indexes[j] = temp;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /**
     * 堆排序
     */
    public static int[] heap(Comparable[] arr) {
        int n = arr.length;
        int[] indexes = new int[n];
        for (int i = 0; i < n; i++) {
            indexes[i] = i;
        }

        for (int i = n / 2 - 1; i >= 0; i--) {
            shiftDown(arr, indexes, i);
        }
        int[] resIndexes = new int[n];

        while (n > 0) {
            resIndexes[--n] = extractMax(arr, indexes, arr.length - 1);
        }
        return resIndexes;
    }

    private static void shiftDown(Comparable[] arr, int[] indexes, int k) {
        int n = arr.length - 1;
        Comparable temp = arr[indexes[k]];
        int indexTemp = indexes[k];
        while (2 * k + 1 <= n) {
            int j = 2 * k + 1;
            if (j < n && arr[indexes[j]].compareTo(arr[indexes[j + 1]]) < 0) {
                j++;
            }
            if (temp.compareTo(arr[indexes[j]]) >= 0) break;
            indexes[k] = indexes[j];
            k = j;
        }
        indexes[k] = indexTemp;
    }

    private static int extractMax(Comparable[] arr, int[] indexes, int count) {
        int maxIndex = indexes[0];
        indexes[0] = indexes[count--];

        // 避免对象游离
        indexes[count + 1] = 0;
        shiftDown(arr, indexes, 0);
        return maxIndex;
    }

    public static void main(String[] args) {
        int n = 1024;
        Integer[] arr1 = SortTestHelper.generateRandomArray(n, 0, 5);
        Integer[] arr2 = Arrays.copyOf(arr1, n);
        Integer[] arr3 = Arrays.copyOf(arr1, n);
        Integer[] arr4 = Arrays.copyOf(arr1, n);
        Integer[] arr5 = Arrays.copyOf(arr1, n);
        Integer[] arr6 = Arrays.copyOf(arr1, n);
        Integer[] arr7 = Arrays.copyOf(arr1, n);

        int[] indexes1 = selection(arr1);
        int[] indexes2 = insertion(arr2);
        int[] indexes3 = shell(arr3);
        int[] indexes4 = bubble(arr4);
        int[] indexes5 = merge(arr5);
        int[] indexes6 = quick(arr6);
        int[] indexes7 = heap(arr7);
        System.out.println(SortTestHelper.isSortedByIndex(arr1, indexes1));
        System.out.println("排序算法稳定性检测：");
        System.out.println("选择排序：" + check(arr1, indexes1));
        System.out.println("插入排序：" + check(arr2, indexes2));
        System.out.println("希尔排序：" + check(arr3, indexes3));
        System.out.println("冒泡排序：" + check(arr4, indexes4));
        System.out.println("归并排序：" + check(arr5, indexes5));
        System.out.println("快速排序：" + check(arr6, indexes6));
        System.out.println("堆排序：" + check(arr7, indexes7));
    }
}

package org.two.one.learn;

/**
 * 算法2.2 插入排序
 * <p>
 * 分析： 对于1到n-1之间的每一个i，将a[i]与a[0]到a[i-1]中比它小的所有元素依次有序地交换。
 * 在索引i由左向右拜年话的过程中，它左侧的元素总是有序的，所以当i到达数组的右端时排序就完成了。
 *
 * @author cheng
 *         2018/1/15 18:00
 */
public class Insertion {

    /**
     * 将a[]按升序排列
     */
    public static void sort(Comparable[] a) {
        int n = a.length;
        // 将a[i]插入到a[i-1]、a[i-2]、a[i-3]...之中
        for (int i = 0; i < n; i++) {
            // 判断a[j]是否小于a[j-1]，如果是则交换位置
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
            }
            assert isSorted(a, 0, i);
        }
        assert isSorted(a);
    }


    /**
     * 重新排序数组 a[lo..hi]
     */
    public static void sort(Comparable[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > lo && less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
            }
        }
        assert isSorted(a, lo, hi);
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    /**
     * 在单行中打印数组
     */
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    /**
     * 测试数组元素是否有序
     */
    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) return false;
        }
        return true;
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i < hi; i++) {
            if (less(a[i], a[i - 1])) return false;
        }
        return true;
    }


    public static void main(String[] args) {
        // 从标准输入读取字符串，将它们排序并输出
        String[] a = {"a", "c", "f", "e", "t", "t", "w", "t"};
        sort(a);
        assert isSorted(a);
        show(a);
    }
}

package org.two.five.exercises;

import org.tool.SortTestHelper;

import java.util.Comparator;

/**
 * 2.5.18 强制稳定。编写一段能够将任意排序方法变得稳定的封装代码，创建一种新的数据类型作为键，
 * 将键的原始索引保存在其中，并在调用sort()之后再恢复原始的键。
 *
 * 分析：目的是为了稳定排序，即原数组中两个相等的值，索引在前的排序或索引仍然在前；
 * 添加一个辅助数据类型，创建其数组，保存原数组中的值和相应的索引；
 * 这个数据类型实现两个比较器，一种按照值比较，一种按照索引比较；
 * 可以通过索引判断数组是否是稳定排序的结果，如果不是则将其再用 索引比较器 排序，排序后的结果就是稳定的。
 *
 * 总结：一个辅助数据类型，两个比较器；关键是找出重复的值，将其按照索引比较器排序；
 *
 * @author cheng
 *         2018/2/4 23:38
 */
public class Exercise18 {
    /**
     * 强制稳定包装类
     */
    static class Wrapper {
        Comparable key;
        int originalIndex;

        public Wrapper(Comparable key, int originalIndex) {
            this.key = key;
            this.originalIndex = originalIndex;
        }

        /**
         * 将需要排序的数组封装
         */
        public static Wrapper[] ws(Comparable[] arr) {
            int n = arr.length;
            Wrapper[] wrappers = new Wrapper[n];
            for (int i = 0; i < n; i++) {
                wrappers[i] = new Wrapper(arr[i], i);
            }
            return wrappers;
        }

        /**
         * 通过键比较
         */
        public static Comparator<Wrapper> compareByKey() {
            return new Comparator<Wrapper>() {
                @Override
                public int compare(Wrapper w, Wrapper v) {
                    return w.key.compareTo(v.key);
                }
            };
        }

        /**
         * 通过索引比较
         */
        public static Comparator<Wrapper> compareByIndex() {
            return new Comparator<Wrapper>() {
                @Override
                public int compare(Wrapper w, Wrapper v) {
                    return w.originalIndex < v.originalIndex ? -1 : w.originalIndex > v.originalIndex ? 1 : 0;
                }
            };
        }


        public boolean keyEquals(Wrapper that) {
            return this.key.equals(that.key);
        }

        @Override
        public String toString() {
            return String.format("{ %s, %d }", key, originalIndex);
        }
    }

    /**
     * 使用比较器的快排
     */
    public static void quick(Object[] objects, Comparator wc) {
        quick(objects, wc, 0, objects.length - 1);
    }

    private static void quick(Object[] arr, Comparator wc, int lo, int hi) {
        if (hi - lo <= 15) {
            for (int i = lo; i <= hi; i++) {
                Object temp = arr[i];
                int j;
                for (j = i - 1; j >= lo && wc.compare(temp, arr[j]) < 0; j--) {
                    arr[j + 1] = arr[j];
                }
                arr[j + 1] = temp;
            }
            return;
        }

        int mid = (lo + hi) / 2;

        if (wc.compare(arr[mid], arr[lo]) < 0) exch(arr, mid, lo);
        if (wc.compare(arr[hi], arr[lo]) < 0) exch(arr, lo, hi);
        if (wc.compare(arr[mid], arr[hi]) < 0) exch(arr, mid, hi);

        // 三路快排
        Object v = arr[hi];
        int i = lo - 1;
        int j = hi;
        int p = lo - 1;
        int q = hi;
        while (true) {
            while (wc.compare(arr[++i], v) < 0) ;
            while (wc.compare(arr[--j], v) > 0) ;
            if (i >= j) break;
            exch(arr, i, j);
            if (wc.compare(arr[i], v) == 0) exch(arr, ++p, i);
            if (wc.compare(arr[j], v) == 0) exch(arr, --q, j);
        }
        exch(arr, hi, i);

        int lt = i - 1;
        int gt = i + 1;
        int m = lo;
        int n = hi - 1;
        while (m <= p) exch(arr, m++, lt--);
        while (n >= q) exch(arr, n--, gt++);
        quick(arr, wc, lo, lt);
        quick(arr, wc, gt, hi);
    }

    /**
     * 从左端开始查找key值
     */
    public static int binarySearch_LB(Object[] objects, Object key, Comparator c) {
        int lo = 0;
        int hi = objects.length - 1;
        while (lo < hi) {
            int mid = (int) (Math.ceil((lo + hi) / 2.0));
            hi = c.compare(objects[mid], key) >= 0 ? mid - 1 : hi;
            lo = c.compare(objects[mid], key) < 0 ? mid : lo;
        }
        // 在左右端点处命中
        if (c.compare(objects[lo], key) == 0) return lo;
        if (++lo == objects.length) return -1;
        if (c.compare(objects[lo], key) == 0) return lo;
        return -1;
    }

    /**
     * 从右端开始查找key值
     */
    public static int binarySearch_RB(Object[] objects, Object key, Comparator c) {
        int lo = 0, hi = objects.length - 1;
        while (lo < hi) {
            int mid = (lo + hi) >> 1;
            hi = c.compare(objects[mid], key) > 0 ? mid : hi;
            lo = c.compare(objects[mid], key) <= 0 ? mid + 1 : lo;
        }
        if (c.compare(objects[lo], key) == 0) return lo;
        if (--lo < 0) return -1;
        if (c.compare(objects[lo], key) == 0) return lo;
        return -1;
    }

    private static void exch(Object[] objects, int i, int j) {
        Object temp = objects[i];
        objects[i] = objects[j];
        objects[j] = temp;
    }

    /**
     * 强制稳定
     */
    public static void stableSort(Wrapper[] wrappers) {
        int n = wrappers.length;
        for (int i = 1; i < n; i++) {
            if (Wrapper.compareByKey().compare(wrappers[i - 1], wrappers[i]) > 0) {
                throw new IllegalArgumentException("The array is not ordered with theMainKey!");
            }
        }
        // 强制稳定
        // 将数组 从左到右 从右到左 遍历，找到两个 key 值；
        // 判断两个值的索引是否相等；
        // 不相等则 将范围 [left , right] 之间的数据按照索引比较的方法重新排序，排序完毕这部分相等的值就是稳定的了。
        int left;
        int right;
        for (int i = 0; i < n; i++) {
            left = binarySearch_LB(wrappers, wrappers[i], Wrapper.compareByKey());
            right = binarySearch_RB(wrappers, wrappers[i], Wrapper.compareByKey());
            if (left != right) {
                quick(wrappers, Wrapper.compareByIndex(), left, right);
            }
        }
    }

    public static void main(String[] args) {
        int n = 20;
        // 生成 [0, 5]之间的随机20个数
        Integer[] arr = SortTestHelper.generateRandomArray(n, 0, 5);
        Wrapper[] wrappers = Wrapper.ws(arr);

        // 使用主键排序
        quick(wrappers, Wrapper.compareByKey());

        System.out.println("不稳定排序后结果：");
        for (Wrapper wrapper : wrappers) {
            System.out.print(wrapper + " ");
        }
        System.out.println();

        // 强制稳定
        stableSort(wrappers);
        System.out.println("强制稳定后的结果：");
        for (Wrapper wrapper : wrappers) {
            System.out.print(wrapper + " ");
        }
    }
}

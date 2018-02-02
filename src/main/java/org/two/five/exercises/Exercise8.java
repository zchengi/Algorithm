package org.two.five.exercises;


import org.two.four.learn2.MaxHeap;

import java.util.Arrays;

/**
 * 2.5.8 编写一段程序Frequency，从标准输入读取一列字符串,
 * 并按照字符串出现频率由高到低的顺序打印每个字符串及出现次数。
 * <p>
 * 创建一个新的数据结构 record，在其中记录字符串和其出现的总次数，
 * 创建一个该数结构的数组，然后将其按照出现次数排序，依次输出即可。
 * <p>
 * 也可以使用最大堆，每次取出最大元素，记录其count值先为1，依次取出，
 * 当取出的元素不等于上一个值时，将其放入 record 数组中，然后重置为1，再重复之前的操作。
 * 再按照frequency排序record数组输出。
 *
 * @author cheng
 *         2018/2/2 21:23
 */
public class Exercise8 {
    static class Record implements Comparable<Record> {

        private final String str;
        private final int frequency;

        public Record(String str, int frequency) {
            this.str = str;
            this.frequency = frequency;
        }

        @Override
        public int compareTo(Record that) {
            return frequency > that.frequency ? -1 : frequency < that.frequency ? 1 : 0;
        }

        @Override
        public String toString() {
            return str + "  " + frequency;
        }
    }

    public static void heap(Comparable[] arr) {
        heap(arr, 0, arr.length - 1);
    }

    private static void heap(Comparable[] arr, int lo, int hi) {
        if (lo >= hi) return;
        Comparable[] a = new Comparable[hi - lo + 1];
        System.arraycopy(arr, lo, a, 0, hi - lo + 1);

        int n = a.length;
        for (int i = n / 2; i > 0; i--) {
            int k = i;
            int j;
            Comparable temp = a[i - 1];
            while ((j = k << 1) <= n) {
                if (j < n && a[j - 1].compareTo(a[j]) < 0) {
                    j++;
                }
                if (temp.compareTo(a[j - 1]) >= 0) break;
                a[k - 1] = a[j - 1];
                k = j;
            }
            a[k - 1] = temp;
        }

        while (n > 1) {
            Comparable temp = a[n - 1];
            a[n - 1] = a[0];
            --n;
            int k = 1, j;
            while ((j = k << 1) <= n) {
                if (j < n && a[j - 1].compareTo(a[j]) < 0) j++;
                a[k - 1] = a[j - 1];
                k = j;
            }
            a[k - 1] = temp;
            while (k > 1 && temp.compareTo(a[(k >> 1) - 1]) > 0) {
                a[k - 1] = a[(k >> 1) - 1];
                k >>= 1;
            }
            a[k - 1] = temp;
        }
        System.arraycopy(a, 0, arr, lo, hi - lo + 1);
    }

    public static void frequency(String[] strings) {
        int n = strings.length;
        heap(strings);
        Record[] r = new Record[n];

        String word = strings[0];
        int frequency = 1;
        int count = 0;
        for (int i = 1; i < n; i++) {
            if (!strings[i].equals(word)) {
                r[count++] = new Record(word, frequency);
                word = strings[i];
                frequency = 0;
            }
            frequency++;
        }

        r[count++] = new Record(word, frequency);
        heap(r, 0, count - 1);
        for (int i = 0; i < count; i++) {
            System.out.print(r[i] + ", ");
        }
    }

    public static void main(String[] args) {
        String[] arr = {"yanghan", "lijie", "yanghan", "zhansdf",
                "zhansdf", "lijie", "yanghan", "zhuyangkai",
                "wangjinyuan", "zhangyafang", "wangjinyuan", "lijie",
                "yanghan", "maliping", "zhuyangkai"};
        frequency(arr);
        System.out.println();


        String[] copy = Arrays.copyOf(arr, arr.length);
        MaxHeap<String> stringMaxHeap = new MaxHeap<>(copy);
        Record[] records = new Record[arr.length];

        int count = 1;
        String current = stringMaxHeap.extractMax();
        int size = 0;

        while (!stringMaxHeap.isEmpty()) {
            String temp = stringMaxHeap.extractMax();
            if (!current.equals(temp)) {
                records[size++] = new Record(current, count);
                current = temp;
                count = 1;
            } else {
                count++;
            }
        }
        records[size++] = new Record(current, count);

        heap(records, 0, size - 1);
        for (int i = 0; i < size; i++) {
            System.out.print(records[i] + ", ");
        }
    }
    /*
     *
     * yanghan  4, lijie  3, wangjinyuan  2, zhansdf  2, zhuyangkai  2, zhangyafang  1, maliping  1,
     * yanghan  4, lijie  3, zhuyangkai  2, wangjinyuan  2, zhansdf  2, maliping  1, zhangyafang  1,
     *
     */
}

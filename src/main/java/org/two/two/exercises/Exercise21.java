package org.two.two.exercises;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

/**
 * 2.2.21 一式三份。给定三个列表，每个列表中包含N个名字，编写一个线性对数级别的算法来判断
 * 三分列表中是否含有公共的名字，如果有，返回第一个被找到的这种名字。
 *
 * @author cheng
 *         2018/1/21 20:34
 */
public class Exercise21 {
    public static void merge(Comparable[] a) {
        Comparable[] aux = a.clone();
        merge(aux, a, 0, a.length - 1);
    }

    private static void merge(Comparable[] src, Comparable[] dest, int lo, int hi) {
        if (hi - lo <= 7) {
            insertion(dest, lo, hi);
            return;
        }
        int mid = (lo + hi) / 2;
        merge(dest, src, lo, mid);
        merge(dest, src, mid + 1, hi);
        if (src[mid].compareTo(src[mid + 1]) < 0) {
            System.arraycopy(src, lo, dest, lo, hi - lo + 1);
            return;
        }
        merge(src, dest, lo, mid, hi);
    }

    private static void merge(Comparable[] src, Comparable[] dest, int lo, int mid, int hi) {
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) dest[k] = src[j++];
            else if (j > hi) dest[k] = src[i++];
            else if (src[j].compareTo(src[i]) < 0) dest[k] = src[j++];
            else dest[k] = src[i++];
        }
    }

    private static void insertion(Comparable[] a, int lo, int hi) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            Comparable temp = a[i];
            int j;
            for (j = i - 1; j >= 0 && temp.compareTo(a[j]) < 0; j--) {
                a[j + 1] = a[j];
            }
            a[j + 1] = temp;
        }
    }

    public static int binarySearch(String[] a, String key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (key.compareTo(a[mid]) < 0) hi = mid - 1;
            else if (key.compareTo(a[mid]) > 0) lo = mid + 1;
            else return mid;
        }
        return -1;
    }

    public static String randomName() {
        return names[StdRandom.uniform(names.length)];
    }

    public static String[] randomNames(int n) {
        String[] names = new String[n];
        for (int i = 0; i < n; i++) {
            names[i] = randomName();
        }
        return names;
    }

    private static String[] names =
            {"Willie", "Winfred", "Wythe", "Xavier", "Yale", "Yehudi", "York", "Yves", "Zachary",
                    "Zebulon", "Ziv", "Bleacher", "Beau", "Bishop", "Alva", "Alvin", "Arthur",
                    "Blair", "Blithe", "Page", "Parker", "Paddy", "Philip", "Porter", "Prescott",
                    "Primo", "Quentin", "Quennel", "Rachel", "Ralap", "Valentine", "Verne", "Vic",
                    "Vivian", "Wade", "Walker", "Will", "William",};


    public static void main(String[] args) {
        int n = 10;
        String[] list1 = randomNames(n);
        String[] list2 = randomNames(n);
        String[] list3 = randomNames(n);

        merge(list1);
        merge(list2);
        merge(list3);

        System.out.println(Arrays.toString(list1));
        System.out.println(Arrays.toString(list2));
        System.out.println(Arrays.toString(list3));

        int i = 0;
        int j = 0;
        int k = 0;
        String[] common = new String[n];
        while (i < n && j < n) {
            String s1 = list1[i];
            String s2 = list2[j];
            if (s1.compareTo(s2) < 0) i++;
            else if (s1.compareTo(s2) > 0) j++;
            else {
                common[k++] = s1;
                i++;
                j++;
            }
        }
        boolean found = false;
        for (int m = 0; m < n; m++) {
            if (common[m] == null) break;
            if (found = (binarySearch(list3, common[m]) > 0)) {
                System.out.println("共同的名字是： " + common[m]);
                break;
            }
        }
        if (!found) {
            System.out.println("没有共同的名字");
        }
    }
}

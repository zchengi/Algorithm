package org.two.five.exercises;

import edu.princeton.cs.algs4.StdOut;

/**
 * 2.5.10 创建一个数据类型Version来表示软件的版本，例如
 * 115.1.1、115.10.1、115.10.2。为他实现Comparable接口，
 * 其中115.1.1的版本低于115.10.1。
 *
 * @author cheng
 *         2018/2/2 23:06
 */
public class Exercise10 {
    static class Version implements Comparable<Version> {
        private final String str;
        private final int first;
        private final int mid;
        private final int last;

        public Version(String str) {
            this.str = str;
            String[] split = str.split("\\.");
            if (split.length != 3) throw new IllegalArgumentException("请输入正确的版本号!");

            for (int i = 0; i < 3; i++) {
                if (!split[i].matches("\\d+")) throw new IllegalArgumentException("请输入正确的版本号!");
            }
            first = Integer.parseInt(split[0]);
            mid = Integer.parseInt(split[1]);
            last = Integer.parseInt(split[2]);
        }

        @Override
        public int compareTo(Version that) {
            if (this.first > that.first) return 1;
            else if (this.first < that.first) return -1;
            else if (this.mid > that.mid) return 1;
            else if (this.mid < that.mid) return -1;
            else if (this.last > that.last) return 1;
            else if (this.last < that.last) return -1;
            else return 0;
        }

        @Override
        public String toString() {
            return str;
        }
    }

    public static void shell(Comparable[] arr) {
        int n = arr.length;

        int h = 1;
        while (n / 3 > h) h = 3 * h + 1;

        while (h >= 1) {
            for (int i = h; i < n; i++) {
                Comparable temp = arr[i];
                int j = i;
                for (; j >= h && temp.compareTo(arr[j - h]) < 0; j -= h) {
                    arr[j] = arr[j - h];
                }
                arr[j] = temp;
            }
            h /= 3;
        }
    }

    public static void main(String[] args) {
        Version[] a = {
                new Version("115.5.1"),
                new Version("114.3.2"),
                new Version("113.2.1"),
                new Version("115.4.2"),
                new Version("115.5.5"),
                new Version("114.5.1"),
                new Version("114.2.1"),
                new Version("114.5.0"),
                new Version("112.1.1"),
                new Version("111.8.1"),
                new Version("111.23.7"),
                new Version("110.3.1"),
                new Version("110.2.1"),
                new Version("117.5.1"),
                new Version("118.10.1"),
                new Version("114.11.1"),
                new Version("110.4.1"),
                new Version("113.2.4"),
                new Version("110.7.15"),
                new Version("114.9.3"),
                new Version("115.10.17"),
                new Version("116.19.18"),
                new Version("100.0.19"),
                new Version("108.10.1"),
                new Version("107.8.1"),
                new Version("107.14.1"),
                new Version("108.19.6"),
                new Version("105.15.2"),
                new Version("109.13.16"),
                new Version("112.12.7"),
                new Version("111.5.3"),
                new Version("111.7.6"),
                new Version("112.4.4"),
                new Version("108.2.2"),
                new Version("109.5.1"),
                new Version("109.9.10"),
                new Version("110.5.5"),
        };
        shell(a);
        for (Version v : a)
            StdOut.println(v);
    }
}

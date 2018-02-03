package org.two.five.exercises;

import org.two.three.learn.Quick2Ways;

import java.util.Arrays;

/**
 * 2。5.14 逆域名排序。为域名编写一个数据类型 Domain 并为它实现一个compareTo() 方法，
 * 使之能够按照逆向的域名排序。例如：cs.princeton.edu 的逆序是 edu.princeton.cs。
 * 这在网络日志处理时很有用。提示：使用s.split("\\.")将域名用点分成若干部分。
 * 编写一个 Domain 用例，从标准输入读取域名并将它们按照逆域名有序的打印出来。
 *
 * @author cheng
 *         2018/2/3 18:08
 */
public class Exercise14 {
    static class Domain implements Comparable<Object> {
        private final String reverse;
        private final String name;

        public Domain(String name) {
            this.name = name;
            reverse = reverseDomain(name);
        }

        private String reverseDomain(String name) {
            String[] split = name.split("\\.");
            StringBuilder builder = new StringBuilder();
            int n = split.length;
            for (int i = 1; i <= n; i++) {
                builder.append(split[n - i]).append(".");
            }
            builder.deleteCharAt(builder.length() - 1);
            return builder.toString();
        }

        @Override
        public String toString() {
            return reverse;
        }

        public String getReverse() {
            return reverse;
        }

        @Override
        public int compareTo(Object obj) {
            if (obj instanceof Domain) {
                Domain that = (Domain) obj;
                return this.reverse.compareTo(that.reverse) > 0 ? 1 : this.reverse.compareTo(that.reverse) < 0 ? -1 : 0;
            }
            return -1;
        }
    }

    public static void main(String[] args) {
        Domain[] domains = new Domain[10];
        domains[0] = new Domain("cs.princeton.edu");
        domains[1] = new Domain("cs.princeton.due");
        domains[2] = new Domain("cs.princeton.fde");
        domains[3] = new Domain("cs.princeton.wqd");
        domains[4] = new Domain("cs.princeton.cza");
        domains[5] = new Domain("cs.princeton.fwq");
        domains[6] = new Domain("cs.princeton.vdw");
        domains[7] = new Domain("cs.princeton.bwq");
        domains[8] = new Domain("cs.princeton.gbc");
        domains[9] = new Domain("cs.princeton.gac");
        Quick2Ways.sort(domains);
        System.out.println(Arrays.toString(domains));
    }
}

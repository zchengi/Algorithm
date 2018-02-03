package org.two.five.exercises;

import org.two.three.learn.Quick2Ways;

import java.util.Arrays;

/**
 * 2.5.16 公正的选举。为了避免对名字排在字母靠后的候选人的偏见，
 * 加州在2003年的州长选举中将所有候选人按照以下字母顺序排列：
 * R W Q O J M V A H B S G Z X N T C I E K U P D Y F L
 * 创建一个遵守这种顺序的数据类型并编写一个用例California，在它的静态方法main()中将字符串按照这种方式排序。
 * 假设所有字符串全部都是大写的。
 *
 * @author cheng
 *         2018/2/3 18:48
 */
public class Exercise16 {

    static class Name implements Comparable<Name> {

        private final String name;

        private static final String ORDER = "RWQOJMVAHBSGZXNTCIEKUPDYFL";

        public Name(String name) {
            this.name = name.toUpperCase();
        }

        @Override
        public int compareTo(Name that) {
            int n = Math.min(this.name.length(), that.name.length());
            for (int i = 0; i < n; i++) {
                int thisIndex = ORDER.indexOf(this.name.charAt(i));
                int thatIndex = ORDER.indexOf(that.name.charAt(i));
                if (thisIndex != thatIndex) return thisIndex - thatIndex;
            }
            return this.name.length() - that.name.length();
        }

        @Override
        public String toString() {
            return name;
        }

        public static Name[] names(String[] arr) {
            Name[] names = new Name[arr.length];
            for (int i = 0; i < arr.length; i++) {
                names[i] = new Name(arr[i]);
            }
            return names;
        }
    }

    private static String randomName() {
        int length = (int) (Math.random() * 6) + 4;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append((char) ('a' + (int) (Math.random() * 26)));
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        String[] arr = new String[50];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = randomName();
        }
        Name[] names = Name.names(arr);
        Quick2Ways.sort(names);
        System.out.println(Arrays.toString(names));

    }
}

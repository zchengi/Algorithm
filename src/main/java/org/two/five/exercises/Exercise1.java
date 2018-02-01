package org.two.five.exercises;

/**
 * 2.5.1 在下面这段String类型的compareTo()方法的实现中，第三行对提高运行效率有何帮助？
 * <p>
 * 第三行判断两个字符串的引用是否相同，如果相同直接相等，不用判断值是否相等。
 * 即先判断内存地址，避免了多余的操作。
 *
 * @author cheng
 *         2018/2/1 15:02
 */
public class Exercise1 {
    private String str = "";

    public int compareTo(String that) {
        // 这一行是第三行
        if (str == that) return 0;
        int n = Math.min(str.length(), that.length());
        for (int i = 0; i < n; i++) {
            if (str.charAt(i) < that.charAt(i)) return -1;
            if (str.charAt(i) > that.charAt(i)) return +1;
        }
        return str.length() - that.length();
    }

}

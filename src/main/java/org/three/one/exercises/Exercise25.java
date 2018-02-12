package org.three.one.exercises;

/**
 * 3.1.25 缓存。因为默认的contains()的实现中调用了get()，所以FrequencyCounter的内循环会将同一个键查找两三遍：
 * if (!st.contains(word)) st.put(word, 1);
 * else                    st.put(word, st.get(word) + 1);
 * 为了能够提高这个的用例代码的效率，我们可以用一种叫缓存的技术手段，即将访问最频繁的键的位置保存在一个变量中。
 * 修改SequentialSearch和BinarySearch来实现这个点子。
 *
 * @author cheng
 *         2018/2/12 20:27
 */
public class Exercise25 {
    public static void main(String[] args) {
        /*
         *
         * 声明一个保存当前使用键的缓存cache，再每次get()时，先判断该键是否与cache相等。
         *
         */
    }
}

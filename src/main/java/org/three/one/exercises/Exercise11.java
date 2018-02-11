package org.three.one.exercises;

import org.three.one.learn.BinarySearchST;

/**
 * 3.1.11 给出BinarySearchST将键 E A S Y Q U E S T I O N
 * 插入一个空符号表的过程轨迹。一共进行了多少次比较？
 *
 * @author cheng
 *         2018/2/11 13:00
 */
public class Exercise11 {
    /*

    *     插入      比较次数      符号表
    *      E           0           E
    *      A           1           A  E
    *      S           2           A  E  S
    *      Y           2           A  E  S  Y
    *      Q           2           A  E  Q  S  Y
    *      U           3           A  E  Q  S  U  Y
    *      E           3           A  E  Q  S  U  Y
    *      S           3           A  E  Q  S  U  Y
    *      T           3           A  E  Q  S  T  U  Y
    *      I           3           A  E  I  Q  S  T  U  Y
    *      O           3           A  E  I  O  Q  S  T  U  Y
    *      N           4           A  E  I  N  O  Q  S  T  U  Y
    *
    *      总比较次数 = 0 + 1 + 2 + 2 + 2 + 3 + 3 + 3 + 3 + 3 + 3 + 4 = 29
    */
    public static void main(String[] args) {
        // E A S Y Q U E S T I O N
        String[] str = {"E", "A", "S", "Y", "Q", "U", "E", "S", "T", "I", "O", "N"};

        BinarySearchST<String, Integer> st = new BinarySearchST<>();
        for (String s : str) {
            st.put(s, 1);
        }
    }
}

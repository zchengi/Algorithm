package org.three.two.exercises;

import org.three.two.learn.BinarySymbolTable;

/**
 * 3.2.1 将 E A S Y Q U E S T I O N 作为键按顺序插入一棵初始为空的二叉查找树中
 * （方便起见设第i个键对应的值为i），画出生成的二叉查找树。构造这棵树需要多少次比较？
 *
 * @author cheng
 *         2018/2/15 12:24
 */
public class Exercise1 {
    /*
     *
     * 见FlowChart 3.2.1.jpg
     * 共需要28次比较。
     * 1 + 1 + 2 + 2 + 3 + 1 + 2 + 4 + 3 + 4 + 5 = 28
     *
     */

    public static void main(String[] args) {
        BinarySymbolTable<String, Integer> bst = new BinarySymbolTable<>();
        String[] words = {"E", "A", "S", "Y", "Q", "U", "E", "S", "T", "I", "O", "N"};
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            bst.put(word, i);
        }
        for (String s : bst.keys()) {
            System.out.print(s + " ");
        }
    }
}

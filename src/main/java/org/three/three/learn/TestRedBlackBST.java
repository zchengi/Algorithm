package org.three.three.learn;

import org.three.two.learn.BinarySymbolTable;

/**
 * RedBlackBST的测试用例
 *
 * @author cheng
 *         2018/3/5 14:54
 */
public class TestRedBlackBST {
    public static void main(String[] args) {
        BinarySymbolTable<String, Integer> bst = new BinarySymbolTable<>();
        String[] words = {"E", "A", "S", "Y", "Q", "U", "T", "I", "O", "N"};

        for (int i = 0; i < words.length; i++) {
            bst.put(words[i], i);
        }

        for (String word : bst.keys()) {
            System.out.print(word + " ");
        }
    }
}

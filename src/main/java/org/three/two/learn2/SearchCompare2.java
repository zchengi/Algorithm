package org.three.two.learn2;

import org.tool.FileOperations;

import java.util.Collections;
import java.util.Vector;

/**
 * 二分搜索树的局限性
 *
 * @author cheng
 *         2018/2/22 15:58
 */
public class SearchCompare2 {
    public static void main(String[] args) {
        String filename = "src/main/java/org/three/one/text/tale.txt";
        Vector<String> words = new Vector<>();

        if (FileOperations.readFile(filename, words)) {
            System.out.println("There are totally " + words.size() + " words in " + filename);
            System.out.println();

            // 测试1：按照文本原有顺序插入进二分搜索树
            long startTime = System.currentTimeMillis();
            BinarySearchTree<String, Integer> bst = new BinarySearchTree<>();
            for (String word : words) {
                Integer res = bst.search(word);

                if (res == null) bst.insert(word, 1);
                else bst.insert(word, res + 1);
            }

            // 查看 be 一词的词频
            if (bst.contains("be")) {
                System.out.println("'be' : " + bst.search("be"));
            } else {
                System.out.println("No word 'be' in " + filename);
            }

            long endTime = System.currentTimeMillis();
            System.out.println("BST , time: " + (endTime - startTime) + "ms.");
            System.out.println();

            // 测试2：按照文本原有顺序插入顺序查找表
            startTime = System.currentTimeMillis();
            SST<String, Integer> sst = new SST<>();
            for (String word : words) {
                Integer res = sst.search(word);

                if (res == null) sst.insert(word, 1);
                else sst.insert(word, res + 1);
            }

            // 查看be一词的词频
            if (sst.contains("be")) {
                System.out.println("'be' : " + sst.search("be"));
            } else {
                System.out.println("No word 'be' in " + filename);
            }

            endTime = System.currentTimeMillis();
            System.out.println("SST , time: " + (endTime - startTime) + "ms.");
            System.out.println();

            // 测试3：将原文本排序后插入二分搜索树，查看其效率
            startTime = System.currentTimeMillis();
            BinarySearchTree<String, Integer> bst2 = new BinarySearchTree<>();
            Collections.sort(words);

            for (String word : words) {
                Integer res = bst2.search(word);

                if (res == null) bst2.insert(word, 1);
                else bst2.insert(word, res + 1);
            }
            // 查看be一词的词频
            if (bst.contains("be"))
                System.out.println("'be' : " + bst2.search("be"));
            else
                System.out.println("No word 'be' in " + filename);

            endTime = System.currentTimeMillis();
            System.out.println("BST2 , time: " + (endTime - startTime) + "ms.");
        }
    }
}

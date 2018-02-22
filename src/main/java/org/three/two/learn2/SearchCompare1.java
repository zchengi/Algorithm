package org.three.two.learn2;

import org.tool.FileOperations;

import java.util.Vector;

/**
 * 测试二分搜索树和顺序查找表之间的性能差距
 * 二分搜索树的性能远远优于顺序查找表
 *
 * @author cheng
 *         2018/2/19 13:11
 */
public class SearchCompare1 {
    public static void main(String[] args) {

        // 使用圣经作为测试用例
        String filename = "src/main/java/org/three/two/text/bible.txt";
        Vector<String> words = new Vector<>();

        if (FileOperations.readFile(filename, words)) {
            System.out.println("There are totally " + words.size() + " word in " + filename);
            System.out.println();

            // 测试 BST
            long startTime = System.currentTimeMillis();

            // 统计圣经中所有词的词频（相对简陋，只是做性能测试用）
            BinarySearchTree<String, Integer> bst = new BinarySearchTree<>();
            for (String word : words) {
                Integer res = bst.search(word);
                if (res == null) {
                    bst.insert(word, 1);
                } else bst.insert(word, res + 1);
            }

            // 输出圣经中 god 一词出现的频率
            if (bst.contains("god")) {
                System.out.println("'god' : " + bst.search("god"));
            } else {
                System.out.println("Not word 'god' in " + filename);
            }

            long endTime = System.currentTimeMillis();
            System.out.println("BST , time : " + (endTime - startTime) + "ms.");
            System.out.println();

            // 顺序查找表 SST （速度太慢，效率太低）
            startTime = System.currentTimeMillis();

            SST<String, Integer> sst = new SST<>();
            for (String word : words) {
                Integer res = sst.search(word);
                if (res == null) sst.insert(word, 1);
                else sst.insert(word, res + 1);
            }

            if (sst.contains("god")) {
                System.out.println("'god' : " + sst.search("god"));
            } else {
                System.out.println("No word 'god' in " + filename);
            }

            endTime = System.currentTimeMillis();
            System.out.println("SST , time: " + (endTime - startTime) + "ms.");
        }
    }
}

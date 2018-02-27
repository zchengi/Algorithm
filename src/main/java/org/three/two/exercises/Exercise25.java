package org.three.two.exercises;

import org.three.two.learn.BinarySymbolTable;

import java.util.Arrays;

/**
 * 3.2.25 完美平衡。编写一段程序，用一组键构造一颗和二分查找等价的二叉查找树。
 * 也就是说，在这棵树中查找任意键所产生的比较序列和在这组键中使用二分查找所产生的比较序列完全相同。
 *
 * @author cheng
 *         2018/2/27 10:39
 */
public class Exercise25 {
    private static BinarySymbolTable<String, Integer> prefect(BinarySymbolTable<String, Integer> bst, String[] a) {
        Arrays.sort(a);
        return prefect(bst, a, 0, a.length - 1);
    }

    private static BinarySymbolTable<String, Integer> prefect(BinarySymbolTable<String, Integer> bst, String[] a, int lo, int hi) {
        if (hi < lo) return null;

        int mid = lo + (hi - lo) / 2;
        bst.put(a[mid], mid);
        prefect(bst, a, lo, mid - 1);
        prefect(bst, a, mid + 1, hi);

        return bst;
    }

    public static void main(String[] args) {

        String[] words = {"P", "E", "R", "F", "C", "T", "B", "I", "N"};
        BinarySymbolTable<String, Integer> bst = new BinarySymbolTable<>();

        BinarySymbolTable<String, Integer> bst2 = prefect(bst, words);

        for (String str : bst2.keys()) {
            System.out.println(str + " " + bst.get(str));
        }
    }
}

package org.three.two.exercises;

import org.three.two.learn.BinarySymbolTable;

/**
 * 2.3.23 delete()方法符合交换律吗？（先删除x后删除y和先删除y后删除x能够得到相同的结果吗？）
 *
 * @author cheng
 *         2018/2/26 20:29
 */
public class Exercise23 {
    /*
     *
     * 不符合交换律；
     *
     * 对如下树进行删除操作：
     *      5
     *    2    8
     *       7
     *
     * 删除 5 2 后的树：
     *     7
     *       8
     *
     * 删除 2 5 后的树：
     *    8
     *      7
     *
     */
    public static void main(String[] args) {
        BinarySymbolTable<Integer, Integer> bst = new BinarySymbolTable<>();
        bst.put(5, 5);
        bst.put(2, 2);
        bst.put(8, 8);
        bst.put(7, 7);
        bst.delete(2);
        bst.delete(5);
    }
}

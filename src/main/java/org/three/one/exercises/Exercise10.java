package org.three.one.exercises;

/**
 * 3.1.10 给出用SequentialSearchST将键 E A S Y Q U E S T I O N
 * 插入一个空符号表的过程轨迹。一共进行了多少次比较？
 *
 * @author cheng
 *         2018/2/11 12:46
 */
public class Exercise10 {
    /*

     *     插入      比较次数      符号表
     *      E           0           E
     *      A           1           A -> E
     *      S           2           S -> A -> E
     *      Y           3           Y -> S -> A -> E
     *      Q           4           Q -> Y -> S -> A -> E
     *      U           5           U -> Q -> Y -> S -> A -> E
     *      E           6           U -> Q -> Y -> S -> A -> E
     *      S           4           U -> Q -> Y -> S -> A -> E
     *      T           6           T -> U -> Q -> Y -> S -> A -> E
     *      I           7           I -> T -> U -> Q -> Y -> S -> A -> E
     *      O           8           O -> I -> T -> U -> Q -> Y -> S -> A -> E
     *      N           9           N -> O -> I -> T -> U -> Q -> Y -> S -> A -> E
     *
     *      总比较次数 = 0 + 1 + 2 + 3 + 4 + 5 + 6 + 4 + 6 + 7 + 8 + 9 = 55
     */
}

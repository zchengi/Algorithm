package org.three.two.learn2;

/**
 * 测试二分搜索树的前中后序遍历、层序遍历
 *
 * @author cheng
 *         2018/2/21 17:00
 */
public class TestTraverseBST {
    public static void main(String[] args) {

        BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<>();

        // 取n个取值范围在[0...m)的随机整数放进二分搜索树中
        int n = 10;
        int m = 100;
        for (int i = 0; i < n; i++) {
            Integer key = (int) (Math.random() * m);
            // 为了测试方便，value值与key值一样
            bst.insert(key, key);
            System.out.println(key + " ");
        }
        System.out.println();

        // 测试二分搜索树的size()
        System.out.println("size： " + bst.size());
        System.out.println();

        //测试二分搜索树的前序遍历 preOrder
        System.out.println("preOrder：");
        bst.preOrder();
        System.out.println();

        //测试二分搜索树的中序遍历 inOrder
        System.out.println("inOrder：");
        bst.inOrder();
        System.out.println();

        //测试二分搜索树的后序遍历 postOrder
        System.out.println("postOrder：");
        bst.postOrder();
        System.out.println();

        // 测试二分搜索树的层序遍历 levelOrder
        System.out.println("levelOrder: ");
        bst.levelOrder();
        System.out.println();
    }
}
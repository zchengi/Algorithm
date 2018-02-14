package org.three.two.learn;

/**
 * BinarySymbolTable的测试用例
 *
 * @author cheng
 *         2018/2/14 18:10
 */
public class TestBST {
    public static void main(String[] args) {
        BinarySymbolTable<Integer, String> bst = new BinarySymbolTable<>();

        // put
        bst.put(4, "4");
        bst.put(1, "1");
        bst.put(2, "2");
        bst.put(5, "5");
        bst.put(9, "9");
        bst.put(6, "6");
        bst.put(3, "3");

        // get()
        System.out.println(bst.get(4) + "\n");


        // delete()
        bst.delete(4);
        System.out.println(bst.get(4) + "\n");

        // deleteMin()
        System.out.println(bst.get(bst.min()));
        bst.deleteMin();
        System.out.println(bst.get(bst.min()) + "\n");

        // deleteMax()
        System.out.println(bst.get(bst.max()));
        bst.deleteMax();
        System.out.println(bst.get(bst.max()) + "\n");

        // select()
        System.out.println(bst.select(0) + " " + bst.min() + "\n");

        // rank()
        System.out.println(bst.select(bst.rank(2)));
        // floor()
        System.out.println(bst.floor(2));
        // ceiling()
        System.out.println(bst.ceiling(2) + "\n");

        // min() && max()
        System.out.println("min：" + bst.min() + " max：" + bst.max() + "\n");

        // keys()
        for (Integer i : bst.keys()) {
            System.out.print(i + ":" + bst.get(i) + " ");
        }

        // contains()
        System.out.println("\n\n是否包含 key 2：" + bst.contains(2) + "\n");

        // size()
        System.out.println(bst.size() + "\n");

        // height()
        System.out.println(bst.height() + "\n");

        // levelOrder()
        for (Integer i : bst.levelOrder()) {
            System.out.print(i + ":" + bst.get(i) + " ");
        }
    }
}










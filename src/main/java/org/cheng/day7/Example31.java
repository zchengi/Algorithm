package org.cheng.day7;

/**
 * 1.3.31 测试类
 *
 * @author cheng
 *         2017/10/22 15:15
 */
public class Example31 {
    public static void main(String[] args) {
        //     testInsertAsFirst();
        //     testInsertAsLast();
//        testInsertAsFirstAndLast();
//        testDeleteFirst();
        //     testDeleteLast();
//        testSearch();
       testInsertBeforeAndAfter();
//        testDeleteNode();
    }

    public static void testInsertAsFirst() {
        System.out.println("--------------------");
        System.out.println("testInsertAsFirst:");
        DoubleList<String> dl = new DoubleList<String>();
        DoubleList.insertAsFirst(dl, "a");
        DoubleList.insertAsFirst(dl, "b");
        DoubleList.insertAsFirst(dl, "c");
        DoubleList.insertAsFirst(dl, "d");
        System.out.println("After insert a, b, c, d:");
        DoubleList.print(dl);
    }

    public static void testInsertAsLast() {
        System.out.println("--------------------");
        System.out.println("testInsertAsLast:");
        DoubleList<String> dl = new DoubleList<String>();
        DoubleList.insertAsLast(dl, "a");
        DoubleList.insertAsLast(dl, "b");
        DoubleList.insertAsLast(dl, "c");
        DoubleList.insertAsLast(dl, "d");
        System.out.println("After insert a, b, c, d:");
        DoubleList.print(dl);
    }

    public static void testInsertAsFirstAndLast() {
        System.out.println("--------------------");
        System.out.println("testInsertAsFirstAndLast:");
        DoubleList<String> dl = new DoubleList<String>();
        DoubleList.insertAsLast(dl, "a");
        DoubleList.insertAsLast(dl, "b");
        DoubleList.insertAsLast(dl, "c");
        DoubleList.insertAsLast(dl, "d");
        DoubleList.insertAsFirst(dl, "1");
        DoubleList.insertAsFirst(dl, "2");
        DoubleList.insertAsFirst(dl, "3");
        DoubleList.insertAsFirst(dl, "4");
        System.out.println("After insertAsLast a, b, c, d and insertAsFirst 1, 2, 3, 4:");
        DoubleList.print(dl);
    }

    public static void testDeleteFirst() {
        System.out.println("--------------------");
        System.out.println("testDeleteFirst:");
        DoubleList<String> dl = new DoubleList<String>();
        DoubleList.insertAsLast(dl, "a");
        DoubleList.insertAsLast(dl, "b");
        DoubleList.insertAsLast(dl, "c");
        DoubleList.insertAsLast(dl, "d");
        System.out.println("list is: ");
        DoubleList.print(dl);
        System.out.println("After deleteFrist:");
        DoubleList.deleteFirst(dl);
        DoubleList.print(dl);

        System.out.println("After deleteFrist again:");
        DoubleList.deleteFirst(dl);
        DoubleList.print(dl);

        System.out.println("After deleteFrist again:");
        DoubleList.deleteFirst(dl);
        DoubleList.print(dl);

        System.out.println("After deleteFrist again:");
        DoubleList.deleteFirst(dl);
        DoubleList.print(dl);

        System.out.println("After deleteFrist again:");
        DoubleList.deleteFirst(dl);
        DoubleList.print(dl);
    }

    public static void testDeleteLast() {
        System.out.println("--------------------");
        System.out.println("testDeleteLast:");
        DoubleList<String> dl = new DoubleList<String>();
        DoubleList.insertAsLast(dl, "a");
        DoubleList.insertAsLast(dl, "b");
        DoubleList.insertAsLast(dl, "c");
        DoubleList.insertAsLast(dl, "d");
        System.out.println("list is: ");
        DoubleList.print(dl);
        System.out.println("After deleteLast:");
        DoubleList.deleteLast(dl);
        DoubleList.print(dl);

        System.out.println("After deleteLast again:");
        DoubleList.deleteLast(dl);
        DoubleList.print(dl);

        System.out.println("After deleteLast again:");
        DoubleList.deleteLast(dl);
        DoubleList.print(dl);

        System.out.println("After deleteLast again:");
        DoubleList.deleteLast(dl);
        DoubleList.print(dl);

        System.out.println("After deleteLast again:");
        DoubleList.deleteLast(dl);
        DoubleList.print(dl);
    }

    public static void testSearch() {
        System.out.println("--------------------");
        System.out.println("testSearch:");
        DoubleList<String> dl = new DoubleList<String>();
        DoubleList.insertAsFirst(dl, "a");
        DoubleList.insertAsFirst(dl, "b");
        DoubleList.insertAsFirst(dl, "c");
        DoubleList.insertAsFirst(dl, "d");

        System.out.println("List is :");
        DoubleList.print(dl);

        DoubleList.DoubleNode<String> node = DoubleList.search(dl, "c");
        System.out.println("Search c: ");
        if (node != null) {
            System.out.println("Find " + node.item);
        } else {
            System.out.println("Not found");
        }

        node = DoubleList.search(dl, "d");
        System.out.println("Search d: ");
        if (node != null) {
            System.out.println("Find " + node.item);
        } else {
            System.out.println("Not found");
        }

        node = DoubleList.search(dl, "a");
        System.out.println("Search a: ");
        if (node != null) {
            System.out.println("Find " + node.item);
        } else {
            System.out.println("Not found");
        }

        node = DoubleList.search(dl, "x");
        System.out.println("Search x: ");
        if (node != null) {
            System.out.println("Find " + node.item);
        } else {
            System.out.println("Not found");
        }
    }

    public static void testInsertBeforeAndAfter() {
        System.out.println("--------------------");
        System.out.println("testInsertBeforeAndAfter:");
        DoubleList<String> dl = new DoubleList<String>();
        DoubleList.insertAsLast(dl, "a");
        DoubleList.insertAsLast(dl, "b");
        DoubleList.insertAsLast(dl, "c");
        DoubleList.insertAsLast(dl, "d");

        System.out.println("List is :");
        DoubleList.print(dl);

        DoubleList.DoubleNode<String> node = DoubleList.search(dl, "c");
        DoubleList.insertAfter(dl, node, "C");
        System.out.println("After insert C after c:");
        DoubleList.print(dl);

        node = DoubleList.search(dl, "d");
        DoubleList.insertAfter(dl, node, "D");
        System.out.println("After insert D after d:");
        DoubleList.print(dl);

        node = DoubleList.search(dl, "c");
        DoubleList.insertBefore(dl, node, "B");
        System.out.println("After insert B before c:");
        DoubleList.print(dl);

        node = DoubleList.search(dl, "a");
        DoubleList.insertBefore(dl, node, "A");
        System.out.println("After insert A before a:");
        DoubleList.print(dl);
    }

    public static void testDeleteNode() {
        System.out.println("--------------------");
        System.out.println("testDeleteNode:");
        DoubleList<String> dl = new DoubleList<String>();
        DoubleList.insertAsLast(dl, "a");
        DoubleList.insertAsLast(dl, "b");
        DoubleList.insertAsLast(dl, "c");
        DoubleList.insertAsLast(dl, "d");

        System.out.println("List is :");
        DoubleList.print(dl);

        DoubleList.DoubleNode<String> node = DoubleList.search(dl, "c");
        DoubleList.deleteNode(dl, node);
        System.out.println("After delete c:");
        DoubleList.print(dl);

        node = DoubleList.search(dl, "a");
        DoubleList.deleteNode(dl, node);
        System.out.println("After delete a:");
        DoubleList.print(dl);

        node = DoubleList.search(dl, "d");
        DoubleList.deleteNode(dl, node);
        System.out.println("After delete d:");
        DoubleList.print(dl);
    }
}

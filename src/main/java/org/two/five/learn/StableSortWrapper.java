package org.two.five.learn;

import org.tool.SortTestHelper;
import org.two.three.learn.Quick2Ways;

/**
 * 排序算法强制稳定的包装类
 *
 * @author cheng
 *         2018/2/8 14:46
 */
public class StableSortWrapper<Item extends Comparable<Item>> implements Comparable<StableSortWrapper> {

    private Item item;
    private int index;

    public StableSortWrapper(Item item, int index) {
        this.item = item;
        this.index = index;
    }

    public static int[] getIndexes(StableSortWrapper[] wrappers) {
        int[] indexes = new int[wrappers.length];
        for (int i = 0; i < wrappers.length; i++) {
            indexes[i] = wrappers[i].index;
        }
        return indexes;
    }

    @Override
    public String toString() {
        return item + " - " + index;
    }

    @Override
    public int compareTo(StableSortWrapper that) {
        if (this.item.compareTo((Item) that.item) < 0) {
            return -1;
        } else if (this.item.compareTo((Item) that.item) > 0) {
            return 1;
        } else if (this.index < that.index) {
            return -1;
        } else if (this.index > that.index) {
            return 1;
        } else return 0;
    }

    public static void main(String[] args) {
        int n = 1000000;
        Integer[] arr = SortTestHelper.generateRandomArray(n, 0, 5);

        StableSortWrapper<Integer>[] wrappers = new StableSortWrapper[n];
        for (int i = 0; i < n; i++) {
            wrappers[i] = new StableSortWrapper<Integer>(arr[i], i);
        }

        Quick2Ways.sort(wrappers);

        System.out.println("是否有序： " + SortTestHelper.isSorted(wrappers));
        System.out.println("是否稳定： " + SortTestHelper.isSortedByIndex(wrappers, getIndexes(wrappers)));
    }
}

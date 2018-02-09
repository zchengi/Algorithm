package org.two.four.learn2;

import java.util.Arrays;

/**
 * 最大索引堆
 * 在堆的有关操作中，需要比较堆中元素的大小，所以 Item 需要 extends Comparable
 * 所有比较data的部分都必须套上indexes索引再比较
 *
 * @author cheng
 *         2018/1/30 10:45
 */
public class IndexMaxHeap<Item extends Comparable> {

    /**
     * 最大索引堆中的数据
     */
    private Item[] data;

    /**
     * 最大索引堆中的索引
     * 记录的是按照堆排序的索引，原来数组的元素位置不改变
     */
    private int[] indexes;

    /**
     * 元素数量
     */
    private int count;

    /**
     * 可容纳总数
     */
    private int capacity;

    /**
     * 构造一个空堆，可容纳capacity个元素
     */
    public IndexMaxHeap(int capacity) {
        data = (Item[]) new Comparable[capacity + 1];
        indexes = new int[capacity + 1];
        count = 0;
        this.capacity = capacity;
    }

    /**
     * 向最大索引堆中插入一个新的元素, 新元素的索引为i, 元素为item
     * 传入的i对用户而言，是从0索引的
     */
    public void insert(int i, Item item) {
        assert count + 1 <= capacity;
        assert i + 1 >= 1 && i + 1 <= capacity;

        data[++i] = item;
        indexes[++count] = i;
        shiftUp(count);
    }

    /**
     * 从最大索引堆中取出堆顶元素, 即索引堆中所存储的最大数据
     */
    public Item extractMax() {
        assert count > 0;

        Item ret = data[indexes[1]];
        swapIndexes(1, count--);

        shiftDown(1);
        return ret;
    }

    /**
     * 从最大索引堆中取出堆顶元素的索引
     */
    public int extractMaxIndex() {
        assert count > 0;

        // 因为外部的索引是从0开始，indexes从1开始
        int ret = indexes[1] - 1;
        swapIndexes(1, count--);

        shiftDown(1);
        return ret;
    }

    /**
     * 获取最大索引堆中的堆顶元素
     */
    public Item getMax() {
        assert count > 0;
        return data[indexes[1]];
    }

    // 获取最大索引堆中的堆顶元素的索引
    public int getMaxIndex() {
        assert count > 0;
        return indexes[1] - 1;
    }

    /**
     * 获取最大索引堆中索引为i的元素
     */
    public Item getItem(int i) {
        assert i + 1 >= 1 && i + 1 <= capacity;
        return data[i + 1];
    }

    /**
     * 将最大索引堆中索引为i的元素修改为newItem
     * o(n)
     */
    public void change(int i, Item newItem) {
        data[++i] = newItem;

        // 为了维持最大堆的性质
        // 找到indexes[j] = i, j表示data[i]在堆中的位置
        // 之后shiftUp(j)，在shiftDown(j)
        for (int j = 1; j <= count; j++) {
            if (indexes[j] == i) {
                shiftUp(j);
                shiftDown(j);
                return;
            }
        }
    }

    /**
     * 上移排序
     */
    private void shiftUp(int k) {
        // 索引堆中, 数据之间的比较根据data的大小进行比较, 但实际操作的是索引
        // data不产生任何的变动
        while (k > 1 && less(indexes[k / 2], indexes[k])) {
            swapIndexes(k, k / 2);
            k = k / 2;
        }
    }

    /**
     * 下移排序
     */
    private void shiftDown(int k) {
        while (2 * k <= count) {
            int j = 2 * k;
            if (j < count && less(indexes[j], indexes[j + 1])) {
                j++;
            }
            if (!less(indexes[k], indexes[j])) {
                break;
            }
            swapIndexes(k, j);
            k = j;
        }
    }

    /**
     * 交换索引堆的索引i和j
     */
    private void swapIndexes(int i, int j) {
        int temp = indexes[i];
        indexes[i] = indexes[j];
        indexes[j] = temp;
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    private boolean less(int i, int j) {
        return data[i].compareTo(data[j]) < 0;
    }

    /**
     * 测试索引堆中的索引数组index
     * 注意：这个测试在向堆中插入元素以后, 不进行extract操作有效
     */
    public boolean testIndexes() {
        int[] copyIndexes = new int[count + 1];
        System.arraycopy(indexes, 0, copyIndexes, 0, count + 1);

        copyIndexes[0] = 0;
        // 重置索引数组
        Arrays.sort(copyIndexes);

        // 在索引堆中的索引进行排序后，应该正好是1...count这个顺序的值
        boolean res = true;
        for (int i = 1; i <= count; i++) {
            if (copyIndexes[i - 1] + 1 != copyIndexes[i]) {
                res = false;
                break;
            }
        }
        if (!res) {
            System.out.println("Error!");
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int N = 1000000;
        IndexMaxHeap<Integer> indexMaxHeap = new IndexMaxHeap<>(N);

        for (int i = 0; i < N; i++) {
            indexMaxHeap.insert(i, (int) (Math.random() * N));
        }
        System.out.println("最大索引堆的索引：" + indexMaxHeap.testIndexes());

        // 验证排序正确性：IndexMaxHeapSort.java
    }
}

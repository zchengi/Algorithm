package org.four.three.learn2;

import org.two.four.learn2.IndexMinHeap;

import java.util.NoSuchElementException;

/**
 * 最小索引堆
 *
 * @author cheng
 *         2018/3/14 20:40
 */
public class IndexMInHeap<Item extends Comparable> {

    /**
     * 最小索引堆中的数据
     */
    private Item[] data;

    /**
     * 最小索引堆中的索引，indexes[x] = i 表示索引i在x的位置
     */
    private int[] indexes;

    /**
     * 最小索引堆中的反向索引，reverse[i] = x 表示索引i在x的位置
     */
    private int[] reverse;

    private int count;

    private int capacity;

    public IndexMInHeap(int capacity) {
        data = (Item[]) new Comparable[capacity + 1];
        indexes = new int[capacity + 1];
        reverse = new int[capacity + 1];
        for (int i = 0; i < capacity; i++) {
            reverse[i] = 0;
        }
        count = 0;
        this.capacity = capacity;
    }

    /**
     * 想最小索引堆中插入一个新的元素，新元素的索引为i，元素为item
     * 传入的i对用户而言，是从0索引的
     */
    public void insert(int i, Item item) {

        if (count + 1 > capacity) {
            throw new NoSuchElementException("Heap overflow.");
        }
        if (i + 1 < 1 && i + 1 > capacity) {
            throw new IllegalArgumentException("Called insert() with a invalid argument " + i);
        }
        if (contain(i)) {
            throw new IllegalArgumentException("Called insert() with a wrong argument " + i);
        }

        data[++i] = item;
        indexes[count + 1] = i;
        reverse[i] = count + 1;
        count++;

        shiftUp(count);
    }

    /**
     * 从最小索引堆中取出堆顶元素，即索引堆中所存储的最小元素
     */
    public Item extractMin() {

        if (count < 1) {
            throw new NoSuchElementException("Heap underflow.");
        }

        Item ret = data[indexes[1]];
        swapIndexes(1, count);
        count--;
        shiftDown(1);

        return ret;
    }

    /**
     * 从最小索引堆中取出堆顶元素的索引
     */
    public int extractMinIndex() {

        if (count < 1) {
            throw new NoSuchElementException("Heap underflow.");
        }

        int ret = indexes[1] - 1;
        swapIndexes(1, count);
        reverse[indexes[count]] = 0;
        count--;
        shiftDown(1);

        return ret;
    }

    /**
     * 获取最小索引堆中的堆顶元素
     */
    public Item getMin() {

        if (count < 1) {
            throw new NoSuchElementException("Heap underflow.");
        }

        return data[indexes[1]];
    }

    /**
     * 获取最小索引堆中的堆顶元素的索引
     */
    public int getMinIndex() {

        if (count < 1) {
            throw new NoSuchElementException("Heap underflow.");
        }

        return indexes[1] - 1;
    }

    /**
     * 查看i所在的位置是否存在元素
     */
    public boolean contain(int i) {

        if (i + 1 < 1 && i + 1 > capacity) {
            throw new IllegalArgumentException("Called contain() with a invalid argument " + i);
        }

        return reverse[i + 1] != 0;
    }

    /**
     * 获取最小索引堆中索引为i的元素
     */
    public Item getItem(int i) {

        if (!contain(i)) {
            throw new IllegalArgumentException("Called getItem() with a invalid argument" + i);
        }

        return data[i + 1];
    }

    /**
     * 将最小索引堆中索引为i的元素修改为newItem
     */
    public void change(int i, Item newItem) {

        if (!contain(i)) {
            throw new IllegalArgumentException("Called change() with a invalid argument " + i);
        }

        data[++i] = newItem;


        //有了reverse之后，可以非常简单的通过reverse直接定位索引i在indexes中的位置
        shiftUp(reverse[i]);
        shiftDown(reverse[i]);
    }

    private void swapIndexes(int i, int j) {
        int t = indexes[i];
        indexes[i] = indexes[j];
        indexes[j] = t;

        reverse[indexes[i]] = i;
        reverse[indexes[j]] = j;
    }

    private void shiftUp(int k) {

        while (k > 1 && data[indexes[k / 2]].compareTo(data[indexes[k]]) > 0) {
            swapIndexes(k, k / 2);
            k /= 2;
        }
    }

    private void shiftDown(int k) {

        while (2 * k <= count) {
            int j = 2 * k;
            if (j + 1 <= count && data[indexes[j + 1]].compareTo(data[indexes[j]]) < 0) {
                j++;
            }
            if (data[indexes[k]].compareTo(data[indexes[j]]) <= 0) {
                break;
            }
            swapIndexes(k, j);
            k = j;
        }
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public static void main(String[] args) {

        int N = 1000000;
        IndexMinHeap<Integer> indexMinHeap = new IndexMinHeap<>(N);
        for (int i = 0; i < N; i++) {
            indexMinHeap.insert(i, (int) (Math.random() * N));
        }

        // 将indexMinHeap中的数据逐渐使用extractMin取出来
        // 取出来的顺序应该是按照从小到大的顺序取出来的
        Integer[] arr = new Integer[N];
        for (int i = 0; i < N; i++) {
            arr[i] = indexMinHeap.extractMin();
        }

        // 确保arr数组是从小到大排列的
        for (int i = 1; i < N; i++) {
            if (arr[i] < arr[i - 1]) {
                System.out.println("数组排序失败.");
            }
        }
        System.out.println("数组排序成功.");
    }
}




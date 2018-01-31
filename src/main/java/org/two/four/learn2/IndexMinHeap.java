package org.two.four.learn2;

import java.util.NoSuchElementException;

/**
 * 最小索引堆
 *
 * @author cheng
 *         2018/1/31 10:20
 */
public class IndexMinHeap<Item extends Comparable> {
    /**
     * 最小索引堆中的数据
     */
    private Item[] data;

    /**
     * 最小索引堆中的索引, indexes[x] = i 表示索引i在x的位置
     */
    private int[] indexes;

    /**
     * 最小索引堆中的反向索引, reverse[i] = x 表示索引i在x的位置
     */
    private int[] reverse;

    private int count;

    private int capacity;

    public IndexMinHeap() {
        this(10);
    }

    /**
     * 构造函数, 构造一个空堆, 可容纳capacity个元素
     */
    public IndexMinHeap(int capacity) {
        data = (Item[]) new Comparable[capacity + 1];
        indexes = new int[capacity + 1];
        reverse = new int[capacity + 1];
        for (int i = 0; i <= capacity; i++) {
            reverse[i] = 0;
        }

        count = 0;
        this.capacity = capacity;
    }

    /**
     * 向最小索引堆中插入一个新的元素, 新元素的索引为i, 元素为item
     * 传入的i对用户而言,是从0索引的
     */
    public void insert(int i, Item item) {
        if (i < 0 || i > capacity) {
            throw new IllegalArgumentException("非法参数!");
        }
        if (count == capacity) {
            resize(count * 2);
        }
        if (contain(i)) {
            throw new IllegalArgumentException("该索引已被使用!");
        }

        data[++i] = item;
        indexes[++count] = i;
        reverse[i] = count;
        shiftUp(count);
    }

    /**
     * 从最小索引堆中取出堆顶元素, 即索引堆中所存储的最小数据
     */
    public Item extractMin() {
        if (count == 0) {
            throw new NoSuchElementException("堆中已经没有元素了!");
        }

        Item res = data[indexes[1]];
        swapIndexes(1, count);
        reverse[indexes[count]] = 0;
        count--;

        shiftDown(1);
        // 不能减少容量。
       /* if (count == capacity / 4) {
            resize(capacity / 2);
        }*/
        return res;
    }

    /**
     * 从最小索引堆中取出堆顶元素的索引
     */
    public int extractMinIndex() {
        if (count == 0) {
            throw new NoSuchElementException("堆中已经没有元素了!");
        }

        int res = indexes[1] - 1;
        swapIndexes(1, count);
        reverse[indexes[count]] = 0;
        count--;

        shiftDown(1);
        return res;
    }

    /**
     * 获取最小堆中的堆顶元素
     */
    public Item getMin() {
        if (count == 0) {
            throw new NoSuchElementException("堆中已经没有元素了!");
        }
        return data[indexes[1]];
    }

    /**
     * 获取最小索引堆中的堆顶元素的索引
     */
    public int getMinIndex() {
        if (count == 0) {
            throw new NoSuchElementException("堆中已经没有元素了!");
        }
        return indexes[1] - 1;
    }

    /**
     * 获取最小索引堆中索引为i的元素
     */
    public Item getItem(int i) {
        if (!contain(i)) {
            throw new IllegalArgumentException("该索引没有元素!");
        }
        return data[i + 1];
    }

    /**
     * 将最小索引堆中索引为i的元素修改为newItem
     */
    public void change(int i, Item newItem) {
        if (!contain(i)) {
            throw new IllegalArgumentException("该索引没有元素!");
        }

        data[++i] = newItem;
        // 有了 reverse 之后,
        // 我们可以非常简单的通过reverse直接定位索引i在indexes中的位置
        shiftUp(reverse[i]);
        shiftDown(reverse[i]);
    }

    /**
     * 索引堆中, 数据之间的比较根据data的大小进行比较, 但实际操作的是索引
     */
    private void shiftUp(int k) {
        while (k > 1 && less(data[indexes[k]], data[indexes[k / 2]])) {
            swapIndexes(k, k / 2);
            k /= 2;
        }
    }

    /**
     * 索引堆中, 数据之间的比较根据data的大小进行比较, 但实际操作的是索引
     */
    private void shiftDown(int k) {
        while (2 * k <= count) {
            int j = 2 * k;
            if (j < count && less(data[indexes[j + 1]], data[indexes[j]])) {
                j++;
            }
            if (!less(data[indexes[j]], data[indexes[k]])) {
                break;
            }

            swapIndexes(k, j);
            k = j;
        }
    }

    /**
     * 交换索引堆的索引i和j
     * 由于有了反向索引reverse数组，
     * indexes数组发生改变后，相应的就需要维护reverse数组
     */
    private void swapIndexes(int i, int j) {
        int temp = indexes[i];
        indexes[i] = indexes[j];
        indexes[j] = temp;

        reverse[indexes[i]] = i;
        reverse[indexes[j]] = j;
    }

    /**
     * 查看索引i所在的位置是否存在元素
     */
    private boolean contain(int i) {
        if (i < 0 || i + 1 > capacity) {
            throw new IllegalArgumentException("非法参数!");
        }
        return reverse[i + 1] != 0;
    }

    /**
     * 元素大小比较
     */
    private boolean less(Item i, Item j) {
        return i.compareTo(j) < 0;
    }

    /**
     * 堆扩容
     */
    private void resize(int newCapacity) {
        Item[] dataCopy = (Item[]) new Comparable[newCapacity + 1];
        int[] indexesCopy = new int[newCapacity + 1];
        int[] reverseCopy = new int[newCapacity + 1];
        System.arraycopy(data, 0, dataCopy, 0, capacity + 1);
        System.arraycopy(indexes, 0, indexesCopy, 0, capacity + 1);
        System.arraycopy(reverse, 0, reverseCopy, 0, capacity + 1);

        data = dataCopy;
        indexes = indexesCopy;
        reverse = reverseCopy;
        capacity = newCapacity;
    }

    /**
     * 返回索引堆中的元素个数
     */
    public int size() {
        return count;
    }

    /**
     * 返回一个布尔值, 表示索引堆中是否为空
     */
    public boolean isEmpty() {
        return count == 0;
    }

    public static void main(String[] args) {
        int n = 50;
        IndexMinHeap<Integer> indexMinHeap = new IndexMinHeap<>();
        for (int i = 0; i < n; i++) {
            indexMinHeap.insert(i, (int) (Math.random() * n));
        }

        // 将minHeap中的数据逐渐使用extractMin取出来，取出来的顺序应该是按照从小到大的顺序取出来的
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = indexMinHeap.extractMin();
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        // 确保arr数组是从小到大排列的
        for (int i = 1; i < n; i++) {
            if (arr[i] < arr[i - 1]) {
                System.out.println("数组排序失败!");
            }
        }
    }
}

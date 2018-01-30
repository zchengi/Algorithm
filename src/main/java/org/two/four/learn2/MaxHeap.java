package org.two.four.learn2;

/**
 * 数据结构：最大堆
 * 在堆的有关操作中，需要比较堆中元素的大小，所以 Item 需要 extends Comparable
 *
 * @author cheng
 *         2018/1/30 10:45
 */
public class MaxHeap<Item extends Comparable> {

    /**
     * 堆中的元素
     */
    Item[] data;

    /**
     * 元素数量
     */
    int count;

    /**
     * 可容纳总数
     */
    private int capacity;

    /**
     * 构造一个空堆，可容纳capacity个元素
     */
    public MaxHeap(int capacity) {
        data = (Item[]) new Comparable[capacity + 1];
        count = 0;
        this.capacity = capacity;
    }

    /**
     * 通过一个给定数组创建一个最大堆，使用heapify方法
     * 时间复杂度为O(n)
     */
    public MaxHeap(Item[] arr) {
        int n = arr.length;
        data = (Item[]) new Comparable[n + 1];
        capacity = n;

        // 使用内存复制，省去大量的数组寻址访问等时间
        System.arraycopy(arr, 0, data, 1, n);
        count = n;

        for (int i = count / 2; i >= 1; i--) {
            shiftDown(i);
        }
    }

    public MaxHeap() {
        this(10);
    }

    /**
     * 加入新元素
     */
    public void insert(Item item) {
        assert count + 1 <= capacity;
        data[++count] = item;
        shiftUp(count);
    }

    /**
     * 取出最大值
     */
    public Item extractMax() {
        assert count > 0;
        Item ret = data[1];
        swap(1, count--);
        shiftDown(1);
        return ret;
    }

    /**
     * 上移排序
     */
    private void shiftUp(int k) {
        while (k > 1 && less(k / 2, k)) {
            swap(k, k / 2);
            k = k / 2;
        }
    }

    /**
     * 下移排序
     */
    private void shiftDown(int k) {
        while (2 * k <= count) {
            int j = 2 * k;
            if (j < count && less(j, j + 1)) {
                j++;
            }
            if (!less(k, j)) {
                break;
            }
            swap(k, j);
            k = j;
        }
    }

    private void swap(int i, int j) {
        Item temp = data[i];
        data[i] = data[j];
        data[j] = temp;
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

    public static void main(String[] args) {
        MaxHeap<Integer> maxHeap = new MaxHeap<>(100);

        // 堆中元素个数
        int n = 50;
        // 堆中元素取值
        int m = 100;
        for (int i = 0; i < n; i++) {
            maxHeap.insert((int) (Math.random() * m));
        }

        Integer[] arr = new Integer[n];

        // 将maxHeap中的数据逐渐使用extractMax取出来，取出来的顺序应该是按照从大到小的顺序取出来的
        for (int i = 0; i < n; i++) {
            arr[i] = maxHeap.extractMax();
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        // 确保arr数组是从大到小排列的
        for (int i = 1; i < n; i++) {
            assert arr[i - 1] >= arr[i];
        }
    }
}

package org.two.five.exercises;

import org.tool.SortTestHelper;

import java.util.NoSuchElementException;

/**
 * 2.5.24 稳定优先队列。实现一个稳定的优先队列（将重复的元素按照它们被插入的顺序返回）。
 * 分析：参考2.5.18。 先插入的先出去，在比较的时候加入判断索引即可。
 * 第一种方法：使用stableSortWrapper类型封装数据，添加新索引比较；
 * 第二种方法：在优先队列内部新增一个索引数组，记录排序好的索引；
 * 当两个值相等时，判断索引小的在前，索引大的在后。
 *
 * @author cheng
 *         2018/2/7 23:52
 */
public class Exercise24 {

    static class StableMaxPQ<Item extends Comparable<Item>> {
        private int indexes[];
        private Item[] data;
        private int count;

        public StableMaxPQ() {
            this(10);
        }

        public StableMaxPQ(int n) {
            indexes = new int[n + 1];
            data = (Item[]) new Comparable[n + 1];
            count = 0;
        }

        public void insert(Item item) {
            if (count == data.length - 1) {
                resize(count * 2);
            }
            indexes[++count] = count;
            data[count] = item;
            shiftUp(count);
        }

        public String extractMax() {
            if (count <= 0) throw new NoSuchElementException("Priority queue overflow!");
            Item ret = data[1];
            int retI = indexes[1];
            swap(1, count);
            count--;
            if (count > 0 && count == (data.length - 1) / 4) {
                resize(count * 2);
            }

            shiftDown(1);
            return ret + " - " + retI;
        }

        private void shiftUp(int k) {
            while (k > 1 && less(k / 2, k)) {
                swap(k / 2, k);
                k = k / 2;
            }
        }

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
            Item dataTemp = data[i];
            data[i] = data[j];
            data[j] = dataTemp;

            int indexTemp = indexes[i];
            indexes[i] = indexes[j];
            indexes[j] = indexTemp;
        }

        private boolean less(int i, int j) {
            if (data[i].compareTo(data[j]) < 0) {
                return true;
            } else if (data[i].compareTo(data[j]) > 0) {
                return false;
            } else return indexes[i] >= indexes[j];
        }

        private void resize(int newCapacity) {
            Item[] dataTemp = (Item[]) new Comparable[newCapacity + 1];
            int[] IndexesTemp = new int[newCapacity + 1];
            System.arraycopy(indexes, 0, IndexesTemp, 0, count + 1);
            System.arraycopy(data, 0, dataTemp, 0, count + 1);
            data = dataTemp;
            indexes = IndexesTemp;
        }

        public void show() {
            for (int i = 1; i < data.length; i++) {
                System.out.println(data[i] + " - " + indexes[i]);
            }
        }

        public boolean isEmpty() {
            return count == 0;
        }
    }

    public static void main(String[] args) {
        int n = 200;
        // 生成 [0, 5]之间的随机20个数
        Integer[] arr = SortTestHelper.generateRandomArray(n, 0, 5);
        StableMaxPQ<Integer> stableMaxPQ = new StableMaxPQ<>();
        for (int i = 0; i < n; i++) {
            stableMaxPQ.insert(arr[i]);
        }
        while (!stableMaxPQ.isEmpty()) {
            System.out.println(stableMaxPQ.extractMax());
        }
    }
}

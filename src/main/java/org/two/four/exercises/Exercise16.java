package org.two.four.exercises;


/**
 * 2.4.16 对于 N=32，构造数组使得堆排序使用的比较次数最多以及最少。
 *
 * @author cheng
 *         2018/2/2 20:04
 */
public class Exercise16 {
    static class maxPQ<Item extends Comparable<Item>> {
        private int count;
        private Item[] data;
        private int compares;

        public maxPQ(int n) {
            data = (Item[]) new Comparable[n + 1];
            count = 0;
            compares = 0;
        }

        public void insert(Item item) {
            assert count < data.length - 1;
            data[++count] = item;

            shiftUp(count);
        }

        private void shiftUp(int k) {
            while (k > 1 && less(k / 2, k)) {
                Item temp = data[k];
                data[k] = data[k / 2];
                data[k / 2] = temp;
                k >>= 1;
            }
        }

        private boolean less(int i, int j) {
            compares++;
            return data[i].compareTo(data[j]) < 0;
        }
    }

    public static void main(String[] args) {
        maxPQ<Integer> maxPQ = new maxPQ<>(32);

        // 按照升序插入使得比较次数最多，因为每次插入，新元素都得交换至根结点
        for (int i = 0; i < 32; i++) {
            maxPQ.insert(i);
        }
        System.out.println("比较次数最多：" + maxPQ.compares);

        maxPQ = new maxPQ<>(32);
        for (int i = 32; i > 0; i--) {
            maxPQ.insert(i);
        }
        // 按照降序插入使得比较次数最少，因为每次插入一次交换都不会发生，所以比较次数为 n - 1
        System.out.println("比较次数最少：" + maxPQ.compares);
    }
}

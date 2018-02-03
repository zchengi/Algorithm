package org.two.four.exercises;

/**
 * 2.4.22 调整数组大小。在MaxPQ中假如调整数组大小的代码，并和命题Q一样证明对于一般性长度为N的队列其数组访问的上限。
 * <p>
 * 命题Q:对于一个含有N个元素的基于堆的优先队列，插入元素操作只需不超过(lgN+1)次比较，
 * 删除最大元素的操作需要不超过2lgN次比较。
 *
 * @author cheng
 *         2018/2/3 15:13
 */
public class Exercise22 {
    static class MaxPQ<Item extends Comparable<Item>> {
        private Item[] data;
        private int count;
        private int capacity;

        public MaxPQ() {
            capacity = 10;
            data = (Item[]) new Comparable[capacity + 1];
            count = 0;
        }

        public void insert(Item item) {
            if (count == capacity) resize(2 * count);
            data[++count] = item;
            shiftUp(count);
        }

        public Item delMax() {
            Item max = data[1];
            data[1] = data[count];
            shiftDown(1);
            data[count--] = null;
            return max;
        }

        private void shiftDown(int k) {
            Item temp = data[k];
            while (2 * k < count) {
                int j = 2 * k;
                if (data[j].compareTo(data[j + 1]) < 0) j++;
                if (temp.compareTo(data[j]) >= 0) {
                    break;
                }
                data[k] = data[j];
                k = j;
            }
            data[k] = temp;
        }

        private void shiftUp(int k) {
            Item temp = data[k];
            while (k > 1 && data[k / 2].compareTo(temp) < 0) {
                data[k] = data[k / 2];
                k /= 2;
            }
            data[k] = temp;
        }

        private void resize(int newCapacity) {
            Item[] temp = (Item[]) new Comparable[newCapacity + 1];
            System.arraycopy(data, 0, temp, 0, count);
        }

        public boolean isEmpty() {
            return count == 0;
        }
    }

    public static void main(String[] args) {
        MaxPQ maxPQ = new MaxPQ();
        maxPQ.insert(1);
        maxPQ.insert(2);
        maxPQ.insert(3);
        maxPQ.insert(234);
        maxPQ.insert(124);
        maxPQ.insert(43);
        maxPQ.insert(14);
        maxPQ.insert(4);

        while (!maxPQ.isEmpty()) {
            System.out.print(maxPQ.delMax() + " ");
        }
    }
}

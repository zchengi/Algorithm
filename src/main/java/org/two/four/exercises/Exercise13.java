package org.two.four.exercises;

import org.tool.ArrayGenerator;

/**
 * 2.4.13 想办法在sink()中避免检查 j < N。
 * <p>
 * 分析：最大堆中 j < N 的目的是防止只有左子结点，不存在右子结点的时候比较两个子结点而报空指针异常；
 * 这种情况只会出现在交换首结点与最后一个右子结点后删除该右子结点，然后只有左子结点，与右子结点比较时产生。
 * <p>
 * 改进：使用哨兵，即交换首结点与最后一个右子结点后，将最后一个右子结点作为哨兵，先不删除，但是记录最大堆count--，
 * 这样比较的时候，相当于 首结点 左子结点 哨兵（首结点的一个副本） 三者比较，而首结点 = 哨兵，也就是首结点与左结点比较，
 * 排序之后再删除哨兵，所以也就不用判断 j < N 了。
 * <p>
 * 另一种情况交换首结点与左结点，不存在最后一个子结点与空指针比较的情况。
 * <p>
 * 哨兵：不影响排序，某个值的一个副本，可以优化比较次数。
 *
 * @author cheng
 *         2018/2/1 21:14
 */
public class Exercise13 {
    static class MaxPQ<Key extends Comparable<Key>> {
        private Key[] keys;
        private int count;

        public MaxPQ(int n) {
            keys = (Key[]) new Comparable[n + 1];
        }

        public void insert(Key key) {
            keys[++count] = key;
            swim(count);
        }

        public Key delMax() {
            Key max = keys[1];
            // 使用 key[count] 作为哨兵
            // 先减小keys的大小，不删除该值
            keys[1] = keys[count--];
            sink(1);

            // 避免对象游离
            keys[count + 1] = null;
            return max;
        }

        private void sink(int k) {
            while (k * 2 <= count) {
                int j = k * 2;
                // 使用 key[count] 作为哨兵
                if (less(j, j + 1)) j++;
                if (less(j, k)) break;
                exch(k, j);
                k = j;
            }
        }

        private void swim(int k) {
            while (k > 1 && less(k / 2, k)) {
                exch(k / 2, k);
                k /= 2;
            }
        }

        private void exch(int i, int j) {
            Key temp = keys[i];
            keys[i] = keys[j];
            keys[j] = temp;
        }

        private boolean less(int i, int j) {
            return keys[i].compareTo(keys[j]) < 0;
        }

        public boolean isEmpty() {
            return count == 0;
        }
    }

    public static void main(String[] args) {
        for (int i = 10; i <= 50; i++) {
            MaxPQ<Integer> pq = new MaxPQ<>(i);
            for (Integer item : ArrayGenerator.Integers(0, i - 1)) {
                pq.insert(item);
            }
            while (!pq.isEmpty()) {
                System.out.print(pq.delMax() + " ");
            }
            System.out.println();
        }
    }
}

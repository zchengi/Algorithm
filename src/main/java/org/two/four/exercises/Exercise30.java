package org.two.four.exercises;

import org.tool.SortTestHelper;
import org.two.four.learn2.MaxHeap;
import org.two.four.learn2.MinHeap;

import java.util.NoSuchElementException;

/**
 * 2.4.30 动态中位数查找。设计一个数据类型，支持在对数时间内插入元素，常数时间内找到中位数并在对数时间内删除中位数。
 * 提示：用一个面向最大元素的堆再用一个面向最小元素的堆。
 * <p>
 * 中位数：若为偶数个数，则为中间两个数的和除以二；若为奇数个数，则为中间的数；
 * <p>
 * 分析：维护一个最大堆，一个最小堆，和一个中位数；
 * 插入时，判断当前值大于中位数，插入最小堆，这样最小堆的第一个数总是大于中位数的最小的一个；
 * 否则插入最大堆，这样最大堆的第一个数总是小于中位数的最大的一个；
 * 然后根据最大最小堆的元素数量，更新中位数的值。
 *
 * @author cheng
 *         2018/2/9 22:39
 */
public class Exercise30 {
    public static class FindTheMedianDynamically {
        private MaxHeap<Double> maxHeap = new MaxHeap<>();
        private MinHeap<Double> minHeap = new MinHeap<>();
        private Double mid;

        public void insert(Double key) {
            if (mid == null) {
                mid = key;
                return;
            }

            if (key.compareTo(mid) > 0) minHeap.insert(key);
            else maxHeap.insert(key);

            if (Math.abs(minHeap.size() - maxHeap.size()) == 2) {
                if (minHeap.size() < maxHeap.size()) {
                    minHeap.insert(mid);
                    mid = maxHeap.extractMax();
                } else {
                    maxHeap.insert(mid);
                    mid = minHeap.extractMin();
                }
            }

        }

        /**
         * 打印中位数
         */
        public Double peekMedian() {
            if (isEmpty()) return null;
            else if (maxHeap.size() > minHeap.size()) return (mid + maxHeap.getMax()) / 2.0;
            else if (maxHeap.size() < minHeap.size()) return (mid + minHeap.getMin()) / 2.0;
            else return mid;
        }

        /**
         * 取出并删除中位数
         */
        public Double getMedian() {
            if (isEmpty()) {
                throw new NoSuchElementException("Priority queue underflow!");
            } else if (maxHeap.isEmpty() && minHeap.isEmpty()) {
                Double m = mid;
                mid = null;
                return m;
            } else {
                if (maxHeap.size() > minHeap.size()) return (mid + maxHeap.extractMax()) / 2.0;
                else if (maxHeap.size() < minHeap.size()) return (mid + minHeap.extractMin()) / 2.0;
                else {
                    Double median = mid;
                    mid = minHeap.extractMin();
                    return median;
                }
            }
        }

        private boolean isEmpty() {
            return mid == null;
        }
    }

    public static void main(String[] args) {
        int n = 10;
        Integer[] integers = SortTestHelper.generateRandomArray(n, 0, n);

        FindTheMedianDynamically fm = new FindTheMedianDynamically();

        SortTestHelper.printArray(integers);
        for (Integer i : integers) {
            fm.insert(i * 1.0);
            System.out.printf("插入元素 %.1f 当前中位数是： %.1f\n", i * 1.0, fm.peekMedian());
        }

        while (!fm.isEmpty()) {
            System.out.printf("删除当前中位数是： %.1f\n", fm.getMedian());
        }
    }
}

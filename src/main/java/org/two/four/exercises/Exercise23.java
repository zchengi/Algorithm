package org.two.four.exercises;

import org.one.day9.StopWatch;

import java.util.Arrays;

import static org.tool.ArrayGenerator.ints;
import static org.tool.ArrayGenerator.isSorted;

/**
 * 2.4.23 Multiway堆。只考虑比较的成本且假设找到t个元素中的最大者需要t次比较，
 * 在堆排序中使用t向堆的情况下找出使比较次数NlgN的系数最小的t值。
 * 首先，假设使用的是一个简单通用的sink()方法；其次，假设Floyd方法在内循环中每轮可以节省一次比较。
 * <p>
 * 分析：假设堆从 0 开始索引，
 * 如果某个子结点索引为i，则它的父结点的索引为：  parent(i) = (i - 1) / d；
 * 如果某个父结点索引为i，则它的子结点为： i * d + 1 至 i * d + d 之间；
 * <p>
 * 解：这里构建多叉堆，堆索引从0开始；
 *
 * @author cheng
 *         2018/2/3 16:02
 */
public class Exercise23 {
    /**
     * 上浮法建立最大多叉堆
     */
    public static int[] createHeapByShiftUp(int[] arr, int d) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            // 多叉堆上浮
            shiftUp(arr, i, d);
        }
        return arr;
    }

    private static void shiftUp(int[] arr, int k, int d) {
        int newK;
        int temp = arr[k];
        while (k > 0 && arr[newK = (k - 1) / d] < temp) {
            arr[k] = arr[newK];
            k = newK;
        }
        arr[k] = temp;
    }

    /**
     * 下沉法建立最大多叉堆
     */
    public static int[] createHeapByShiftDown(int[] arr, int d) {
        int n = arr.length;
        // d叉堆的最后一个首结点的索引位置 = (arr.length - 2) / d
        for (int i = (n - 2) / d; i >= 0; i--) {
            // 多叉堆下沉
            shiftDown(arr, i, d);
        }
        return arr;
    }

    private static void shiftDown(int[] arr, int k, int d) {
        int n = arr.length - 1;
        int temp = arr[k];
        while (d * k + 1 <= n) {
            // d叉堆的第一个子结点
            int j = d * k + 1;

            // 弃用对二叉堆的判断，改为对d叉堆判断
            /*if (j < n && arr[j] < arr[j + 1]) {
                j++;
            }*/

            // d叉堆需要比较d-1次
            int dCopy = d;
            // 获取比较后最大值的索引
            int maxIndex = j;
            while (--dCopy > 0) {
                if (j < n && arr[maxIndex] < arr[++j]) {
                    maxIndex = j;
                }
            }

            if (!(temp < arr[maxIndex])) {
                break;
            }
            arr[k] = arr[maxIndex];
            k = maxIndex;
        }
        arr[k] = temp;
    }

    /**
     * 多叉堆排序
     */
    public static double multiwayHeapSort(int[] arr, int d) {
        StopWatch timer = new StopWatch();
        int n = arr.length;
        for (int i = (n - 2) / d; i >= 0; i--) {
            // 多叉堆下沉
            shiftDown(arr, i, d);
        }
        int[] sortArr = new int[arr.length];
        while (n > 0) {
            sortArr[--n] = extractMax(arr, d, arr.length - 1);
        }
        // System.out.println(Arrays.toString(sortArr));
        return timer.elapsedTime();
    }

    /**
     * 取出最大多叉堆的最大值
     */
    public static int extractMax(int arr[], int d, int count) {
        int max = arr[0];
        arr[0] = arr[count--];

        //避免对象游离
        arr[count + 1] = 0;

        shiftDown(arr, 0, d);
        return max;
    }

    /**
     * 在 d 叉堆中找出排序性能最好的分支数
     */
    public static void testBestD() {
        int count = 20;
        int n = 1000000;
        double times;

        for (int d = 2; d < 20; d++) {
            times = 0;
            for (int i = 0; i < count; i++) {
                int[] arr = ints(0, n - 1);
                times += multiwayHeapSort(arr, d);
                assert isSorted(arr);
            }
            System.out.println("规模： " + n + " 的 " + d + " 叉堆 " + count + " 次排序耗时：" + times + " ms");
        }
    }

    public static void main(String[] args) {
        int[] arr1 = ints(20, 0, 50);
        int[] arr2 = Arrays.copyOf(arr1, arr1.length);
        int[] arr3 = Arrays.copyOf(arr1, arr1.length);

        System.out.println(Arrays.toString(createHeapByShiftUp(arr1, 2)));
        System.out.println(Arrays.toString(createHeapByShiftDown(arr2, 2)));

        multiwayHeapSort(arr3, 3);

        testBestD();
    }
    /*
     * 规模： 1000000 的 2  叉堆 20 次排序耗时：8.6 ms
     * 规模： 1000000 的 3  叉堆 20 次排序耗时：5.2780000000000005 ms
     * 规模： 1000000 的 4  叉堆 20 次排序耗时：5.225 ms
     * 规模： 1000000 的 5  叉堆 20 次排序耗时：5.342999999999999 ms
     * 规模： 1000000 的 6  叉堆 20 次排序耗时：5.1049999999999995 ms
     * 规模： 1000000 的 7  叉堆 20 次排序耗时：5.340000000000001 ms
     * 规模： 1000000 的 8  叉堆 20 次排序耗时：5.428000000000001 ms
     * 规模： 1000000 的 9  叉堆 20 次排序耗时：5.151000000000001 ms
     * 规模： 1000000 的 10 叉堆 20 次排序耗时：5.422000000000001 ms
     * 规模： 1000000 的 11 叉堆 20 次排序耗时：5.313 ms
     * 规模： 1000000 的 12 叉堆 20 次排序耗时：5.942 ms
     * 规模： 1000000 的 13 叉堆 20 次排序耗时：6.068 ms
     * 规模： 1000000 的 14 叉堆 20 次排序耗时：7.0680000000000005 ms
     * 规模： 1000000 的 15 叉堆 20 次排序耗时：6.822 ms
     * 规模： 1000000 的 16 叉堆 20 次排序耗时：6.590999999999999 ms
     * 规模： 1000000 的 17 叉堆 20 次排序耗时：6.869999999999999 ms
     * 规模： 1000000 的 18 叉堆 20 次排序耗时：6.471000000000002 ms
     * 规模： 1000000 的 19 叉堆 20 次排序耗时：7.748999999999999 ms
     */
}

package org.tool;

import java.lang.reflect.Method;

/**
 * 算法性能测试类
 *
 * @author cheng
 *         2018/1/23 14:17
 */
public class SortTestHelper {

    // SortTestHelper不允许产生任何实例
    private SortTestHelper() {
    }

    /**
     * 生成有n个元素的随机数组，每个元素的随机范围为[rangeL, rangR]
     */
    public static Integer[] generateRandomArray(int n, int rangeL, int rangeR) {

        assert rangeL <= rangeR;

        Integer[] arr = new Integer[n];

        for (int i = 0; i < n; i++) {
            arr[i] = (int) (Math.random() * (rangeR - rangeL + 1) + rangeL);
        }
        return arr;
    }

    /**
     * 生成一个近乎有序的数组
     * 首先生成一个含有[0...n-1]的完全有序数组，之后随机交换swapTimes对数据
     * <p>
     * swapTimes定义了数组的无序程度：
     * swapTimes == 0 时，数组完全有序
     * swapTimes 越大，数组越趋向于无序
     */
    public static Integer[] generateNearlyOrderedArray(int n, int swapTimes) {

        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }

        for (int i = 0; i < swapTimes; i++) {
            int a = (int) (Math.random() * n);
            int b = (int) (Math.random() * n);
            int temp = arr[a];
            arr[a] = arr[b];
            arr[b] = temp;
        }
        return arr;
    }

    /**
     * 打印arr数组的所有内容
     */
    public static void printArray(Object[] arr) {

        for (Object item : arr) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    /**
     * 判断arr数组是否语序
     */
    @SuppressWarnings("unchecked")
    public static boolean isSorted(Comparable[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i].compareTo(arr[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 测试sortClassName所对应的排序算法排序arr数组得到结果的正确性和算法运行时间
     */
    public static void testSort(String sortClassName, Comparable[] arr) {

        // 通过java的反射机制，通过排序的类名，运行排序函数
        try {
            // 通过sortClassName获得排序函数的Class对象
            Class sortClass = Class.forName(sortClassName);
            // 通过排序函数Class对象获得排序方法
            Method sortMethod = sortClass.getMethod("sort", Comparable[].class);
            // 排序参数只有一个，是可比较数组arr
            Object[] params = new Object[]{arr};

            long startTime = System.currentTimeMillis();
            // 调用排序函数
            sortMethod.invoke(null, params);
            long endTime = System.currentTimeMillis();

            assert isSorted(arr);
            System.out.println(sortClass.getSimpleName() + " ： " + (double) (endTime - startTime) / 1000 + "s");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

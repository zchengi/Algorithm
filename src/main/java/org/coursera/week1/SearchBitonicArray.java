package org.coursera.week1;

/**
 * @author cheng
 *         2018/1/8 15:06
 *         <p>
 *         -Test5 Search in a bitonic array（双向数组搜索：时间复杂度为2lgN）
 *         -分析：
 *         bitonic array 是一个内部先递增再递减的队列，实现3lgN很简单，先找出最大值用掉lgN，
 *         再从左侧有序数组中查找用掉lgN，未找到则从右侧有序数组中查找用掉lgN，最坏情况下复杂度为3lgN。
 *         2lgN的做法：意味着去掉查找最大值可以减少复杂度。
 *         http://blog.csdn.net/fiveyears/article/details/11263381
 */
public class SearchBitonicArray {
    private static int searchLeft(int key, int[] a, int lo, int hi) {
        // 遍历左侧增长序列
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key > a[mid]) {
                lo = mid + 1;
            } else if (key < a[mid]) {
                hi = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    private static int searchRight(int key, int[] a, int lo, int hi) {
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key > a[mid]) {
                hi = mid - 1;
            } else if (key < a[mid]) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    // worst case use 3lgN
    public static int findNormal(int key, int[] a) {
        int lo = 0;
        int hi = a.length - 1;
        int mid = 0;
        while (lo <= hi) {
            mid = lo + (hi - lo) / 2;
            if (a[mid] == key) {
                return mid;
            } else {
                if (mid == 0 || mid == a.length - 1) {
                    return -1;
                }
                // mid刚好取到最大值
                if (a[mid] > a[mid - 1] && a[mid] > a[mid + 1]) {
                    break;
                } else if (a[mid] > a[mid - 1] && a[mid] < a[mid + 1]) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
        }
        // 循环结束，但没有return，说明key未找到，并且mid此时已经取到最大数。
        int maxValueIndex = mid;
        // key大于a[i]中最大值，key肯定不在数组中
        if (key > a[maxValueIndex]) {
            return -1;
        }
        if (key < a[0] && key < a[a.length - 1]) {
            return -1;
        }
        int findValue = searchLeft(key, a, 0, maxValueIndex - 1);
        if (findValue == -1) {
            findValue = searchRight(key, a, maxValueIndex + 1, a.length - 1);
        }
        return findValue;
    }

    public static int findFast(int key, int[] a) {
        int lo = 0;
        int hi = a.length - 1;
        int mid = 0;
        int findValue = -1;
        while (lo <= hi) {
            mid = lo + (hi - lo) / 2;
            if (a[mid] == key) {
                return mid;
            } else if (a[mid] > key) {
                if (a[mid] > a[mid + 1] && a[mid] > a[mid - 1]) {
                    findValue = searchLeft(key, a, 0, mid - 1);
                    if (findValue != -1) {
                        return findValue;
                    } else {
                        return searchRight(key, a, mid + 1, a.length - 1);
                    }
                } else if (a[mid] > a[mid + 1] && a[mid] < a[mid - 1]) {
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            } else {
                // 说明key值可能在a[mid]两边
                findValue = searchLeft(key, a, 0, mid - 1);
                if (findValue != -1) {
                    return findValue;
                } else {
                    return searchRight(key, a, mid + 1, a.length);
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] a = {5, 6, 7, 8, 9, 3, 2, 1, 0};
        System.out.println(SearchBitonicArray.findNormal(4, a));
        System.out.println(SearchBitonicArray.findFast(8, a));

    }
}

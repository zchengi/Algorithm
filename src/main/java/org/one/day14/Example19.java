package org.one.day14;

import java.util.Arrays;

/**
 * 1.4.19 矩形的局部最小元素（难）
 * 给定一个含有n^2个不同整数的NxN数组a[]。设计一个运行时间和N成正比的算法来找出一个
 * 局部最小元素：满足a[i][j] < a[i+1][j]、a[i][j] < a[i][j+1]、a[i][j] < a[i-1][j]
 * 以及a[i][j] < a[i][j-1]的索引i和j。程序的运行时间在最坏的情况下应该和N成正比。
 *
 * @author cheng
 *         2018/1/14 16:34
 */
public class Example19 {
    private static class IndexPath {
        int row;
        int column;
    }

    private static IndexPath miniumIndexPathOfItem(int[][] matrix, IndexPath indexPath) {
        final IndexPath resultItem = new IndexPath();
        resultItem.column = indexPath.column;
        resultItem.row = indexPath.row;

        int currentItem = matrix[indexPath.row][indexPath.column];
        int left = matrix[indexPath.row][(indexPath.column - 1) >= 0 ? (indexPath.column - 1) : indexPath.column];
        int right = matrix[indexPath.row][(indexPath.column + 1) <= matrix.length-1 ? (indexPath.column + 1) : indexPath.column];
        int top = matrix[(indexPath.row - 1) >= 0 ? (indexPath.row - 1) : indexPath.row][indexPath.column];
        int bottom = matrix[(indexPath.row + 1) <= matrix.length-1 ? (indexPath.row + 1) : indexPath.row][indexPath.column];

        // 将5个数添加到一个数组中，判断最小数是不是当前的数，如果是则当前数为局部最小元素
        int[] rounder = {left, right, top, bottom, currentItem};
        Arrays.sort(rounder);
        if (rounder[0] == currentItem) {
            System.out.println("row:" + resultItem.row + ";column:" + resultItem.column);
            return resultItem;
        } else if (rounder[0] == left) {
            resultItem.column = indexPath.column - 1;
            miniumIndexPathOfItem(matrix, resultItem);
        } else if (rounder[0] == right) {
            resultItem.column = indexPath.column + 1;
            miniumIndexPathOfItem(matrix, resultItem);
        } else if (rounder[0] == top) {
            resultItem.row = indexPath.row - 1;
            miniumIndexPathOfItem(matrix, resultItem);
        } else if (rounder[0] == bottom) {
            resultItem.row = indexPath.row + 1;
            miniumIndexPathOfItem(matrix, resultItem);
        }
        return resultItem;
    }

    /**
     * 找出数组中的最小值的索引
     */
    private static int miniumOfArray(int[] a) {
        int indexOfMinium = 0;
        int itemOfMinium = Integer.MAX_VALUE;
        for (int i = 0; i < a.length; i++) {
            if (a[i] < itemOfMinium) {
                itemOfMinium = a[i];
                indexOfMinium = i;
            }
        }
        return indexOfMinium;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {11, 18, 9, 50, 102},
                {53, 11, 20, 18, 101},
                {12, 11, 13, 50, 100},
                {14, 15, 80, 60, 50},
                {114, 51, 58, 10, 99}
        };
        // 找出中间行
        int middleRow = matrix.length / 2;
        // 将中间行的数组取出
        int[] row = matrix[middleRow];
        // 计算中间行数组中的最小值的索引
        int index = miniumOfArray(row);
        // 设置开始查找的位置
        IndexPath indexPath = new IndexPath();
        indexPath.row = middleRow;
        indexPath.column = index;
        // 找出满足条件的索引位置
        miniumIndexPathOfItem(matrix, indexPath);
    }
}

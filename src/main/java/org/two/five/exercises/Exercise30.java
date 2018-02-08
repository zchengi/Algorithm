package org.two.five.exercises;

/**
 * 2.5.30 Boerner定理。真假判断：如果你先将一个矩阵的每一列排序，
 * 再将矩阵的每一行排列，所有的列仍然是有序的。证明你的结论。
 * <p>
 * 分析：矩阵就是多维数组，对列排列相当于对多维数组中的竖位排序，
 * 对行排列相当于对每一纬排列，显然排序结果是有序的。
 *
 * @author cheng
 *         2018/2/8 22:48
 */
public class Exercise30 {

    public static int[][] createMatrix(int n, int m) {
        int[][] matrix = new int[n][];
        for (int i = 0; i < n; i++) {
            matrix[i] = new int[m];
            for (int j = 0; j < m; j++) {
                matrix[i][j] = (int) (Math.random() * 101);
            }
        }
        return matrix;
    }

    public static void printMatrix(int[][] a) {
        System.out.println();
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                System.out.printf(" %4d ", a[i][j]);
            }
            System.out.println("\n");
        }
    }

    public static void shellColumn(int[][] a, int k) {
        int n = a.length;
        int h = 1;
        while (h < n / 3) h = 3 * n + 1;
        while (h >= 1) {
            for (int i = 0; i < n; i++) {
                int temp = a[i][k];
                for (int j = i - h; j >= 0 && temp < a[j][k]; j -= h) {
                    a[j + h][k] = a[j][k];
                }
            }
            h /= 3;
        }
    }

    public static void shellRow(int[][] a, int k) {
        int n = a[0].length;
        int h = 1;
        while (h <= n) h = 3 * h + 1;
        while (h >= 1) {
            for (int i = h; i < n; i++) {
                int temp = a[k][i];
                int j;
                for (j = i - h; j >= 0 && temp < a[k][j]; j -= h) {
                    a[k][j + h] = a[k][j];
                }
                a[k][j + h] = temp;
            }
            h /= 3;
        }
    }

    public static void main(String[] args) {
        int rows = 10;
        int columns = 10;
        int[][] a = createMatrix(rows, columns);

        System.out.println("-------------------------- 排序前 --------------------------");
        printMatrix(a);

        for (int i = 0; i < columns; i++) shellColumn(a, i);
        System.out.println("----------------------- 对所有列排序 -----------------------");
        printMatrix(a);

        for (int i = 0; i < rows; i++) shellRow(a, i);
        System.out.println("----------------------- 对所有行排序 -----------------------");
        printMatrix(a);
    }
}

/*

        -------------------------- 排序前 --------------------------

           30    14    40     8    40    11    61    38     1    23

           88     6    44    68    28    14    79    88    95    78

           96    39    89    29    51    46    61    34    97    15

            4    90    53    54    59    30    43    88    38    98

           37    38    43    49    14    71    62     2    25    45

           98     4    32    12    54    31    62    77    62    66

           11    39    82    61    58    87    65    26    17    93

           66    21     2    81    58    67    87    48    72    48

           70    45    29     2     0     0    88    11    54    87

           41     0     9    81    74     8    13    81    23     1

        ----------------------- 对所有列排序 -----------------------

           30    14    40     8    40    11    61    38     1    23

           88    14    44    68    40    14    79    38    95    23

           88    39    44    68    40    14    79    38    95    78

           88    39    89    68    51    46    79    88    95    78

           88    39    89    68    59    46    79    88    95    78

           96    39    89    68    59    71    79    88    97    78

           96    39    89    68    59    71    79    88    97    98

           96    90    89    68    59    71    79    88    97    98

           96    90    89    81    59    71    87    88    97    98

           98    90    89    81    74    87    88    88    97    98

        ----------------------- 对所有行排序 -----------------------

            1     8    11    14    23    30    38    40    40    61

           14    14    23    38    40    44    68    79    88    95

           14    38    39    40    44    68    78    79    88    95

           39    46    51    68    78    79    88    88    89    95

           39    46    59    68    78    79    88    88    89    95

           39    59    68    71    78    79    88    89    96    97

           39    59    68    71    79    88    89    96    97    98

           59    68    71    79    88    89    90    96    97    98

           59    71    81    87    88    89    90    96    97    98

           74    81    87    88    88    89    90    97    98    98

 */
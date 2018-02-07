package org.two.five.exercises;

import org.tool.SortTestHelper;
import org.two.four.learn2.MaxHeapSort2;

/**
 * 2.5.21 多维排序。编写一个Vector数据类型并将d维整型向量排序。排序方法是先按照一维数字排序，
 * 一维数字相同的向量则按照二维数字排序，再相同的向量按照三维数字排序，如此这般。
 * <p>
 * 分析：多维中的每一维并不有序。先排序竖行，如果竖行相同，则排序下一个竖行，举例如下：
 * { 1, 1, 4, 6, 3, 2 }
 * { 1, 3, 4, 5, 3, 4 }
 * { 1, 3, 5, 3, 6, 5 }
 * { 2, 1, 1, 5, 2, 5 }
 * { 2, 2, 1, 6, 3, 5 }
 * { 2, 4, 4, 5, 6, 6 }
 *
 * @author cheng
 *         2018/2/7 22:15
 */
public class Exercise21 {
    static class Vector<T extends Comparable<T>> implements Comparable<Vector<T>> {
        private int dimension;
        private T[] data;

        public Vector(int dimension) {
            this.dimension = dimension;
            data = (T[]) new Comparable[dimension];
        }

        public Vector(Double... v) {
            dimension = v.length;
            data = (T[]) new Comparable[dimension];
            System.arraycopy(v, 0, data, 0, dimension);
        }

        public void setV(int d, T v) {
            if (d >= dimension || d < 0) {
                throw new IllegalArgumentException("dimension overflow");
            }
            data[d] = v;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("{ ");
            int i;
            for (i = 0; i < dimension - 1; i++) {
                builder.append(data[i]).append(", ");
            }
            builder.append(data[i]).append(" } ");
            return builder.toString();
        }

        @Override
        public int compareTo(Vector<T> that) {
            if (that.dimension != this.dimension) {
                return this.dimension - that.dimension;
            }
            for (int i = 0; i < dimension; i++) {
                if (this.data[i].compareTo(that.data[i]) < 0) return -1;
                if (this.data[i].compareTo(that.data[i]) > 0) return 1;
            }
            return 0;
        }
    }

    public static void main(String[] args) {
        int n = 20;
        int dimension = 6;
        Vector<Integer>[] vectors = new Vector[n];
        for (int i = 0; i < n; i++) {
            Integer[] dv = SortTestHelper.generateRandomArray(dimension, 1, dimension);
            vectors[i] = new Vector<>(dimension);
            for (int j = 0; j < dimension; j++) {
                vectors[i].setV(j, dv[j]);
            }
        }

        MaxHeapSort2.sort(vectors);
        for (Vector<Integer> vector : vectors) {
            System.out.println(vector);
        }
    }
}

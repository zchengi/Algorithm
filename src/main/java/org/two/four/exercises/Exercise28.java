package org.two.four.exercises;


import edu.princeton.cs.algs4.StdRandom;

import java.util.NoSuchElementException;

/**
 * 2.4.28 选择过滤。编写一个TopM的用例，从标准输入读取坐标(x,y,z)，从命令行得到值M，
 * 然后打印出距离原点的欧几里德距离最小的M个点。在N=10^8 且 M=10^4时，预计程序的运行时间。
 * <p>
 * 分析：自定义一个数据结构 Coordinate 记录 x,y,z 坐标和欧几里德距离，并实现Comparable接口，
 * 重写比较方法；再使用最小堆排序，删除并打印M个元素；
 *
 * @author cheng
 *         2018/2/6 20:16
 */
public class Exercise28 {
    static class Coordinate implements Comparable<Coordinate> {

        public final double x;
        public final double y;
        public final double z;
        public final double dis;

        public Coordinate(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.dis = Math.sqrt(x * x + y * y + z * z);
        }

        @Override
        public int compareTo(Coordinate that) {
            return this.dis > that.dis ? 1 : this.dis < that.dis ? -1 : 0;
        }

        @Override
        public String toString() {
            return String.format("{%-8.1f, \t%-8.1f, \t%-8.1f \t} \t= %-8.3f", x, y, z, dis);
        }
    }

    static class TopM {
        private Coordinate[] coors = new Coordinate[2];
        private int size;

        public void insert(Coordinate c) {
            if (size == coors.length - 1) resize(size * 2);

            int k = ++size;
            while (k > 1 && coors[k >> 1].compareTo(c) > 0) {
                coors[k] = coors[k >> 1];
                k >>= 1;
            }
            coors[k] = c;
        }

        public Coordinate delMin() {
            if (isEmpty()) throw new NoSuchElementException("priority queue overflow!");
            Coordinate min = coors[1];
            coors[1] = coors[size];

            int k = 1;
            Coordinate last = coors[size--];
            while ((k << 1) <= size) {
                int j = k << 1;
                if (coors[j].compareTo(coors[j + 1]) > 0) j++;
                if (coors[j].compareTo(last) > 0) break;
                coors[k] = coors[j];
                k = j;
            }
            coors[k] = last;
            coors[size + 1] = null;
            if (size > 0 && size == (coors.length - 1) >> 2) {
                resize(size * 2);
            }
            return min;
        }

        public void resize(int newCapacity) {
            Coordinate[] temp = new Coordinate[newCapacity + 1];
            System.arraycopy(coors, 1, temp, 1, size);
            coors = temp;
        }

        public boolean isEmpty() {
            return size == 0;
        }
    }

    public static Coordinate[] random(int N) {
        Coordinate[] coors = new Coordinate[N];
        double lo = -N * 100;
        double hi = N * 100;
        for (int i = 0; i < N; i++) {
            coors[i] = new Coordinate(
                    StdRandom.uniform(lo, hi),
                    StdRandom.uniform(lo, hi),
                    StdRandom.uniform(lo, hi));
        }
        return coors;
    }

    public static void main(String[] args) {
        int N = 100000, M = 100;
        TopM topM = new TopM();
        for (Coordinate coordinate : random(N)) {
            topM.insert(coordinate);
        }
        while (M-- > 0) {
            System.out.println(topM.delMin());
        }
    }
}

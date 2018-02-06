package org.two.four.exercises;

import org.two.four.learn2.MinHeap;

/**
 * 2.4.25 计算数论。编写程序CubeSum.java，在不使用额外空间的条件下，按大小顺序打印所有 a^3 + b^3 的结果，
 * 其中a和b为0至n之间的整数。也就是说，不要全部计算n^2个和然后排序，而是创建一个最小优先队列，
 * 初始状态为(0^3,0,0),(1^3,1,0),(2^3,2,0),...,(n^3,n,0)。这样只要优先队列非空，删除并打印最小元素(i^3+j^3,i,j)。
 * 然后如果j<n，插入元素(i^3+(j+1)^3,i,j+1)。用这段程序找出0到10^6之间所有满足 a^3 + b^3 = c^3 + d^3 的不同整数a,b,c,d。
 * <p>
 * 分析：按排序顺序打印出形式为 a^3 + b^3 的整数，其中 0 <= a <= b <= n：
 * 0 = 0^3 + 0^3
 * 1 = 0^3 + 1^3
 * 2 = 1^3 + 1^3
 * 8 = 0^3 + 2^3
 * 9 = 1^3 + 2^3
 * ···
 * 1729 = 9^3 + 10^3
 * 1729 = 1^3 + 12^3
 * ···
 * 3456 = 12^3 + 12^3
 * 由上述分析可得出结论：
 * -很容易e扩展到处理形式 f(a) + g(b)
 * -例如： 1729 = 9^3 +10^3 = 1^3 + 12^3
 *
 * @author cheng
 *         2018/2/6 15:32
 */
public class Exercise25 {
    static class CubeSum implements Comparable<CubeSum> {

        private int i;
        private int j;
        private int sum;

        public CubeSum(int i, int j) {
            this.i = i;
            this.j = j;
            sum = i * i * i + j * j * j;
        }

        @Override
        public int compareTo(CubeSum that) {
            return this.sum < that.sum ? -1 : this.sum > that.sum ? 1 : 0;
        }

        public int getJ() {
            return j;
        }

        public int getI() {
            return i;
        }

        @Override
        public String toString() {
            return sum + " = " + i + "^3" + " + " + j + "^3";
        }
    }

    public static void main(String[] args) {
        int n = 10;
        MinHeap<CubeSum> minHeap = new MinHeap<>();
        for (int i = 0; i <= n; i++) {
            minHeap.insert(new CubeSum(i, i));
        }

        // 找到最小的sum，打印出来，然后更新堆
        while (!minHeap.isEmpty()) {
            CubeSum min = minHeap.extractMin();
            System.out.println(min);
            if (min.getJ() < n) {
                minHeap.insert(new CubeSum(min.getI(), min.getJ() + 1));
            }
        }
    }
}

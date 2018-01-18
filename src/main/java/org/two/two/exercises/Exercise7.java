package org.two.two.exercises;

/**
 * 2.2.7 证明归并排序的比较次数是单调递增的。
 * 即对于N>0，C(N+1)>C(N)
 * @author cheng
 *         2018/1/17 20:21
 */
public class Exercise7 {
    // 由2.2.6图示证明
    /*
     * 因为当 N 是2的幂次方时，比较次数 C(N) = N * log(N)
     *
     * 因此 C(N + 1) - C(N) = (N + 1) * log(N + 1) - N * log(N)
     *
     * 因为 N 和 log(N) 都是单调递增函数，而单调递增函数之积仍然是单调递增的
     *
     * 所以 C(N) 是单调递增的
     */
}

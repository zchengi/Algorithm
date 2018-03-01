package org.three.three.exercises;

/**
 * 3.3.4 s证明含有N个键的 2-3 树的高度在 ~log3N 即 0.63lgN（树完全由3-结点组成）
 * 和 ~lgN （树完全由2-结点组成）之间。
 *
 * @author cheng
 *         2018/3/1 13:20
 */
public class Exercise4 {
    /*
     *
     * 2-3 树是一种向上生长的树
     *
     * 3- 结点会首先出现在叶子结点，然后向树根方向分裂传递，如果树根由 3- 结点分裂，那么树高加 1
     * 如果叶子结点的 2- 结点没有转化为 3- 结点并向上传递分裂过程，那么树高永远不会增加
     *
     * 我们可以得到，假如这 N 个结点恰好满足构造出一棵只有 2- 结点的树，那么此时树高为最大
     * 即 floor（logN/log2）
     *
     * 如果这 N 个结点恰好满足构造出一颗只有 3- 结点的树，那么此时树高为最小
     * 即 floor（logN/log3）
     *
     */
}

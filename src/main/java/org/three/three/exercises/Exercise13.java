package org.three.three.exercises;

/**
 * 3.3.13 真假判断：如果你按照升序将键插入一棵红黑树中，树的高度是单调递增的。
 *
 * @author cheng
 *         2018/3/2 15:16
 */
public class Exercise13 {
    /*
     *
     * 真命题；
     * 见 FlowChart 3.3.13.jpg
     *
     * 每插入一个结点，树高要么保持不变，要么在叶子形成 3- 结点，首先分裂，然后将 2- 结点向上传递
     * 当 2- 结点传递到根结点并形成 3- 结点时，树高加 1
     * 因此，每插入一个结点，树高要么不变，要么加 1
     *
     */
}

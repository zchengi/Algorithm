package org.coursera.week1;

import edu.princeton.cs.algs4.In;
import org.one.day13.WeightedQuickUnion;

/**
 * @author cheng
 *         2018/1/6 15:31
 *         <p>
 *         Programming Assignment 1: Percolation
 *         -题目：1.建立模型。
 *         -分析：
 *         本次作业根据教授在视频课程上的提示，可以在grid的上方和下方各加入一个虚节点，grid第一行
 *         的open结点都与top虚节点连通，grid最后一行的open节点都与bottom虚节点连通。这样只需判断top
 *         虚节点与bottom虚节点是否连通就知道grid是否渗透，而不需要去一一选取特定节点对比了。照着这个
 *         思路，可以实现下述模型代码。
 *         值得注意的是，模型代码中的main中测试方法不是仅仅进行本地测试就可以了，提交作业的时候会进行
 *         自动脚本测试，所以提交的版本main方法中必须读取arg[0]中的文件名，并加载文件内容进行生成grid
 *         和open对应的site。
 */
public class Percolation {

    /**
     * block state
     */
    private static final boolean BLOCK = false;
    /**
     * open state
     */
    private static final boolean OPEN = true;

    // topUF bottomUF n均为final是因为它们只在构造函数时初始化，后续其值未发生变化
    /**
     * 用来记录与top虚节点的连通性
     */
    private final WeightedQuickUnion topUF;
    /**
     * 用来记录与bottom虚节点的连通性
     */
    private final WeightedQuickUnion bottomUF;
    /**
     * 创建正方形的边
     */
    private final int n;
    /**
     * 保存是否open的grid数组
     */
    private boolean[][] grid;
    /**
     * grid是否渗透的标志
     */
    private boolean percolateFlag = false;
    /**
     * 已经open的site数目
     */
    private int openedNum = 0;

    /**
     * 创建 n-by-n grid，且所有grid sites blocked
     */
    public Percolation(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("grid size should be bigger than one!");
        }
        this.n = n;

        // 多一个节点空间，位于n*n处用来代表虚节点
        topUF = new WeightedQuickUnion(n * n + 1);
        // 多一个节点空间，位于n*n处用来代表虚节点
        bottomUF = new WeightedQuickUnion(n * n + 1);

        //创建网格
        grid = new boolean[n][n];
        // 初始化grid设为block
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = BLOCK;
            }
        }
    }

    private void validate(int row, int col) {
        if (row < 1 || col < 1 || row > n || col > n) {
            throw new IllegalArgumentException("input row or col is not illegal!");
        }
    }

    /**
     * open site (row, col) if it is not open already
     */
    public void open(int row, int col) {
        validate(row, col);
        if (grid[row - 1][col - 1] == OPEN) {
            return;
        }

        grid[row - 1][col - 1] = OPEN;
        openedNum++;

        // 当n为1时，open一个节点就达到渗透的要求
        if (n == 1) {
            topUF.union(0, 1);
            bottomUF.union(0, 1);
            percolateFlag = true;
            return;
        }

        // 第一行的所有节点都与top虚节点相连
        if (row == 1) {
            topUF.union(n * n, col - 1);
        }

        // 最后一行的所有节点都与bottom虚节点相连
        if (row == n) {
            bottomUF.union(n * n, (n - 1) * n + col - 1);
        }

        // 与上方节点的连通性
        if (row > 1 && grid[row - 2][col - 1] == OPEN) {
            topUF.union((row - 2) * n + col - 1, (row - 1) * n + col - 1);
            bottomUF.union((row - 2) * n + col - 1, (row - 1) * n + col - 1);
        }

        // 与下方节点的连通性
        if (row < n && grid[row][col - 1] == OPEN) {
            topUF.union(row * n + col - 1, (row - 1) * n + col - 1);
            bottomUF.union(row * n + col - 1, (row - 1) * n + col - 1);
        }

        // 与左侧节点的连通性
        if (col > 1 && grid[row - 1][col - 2] == OPEN) {
            topUF.union((row - 1) * n + col - 2, (row - 1) * n + col - 1);
            bottomUF.union((row - 1) * n + col - 2, (row - 1) * n + col - 1);
        }

        // 与右侧节点的连通性
        if (col < n && grid[row - 1][col] == OPEN) {
            topUF.union((row - 1) * n + col, (row - 1) * n + col - 1);
            bottomUF.union((row - 1) * n + col, (row - 1) * n + col - 1);
        }

        /*

         * 判断条件!percolateFlag： 是为了防止渗透以后的重复判断
         * 判断条件openedNum >= n： 是因为openNum达到n时才有可能渗透，在未达到n之前，不需要进行后续判断
         * 一个节点open的时候刚好使grid渗透的条件是： 该节点同时与top虚节点和bottom虚节点连通
         */
        if (!percolateFlag && openedNum >= n && topUF.connected(n * n, (row - 1) * n + col - 1)
                && bottomUF.connected(n * n, (row - 1) * n + col - 1)) {
            percolateFlag = true;
        }
    }

    /**
     * is site(row, col) open?
     */
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row - 1][col - 1] == OPEN;
    }

    /**
     * 一个节点只有在open状态并且与top虚节点连通时才是full状态
     * is site(row, col) full?
     */
    public boolean isFull(int row, int col) {
        validate(row, col);
        return isOpen(row, col) && topUF.connected(n * n, (row - 1) * n + col - 1);
    }

    /**
     * number of open sites
     */
    public int numberOfOpenSites() {
        return openedNum;
    }

    /**
     * does the system percolates?
     */
    public boolean percolates() {
        return percolateFlag;
    }

    /**
     * 打印一些便于查看的信息
     */
    private void printCheckResult(int row, int col) {
        System.out.println("p(" + row + "," + col + ") is open=" + isOpen(row, col)
                + ";is full=" + isFull(row, col) + ";percolates=" + percolates());
    }

    /**
     * test client (optional)
     * 作业提交时main需要调用该方法，因为提交后在线脚本会使用一堆input文件进行测试
     *
     * @param arg0
     */
    private static void fileInputCheck(String arg0) {
        // 读入input文件名，并加载文件内容
        In in = new In(arg0);
        String s = null;
        int n;
        // 读入grid的n
        while (in.hasNextLine()) {
            s = in.readLine();
            if (s != null && !"".equals(s.trim())) {
                break;
            }
        }
        s = s.trim();
        n = Integer.parseInt(s);
        Percolation p = new Percolation(n);

        // 读入open的site坐标
        while (in.hasNextLine()) {
            s = in.readLine();
            if (s != null && !"".equals(s.trim())) {
                // 去掉出入字符串头尾空格
                s = s.trim();
                // 用空格分割将s转为数组
                String[] sa = s.split("\\s+");
                if (sa.length != 2) {
                    break;
                }
                int row = Integer.parseInt(sa[0]);
                int col = Integer.parseInt(sa[1]);
                p.open(row, col);
                p.printCheckResult(row, col);
            }
        }
    }

    /**
     * 本地测试专用
     */
    private static void generateCheck() {
        Percolation p = new Percolation(3);
        int row = 1, col = 3;
        p.open(row, col);
        p.printCheckResult(row, col);
        row = 2;
        col = 3;
        p.open(row, col);
        p.printCheckResult(row, col);
        row = 3;
        col = 3;
        p.open(row, col);
        p.printCheckResult(row, col);
        row = 3;
        col = 1;
        p.open(row, col);
        p.printCheckResult(row, col);
        row = 2;
        col = 1;
        p.open(row, col);
        p.printCheckResult(row, col);
        row = 1;
        col = 1;
        p.open(row, col);
        p.printCheckResult(row, col);
    }

    public static void main(String[] args) {
        generateCheck();
        // fileInputCheck(args[0]);
    }
}

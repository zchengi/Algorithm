package org.unionfind;

/**
 * Union-Find 接口
 *
 * @author cheng
 *         2018/2/25 11:02
 */
public interface UF {
    void unionElements(int p, int q);

    boolean isConnected(int p, int q);
}

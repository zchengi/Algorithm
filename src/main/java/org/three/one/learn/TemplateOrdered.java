package org.three.one.learn;

/**
 * 表3.1.4 一种有序的泛型符号表的API
 * **********************************************************************************
 * public  class ST<Key extend Comparable<Key>,Value>
 * **********************************************************************************
 * ST()                                 创建一张有序符号表
 * void put(Key key, Value value)       将键值对存入表中（若值为空则将key从表中删除）
 * Value get(Key key)                   获取键key对应的值（若键key不存在则返回空）
 * void delete(Key key)                 从表中删去键key（及其对应的值）
 * boolean contains(Key key)            键key在表中是否有对应的值
 * boolean isEmpty()                    表中的键值对数量
 * int size()                           表是否为空
 * Key min()                            最小的键
 * Key max()                            最大的键
 * Key floor(Key key)                   小于等于key的最大键
 * Key ceiling(Key key)                 大于等于key的最小键
 * int rank(Key key)                    小于key的键的数量
 * Key select(int k)                    排名为k的键
 * void deleteMin()                     删除最小的键
 * void deleteMax()                     删除最大的键
 * int size(Key lo, Key hi)             [lo...hi]之间的数量
 * Iterable<Key> keys(Key lo, Key hi)   [lo...hi]之间的所有键,已排序
 * Iterable<Key> keys()                 表中所有键的集合,已排序
 * **********************************************************************************
 *
 * @author cheng
 *         2018/2/5 11:37
 */
public class TemplateOrdered<Key extends Comparable<Key>, Value> {
    public TemplateOrdered() {
    }

    void put(Key key, Value value) {
        if (value == null) {
            delete(key);
            return;
        }

    }

    Value get(Key key) {
        return null;
    }

    void delete(Key key) {
    }

    boolean contains(Key key) {
        return get(key) != null;
    }

    boolean isEmpty() {
        return size() == 0;
    }

    private int size() {
        return 0;
    }

    Key min() {
        return null;
    }

    Key max() {
        return null;
    }

    Key floor(Key key) {
        return null;
    }

    Key ceiling(Key key) {
        return null;
    }

    int rank(Key key) {
        return 0;
    }

    Key select(int k) {
        return null;
    }

    void deleteMin() {
        delete(min());
    }

    void deleteMax() {
        delete(max());
    }

    int size(Key lo, Key hi) {
        if (hi.compareTo(lo) < 0) return 0;
        else if (contains(hi)) return rank(hi) - rank(lo) + 1;
        else return rank(hi) - rank(lo);
    }

    Iterable<Key> keys(Key lo, Key hi) {
        return null;
    }

    Iterable<Key> keys() {
        return keys(min(), max());
    }
}

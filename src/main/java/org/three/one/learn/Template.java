package org.three.one.learn;

/**
 * 表3.1.2 一种简单的泛型符号表API
 * **********************************************************************************
 * public  class ST<Key,Value>
 * **********************************************************************************
 * ST()                             创建一张有序符号表
 * void put(Key key, Value value)   将键值对存入表中（若值为空则将key从表中删除）
 * Value get(Key key)               获取键key对应的值（若键key不存在则返回空）
 * void delete(Key key)             从表中删去键key（及其对应的值）
 * boolean contains(Key key)        键key在表中是否有对应的值
 * boolean isEmpty()                表中的键值对数量
 * int size()                       表是否为空
 * Iterable<Key> keys               表中所有键的集合
 * **********************************************************************************
 *
 * @author cheng
 *         2018/2/5 10:51
 */
public class Template<Key, Value> {

    public Template() {
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

    int size() {
        return 0;
    }

    boolean isEmpty() {
        return size() == 0;
    }

    Iterable<Key> keys() {
        return null;
    }
}

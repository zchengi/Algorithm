package org.three.one.learn;

import org.example.Queue;

import java.util.NoSuchElementException;

/**
 * 算法3.2 二分查找（基于有序数组）
 *
 * @author cheng
 *         2018/2/5 14:54
 */
public class BinarySearchST<Key extends Comparable<Key>, Value> {

    private static final int INIT_CAPACITY = 2;
    private Key[] keys;
    private Value[] values;
    private int count = 0;
    private int capacity;

    public BinarySearchST() {
        this(INIT_CAPACITY);
    }

    public BinarySearchST(int capacity) {
        this.capacity = capacity;
        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Object[capacity];
    }

    public void put(Key key, Value value) {
        if (key == null) throw new IllegalArgumentException("key mush be not null!");
        if (value == null) {
            delete(key);
            return;
        }

        // 查找键，找到则更新，否则创建新的元素
        int i = rank(key);
        if (i < count && keys[i].compareTo(key) == 0) {
            values[i] = value;
            return;
        }

        if (count == capacity) resize(2 * capacity);

        for (int j = count; j > i; j--) {
            keys[j] = keys[j - 1];
            values[j] = values[j - 1];
        }
        keys[i] = key;
        values[i] = value;
        count++;

        assert check();
    }

    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("key must be not null!");
        if (isEmpty()) return null;

        int i = rank(key);
        if (i < count && keys[i].compareTo(key) == 0) return values[i];
        return null;
    }

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("key must be not null!");
        if (isEmpty()) return;

        int i = rank(key);
        if (i == count || keys[i].compareTo(key) != 0) {
            return;
        }

        for (int j = i; j < count - 1; j++) {
            keys[j] = keys[j + 1];
            values[j] = values[j + 1];
        }

        count--;
        keys[count] = null;
        keys[count] = null;

        if (count > 0 && count == capacity / 4) resize(capacity / 2);
        assert check();
    }

    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("Symbol table is null!");
        delete(min());
    }

    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("Symbol table is null!");
        delete(max());
    }

    /**
     * 基于有序数组的二分查找（迭代）
     */
    private int rank(Key key) {
        int lo = 0;
        int hi = count - 1;

        while (lo <= hi) {
            int mid = (hi - lo) / 2 + lo;
            int temp = key.compareTo(keys[mid]);

            if (temp > 0) lo = mid + 1;
            else if (temp < 0) hi = mid - 1;
            else return mid;
        }
        // 如果 key 不在keys数组中 那么返回值只能是小于 key 的最大元素的索引,或者大于 key 的最小索引
        return lo;
    }

    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("Symbol table is null!");
        return keys[0];
    }

    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("Symbol table is null!");
        return keys[count - 1];
    }

    public Key select(int k) {
        if (k < 0 || k >= size()) throw new IllegalArgumentException("invalid argument: " + k);
        return keys[k];
    }

    public Key ceiling(Key key) {
        if (key == null) throw new IllegalArgumentException("key must be not null!");
        int i = rank(key);
        if (i == count) return null;
        else return keys[i];
    }

    public Key floor(Key key) {
        if (key == null) throw new IllegalArgumentException("key must be not null!");
        int i = rank(key);

        if (i < count && key.compareTo(keys[i]) == 0) return key;
        else if (i == 0) return null;
            // 这种情况下 i 的值只能为count
        else return keys[i - 1];
    }

    private void resize(int newCapacity) {
        Key[] tempKeys = (Key[]) new Comparable[newCapacity];
        Value[] tempValues = (Value[]) new Object[newCapacity];

        System.arraycopy(keys, 0, tempKeys, 0, count);
        System.arraycopy(values, 0, tempValues, 0, count);

        keys = tempKeys;
        values = tempValues;
        capacity = newCapacity;
    }

    private boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("key must be not null!");
        return get(key) != null;
    }

    public int size(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument must be not null!");
        if (hi == null) throw new IllegalArgumentException("second argument must be not null!");

        if (lo.compareTo(hi) > 0) return 0;
        if (contains(hi)) return rank(hi) - rank(lo) + 1;
        else return rank(hi) - rank(lo);
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument must be not null!");
        if (hi == null) throw new IllegalArgumentException("second argument must be not null!");


        Queue<Key> queue = new Queue<>();
        if (lo.compareTo(hi) > 0) return queue;

        for (int i = rank(lo); i < rank(hi); i++) {
            queue.enqueue(keys[i]);
        }

        if (contains(hi)) {
            queue.enqueue(keys[rank(hi)]);
        }
        return queue;
    }

    public boolean check() {
        return isSorted() && rankCheck();
    }

    public boolean isSorted() {
        for (int i = 1; i < size(); i++) {
            if (keys[i].compareTo(keys[i - 1]) < 0) return false;
        }
        return true;
    }

    private boolean rankCheck() {
        for (int i = 0; i < size(); i++) {
            if (i != rank(select(i))) return false;
        }
        for (int i = 0; i < size(); i++) {
            if (keys[i].compareTo(select(rank(keys[i]))) != 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        BinarySearchST<String, Integer> st = new BinarySearchST<>();

        // b c d e f
        for (int i = 0; i < 5; i++) {
            st.put(String.valueOf((char) ('b' + i)), i);
        }

        st.delete("b");
        st.deleteMin();
        st.deleteMax();

        System.out.println("符号表最小键值对：" + st.min() + " " + st.get(st.min()));
        System.out.println("符号表最大键值对：" + st.max() + " " + st.get(st.max()));
        System.out.println("符号表排名为0的键：" + st.select(0));
        System.out.println("对字母a向上取整：" + st.ceiling("a"));
        System.out.println("对字母g向下取整：" + st.floor("g"));
        System.out.println("符号表是否包含字母d：" + st.contains("d"));
        System.out.println("符号表大小：" + st.size() + "，是否为空：" + st.isEmpty());
        System.out.println("符号表的键是否有序：" + st.check());

        System.out.println("循环输出符号表剩余键值对：");
        Iterable<String> keys = st.keys();
        for (String key : keys) {
            System.out.print(key + " " + st.get(key) + "\0\0\0\0");
        }
    }
}

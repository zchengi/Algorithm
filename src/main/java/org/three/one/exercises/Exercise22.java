package org.three.one.exercises;

import org.example.Queue;

import java.util.NoSuchElementException;

/**
 * 3.1.22 自组织查找。自组织查找指的是一种能够将数组元素重新排序使得被访问频率较高的元素更容易被找到的查找算法。
 * 请修改你为练习3.1.2给出的答案，在每次查找命中时：将被找到的键值对移动到数组开头，将所有中间的键值对向右移动一格。
 * 这个启发式的过程被称为前移编码。
 * <p>
 * 分析：即查找命中后将该元素移动至数组开头，将在它之前的元素向后移动一位。
 *
 * @author cheng
 *         2018/2/11 23:14
 */
public class Exercise22 {
    static class ArrayST<Key, Value> {

        private static final int INIT_SIZE = 8;

        private Key[] keys;
        private Value[] values;
        private int count;

        public ArrayST() {
            keys = (Key[]) new Object[INIT_SIZE];
            values = (Value[]) new Object[INIT_SIZE];
            count = 0;
        }

        public void put(Key key, Value value) {
            if (key == null) throw new IllegalArgumentException("key can not be null!");

            if (value == null) {
                delete(key);
                return;
            }

            for (int i = 0; i < count; i++) {
                if (key.equals(keys[i])) {
                    values[i] = value;
                    return;
                }
            }

            if (count == values.length) resize(2 * count);

            keys[count] = key;
            values[count] = value;
            count++;
        }

        public Value get(Key key) {
            if (key == null) throw new IllegalArgumentException("key can not be null!");
            for (int i = 0; i < count; i++) {
                if (key.equals(keys[i])) {
                    // 自组织查找 将每次get()到的键值对移动到数组开头
                    Key tempK = keys[i];
                    Value valueK = values[i];
                    while (--i >= 0) {
                        keys[i + 1] = keys[i];
                        values[i + 1] = values[i];
                    }
                    keys[0] = tempK;
                    values[0] = valueK;
                    return valueK;
                }
            }
            return null;
        }

        public void delete(Key key) {
            if (key == null) throw new IllegalArgumentException("key can not be null!");
            for (int i = 0; i < count; i++) {
                if (key.equals(keys[i])) {
                    keys[i] = keys[count - 1];
                    values[i] = values[count - 1];
                    keys[count - 1] = null;
                    values[count - 1] = null;

                    count--;
                    if (count > 0 && count == values.length / 4) resize(count * 2);
                    return;
                }
            }
        }

        public Iterable<Key> keys() {
            if (count == 0) throw new NoSuchElementException("Symbol table underflow!");
            Queue<Key> queue = new Queue<>();
            for (int i = 0; i < count; i++) {
                queue.enqueue(keys[i]);
            }
            return queue;
        }

        public int size() {
            return count;
        }

        public boolean isEmpty() {
            return count == 0;
        }

        private void resize(int capacity) {
            Key[] tempK = (Key[]) new Object[capacity];
            Value[] tempV = (Value[]) new Object[capacity];
            System.arraycopy(keys, 0, tempK, 0, count);
            System.arraycopy(values, 0, tempV, 0, count);
            keys = tempK;
            values = tempV;
        }
    }

    public static void main(String[] args) {
        ArrayST<String, Integer> arrayST = new ArrayST<>();
        arrayST.put("a", 1);
        arrayST.put("b", 2);
        arrayST.put("c", 3);
        arrayST.put("d", 4);
        arrayST.put("e", 5);
        arrayST.put("f", 6);

        for (String key : arrayST.keys()) {
            System.out.println(key + " " + arrayST.get(key));
        }

        arrayST.delete("f");
        arrayST.delete("c");
        arrayST.put("a", 11);

        System.out.println();
        for (String key : arrayST.keys()) {
            System.out.println(key + " " + arrayST.get(key));
        }
    }
}

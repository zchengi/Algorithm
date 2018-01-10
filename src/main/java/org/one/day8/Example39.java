package org.one.day8;


/**
 * 1.3.39 环形缓冲区
 * 环形缓冲区，又称为环形队列，是一种定长为N的先进先出的数据结构。它在进程间的异步数据传输或记录日志文件时十分有用。
 * 当缓存区为空时，消费者会在数据存入缓存区前等待；当缓冲区满时，生产者会等待将数据存入缓存区。为RingBuffer设计一份
 * API并用（回环）数组将其实现。
 * <p>
 * ------------------------------------------------------------
 * public class RingBuffer<Item>
 * -------------------------------------------------------------
 * boolean reset()          重新设置
 * int size()               总容量
 * int available()          可读取容量
 * int remainingCapacity()  剩余容量
 * boolean put(Item x)      存入缓存
 * Item take()              读取
 * -------------------------------------------------------------
 *
 * @author cheng
 *         2018/1/10 20:25
 */
@SuppressWarnings("unchecked")
public class Example39<Item> {

    private Item[] a = null;

    private int writePos = 0;
    private int readPos = 0;
    /**
     * the flipped marker
     * false：队列可以插入元素
     * true：队列已满，不可插入
     */
    private boolean flipped = false;

    public Example39(int cap) {
        this.a = (Item[]) new Object[cap];
    }

    public void reset() {
        this.writePos = 0;
        this.readPos = 0;
        this.flipped = false;
    }

    public int size() {
        return a.length;
    }

    public int available() {
        if (!flipped) {
            return writePos - readPos;
        }
        return size() - readPos + writePos;
    }

    public int remainingCapacity() {
        if (!flipped) {
            return size() - writePos;
        }
        return readPos - writePos;
    }

    public boolean put(Item x) {
        if (!flipped) {
            if (writePos == size()) {
                writePos = 0;
                flipped = true;

                if (writePos < readPos) {
                    a[writePos++] = x;
                    return true;
                } else {
                    return false;
                }
            } else {
                a[writePos++] = x;
                return true;
            }
        } else {
            if (writePos < readPos) {
                a[writePos++] = x;
                return true;
            } else {
                return false;
            }
        }
    }

    public Item take() {
        if (!flipped) {
            if (readPos < writePos) {
                return a[readPos++];
            } else {
                return null;
            }
        } else {
            if (readPos == size()) {
                readPos = 0;
                flipped = false;

                if (readPos < writePos) {
                    return a[readPos++];
                } else {
                    return null;
                }
            } else {
                return a[readPos++];
            }
        }
    }

    public static void main(String[] args) {
        int capacity = 10;
        Example39<String> ringBuffer = new Example39<String>(capacity);


        /* ******************测试用例1************************ */
      /*  for (int i = 0; i < capacity; i++) {
            String inputItem = i + "";
            boolean putSuccess = ringBuffer.put(inputItem);
            System.out.println(putSuccess ? "插入" + inputItem + "成功" : "插入" + inputItem + "失败");
        }*/

        /* ******************测试用例2************************ */
        for (int i = 0; i < capacity + 1; i++) {

            if (i == capacity - 1) {
                String takeItem = ringBuffer.take();
                System.out.println("取出" + takeItem + "成功");
            }

            if (i == capacity) {
                String takeItem = ringBuffer.take();
                System.out.println("取出" + takeItem + "成功");
            }

            String inputItem = i + "";
            boolean putSuccess = ringBuffer.put(inputItem);
            System.out.println(putSuccess ? "插入" + inputItem + "成功" : "插入" + inputItem + "失败");
        }
    }
}






























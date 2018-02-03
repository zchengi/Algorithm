package org.two.four.exercises;

/**
 * 2.4.21 基础数据结构，说说如何使用优先队列实现第一章中的
 * 栈、队列和随机队列这几种数据结构。
 *
 * @author cheng
 *         2018/2/3 13:48
 */
public class Exercise21 {

    /*
     *
     * 使用优先队列实现栈：栈的定义就是 后进先出；所以可以赋予入栈的元素一个优先级：1,2,3...依次递增，
     * 用最大堆排序优先级数组，每次出栈删除优先级最大的元素。
     *
     * 使用优先队列实现队列：队列的定义就是 先进先出；所以可以赋予入列的元素一个优先级：1,2,3...依次递增，
     * 用最小堆排序优先级数组，每次出列删除优先级最小的元素。
     *
     * 使用优先队列实现随机队列：随机队列定义就是 随机出列；所以一样可以赋予元素一个优先级：1,2,3...依次递增，
     * 用最大堆排序优先级数组，每次出列都随机选取一个元素，将它与最大的元素交换位置，然后删除。
     */

    /**
     * 栈的实现
     */
    static class Stack<Item> {
        /**
         * 元素数组
         */
        private InnerWrapper<Item>[] data;

        /**
         * 元素总数
         */
        private int count;

        /**
         * 数组大小
         */
        private int capacity;

        class InnerWrapper<IItem> {
            IItem item;
            // 用来控制插入先后顺序的优先级
            int priority;
        }

        public Stack() {
            capacity = 10;
            data = new InnerWrapper[capacity];
            count = 0;
        }

        public void push(Item item) {
            if (count == capacity) resize(count << 1);
            InnerWrapper<Item> innerWrapper = new InnerWrapper();
            innerWrapper.item = item;
            innerWrapper.priority = count;
            data[count++] = innerWrapper;
        }

        public Item pop() {
            if (count == capacity / 4) resize(count << 1);
            int popIndex = 0;
            for (int i = 0; i < count; i++) {
                if (data[popIndex].priority < data[i].priority) popIndex = i;
            }
            Item ret = data[popIndex].item;
            if (popIndex != count - 1) {
                data[popIndex] = data[count - 1];
            }
            data[--count] = null;
            return ret;
        }

        private void resize(int newCapacity) {
            InnerWrapper<Item>[] temp = new InnerWrapper[newCapacity];
            System.arraycopy(data, 0, temp, 0, count);
            data = temp;
            capacity = newCapacity;
        }

        public boolean isEmpty() {
            return count == 0;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();

        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop());
        stack.push(4);
        stack.push(5);
        stack.push(6);
        stack.push(7);
        System.out.println(stack.pop());
        stack.push(8);
        stack.push(9);
        stack.push(10);
        System.out.println(stack.pop());
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }
}


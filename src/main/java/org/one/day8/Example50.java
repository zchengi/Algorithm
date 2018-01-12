package org.one.day8;

/**
 * 1.3.50 快速出错的迭代器
 * 修改Stack的迭代器代码，确保一旦用例在迭代器中（通过push()或pop()操作）修改集合数据就抛出一个
 * java.util.ConcurrentModificationException异常。
 * 分析：
 * 用一个计数器记录push()和pop()操作的次数。在创建迭代器时，将该值记录到Iterator的一个实例变量中。
 * 在每次调用hasNext()和next()之前，检查该值是否发生了变化，如果变化则抛出异常。
 *
 * @author cheng
 *         2018/1/12 13:38
 */
public class Example50 {
}

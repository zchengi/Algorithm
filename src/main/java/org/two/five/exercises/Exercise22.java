package org.two.five.exercises;

import org.two.four.learn2.MaxHeap;
import org.two.four.learn2.MinHeap;

/**
 * 2.5.22 股票交易。投资者对一只股票的买卖交易都发布在电子交易市场中。他们会指定最高买入价和最低买入价，
 * 以及在该价位买卖的笔数。编写一段程序。用优先队列来匹配买家和卖家并用模拟数据进行测试。可以用两个优先队列，
 * 一个用于买家一个用于卖家，当一方的报价能够和另一方的一份或多份报价匹配时就进行交易。
 * <p>
 * 分析：创建一个数据类型记录买入价格和该价位买卖的比数。
 *
 * @author cheng
 *         2018/2/7 22:50
 */
public class Exercise22 {
    static class Stock implements Comparable<Stock> {

        private final int id;
        private double price;
        private int count;

        public Stock(int id, double price, int count) {
            this.id = id;
            this.price = price;
            this.count = count;
        }

        @Override
        public int compareTo(Stock that) {
            if (this.price < that.price) return -1;
            else if (this.price > that.price) return 1;
            else if (this.count < that.count) return -1;
            else if (this.count > that.count) return 1;
            else return 0;
        }

        @Override
        public String toString() {
            return "股票价格： " + Math.ceil(price) + ", 数量： " + count;
        }

        public double getPrice() {
            return price;
        }

        public int getCount() {
            return count;
        }
    }

    public static void main(String[] args) {

        int n = 100;
        // 买家初始化
        Stock[] buyerStock = new Stock[n];
        for (int i = 0; i < n; i++) {
            double price = Math.random() * 3000 + 1000;
            int count = (int) (Math.random() * 100) + 10;
            buyerStock[i] = new Stock(i, price, count);
        }

        // 卖家初始化
        Stock[] sellerStock = new Stock[n];
        for (int i = 0; i < n; i++) {
            double price = Math.random() * 3000 + 1000;
            int count = (int) (Math.random() * 100) + 10;
            sellerStock[i] = new Stock(i, price, count);
        }

        // 买家先买最贵的
        MaxHeap<Stock> buyer = new MaxHeap<>(buyerStock);
        // 卖家先卖最便宜的
        MinHeap<Stock> seller = new MinHeap<>(sellerStock);

        while (!buyer.isEmpty() && !seller.isEmpty()) {
            Stock wanToBuy = buyer.extractMax();
            Stock wantToSell = seller.extractMin();
            if (wanToBuy.getPrice() < wantToSell.getPrice()) {
                seller.insert(wantToSell);
            } else {
                System.out.println("交易成功，买家买了价格为 "
                        + (double) Math.round(wanToBuy.getPrice() * 100) / 100 + " 的股票 "
                        + wanToBuy.getCount() + " 股");
            }
        }
        if (seller.isEmpty()) System.out.println("股票售空");
        if (buyer.isEmpty()) System.out.println("缺少买家");
    }
}

package org.one.day6;

import org.example.Queue;

/**
 * 1.3.16
 * 日期数据类型
 *
 * @author cheng
 *         2017/10/21 13:28
 */
public class Date {

    private final int year;
    private final int month;
    private final int day;

    public Date(int y, int m, int d) {
        month = m;
        day = d;
        year = y;
    }

    public Date(String date) {
        String[] str = date.split("\\/");
        if (str.length != 3) {
            throw new IllegalArgumentException("非法参数:" + date);
        }
        day = Integer.parseInt(str[0]);
        month = Integer.parseInt(str[1]);
        year = Integer.parseInt(str[2]);
    }

    public static Date[] readDates(String s) {
        String[] dates = s.split(" ");
        int n = dates.length;
        Queue<Date> q = new Queue<Date>();
        for (int i = 0; i < n; i++) {
            q.enqueue(new Date(dates[i]));
        }

        Date[] result = new Date[n];
        for (int i = 0; i < n; i++) {
            result[i] = q.dequeue();
        }
        return result;
    }

    public int year() {
        return year;
    }

    public int month() {
        return month;
    }

    public int day() {
        return day;
    }

    @Override
    public String toString() {
        return "Date{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Date date = (Date) obj;
        if (year != date.year) {
            return false;
        }
        if (month != date.month) {
            return false;
        }
        return day == date.day;
    }

    @Override
    public int hashCode() {
        int result = year;
        result = 31 * result + month;
        result = 31 * result + day;
        return result;
    }

    public static void main(String[] args) {
        String str = "30/2/2009 19/2/2012";
        Date[] dates = Date.readDates(str);
        for (Date date : dates) {
            System.out.println(date);
        }
    }
}

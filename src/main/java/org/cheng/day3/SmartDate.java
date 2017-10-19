package org.cheng.day3;


/**
 * Example11、12
 * 根据Date的API实现一个SmartDate类型，在日期非法时抛出一个异常。
 * 为SmartDate添加一个方法dayOfTheWeek()，为日期中的每周的日返回Monday、Tuesday···Sunday中的适当值。可以假定时间是21世纪。
 *
 * @author cheng
 *         2017/10/19 10:34
 */
public class SmartDate {
    /**
     * 年
     */
    private final int year;
    /**
     * 月 1-12之间
     */
    private final int month;
    /**
     * 日 1-31之间
     */
    private final int day;

    /**
     * 初始化日期并判断日期格式
     */
    public SmartDate(int y, int m, int d) {
        switch (m) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                if (d > 0 && d <= 31) {
                    year = y;
                    month = m;
                    day = d;
                } else {
                    throw new IllegalArgumentException("非法日期!");
                }
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                if (d > 0 && d < 30) {
                    year = y;
                    month = m;
                    day = d;
                } else {
                    throw new IllegalArgumentException("非法日期!");
                }
                break;
            case 2:
                if (y % 4 == 0 && y % 100 != 0 || y % 400 == 0) {
                    if (d > 0 && d <= 29) {
                        year = y;
                        month = m;
                        day = d;
                    } else {
                        throw new IllegalArgumentException("非法日期!");
                    }
                } else {
                    if (d > 0 && d <= 28) {
                        year = y;
                        day = d;
                        month = m;
                    } else {
                        throw new IllegalArgumentException("非法日期!");
                    }
                }
                break;
            default:

                throw new IllegalArgumentException("非法日期!");
        }
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

    /**
     * 判断星期(使用 Zeller formula)
     *
     * @return 返回星期几
     */
    public String dayOfTheWeek() {

        int year = this.year;
        int month = this.month;
        int day = this.day;
        if (month <= 2) {
            month += 12;
            year--;
        }
        //基姆拉尔森计算公式
        int week = (day + 2 * month + 3 * (month + 1) / 5 + year + year / 4 - year / 100 + year / 400) % 7;
        switch (week) {
            case 0:
                return "Monday";
            case 1:
                return "Tuesday";
            case 2:
                return "Wednesday";
            case 3:
                return "Thursday";
            case 4:
                return "Friday";
            case 5:
                return "Saturday";
            case 6:
                return "Sunday";
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return day() + "/" + month() + "/" + year();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) return true;
        if (that == null || getClass() != that.getClass()) return false;

        SmartDate smartDate = (SmartDate) that;

        if (year != smartDate.year) return false;
        if (month != smartDate.month) return false;
        return day == smartDate.day;
    }

    public static void main(String[] args) {
        int year = 2012;
        int month = 2;
        int day = 29;
        try {
            SmartDate date = new SmartDate(year, month, day);
            System.out.println(date);
            System.out.println(date.dayOfTheWeek());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}

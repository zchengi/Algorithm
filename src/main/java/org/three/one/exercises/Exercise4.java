package org.three.one.exercises;

import org.three.one.learn.BinarySearchST;

/**
 * 3.1.4 开发抽象数据类型Time和Event来处理表3.1.5的例子。
 *
 * @author cheng
 *         2018/2/11 0:45
 */
public class Exercise4 {
    static class Time implements Comparable<Time> {
        private final String timeString;
        private final int hour;
        private final int minute;
        private final int second;
        private final long millisecondsTime;

        public Time(String time) {
            if (!time.matches("(20|21|22|23|[0-1]\\d):[0-5]\\d:[0-5]\\d")) {
                throw new IllegalArgumentException("The time format is incorrect!");
            }

            this.timeString = time;
            String[] split = time.split(":");
            hour = Integer.parseInt(split[0]);
            minute = Integer.parseInt(split[1]);
            second = Integer.parseInt(split[2]);
            millisecondsTime = hour * 60 * 60 + minute * 60 + second;
        }

        @Override
        public int compareTo(Time that) {
            return this.millisecondsTime < that.millisecondsTime ? -1 :
                    this.millisecondsTime > that.millisecondsTime ? 1 : 0;
        }

        @Override
        public String toString() {
            return timeString;
        }
    }

    static class Place {
        private final String event;

        public Place(String event) {
            this.event = event;
        }

        @Override
        public String toString() {
            return event;
        }
    }

    public static void main(String[] args) {
        BinarySearchST<Time, Place> st = new BinarySearchST<>();
        st.put(new Time("09:00:00"), new Place("Chicago"));
        st.put(new Time("09:00:03"), new Place("Phoenix"));
        st.put(new Time("09:00:13"), new Place("Houston"));
        st.put(new Time("09:00:59"), new Place("Chicago"));
        st.put(new Time("09:01:10"), new Place("Houston"));
        st.put(new Time("09:03:13"), new Place("Chicago"));
        st.put(new Time("09:10:11"), new Place("Seattle"));
        st.put(new Time("09:10:25"), new Place("Seattle"));
        st.put(new Time("09:14:25"), new Place("Phoenix"));
        st.put(new Time("09:19:32"), new Place("Chicago"));
        st.put(new Time("09:19:46"), new Place("Chicago"));
        st.put(new Time("09:21:05"), new Place("Chicago"));
        st.put(new Time("09:22:43"), new Place("Seattle"));
        st.put(new Time("09:22:54"), new Place("Seattle"));
        st.put(new Time("09:25:52"), new Place("Chicago"));
        st.put(new Time("09:35:21"), new Place("Chicago"));
        st.put(new Time("09:36:14"), new Place("Seattle"));
        st.put(new Time("09:37:44"), new Place("Phoenix"));

        for (Time key : st.keys()) {
            System.out.println(key + " " + st.get(key));
        }

        System.out.printf("min() = %s\n", st.min());
        System.out.printf("get(09:00:13) = %s\n", st.get(new Time("09:00:13")));
        System.out.printf("floor(09:05:00) = %s\n", st.floor(new Time("09:05:00")));
        System.out.printf("select(7) = %s\n", st.select(7));
        System.out.println("遍历 keys(09:15:00, 09:25:00)");
        for (Time t : st.keys(new Time("09:15:00"), new Time("09:25:00")))
            System.out.println(t.toString() + " : " + st.get(t));
        System.out.printf("ceiling(09:30:00) = %s\n", st.ceiling(new Time("09:30:00")));
        System.out.printf("max() = %s\n", st.max());
        System.out.printf("size(09:15:00, 09:25:00) = %s\n", st.size(new Time("09:15:00"), new Time("09:25:00")));
    }
}

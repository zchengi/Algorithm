package org.two.five.exercises;

import java.util.Comparator;

/**
 * 2.5.25 平面上的点。为表1.2.3的Point2D类型编写三个静态的比较器，一个按照x坐标比较，一个按到第三点的距离比较，
 * 一个按照两点相对第三点的幅角比较。
 *
 * @author cheng
 *         2018/2/8 21:06
 */
public class Exercise25 {
    static class Point2D {
        double x;
        double y;
        double r;

        public Point2D(double x, double y) {
            if (Double.isInfinite(x) || Double.isInfinite(y))
                throw new IllegalArgumentException("Coordinates must be finite");
            if (Double.isNaN(x) || Double.isNaN(y))
                throw new IllegalArgumentException("Coordinates cannot be NaN");
            if (x == 0.0) this.x = 0.0;
            else this.x = x;

            if (y == 0.0) this.y = 0.0;
            else this.y = y;
        }

        public double x() {
            return x;
        }

        public double y() {
            return y;
        }

        public double r() {
            return r;
        }

        double disTo(Point2D point2D) {
            return 0;
        }

        double disToOrgi() {
            return 0;
        }

        double angleTo(Point2D that) {
            return 0;
        }

        public static Comparator<Point2D> sortX() {
            return new Comparator<Point2D>() {
                @Override
                public int compare(Point2D a, Point2D b) {
                    return a.x() < b.x() ? -1 : a.x() > b.x() ? 1 : 0;
                }
            };
        }

        public static Comparator<Point2D> sortY() {
            return new Comparator<Point2D>() {
                public int compare(Point2D a, Point2D b) {
                    return a.y() < b.y() ? -1 :
                            a.y() > b.y() ? 1 : 0;
                }
            };
        }

        public static Comparator<Point2D> sortDis() {
            return new Comparator<Point2D>() {
                public int compare(Point2D a, Point2D b) {
                    return a.disToOrgi() < b.disToOrgi() ? -1 :
                            a.disToOrgi() > b.disToOrgi() ? 1 : 0;
                }
            };
        }

        public Comparator<Point2D> sortDisToSelf() {
            return new Comparator<Point2D>() {
                public int compare(Point2D a, Point2D b) {
                    return disTo(a) < disTo(b) ? -1 :
                            disTo(a) > disTo(b) ? 1 : 0;
                }
            };
        }

        public Comparator<Point2D> sortAngleToSelf() {
            return new Comparator<Point2D>() {
                public int compare(Point2D a, Point2D b) {
                    return angleTo(a) < angleTo(b) ? -1 :
                            angleTo(a) > angleTo(b) ? 1 : 0;
                }
            };
        }
    }
}

package org.two.five.exercises;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * 2.5.29 按大小和最后修改日期将文件排序。为File数据类型编写比较器，使之能够将文件按照大小、
 * 文件名或最后修改日期将文件升序或降序排列。在程序LS中使用这些比较器，它接受一个命令行参数并
 * 根据指定的顺序列出目录内容。例如，“-t”指按照时间戳排序。支持多个选项以消除排序位次相同者，
 * 同时必须保证排序的稳定性。
 *
 * @author cheng
 *         2018/2/8 22:05
 */
public class Exercise29 {
    enum SortType {
        ASCEND,
        DESCEND,
    }

    public static Comparator<File> compareByFileSize(final SortType type) {
        return new Comparator<File>() {
            @Override
            public int compare(File f1, File f2) {
                switch (type) {
                    case ASCEND:
                        return f1.length() < f2.length() ? -1 : f1.length() > f2.length() ? 1 : 0;
                    case DESCEND:
                        return f1.length() > f2.length() ? -1 : f1.length() < f2.length() ? 1 : 0;
                }
                throw new RuntimeException("Comparator failed!");
            }
        };
    }

    public static Comparator<File> compareByLastModified(final SortType type) {
        return new Comparator<File>() {
            @Override
            public int compare(File f1, File f2) {
                switch (type) {
                    case ASCEND:
                        return f1.lastModified() < f2.lastModified() ? -1 : f1.lastModified() > f2.length() ? 1 : 0;
                    case DESCEND:
                        return f1.lastModified() > f2.lastModified() ? -1 : f1.lastModified() < f2.length() ? 1 : 0;
                }
                throw new RuntimeException("Comparator failed!");
            }
        };
    }

    public static Comparator[] comparators(String condition) {
        if (condition == null || condition.length() == 0) return null;
        String[] split = condition.split("\\s+");
        int count = 0;
        ArrayList<Comparator> list = new ArrayList<>();
        for (String str : split) {
            if ("-t".equals(str)) {
                if (condition.contains("-d")) {
                    list.add(compareByLastModified(SortType.DESCEND));
                } else {
                    list.add(compareByLastModified(SortType.ASCEND));
                }
            }
            if (str.equals("-l")) {
                if (condition.contains("-d")) {
                    list.add(compareByFileSize(SortType.DESCEND));
                } else {
                    list.add(compareByFileSize(SortType.ASCEND));
                }
            }
        }

        int i = 0;
        Object[] objects = list.toArray();
        Comparator[] comparators = new Comparator[objects.length];
        for (Object object : objects) {
            comparators[i++] = (Comparator) object;
        }
        return comparators;
    }

    public static void shell(File[] files, Comparator[] comparators) {
        int N = files.length, h = 1;
        while (h < N / 3) h = 3 * h + 1;
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                File t = files[i];
                int j;
                for (j = i - h; j >= 0 && less(t, files[j], comparators); j -= h)
                    files[j + h] = files[j];
                files[j + h] = t;
            }
            h /= 3;
        }
    }

    private static boolean less(File f1, File f2, Comparator[] comparators) {
        return compare(f1, f2, comparators) < 0;
    }

    private static int compare(File f1, File f2, Comparator[] comparators) {
        for (Comparator cmp : comparators) {
            if (cmp.compare(f1, f2) < 0) return -1;
            if (cmp.compare(f1, f2) > 0) return 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        File directory = new File("D:/IntelliJ Project/Algorithm/src/main/java/org/two/four/learn2");
        File[] files = directory.listFiles();

        // 将文件按照大小排序，如果文件名相同按照最后修改时间排序，升序
        String condition = "-l -t -a";
        shell(files, comparators(condition));
        for (File file : files) {
            System.out.println(file);
        }
    }
}




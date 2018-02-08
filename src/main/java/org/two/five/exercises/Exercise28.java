package org.two.five.exercises;

import java.io.File;
import java.util.Arrays;

/**
 * 2.5.28 按文件名排序。编写一个FileSorter程序，从命令行接受一个目录名并打印出按照文件名排序后的所有文件。
 * 提示：使用File数据类型。
 *
 * @author cheng
 *         2018/2/8 21:52
 */
public class Exercise28 {
    public static void getFileNameUnderDirectory(String pathname) {
        File directory = new File(pathname);
        if (!directory.exists()) System.out.println("Directory does not exist!");

        if (!directory.isDirectory()) System.out.println("Directory is not a directory!");

        File[] files = directory.listFiles();
        if (files == null) System.out.println("Could not read files!");

        Arrays.sort(files);
        for (File file : files) System.out.println(file.getName());
    }

    public static void main(String[] args) {
        getFileNameUnderDirectory("D:/IntelliJ Project");
    }
}

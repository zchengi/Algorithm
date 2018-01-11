package org.one.day8;

import java.io.File;

/**
 * 1.3.43 文件列表
 * 文件夹就是一列文件和文件夹的列表。编写一个程序，从命令行接受一个文件夹名作为参数，
 * 打印出该文件夹下的所有文件并用递归的方式在所有子文件的名下（缩进）列出其下的所有文件。
 * 提示：使用队列，并参考java.io.File
 *
 * @author cheng
 *         2018/1/11 15:20
 */
public class Example43 {
    public static void listAllFiles(String path, int deep) {
        File file = new File(path);
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (File listFile : listFiles) {
                for (int i = 0; i < deep; i++) {
                    System.out.print("\t");
                }
                System.out.println(listFile.getName());
                if (listFile.isDirectory()) {
                    listAllFiles(listFile.getAbsolutePath(), deep + 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        String path = "D:/思维导图";
        listAllFiles(path, 0);
    }
}

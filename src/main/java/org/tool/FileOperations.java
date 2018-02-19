package org.tool;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;
import java.util.Vector;

/**
 * 文件相关操作
 *
 * @author cheng
 *         2018/2/19 12:48
 */
public class FileOperations {
    /**
     * 读取文件名为filename中的内容，并将其中包含的所有词语放入words中
     */
    public static boolean readFile(String filename, Vector<String> words) {
        if (filename == null) {
            System.out.println("Filename is null!");
            return false;
        }

        // 文件读取
        Scanner scanner;

        try {
            File file = new File(filename);
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                scanner = new Scanner(new BufferedInputStream(fis));
                scanner.useLocale(Locale.ENGLISH);
            } else return false;

        } catch (IOException e) {
            System.out.println("Cannot open " + filename);
            return false;
        }

        // 简单分词
        // 没有考虑很多文本处理中的特殊问题
        // 只做demo展示用
        if (scanner.hasNextLine()) {
            String contents = scanner.useDelimiter("\\A").next();

            int start = firstCharacterIndex(contents, 0);
            for (int i = start + 1; i <= contents.length(); ) {
                if (i == contents.length() || !Character.isLetter(contents.charAt(i))) {
                    String word = contents.substring(start, i).toLowerCase();
                    words.add(word);
                    start = firstCharacterIndex(contents, i);
                    i = start + 1;
                } else {
                    i++;
                }
            }
        }

        return true;
    }

    /**
     * 寻找字符串str中，从start的位置开始的第一个字母字符的位置
     */
    private static int firstCharacterIndex(String str, int start) {
        for (int i = start; i < str.length(); i++) {
            if (Character.isLetter(str.charAt(i))) {
                return i;
            }
        }
        return str.length();
    }
}

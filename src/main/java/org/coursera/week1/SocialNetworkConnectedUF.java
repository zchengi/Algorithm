package org.coursera.week1;

import org.one.day13.WeightedQuickUnion;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author cheng
 *         2018/1/5 17:21
 *         <p>
 *         Test1： Social network connectivity
 *         -题目：
 *         有一个包含n个成员的社交网络，日志文件log按照时间戳顺序存储了两个成员之间成为朋友的时间，共有m条记录。
 *         设计一个算法来根据这个log文件计算n个成员全部通过朋友关系连通的最早时间（时间戳）。
 *         -分析：
 *         这是一个union-find问题，思路是读取日志文件，遍历文件记录，逐条记录union。
 *         采用quick-union算法，就可以满足mlogn的复杂度要求。
 */
public class SocialNetworkConnectedUF {

    private FileInputStream fis;

    private WeightedQuickUnion uf;

    public SocialNetworkConnectedUF(int num, FileInputStream fis) {
        this.fis = fis;
        uf = new WeightedQuickUnion(num);
    }

    public String getEarliestConnectedTime() {
        String earliestConnected = null;
        Scanner scanner = new Scanner(fis, "utf-8");
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line != null && !line.trim().equals("")) {
                String[] lineArray = line.split(" ");
                if (lineArray.length == 3) {
                    String timestamp = lineArray[0];
                    int p = Integer.parseInt(lineArray[1]);
                    int q = Integer.parseInt(lineArray[2]);
                    if (uf.connected(p, q)) {
                        continue;
                    }
                    uf.union(p, q);
                    if (uf.count() == 1) {
                        earliestConnected = timestamp;
                        break;
                    }
                }
            }
        }
        return earliestConnected;
    }

    public static void main(String[] args) {
        FileInputStream fis;
        try {
            fis = new FileInputStream("src/main/java/org/coursera/week1/socialNetworkLog.txt");
            SocialNetworkConnectedUF socialNet = new SocialNetworkConnectedUF(10, fis);
            System.out.println(" the earliest connected time is :" + socialNet.getEarliestConnectedTime());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

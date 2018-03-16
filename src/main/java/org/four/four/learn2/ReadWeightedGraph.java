package org.four.four.learn2;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * 读取文件内容
 *
 * @author cheng
 *         2018/3/14 15:40
 */
public class ReadWeightedGraph {

    private Scanner scanner;

    /**
     * 由于文件格式的限制，我们的文件读取类只能读取值为Double类型的图
     */
    public ReadWeightedGraph(WeightedGraph<Double> graph, String filename) {

        readFile(filename);

        try {
            int V = scanner.nextInt();
            if (V < 0) {
                throw new IllegalArgumentException("Number of vertices in a Graph must be nonegative!");
            }

            if (V != graph.V()) {
                throw new IllegalArgumentException("Number of vertices in a Graph and argument not equal!");
            }

            int E = scanner.nextInt();
            if (E < 0) {
                throw new IllegalArgumentException("Number of edges is in a Graph must be nonegative!");
            }

            for (int i = 0; i < E; i++) {
                int v = scanner.nextInt();
                int w = scanner.nextInt();
                Double weight = scanner.nextDouble();

                if (v < 0 || v >= V || w < 0 || w >= V) throw new IllegalArgumentException("Invalid parameter!");

                graph.addEdge(new Edge<Double>(v, w, weight));
            }

        } catch (InputMismatchException e) {
            String token = scanner.next();
            throw new InputMismatchException("Attempts to read an 'int' value from input stream, but the next token is \"" + token + "\"!");
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Attempts to read an 'int' value from input stream, but there are no more tokens available!");
        }
    }

    private void readFile(String filename) {

        if (filename == null) throw new IllegalArgumentException("Called readFile() with a null filename!");

        try {
            File file = new File(filename);
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                scanner = new Scanner(new BufferedInputStream(fis), "UTF-8");
                scanner.useLocale(Locale.ENGLISH);
            } else {
                throw new IllegalArgumentException(filename + " doesn't exist!");
            }
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Could not open " + filename, ioe);
        }
    }
}

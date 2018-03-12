package org.four.one.exercises;

import edu.princeton.cs.algs4.In;
import org.four.one.learn.Bag;

import java.util.NoSuchElementException;

/**
 * 4.1.15 修改Graph的输入流构造函数，运行从标准输入读入图的邻接表（方法类似于SymbolGraph），
 * 如图4.1.26的tinyGadj.txt所示。在顶点和边的总数之后，每一行由一个顶点和它的所有相邻顶点组成。
 *
 * @author cheng
 *         2018/3/12 12:16
 */
public class Exercise15 {
    private static class Graph {

        private static final String NEWLINE = System.getProperty("line.separator");

        private final int V;

        private int E;

        private Bag<Integer>[] adj;

        public Graph(int V) {
            if (V < 0) throw new IllegalArgumentException("Number of vertices must be nonegative!");

            this.V = V;
            this.E = 0;
            adj = (Bag<Integer>[]) new Bag[V];
            for (int i = 0; i < V; i++) {
                adj[i] = new Bag<>();
            }
        }

        /**
         * 在顶点和边的总数之后，每一行由一个顶点和它的所有相邻顶点组成
         */
        public Graph(In in) {
            try {
                this.V = in.readInt();
                if (V < 0) throw new IllegalArgumentException("Number of vertices must be nonegative!");
                adj = (Bag<Integer>[]) new Bag[V];
                for (int i = 0; i < V; i++) {
                    adj[i] = new Bag<>();
                }

                int E = in.readInt();
                if (E < 0) throw new IllegalArgumentException("Number of edges in a Graph must be nonegative!");

                while (in.hasNextLine()) {
                    String line = in.readLine();
                    if (line == null || line.length() == 0) continue;

                    String[] split = line.split(" ");
                    int[] vs = new int[split.length];
                    for (int i = 0; i < split.length; i++) {
                        vs[i] = Integer.parseInt(split[i]);
                    }

                    int v = vs[0];
                    validateVertex(v);

                    for (int w = 1; w < vs.length; w++) {
                        validateVertex(vs[w]);
                        addEdge(v, vs[w]);
                    }

                }
            } catch (NoSuchElementException e) {
                throw new IllegalArgumentException("Invalid input format in Graph constructor", e);
            }
        }

        public Graph(String filename) {
            this(new In(filename));
        }

        private void addEdge(int v, int w) {
            validateVertex(v);
            validateVertex(w);
            adj[v].add(w);
            adj[w].add(v);
            E++;
        }

        public String toString() {
            StringBuilder s = new StringBuilder();
            s.append(V).append(" vertices, ").append(E).append(" edges ").append(NEWLINE);
            for (int v = 0; v < V; v++) {
                s.append(v).append(": ");
                for (int w : adj[v]) {
                    s.append(w).append(" ");
                }
                s.append(NEWLINE);
            }
            return s.toString();
        }

        public int V() {
            return V;
        }

        public int E() {
            return E;
        }

        private void validateVertex(int v) {
            if (v < 0 || v >= V) {
                throw new IllegalArgumentException("Vertex " + v + " is not between 0 and " + (V - 1));
            }
        }
    }

    public static void main(String[] args) {

        // tinyGadj.txt
        String filename = "src/main/java/org/four/one/text/tinyGadj.txt";
        Graph G = new Graph(filename);
        System.out.println(G);
    }
}

package org.four.one.exercises;

import edu.princeton.cs.algs4.In;
import org.four.one.learn.Bag;

import java.util.NoSuchElementException;

/**
 * 4.1.5 修改Graph，不允许存在平行边和自环。
 *
 * @author cheng
 *         2018/3/11 12:31
 */
public class Exercise5 {
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
                for (int i = 0; i < E; i++) {
                    int v = in.readInt();
                    int w = in.readInt();

                    validateVertex(v);
                    validateVertex(w);
                    addEdge(v, w);
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

            // 添加判断，不允许出现平行边和自环
            for (int x : adj[v]) {
                if (w == x) return;
            }

            if (v == w) return;


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

        // tinyG2.txt
        String filename = "src/main/java/org/four/one/text/tinyG2.txt";
        Graph G = new Graph(filename);
        System.out.println(G);
    }
}

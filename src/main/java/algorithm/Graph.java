package algorithm;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph {

    private Integer v;
    private LinkedList<Integer> adj[];

    public Graph(Integer v) {
        this.v = v;
        this.adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            this.adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int s, int t) {
        this.adj[s].add(t);
        this.adj[t].add(s);
    }

    public void bfs(int s, int t) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);

        boolean[] visited = new boolean[v];
        visited[s] = true;

        int[] prev = new int[v];
        for (int i = 0; i < v; ++i) {
            prev[i] = -1;
        }

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int i = 0; i < adj[current].size(); ++i) {

                final Integer next = adj[current].get(i);

                if (!visited[next]) {
                    prev[next] = current;
                    if (next == t) {
                        print(prev, s, t);
                        return;
                    }
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }
    }

    private void print(int[] prev, int s, int t) { // 递归打印s->t的路径
        if (prev[t] != -1 && t != s) {
            print(prev, s, prev[t]);
        }
        System.out.print(t + " ");
    }
}

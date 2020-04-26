package algorithm;

import java.util.LinkedList;
import java.util.Queue;

public class GraphTest {

    public static void main(String[] args) {
        Graph graph = initiateGraph();

        int s = 0 , t = 7;
        graph.bfs(s, t);
    }



    private static Graph initiateGraph() {
        // 0--1--2
        // |  |  |
        // 3--4--5
        //    |  |
        //    6--7
        Graph graph = new Graph(8);
        graph.addEdge(0,1);
        graph.addEdge(0,3);
        graph.addEdge(1,2);
        graph.addEdge(1,4);
        graph.addEdge(2,5);
        graph.addEdge(4,5);
        graph.addEdge(4,6);
        graph.addEdge(5,7);
        graph.addEdge(6,7);
        return graph;
    }
}

package Labs.Lab10_GraphsAlgorithms;

import java.util.*;

import DataStructures.Edge;
import DataStructures.GraphsAlgorithms.AdjacencyMatrixGraph;

public class MinimumCostBetweenNeighborhoods {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        AdjacencyMatrixGraph<String> graph = new AdjacencyMatrixGraph<>(n);
        Map<String, Integer> mapping = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String vertex = scanner.next();
            graph.addVertex(i, vertex);
            mapping.put(vertex, i);
        }
        int m = scanner.nextInt();
        for (int i = 0; i < m; i++) {
            graph.addEdge(mapping.get(scanner.next()), mapping.get(scanner.next()), scanner.nextInt());
        }
        List<Edge> costs = graph.kruskal();
        int result = 0;
        for (Edge edge : costs) {
            result += edge.getWeight();
        }
        System.out.println(result);
    }
}

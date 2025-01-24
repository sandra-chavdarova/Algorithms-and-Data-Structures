/*
Компоненти на сврзаност
За дадено теме да се прикажат сите темиња кои припаѓаат на иста компонента на сврзаност во даден граф.
Темињата припаѓаат на иста компонента на сврзаност во графот доколку постои пат помеѓу нив.
На влез прво се внесува броjот на темиња, потоа за секое теме се пишува броjот на темиња со кои е поврзано
и индексите на темињата. На краj се внесува индексот на темето за кое ќе се бара решението.
Пример:
Влез:
10
1 5               // темето на индекс 0 е поврзано со темето на индекс 5
3 2 4 5           // темето со индекс 1 е поврзано со темињата на индекси 2, 4 и 5
3 1 3 4
3 2 4 5
4 1 2 3 5
4 0 1 3 4
2 7 8
2 6 8
2 6 7
0
4
Излез:
0 1 2 3 4 5
*/

package Exercises.SecondPartialExam;

import java.util.*;
import java.util.Queue;
import java.util.Stack;

import DataStructures.AdjacencyListGraph;

public class KomponentiNaSvrzanost {
    static Set<Integer> components(AdjacencyListGraph<Integer> graph, Integer startVertex) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        visited.add(startVertex);
        queue.add(startVertex);
        int i = 0;
        while (!queue.isEmpty()) {
            Integer vertex = queue.poll();
            for (Integer neighbor : graph.getNeighbors(vertex)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        return visited;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AdjacencyListGraph<Integer> graph = new AdjacencyListGraph<>();
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            int nodes = scanner.nextInt();
            for (int j = 0; j < nodes; j++) {
                graph.addEdge(i, scanner.nextInt());
            }
        }
        int startVertex = scanner.nextInt();
        Set<Integer> result = components(graph, startVertex);
        for (Integer vertex : result) {
            System.out.print(vertex + " ");
        }
    }
}

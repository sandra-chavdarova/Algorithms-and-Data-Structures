/*
Даден е неориентиран нетежински граф чии темиња се позитивни цели броеви.
Да се најде бројот на патишта во графот, почнувајќи од фиксно теме V, кај кои сумата на темињата има вредност N.
Темињата во патот може да се повторуваат.

Влез: Во првиот ред е даден бројот на рабови во графот M.
Потоа во следните M редови се дадени рабовите во графот во формат теме1 теме2.
Во претпоследниот ред е дадено почетното теме V, a во последниот ред е дадена бараната сума N.
Излез:  Бројот на патишта во графот кај кои сумата на темињата изнесува N.

/

Given an undirected unweighted graph whose vertices are positive integers,
find the number of paths in the graph, starting from a fixed vertex V,
such that the sum of the vertices is N. The vertices in the path can be repeated.

Input: The first line contains the number of edges in the graph M, followed by the edges in the next M lines.
The edges are given in the format vertex1 vertex2.
The second-to-last line contains the start vertex V and the last line contains the wanted sum N.
Output: The number of paths where the sum of the vertices is N.

Input:
6
2 5
2 3
5 3
5 1
3 1
3 4
5
10

Result:
3
(Explanation: the paths are 5-2-3, 5-3-2, 5-1-3-1)
*/

package Labs.Lab09_Graphs;

import java.util.*;

import DataStructures.AdjacencyListGraph;

public class SumOfVertices {
    public int count(AdjacencyListGraph<Integer> graph, Integer startVertex, int goalSum) {
        Set<Integer> visited = new HashSet<>();
        return countUtil(graph, startVertex, goalSum, Integer.parseInt(startVertex.toString()), visited);
    }

    private int countUtil(AdjacencyListGraph<Integer> graph, Integer vertex, int goalSum, int sum, Set<Integer> visited) {
        if (sum == goalSum)
            return 1;
        if (sum > goalSum)
            return 0;
        visited.add(vertex);
        int counter = 0;
        for (Integer neighbour : graph.getNeighbors(vertex)) {
            int currentSum = sum + Integer.parseInt(neighbour.toString());
            if (!visited.contains(neighbour) || currentSum <= goalSum) {
                counter += countUtil(graph, neighbour, goalSum, currentSum, visited);
            }
        }
        visited.remove(vertex);
        return counter;
    }

    // second solution
    public int countPaths(AdjacencyListGraph<Integer> graph, Integer startVertex, int goalSum, int sum) {
        if (sum == goalSum)
            return 1;
        if (sum > goalSum)
            return 0;
        int counter = 0;

        for (Integer neighbor : graph.getNeighbors(startVertex)) {
            int currentSum = sum + (int) neighbor;
            counter += countPaths(graph, neighbor, goalSum, currentSum);
        }
        return counter;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int edges = scanner.nextInt();
        AdjacencyListGraph<Integer> graph = new AdjacencyListGraph<>();
        for (int i = 0; i < edges; i++) {
            graph.addEdge(scanner.nextInt(), scanner.nextInt());
        }
        int V = scanner.nextInt();
        int sum = scanner.nextInt();
        System.out.println(graph.count(V, sum));
//        System.out.println(graph.countPaths(V, sum, V));
    }
}

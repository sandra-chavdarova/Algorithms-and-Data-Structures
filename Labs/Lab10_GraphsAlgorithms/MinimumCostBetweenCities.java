/*
Една компанија сака да воспостави комуникациска мрежа меѓу своите канцеларии во различни градови.
Компанијата има листа на можни врски помеѓу градовите и нивната цена.
Целта е да се минимизира вкупната цена за поврзување на сите градови.
Да се одреди минималната цена за поврзување на сите градови.

Влез: Во првиот ред е даден бројот на градови N.
Потоа во следните N редови се дадени имињата на градовите.
Во следниот ред е даден бројот на можни врски M,
а потоа во следните M редови се дадени градовите што ги поврзува секоја врска и цената за воспоставување на таа врска.

Излез:  Минималната цена да се поврзат сите градови.

/

A company wants to establish a communication network between its offices in different cities.
They have a list of potential connections between cities, along with their costs.
The company wants to minimize the total cost of connecting all cities.
Determine the minimum cost to connect all cities.

Input: The first line contains the number of cities N.
The next N lines contain the names of the cities.
The next line contains the number of possible connections M,
followed by M lines that contain the two cities connected by each connection and the cost of establishing that connection.

Output: The minimum cost to connect all cities.

Input:
4
London
Paris
Brussels
Amsterdam
5
London Paris 400
London Brussels 200
Paris Brussels 100
Brussels Amsterdam 150
Paris Amsterdam 300

Result:
450
(Explanation: the connections London-Brussels, Brussels-Amsterdam and Brussels-Paris will be established)
*/

package Labs.Lab10_GraphsAlgorithms;

import java.util.*;

import DataStructures.Edge;
import DataStructures.GraphsAlgorithms.AdjacencyMatrixGraph;

public class MinimumCostBetweenCities {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        AdjacencyMatrixGraph<String> graph = new AdjacencyMatrixGraph<>(n);
        HashMap<String, Integer> mapping = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String vertex = scanner.next();
            graph.addVertex(i, vertex);
            mapping.put(vertex, i);
        }
        int m = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < m; i++) {
            graph.addEdge(mapping.get(scanner.next()), mapping.get(scanner.next()), scanner.nextInt());
            scanner.nextLine();
        }
        List<Edge> result = graph.kruskal();
        int total = 0;
        for (Edge edge : result) {
            total += edge.getWeight();
        }
        System.out.println(total);
    }
}
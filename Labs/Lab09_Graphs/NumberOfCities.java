/*
Дадени се објекти кои се поврзани со улици и се групирани во градови.
За група на објекти се смета дека припаѓаат на еден град ако секој објект
е поврзан со барем еден друг објект од тој град, а не е поврзан со ниту еден објект од некој друг град.
Да се одреди во колку градови се групирани дадените објекти.

Влез: Во првиот ред е даден бројот на објекти N.
Потоа во следните N редови се дадени имињата на објектите.
Во следниот ред е даден бројот на улици M,
а во следните M редови се дадени објектите кои ги поврзува секоја улица.

Излез:  Бројот на градови во кои се групирани дадените објекти.

/

You're given objects connected by streets and grouped into cities.
A group of objects is considered to belong to the same city if each object
is connected to at least one other object in that city and is not connected
to any object from another city. Determine how many cities the given objects are grouped into.

Input: The first line contains the number of objects N.
The next N lines contain the names of the objects.
The next line contains the number of streets M,
followed by M lines that specify the objects connected by each street.

Output: The number of cities the given objects are grouped into.

Input:
5
School1
ApartmentBuilding1
Park1
Supermarket1
Hospital1
2
School1 ApartmentBuilding1
Park1 Supermarket1

Result:
3

Explanation:
School1 and ApartmentBuilding1 are in one city, Park1 and Supermarket1 in another, and Hospital1 in a third
*/

package Labs.Lab09_Graphs;

import java.util.*;

import DataStructures.AdjacencyListGraph;

public class NumberOfCities {
    static void visit(AdjacencyListGraph<String> graph, String vertex, Set<String> visited) {
        visited.add(vertex);
        for (String neighbor : graph.getNeighbors(vertex)) {
            if (!visited.contains(neighbor))
                visit(graph, neighbor, visited);
        }
    }

    static int countCities(AdjacencyListGraph<String> graph) {
        int counter = 0;
        Set<String> visited = new HashSet<>();
        for (String vertex : graph.getAdjacencyList().keySet()) {
            if (!visited.contains(vertex)) {
                visit(graph, vertex, visited);
                counter++;
            }
        }
        return counter;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        AdjacencyListGraph<String> graph = new AdjacencyListGraph<>();
        for (int i = 0; i < n; i++) {
            graph.addVertex(scanner.next());
        }
        int m = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < m; i++) {
            graph.addEdge(scanner.next(), scanner.next());
        }
        System.out.println(countCities(graph));
    }
}

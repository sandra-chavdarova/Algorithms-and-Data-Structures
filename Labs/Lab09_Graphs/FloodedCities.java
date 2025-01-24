/*
Дадени се градови и патишта кои постојат помеѓу нив.
Дел од патиштата се поплавени и не може да се користат,
што може да доведе до тоа градовите да се поделат на групи
што не се меѓусебно поврзани.
Секој град од една група е поврзан со барем еден друг град од групата
преку достапни (непоплавени) патишта, а не е поврзан со градови надвор од групата.
Да се одреди колку вакви групи ќе има во случај на поплава.

Влез: Во првиот ред е даден бројот на патишта M.
Во следните M редови се дадени градовите кои ги поврзува секој пат.
Потоа е даден бројот k на поплавени патишта,
а во следните k редови се дадени поплавените патишта.

Излез:  Бројот на групи на градови кои ќе останат меѓусебно поврзани.

/

Given cities and roads that exist between them,
some roads are flooded and cannot be used.
This may cause the cities to split into groups that are no longer connected.
Each city in a group is connected to at least one other city in the same group
via accessible (non-flooded) roads and is not connected to cities outside the group.
Determine how many such groups will exist in the event of a flood.

Input: The first line contains the number of roads M.
The next M lines specify the cities connected with each road.
In the next line, you're given the number of flooded roads k,
followed by k lines which give the flooded roads.

Output: The number of groups of still connected cities.

Input:
6
London Paris
Paris Brussels
Brussels Amsterdam
London Brussels
Paris Berlin
Berlin Warsaw
2
Paris Berlin
London Brussels

Result:
2

Explanation:
If the roads Paris-Berlin and London-Brussels can't be used,
the cities will be split into two groups:
{London, Paris, Brussels, Amsterdam} and {Berlin, Warsaw}.
*/

package Labs.Lab09_Graphs;

import java.util.*;

import DataStructures.AdjacencyListGraph;

public class FloodedCities {
    static void visit(AdjacencyListGraph<String> graph, String startVertex, Set<String> visited) {
        visited.add(startVertex);
        for (String vertex : graph.getNeighbors(startVertex)) {
            if (!visited.contains(vertex)) {
                visit(graph, vertex, visited);
            }
        }
    }

    static int countGroups(AdjacencyListGraph<String> graph) {
        int counter = 0;
        Set<String> visited = new HashSet<>();
        for (String vertex : graph.getAdjacencyList().keySet()) {
            if (!visited.contains(vertex)) {
                counter++;
                visit(graph, vertex, visited);
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
            graph.addEdge(scanner.next(), scanner.next());
        }
        int k = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < k; i++) {
            graph.removeEdge(scanner.next(), scanner.next());
        }
        System.out.println(countGroups(graph));
    }
}

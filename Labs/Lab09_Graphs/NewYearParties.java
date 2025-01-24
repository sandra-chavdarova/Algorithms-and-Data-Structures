/*
Дадени се луѓе, дел од кои меѓусебно се познаваат. Треба да се организираат дочеци на Нова Година,
така што секој човек во едно друштво што слави заедно се познава со барем еден друг човек од тоа друштво,
а не се познава со никој надвор од тоа друштво.
Да се одреди колку дочеци на Нова Година ќе организираат дадените луѓе.

Влез: Во првиот ред е даден бројот на луѓе N.
Потоа во следните N редови се дадени имињата на луѓето.
Во следниот ред е даден бројот на познанства M, а во следните M редови се дадени луѓето кои се познаваат.

Излез:  Бројот на дочеци на Нова Година.

/

You're given a group of people, some of whom know each other.
The task is to organize New Year's Eve gatherings such that every person in a group celebrating together
knows at least one other person in that group, and no one in the group knows anyone outside of it.
Determine how many New Year's Eve gatherings will be organized by the given people.

Input: The first line contains the number of people N.
The next N lines contain the names of the people.
The next line contains the number of connections M,
followed by M lines that specify the pairs of people who know each other.

Output: The number of cities the given objects are grouped into.

Input:
6
Alice
Bob
Charlie
Diana
Eve
Frank
3
Alice Bob
Charlie Diana
Eve Frank

Result:
3

(Explanation: Alice and Bob are one group, Charlie and Diana a second group, and Eve and Frank a third group)
*/

package Labs.Lab09_Graphs;

import java.util.*;

import DataStructures.AdjacencyListGraph;

public class NewYearParties {
    static void DFSUtil(AdjacencyListGraph<String> graph, String vertex, Set<String> visited) {
        visited.add(vertex);
        for (String neighbor : graph.getNeighbors(vertex)) {
            if (!visited.contains(neighbor)) {
                DFSUtil(graph, neighbor, visited);
            }
        }
    }

    static int count(AdjacencyListGraph<String> graph) {
        Set<String> visited = new HashSet<>();
        int counter = 0;
        for (String vertex : graph.getAdjacencyList().keySet()) {
            if (!visited.contains(vertex)) {
                DFSUtil(graph, vertex, visited);
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
        String startVertex = "";
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                startVertex = scanner.next();
                graph.addVertex(startVertex);
            } else {
                graph.addVertex(scanner.next());
            }
        }
        int m = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < m; i++) {
            graph.addEdge(scanner.next(), scanner.next());
        }
        System.out.println(count(graph));
    }
}
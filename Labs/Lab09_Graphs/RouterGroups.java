/*
Дадени се рутери означени со броеви од 0 до N-1 кои се поврзани во мрежа.
Ако некој рутер R падне, мрежата може да се подели на групи на рутери
што не се меѓусебно поврзани. Секој рутер од една група е поврзан
со барем еден друг рутер од групата, а не е поврзан со рутери надвор од групата.
Да се одреди колку вакви групи ќе настанат доколку рутерот R престане да работи.

Влез: Во првиот ред е даден бројот на рутери N.
Во следниот ред е даден бројот на врски помеѓу рутерите M,
а во следните M редови се дадени рутерите кои ги поврзува секоја врска.
Во последниот ред е даден рутерот R што престанал да работи.

Излез:  Бројот на групи на рутери.

/

Given routers labeled from 0 to N−1 that are connected in a network,
the failure of a specific router R may split the network
into groups of routers that are no longer connected.
Each router in a group is connected to at least one other router from that group,
and isn't connected to any routers outside of that group.
Determine the number of such groups that will form if router R fails.

Input: The first line contains the number of routers N.
The next line contains the number of connections between the routers M,
followed by M lines that specify which routers are connected with each connection.
The last line contains the router R that failed.

Output: The number of groups of routers.

Input:
5
4
0 1
1 2
2 3
2 4
1

Result:
2

Explanation:
If router 1 fails, the network will split into two groups: 0 and 2,3,4
*/

package Labs.Lab09_Graphs;

import java.util.*;

import DataStructures.AdjacencyListGraph;

public class RouterGroups {
    static void visit(AdjacencyListGraph<Integer> graph, Integer vertex, Set<Integer> visited) {
        visited.add(vertex);
        for (Integer v : graph.getNeighbors(vertex)) {
            if (!visited.contains(v)) {
                visit(graph, v, visited);
            }
        }
    }

    static int countGroups(AdjacencyListGraph<Integer> graph) {
        int counter = 0;
        Set<Integer> visited = new HashSet<>();
        for (Integer vertex : graph.getAdjacencyList().keySet()) {
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
        int m = scanner.nextInt();
        AdjacencyListGraph<Integer> graph = new AdjacencyListGraph<>();
        for (int i = 0; i < m; i++) {
            graph.addEdge(scanner.nextInt(), scanner.nextInt());
        }
        int router = scanner.nextInt();
        graph.removeVertex(router);
        System.out.println(countGroups(graph));
    }
}

/*
You are given an integer n.
There is an undirected graph with n nodes, numbered from 0 to n - 1.
You are given a 2D integer array edges where edges[i] = [ai, bi] denotes that there exists
an undirected edge connecting nodes ai and bi.

Return the number of pairs of different nodes that are unreachable from each other.

Link: https://leetcode.com/problems/count-unreachable-pairs-of-nodes-in-an-undirected-graph/description/?envType=problem-list-v2&envId=graph
*/

package Leetcode;

import java.util.*;
import java.util.Map.Entry;

import DataStructures.AdjacencyListGraph;

public class CountUnreachablePairsInUndirectedGraph {
    public static void visit(AdjacencyListGraph<Integer> graph, Set<Integer> visited, int startVertex) {
        visited.add(startVertex);
        for (int vertex : graph.getNeighbors(startVertex)) {
            if (!visited.contains(vertex))
                visit(graph, visited, vertex);
        }
    }

    public long unreachable(AdjacencyListGraph<Integer> graph, int startVertex) {
        Set<Integer> visited = new HashSet<>();
        visit(graph, visited, startVertex);
        return graph.getAdjacencyList().keySet().size() - visited.size();
    }

    public long countPairs(int n, int[][] edges) {
        AdjacencyListGraph<Integer> graph = new AdjacencyListGraph<>();
        for (int i = 0; i < n; i++) {
            graph.addVertex(i);
        }
        for (int i = 0; i < edges.length; i++) {
            graph.addEdge(edges[i][0], edges[i][1]);
        }
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += unreachable(graph, i);
        }
        return sum / 2;
    }
}

/*
There are n rooms labeled from 0 to n - 1 and all the rooms are locked except for room 0.
Your goal is to visit all the rooms. However, you cannot enter a locked room without having its key.

When you visit a room, you may find a set of distinct keys in it.
Each key has a number on it, denoting which room it unlocks, and you can take all of them with you to unlock the other rooms.

Given an array rooms where rooms[i] is the set of keys that you can obtain if you visited room i,
return true if you can visit all the rooms, or false otherwise.

Link: https://leetcode.com/problems/keys-and-rooms/description/?envType=problem-list-v2&envId=graph
*/

package Leetcode;

import java.util.*;

import DataStructures.AdjacencyListGraph;

public class KeysAndRooms {
    public boolean isVisited(AdjacencyListGraph<Integer> graph, Set<Integer> visited, int startVertex) {
        for (int vertex : graph.getAdjacencyList().keySet()) {
            if (!visited.contains(vertex))
                return false;
        }
        return true;
    }

    public static void visit(AdjacencyListGraph<Integer> graph, Set<Integer> visited, int startVertex) {
        visited.add(startVertex);
        for (int vertex : graph.getNeighbors(startVertex)) {
            if (!visited.contains(vertex))
                visit(graph, visited, vertex);
        }
    }

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        AdjacencyListGraph<Integer> graph = new AdjacencyListGraph<>();
        for (int i = 0; i < rooms.size(); i++) {
            graph.addVertex(i);
        }
        for (int i = 0; i < rooms.size(); i++) {
            for (int j = 0; j < rooms.get(i).size(); j++) {
                graph.addEdge(i, rooms.get(i).get(j));
            }
        }
        Set<Integer> visited = new HashSet<>();
        visit(graph, visited, 0);
        return isVisited(graph, visited, 0);
    }
}

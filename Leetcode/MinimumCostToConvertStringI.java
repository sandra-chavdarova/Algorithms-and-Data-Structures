/*
You are given two 0-indexed strings source and target,
both of length n and consisting of lowercase English letters.
You are also given two 0-indexed character arrays original and changed, and an integer array cost,
where cost[i] represents the cost of changing the character original[i] to the character changed[i].

You start with the string source.
In one operation, you can pick a character x from the string
and change it to the character y at a cost of z
if there exists any index j such that cost[j] == z, original[j] == x, and changed[j] == y.

Return the minimum cost to convert the string source
to the string target using any number of operations.
If it is impossible to convert source to target, return -1.

Note that there may exist indices i, j such that original[j] == original[i] and changed[j] == changed[i].

Link: https://leetcode.com/problems/minimum-cost-to-convert-string-i/description/?envType=problem-list-v2&envId=graph
*/

package Leetcode;

import java.util.*;

class AdjacencyListGraph<T> {
    private Map<T, Map<T, Integer>> adjacencyList;

    public AdjacencyListGraph() {
        this.adjacencyList = new HashMap<>();
    }

    // Add a vertex to the graph
    public void addVertex(T vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            adjacencyList.put(vertex, new HashMap<>());
        }
    }

    // Remove a vertex from the graph
    public void removeVertex(T vertex) {
        // Remove the vertex from all adjacency lists
        for (Map<T, Integer> neighbors : adjacencyList.values()) {
            neighbors.remove(vertex);
        }
        // Remove the vertex's own entry in the adjacency list
        adjacencyList.remove(vertex);
    }

    // Add an edge to the graph
    public void addEdge(T source, T destination, int weight) {
        addVertex(source);
        addVertex(destination);

        adjacencyList.get(source).put(destination, weight);
        //  adjacencyList.get(destination).put(source, weight); // for undirected graph
    }

    // Remove an edge from the graph
    public void removeEdge(T source, T destination) {
        if (adjacencyList.containsKey(source)) {
            adjacencyList.get(source).remove(destination);
        }
        if (adjacencyList.containsKey(destination)) {
            adjacencyList.get(destination).remove(source); // for undirected graph
        }
    }

    // Get all neighbors of a vertex
    public Map<T, Integer> getNeighbors(T vertex) {
        return adjacencyList.getOrDefault(vertex, new HashMap<>());
    }

    public void DFS(T startVertex) {
        Set<T> visited = new HashSet<>();
        DFSUtil(startVertex, visited);
    }

    private void DFSUtil(T vertex, Set<T> visited) {
        // Mark the current node as visited and print it
        visited.add(vertex);
        System.out.print(vertex + " ");

        // Recur for all the vertices adjacent to this vertex
        for (T neighbor : getNeighbors(vertex).keySet()) {
            if (!visited.contains(neighbor)) {
                DFSUtil(neighbor, visited);
            }
        }
    }


    public void DFSNonRecursive(T startVertex) {
        Set<T> visited = new HashSet<>();
        Stack<T> stack = new Stack<>();

        stack.push(startVertex);
        while (!stack.isEmpty()) {
            T vertex = stack.pop();
            if (!visited.contains(vertex)) {
                visited.add(vertex);
                System.out.print(vertex + " ");
                for (T neighbor : getNeighbors(vertex).keySet()) {
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
        }
    }

    public void printPath(T source, T destination) {
        Set<T> visited = new HashSet<>();
        Stack<T> stack = new Stack<>();

        stack.push(source);
        visited.add(source);
        while (!stack.isEmpty() && !stack.peek().equals(destination)) {
            T vertex = stack.peek();

            boolean f = true;
            for (T neighbor : getNeighbors(vertex).keySet()) {
                if (!visited.contains(neighbor)) {
                    stack.push(neighbor);
                    visited.add(neighbor);
                    f = false;
                    break;
                }
            }

            if (f) {
                stack.pop();
            }
        }

        Stack<T> path = new Stack<>();

        while (!stack.isEmpty()) {
            path.push(stack.pop());
        }

        while (!path.isEmpty()) {
            System.out.print(path.pop() + " ");
        }
    }

    public void BFS(T startVertex) {
        Set<T> visited = new HashSet<>();
        Queue<T> queue = new LinkedList<>();

        visited.add(startVertex);
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            T vertex = queue.poll();
            System.out.print(vertex + " ");

            for (T neighbor : getNeighbors(vertex).keySet()) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }

    public Map<T, Integer> shortestPath(T startVertex) {
        Map<T, Integer> distances = new HashMap<>();
        PriorityQueue<T> queue = new PriorityQueue<>(Comparator.comparingInt(distances::get));
        Set<T> explored = new HashSet<>();

        // Initialize distances
        for (T vertex : adjacencyList.keySet()) {
            distances.put(vertex, Integer.MAX_VALUE);
        }
        distances.put(startVertex, 0);

        queue.add(startVertex);

        while (!queue.isEmpty()) {
            T current = queue.poll();
            explored.add(current);

            for (Map.Entry<T, Integer> neighborEntry : adjacencyList.get(current).entrySet()) {
                T neighbor = neighborEntry.getKey();
                int newDist = distances.get(current) + neighborEntry.getValue();

                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);

                    // Update priority queue
                    if (!explored.contains(neighbor)) {
                        queue.add(neighbor);
                    }
                }
            }
        }

        return distances;
    }

    @Override
    public String toString() {
        String ret = new String();
        for (Map.Entry<T, Map<T, Integer>> vertex : adjacencyList.entrySet())
            ret += vertex.getKey() + ": " + vertex.getValue() + "\n";
        return ret;
    }
}

public class MinimumCostToConvertStringI {
    public static long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        AdjacencyListGraph<Character> graph = new AdjacencyListGraph<>();
        List<Character> nodes = new ArrayList<>();
        for (int i = 0; i < original.length; i++) {
            if (!nodes.contains(original[i])) nodes.add(original[i]);
            if (!nodes.contains(changed[i])) nodes.add(changed[i]);
            graph.addEdge(original[i], changed[i], cost[i]);
        }
        long result = 0;
        for (int i = 0; i < source.length(); i++) {
            Map<Character, Integer> paths = new HashMap<>();
            if (!nodes.contains(source.charAt(i))) {
                return -1;
            }
            paths = graph.shortestPath(source.charAt(i));
            if (paths.get(target.charAt(i)) > 100000000) {
                return -1;
            }
            result += paths.get(target.charAt(i));
        }
        return result;
    }

    public static void main(String[] args) {
        AdjacencyListGraph<Character> graph = new AdjacencyListGraph<>();
        String source = "abcd";
        String target = "acbe";
        char[] original = {'a', 'b', 'c', 'c', 'e', 'd'};
        char[] changed = {'b', 'c', 'b', 'e', 'b', 'e'};
        int[] cost = {2, 5, 5, 1, 2, 20};
        for (int i = 0; i < original.length; i++) {
            graph.addVertex(original[i]);
        }
        for (int i = 0; i < original.length; i++) {
            graph.addEdge(original[i], changed[i], cost[i]);
        }
        long result = minimumCost(source, target, original, changed, cost);
        System.out.println(result);
    }
}
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

package Labs.Lab9_Graphs;

import java.util.*;

class AdjacencyListGraph<T> {
    private Map<T, Set<T>> adjacencyList;

    public AdjacencyListGraph() {
        this.adjacencyList = new HashMap<>();
    }

    // Add a vertex to the graph
    public void addVertex(T vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            adjacencyList.put(vertex, new HashSet<>());
        }
    }

    // Remove a vertex from the graph
    public void removeVertex(T vertex) {
        // Remove the vertex from all adjacency lists
        for (Set<T> neighbors : adjacencyList.values()) {
            neighbors.remove(vertex);
        }
        // Remove the vertex's own entry in the adjacency list
        adjacencyList.remove(vertex);
    }

    // Add an edge to the graph
    public void addEdge(T source, T destination) {
        addVertex(source);
        addVertex(destination);

        adjacencyList.get(source).add(destination);
        adjacencyList.get(destination).add(source); // for undirected graph
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
    public Set<T> getNeighbors(T vertex) {
        return adjacencyList.getOrDefault(vertex, new HashSet<>());
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
        for (T neighbor : getNeighbors(vertex)) {
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
                for (T neighbor : getNeighbors(vertex)) {
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
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

            for (T neighbor : getNeighbors(vertex)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }

    public int shortestPath(T startVertex, T endVertex) {
        Set<T> visited = new HashSet<>();
        Queue<T> queue = new LinkedList<>();

        visited.add(startVertex);
        queue.add(startVertex);
        int elementsAtLevel;
        int level = 0;

        while (!queue.isEmpty()) {
            elementsAtLevel = queue.size();
            while (elementsAtLevel > 0) {
                T vertex = queue.poll();
                if (vertex.equals(endVertex))
                    return level;

                for (T neighbor : getNeighbors(vertex)) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.add(neighbor);
                    }
                }
                elementsAtLevel--;
            }
            level++;
        }
        return -1;
    }

    public void pathsOfLengthN(T startVertex, int goalLength) {
        Set<T> visited = new HashSet<>();
        ArrayList<T> startPath = new ArrayList<>();
        startPath.add(startVertex);
        pathsOfLengthNUtil(startVertex, goalLength, visited, startPath);
    }

    private void pathsOfLengthNUtil(T vertex, int goalLength, Set<T> visited, List<T> currentPath) {
        if (currentPath.size() == goalLength + 1) {
            System.out.println(currentPath);
            return;
        }
        visited.add(vertex);
        for (T neighbor : getNeighbors(vertex)) {
            if (!visited.contains(neighbor)) {
                currentPath.add(neighbor);
                pathsOfLengthNUtil(neighbor, goalLength, visited, currentPath);
                currentPath.remove(neighbor);
            }
        }
        visited.remove(vertex);
    }

    public void findPath(T startVertex, T endVertex) {
        Set<T> visited = new HashSet<>();
        Stack<T> invertedPath = new Stack<>();
        visited.add(startVertex);
        invertedPath.push(startVertex);

        while (!invertedPath.isEmpty() && !invertedPath.peek().equals(endVertex)) {
            T currentVertex = invertedPath.peek();
            T tmp = currentVertex;

            for (T vertex : getNeighbors(currentVertex)) {
                tmp = vertex;
                if (!visited.contains(vertex)) {
                    break;
                }
            }

            if (!visited.contains(tmp)) {
                visited.add(tmp);
                invertedPath.push(tmp);
            } else {
                invertedPath.pop();
            }
        }

        Stack<T> path = new Stack<>();
        while (!invertedPath.isEmpty()) {
            path.push(invertedPath.pop());
        }
        while (!path.isEmpty()) {
            System.out.println(path.pop());
        }
    }

    @Override
    public String toString() {
        String ret = new String();
        for (Map.Entry<T, Set<T>> vertex : adjacencyList.entrySet())
            ret += vertex.getKey() + ": " + vertex.getValue() + "\n";
        return ret;
    }

    public int count(T startVertex, int goalSum) {
        Set<T> visited = new HashSet<>();
        return countUtil(startVertex, goalSum, Integer.parseInt(startVertex.toString()), visited);
    }

    private int countUtil(T vertex, int goalSum, int sum, Set<T> visited) {
        if (sum == goalSum)
            return 1;
        if (sum > goalSum)
            return 0;
        visited.add(vertex);
        int counter = 0;
        for (T neighbour : getNeighbors(vertex)) {
            int currentSum = sum + Integer.parseInt(neighbour.toString());
            if (!visited.contains(neighbour) || currentSum <= goalSum) {
                counter += countUtil(neighbour, goalSum, currentSum, visited);
            }
        }
        visited.remove(vertex);
        return counter;
    }
}

public class SumOfVertices {
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
    }
}

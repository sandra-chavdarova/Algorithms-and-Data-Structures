package DataStructures;

import java.util.*;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;

public class AdjacencyListGraph<T> {
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
    }

    // Remove an edge from the graph
    public void removeEdge(T source, T destination) {
        if (adjacencyList.containsKey(source)) {
            adjacencyList.get(source).remove(destination);
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
        // System.out.print(vertex + " ");

        // Recur for all the vertices adjacent to this vertex
        for (T neighbor : getNeighbors(vertex)) {
            if (!visited.contains(neighbor)) {
                DFSUtil(neighbor, visited);
            }
        }
    }

    // Personally added function
    public boolean checkCourses() {
        Set<T> visited = new HashSet<>();
        Set<T> recursionStack = new HashSet<>();

        for (T vertex : adjacencyList.keySet()) {
            if (!visited.contains(vertex)) {
                if (!checkCoursesUtil(vertex, visited, recursionStack)) {
                    return false;
                }
            }
        }
        return true;
    }

    // Personally added function
    private boolean checkCoursesUtil(T vertex, Set<T> visited, Set<T> recursionStack) {
        visited.add(vertex);
        recursionStack.add(vertex);

        for (T neighbor : getNeighbors(vertex)) {
            if (recursionStack.contains(neighbor)) {
                return false;
            }
            if (!visited.contains(neighbor)) {
                if (!checkCoursesUtil(neighbor, visited, recursionStack)) {
                    return false;
                }
            }
        }
        recursionStack.remove(vertex);
        return true;
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

    public void printPath(T source, T destination) {
        Set<T> visited = new HashSet<>();
        Stack<T> stack = new Stack<>();

        stack.push(source);
        visited.add(source);
        while (!stack.isEmpty() && !stack.peek().equals(destination)) {
            T vertex = stack.peek();

            boolean f = true;
            for (T neighbor : getNeighbors(vertex)) {
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

            for (T neighbor : getNeighbors(vertex)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }

    // DFS utility function used for topological sorting
    private void topologicalSortUtil(T vertex, Set<T> visited, Stack<T> stack) {
        visited.add(vertex);
        for (T neighbor : getNeighbors(vertex)) {
            if (!visited.contains(neighbor)) {
                topologicalSortUtil(neighbor, visited, stack);
            }
        }
        stack.push(vertex);
    }

    public List<T> topologicalSort() {
        Stack<T> stack = new Stack<>();
        Set<T> visited = new HashSet<>();

        for (T vertex : adjacencyList.keySet()) {
            if (!visited.contains(vertex)) {
                topologicalSortUtil(vertex, visited, stack);
            }
        }

        List<T> order = new ArrayList<>();
        while (!stack.isEmpty()) {
            order.add(stack.pop());
        }
        return order;
    }


    @Override
    public String toString() {
        String ret = new String();
        for (Map.Entry<T, Set<T>> vertex : adjacencyList.entrySet())
            ret += vertex.getKey() + ": " + vertex.getValue() + "\n";
        return ret;
    }

    // Personally added class
    public int count(T vertex, int sum) {
        Set<T> set = new HashSet<>();
        return countR(vertex, Integer.parseInt(vertex.toString()), sum, set);
    }

    // Personally added class
    public int countR(T vertex, int currsum, int sum, Set<T> set) {
        if (sum == currsum) {
            return 1;
        }
        if (currsum > sum) {
            return 0;
        }
        set.add(vertex);
        int count = 0;
        for (T neighbour : getNeighbors(vertex)) {
            int currentSum = currsum + Integer.parseInt(neighbour.toString());
            if (!set.contains(neighbour) || currentSum <= sum)
                count += countR(neighbour, currentSum, sum, set);
        }
        set.remove(vertex);
        return count;
    }

    // Personally added class
    public void DFSUtilA(T vertex, Set<T> visited) {
        // Mark the current node as visited and print it
        visited.add(vertex);
        // System.out.print(vertex + " ");

        // Recur for all the vertices adjacent to this vertex
        for (T neighbor : getNeighbors(vertex)) {
            if (!visited.contains(neighbor)) {
                DFSUtilA(neighbor, visited);
            }
        }
    }

    //Personally added classs
    public int countSections() {
        Set<T> visited = new HashSet<>();
        int count = 0;
        for (T vertex : adjacencyList.keySet()) {
            if (!visited.contains(vertex)) {
                DFSUtilA(vertex, visited);
                count++;
            }
        }
        return count;
    }

    public int count() {
        Set<T> visited = new HashSet<>();
        int count = 0;
        for (T vertex : adjacencyList.keySet()) {
            if (!visited.contains(vertex)) {
                DFSUtil(vertex, visited);
                count++;
            }
        }
        return count;
    }

    public boolean exists(T source, T destination) {
        if (adjacencyList.get(source).contains(destination)) {
            return true;
        }
        return false;
    }
}

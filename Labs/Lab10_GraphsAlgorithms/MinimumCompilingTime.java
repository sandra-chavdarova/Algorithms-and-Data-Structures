/*
Даден е софтверски проект што се состои од повеќе модули.
За секој модул е потребно одредено време да се компајлира,
а модулите може да се зависни од други модули
(не може да се компајлира модул 2 пред да се заврши компајлирањето на модул 1).
Да се одреди колку најмалку време е потребно да се компајлира целиот проект
ако независните модули може да се компајлираат паралелно.

Влез: Во првиот ред е даден бројот на модули N.
Потоа во следните N редови се дадени модулите и времето што е потребно за компајлирање на секој модул.
Во следниот ред е даден бројот на зависности M, а потоа во следните M редови се дадени зависностите.
(Модул2 Модул1 значи дека Модул2 зависи од Модул1).

Излез:  Минималното време да се компајлира целиот проект.

/

You're given a software project that consists of multiple modules.
Each module takes a certain amount of time to compile,
and modules can be dependent on each other (can't start compiling module 2 before module 1 is compiled).
Determine the minimum time to compile the entire project if it's possible to compile independent modules in parallel.

Input: The first line contains the number of modules N.
The next N lines contain the names of the modules and the time needed for each module.
The next line contains the number of dependencies M, followed by M lines that contain the dependencies.
(Module2 Module1 means that Module2 depends on Module1)

Output: The minimum time to compile the project.

Input:
5
Module1 3
Module2 5
Module3 2
Module4 7
Module5 4
2
Module2 Module1
Module4 Module3

Result:
9

(Explanation: modules 1, 3 and 5 can be compiled in parallel.
After module 1 is done, module 2 can be compiled,
and the minimum time needed for it will be (5+3) 8.
Module 4 can start after module 3 and needs a minimum time of (2 + 7) 9.
Because modules 1 and 2 and modules 3 and 4 can be compiled in parallel,
and module 5 can also be compiled in parallel, the minimum time to compile the entire project is 9.)
*/

package Labs.Lab10_GraphsAlgorithms;

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
}

public class MinimumCompilingTime {
    public static int compileTime(AdjacencyListGraph<String> graph, String startVertex, Map<String, Integer> days) {
        int numberOfDays = days.get(startVertex);
        int max = 0;
        for (String vertex : graph.getNeighbors(startVertex)) {
            if (max < days.get(vertex))
                max = days.get(vertex);
        }
        return numberOfDays + max;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        AdjacencyListGraph<String> graph = new AdjacencyListGraph<>();
        Map<String, Integer> compileTimes = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String vertex = scanner.next();
            int weight = scanner.nextInt();
            graph.addVertex(vertex);
            compileTimes.put(vertex, weight);
            scanner.nextLine();
        }
        int m = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < m; i++) {
            graph.addEdge(scanner.next(), scanner.next());
        }
        int result = 0;
        List<String> topologicalSort = graph.topologicalSort();
//        System.out.println(topologicalSort);
        for (int i = topologicalSort.size() - 1; i >= 0; i--) {
//            System.out.print(days.get(topologicalSort.get(i)) + " ");
            compileTimes.put(topologicalSort.get(i), compileTime(graph, topologicalSort.get(i), days));
            if (compileTimes.get(topologicalSort.get(i)) > result)
                result = compileTimes.get(topologicalSort.get(i));
        }
        System.out.println(result);
    }
}

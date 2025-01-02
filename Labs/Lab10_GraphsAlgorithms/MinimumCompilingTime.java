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
//        adjacencyList.get(destination).put(source, weight); // for undirected graph
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

    int calculate(Map<String, Integer> compileTimes) {
        Map<T, Integer> isDependant = new HashMap<>();
        Map<T, Integer> maxCompileTime = new HashMap<>();
        Queue<T> queue = new LinkedList<>();
        int total = 0;

        for (T module : adjacencyList.keySet()) {
            isDependant.put(module, 0);
            maxCompileTime.put(module, compileTimes.get(module));
        }

        for (T module : adjacencyList.keySet()) {
            for (T neighbor : getNeighbors(module).keySet()) {
                if (isDependant.containsKey(neighbor)) {
                    isDependant.put(neighbor, isDependant.get(neighbor) + 1);
                }
            }
        }

        for (T module : adjacencyList.keySet()) {
            if (isDependant.get(module) == 0) {
                queue.offer(module);
            }
        }

        while (!queue.isEmpty()) {
            T currentModule = queue.poll();
            int currentMaxTime = maxCompileTime.get(currentModule);
            for (T neighbor : getNeighbors(currentModule).keySet()) {
                isDependant.put(neighbor, isDependant.get(neighbor) - 1);
                maxCompileTime.put(neighbor, Math.max(maxCompileTime.get(neighbor), currentMaxTime + compileTimes.get(neighbor)));
                if (isDependant.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
            total = Math.max(total, currentMaxTime);
        }
        return total;
    }
}

public class MinimumCompilingTime {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AdjacencyListGraph<String> graph = new AdjacencyListGraph<>();
        Map<String, Integer> compileTimes = new HashMap<>();
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            String vertex = scanner.next();
            int compileTime = scanner.nextInt();
            compileTimes.put(vertex, compileTime);
            graph.addVertex(vertex);
            scanner.nextLine();
        }
        int m = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < m; i++) {
            String first = scanner.next();
            String second = scanner.next();
            graph.addEdge(first, second, 0);
            scanner.nextLine();
        }
        System.out.println(graph.calculate(compileTimes));
    }
}
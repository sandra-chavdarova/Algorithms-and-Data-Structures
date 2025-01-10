/*
Дадени се состојки за рецепт, при што не се достапни сите потребни состојки.
Можно е достапните состојки да се транформираат во потребните,
при што секоја трансофрмација има одредена цена.
Дадени се можните директни трансформации и цената за секоја од нив.
Да се одреди најмалата цена за да се транформираат достапните состојки во потребните.

Влез: Во првиот ред е даден бројот на состојки N во секоја група (достапни и потребни).
Потоа во следните N редови се дадени парови на состојки:
достапната состојка и состојката во кој треба да се трансформира.
Во следниот ред е даден бројот на можни трансформации M,
а потоа во следните M редови се дадени трансформациите и нивните цени.

Излез: Минималната цена да се трансформираат сите состојки.

/

You're given recipe ingredients, where some needed ingredients aren't available.
It's possible to transform the available ingredients into the ones needed
for the recipe and each transformation has a price.
You're given the possible direct transformations
and the price of each transformation.
Determine the minimum price to transform the available ingredients into the needed ingredients.

Input: The first line contains the number of ingredients in each set
(available/needed) N. The next N lines contain the pairs of ingredients:
available ingredient and the ingredient it needs to be transformed into.
The next line contains the number of possible transformations M,
followed by M lines that contain the transformations and their prices.

Output: The minimum cost to transform all ingredients.

Input:
4
Flour Flour
Pork Bacon
Bacon Pork
Chicken Ham
6
Flour Pork 2
Pork Bacon 5
Bacon Pork 5
Bacon Ham 1
Ham Pork 2
Chicken Ham 20

Result:
28

Explanation:
transformation flour->flour 0
transformation pork->bacon 5
transformation bacon->pork 3
transformation chicken->ham 20
total: 28
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
}

public class TransformingIngredients {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        AdjacencyListGraph<String> graph = new AdjacencyListGraph<>();
        String[] start = new String[n];
        String[] end = new String[n];
        for (int i = 0; i < n; i++) {
            start[i] = scanner.next();
            end[i] = scanner.next();
            graph.addVertex(start[i]);
            graph.addVertex(end[i]);
        }
        int m = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < m; i++) {
            graph.addEdge(scanner.next(), scanner.next(), scanner.nextInt());
        }
        int result = 0;
        for (int i = 0; i < n; i++) {
            Map<String, Integer> dijkstra = graph.shortestPath(start[i]);
            result += dijkstra.get(end[i]);
        }
        System.out.println(result);
    }
}

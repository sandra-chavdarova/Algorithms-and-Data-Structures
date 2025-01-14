/*
Некоја држава одлучила да започне процес на реновирање на патишта помеѓу грдовите.
Помеѓу секои два града постои пат кој или веќе е реновиран или треба да се реновира.
Притоа, за реновирањето да биде извршено побрзо, потребно е да се најдат
најприоритетните патишта што треба да се реновираат,
со што сите градови би имале пристап до барем еден реновиран пат до другите градови.
Реновирањето треба да се направи по најниска можна цена.
Процесот на реновирање е веќе започнат.
Ваша задача е да се заврши тој процес, а притоа да се потрошат најмалку пари за поврзување на сите градови.

Влез:
Во првиот ред е даден бројот на градови, M.
Во вториот ред е даден бројот на патишта меѓу градовите, N
Во третиот ред е даден бројот на веќе реновирани патишта. P.
Во наредните M реда се дадени имињата на градовите.
Во следните N реда се дадени парови на имиња на градови, проследени со цел број
што претставува цена на реновирање на патот меѓу тие два града.
Во последните P реда се дадени парови од градови помеѓу кои веќе постои
реновиран пат и за кои не треба ние да трошиме дополнителни пари.

Излез:
Во првиот ред се печатат два броја:
бројот на патишта кои се останати да се реновираат, и цената на реновирање на тие патишта.
Потоа се печатат сите парови на градови помеѓу кои треба да бидат реновирани патиштата, секој во посебен ред.

Input:
5
6
3
Skopje
Kumanovo
SvetiNikole
Veles
Shtip
Skopje Veles 5
Skopje Kumanovo 3
Skopje SvetiNikole 6
Kumanovo SvetiNikole 4
Shtip Veles 4
Shtip SvetiNikole 2
Skopje SvetiNikole
Shtip SvetiNikole
Kumanovo SvetiNikole

Result:
1 4
Veles Shtip

Објаснување:
Скопје, Свети Николе, Куманово и Штип се веќе поврзани со реновирани патишта.
Велес не е поврзан и има две опции за поврзување: кон Скопје или кон Штип.
Патот кон Штип е поевтин за реновирање, па доволно е да се реновира само тој
за сите градови да бидат поврзани со барем еден реновиран пат.
*/

package Exercises.SecondPartialExam;

import java.util.*;

class Edge {
    private int fromVertex, toVertex;
    private int weight;

    public Edge(int from, int to, int weight) {
        this.fromVertex = from;
        this.toVertex = to;
        this.weight = weight;
    }

    public int getFrom() {
        return this.fromVertex;
    }

    public int getTo() {
        return this.toVertex;
    }

    public int getWeight() {
        return this.weight;
    }
}

class AdjacencyMatrixGraph<T> {
    private int numVertices;
    private int[][] matrix;
    private T[] vertices;

    @SuppressWarnings("unchecked")
    public AdjacencyMatrixGraph(int numVertices) {
        this.numVertices = numVertices;
        matrix = new int[numVertices][numVertices];
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                matrix[i][j] = 0;
            }
        }
        vertices = (T[]) new Object[numVertices];
    }

    public void addVertex(int index, T data) {
        vertices[index] = data;
    }

    public T getVertex(int index) {
        return vertices[index];
    }

    public void addEdge(int source, int destination, int weight) {
        matrix[source][destination] = weight;
        matrix[destination][source] = weight; // For undirected graph
    }

    public boolean isEdge(int source, int destination) {
        return matrix[source][destination] != 0;
    }


    public void removeEdge(int source, int destination) {
        matrix[source][destination] = 0;
        matrix[destination][source] = 0; // For undirected graph
    }

    @SuppressWarnings("unchecked")
    public void removeVertex(int vertexIndex) {
        if (vertexIndex < 0 || vertexIndex >= numVertices) {
            throw new IndexOutOfBoundsException("Vertex index out of bounds!");
        }
        int[][] newMatrix = new int[numVertices - 1][numVertices - 1];
        T[] newVertices = (T[]) new Object[numVertices - 1];
        // Copy the vertices and matrix excluding the given vertex
        int ni = 0;
        for (int i = 0; i < numVertices; i++) {
            if (i == vertexIndex) continue;
            int nj = 0;
            for (int j = 0; j < numVertices; j++) {
                if (j == vertexIndex) continue;
                newMatrix[ni][nj] = matrix[i][j];
                nj++;
            }
            newVertices[ni] = vertices[i];
            ni++;
        }
        // Replace the old matrix and vertices with the new ones
        matrix = newMatrix;
        vertices = newVertices;
        numVertices--;
    }

    public List<T> getNeighbors(int vertexIndex) {
        List<T> neighbors = new ArrayList<>();
        for (int i = 0; i < matrix[vertexIndex].length; i++) {
            if (matrix[vertexIndex][i] != 0) {
                neighbors.add(vertices[i]);
            }
        }
        return neighbors;
    }

    public List<Edge> getAllEdges() {
        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (isEdge(i, j)) {
                    edges.add(new Edge(i, j, matrix[i][j]));
                }
            }
        }

        return edges;
    }

    private void union(int u, int v, int[] trees) {
        int findWhat, replaceWith;
        if (u < v) {
            findWhat = trees[v];
            replaceWith = trees[u];
        } else {
            findWhat = trees[u];
            replaceWith = trees[v];
        }

        for (int i = 0; i < trees.length; i++) {
            if (trees[i] == findWhat) {
                trees[i] = replaceWith;
            }
        }
    }

    public List<Edge> kruskal() {
        List<Edge> mstEdges = new ArrayList<>();
        List<Edge> allEdges = getAllEdges();

        allEdges.sort(Comparator.comparingInt(Edge::getWeight));

        int trees[] = new int[numVertices];

        for (int i = 0; i < numVertices; i++)
            trees[i] = i;

        for (Edge e : allEdges) {
            if (trees[e.getFrom()] != trees[e.getTo()]) {
                mstEdges.add(e);

                union(e.getFrom(), e.getTo(), trees);
            }
        }

        return mstEdges;
    }

    public List<Edge> prim(int startVertexIndex) {
        List<Edge> mstEdges = new ArrayList<>();
        Queue<Edge> q = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));

        boolean included[] = new boolean[numVertices];

        for (int i = 0; i < numVertices; i++) {
            included[i] = false;
        }

        included[startVertexIndex] = true;

        for (int i = 0; i < numVertices; i++) {
            if (isEdge(startVertexIndex, i)) {
                q.add(new Edge(startVertexIndex, i, matrix[startVertexIndex][i]));
            }
        }

        while (!q.isEmpty()) {
            Edge e = q.poll();

            if (!included[e.getTo()]) {
                included[e.getTo()] = true;
                mstEdges.add(e);
                for (int i = 0; i < numVertices; i++) {
                    if (!included[i] && isEdge(e.getTo(), i)) {
                        q.add(new Edge(e.getTo(), i, matrix[e.getTo()][i]));
                    }
                }
            }
        }

        return mstEdges;
    }

    public List<Edge> adaptedKruskal(int trees[]) {
        List<Edge> mstEdges = new ArrayList<>();
        List<Edge> allEdges = getAllEdges();

        allEdges.sort(Comparator.comparingInt(Edge::getWeight));

        for (Edge e : allEdges) {
            if (trees[e.getFrom()] != trees[e.getTo()]) {
                mstEdges.add(e);

                union(e.getFrom(), e.getTo(), trees);
            }
        }

        return mstEdges;
    }

    @Override
    public String toString() {
        String ret = "  ";
        for (int i = 0; i < numVertices; i++)
            ret += vertices[i] + " ";
        ret += "\n";
        for (int i = 0; i < numVertices; i++) {
            ret += vertices[i] + " ";
            for (int j = 0; j < numVertices; j++)
                ret += matrix[i][j] + " ";
            ret += "\n";
        }
        return ret;
    }
}

public class RenovatingHighways {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int cities = scanner.nextInt();
        int highways = scanner.nextInt();
        int renovated = scanner.nextInt();
        scanner.nextLine();
        AdjacencyMatrixGraph<String> graph = new AdjacencyMatrixGraph<>(cities);
        Map<String, Integer> mapping = new HashMap<>();
        int[] trees = new int[cities];
        for (int i = 0; i < cities; i++) {
            String city = scanner.next();
            graph.addVertex(i, city);
            mapping.put(city, i);
            trees[i] = i + 1;
        }

        for (int i = 0; i < highways; i++) {
            graph.addEdge(mapping.get(scanner.next()), mapping.get(scanner.next()), scanner.nextInt());
            scanner.nextLine();
        }
        for (int i = 0; i < renovated; i++) {
            String first = scanner.next();
            String second = scanner.next();
            graph.addEdge(mapping.get(first), mapping.get(second), 0);
            trees[mapping.get(first)] = 0;
            trees[mapping.get(second)] = 0;
        }
        List<Edge> result = graph.adaptedKruskal(trees);
        int sum = 0, counter = 0;
        for (Edge e : result) {
            sum += e.getWeight();
            if (e.getWeight() != 0)
                counter++;
        }

        System.out.println(counter + " " + sum);
        for (Edge e : result) {
            if (e.getWeight() != 0)
                System.out.println(graph.getVertex(e.getFrom()) + " " + graph.getVertex(e.getTo()));
        }
    }
}

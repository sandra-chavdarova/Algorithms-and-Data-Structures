/*
Во земjата Лапониjа живее Дедо Мраз.
Во слободното време кога не е Нова Година, додека џуџињата си работат на играчките
за следната година, Дедо Мраз има хоби. Тоj сака да одгледува рибички.
Но, тоj своите рибички ги одгледува во природни езера.
Езерата се меѓусебно поврзани со рекички, и рекичките течат од едно езерце до друго.
Рибите од едно езеро слободно можат преку рекичките да отидат во друго езеро.
Секоjа пролет дедо мраз сака да прави порибување на езерцата со нови рибички.
Ваша задача е да му кажете на Дедо Мраз доколку тоj пушти нови рибички во езерцето X,
во колку други езерца ќе можат рибичките сами да стигнат,
а со тоа да нема потреба тоj самиот да ги порибува тие езерца.

Влез: Во првата линиjа од влезот е даден броj N < 15 броjот на езерца.
Во втората линиjа е даден броj U < 20 броjот на реки меѓу езерцата.
Во следните U линии се дадени парови од 2 броjа R и Q,
што значи постои рекичка коjа тече од R до Q, каде R и Q се броеви на езерцата.
Во последната линиjа е даден броj L, во кое езерце Дедо Мраз ќе ги пушти рибичките.

Излез: Се испишува броjот, колку езерца освен почетното ќе бидат порибени.

/

Santa Claus lives in Lapland. In his free time when it is not around New Year,
while the elves are working on toys for next year, Santa Claus has a hobby.
He likes to raise fish. But he does this in natural lakes.
The ponds are interconnected by creeks, and the creeks flow from one pond to another.
Fish from one lake can freely go to another lake through the streams.
Every spring Santa Claus likes to stock the ponds with new fish.
Your task is to tell Santa Claus, if he releases new fishes into pond X,
how many other ponds the fishes will be able to reach on their own,
so that there is no need for him to stock those ponds himself.

Input: In the first line of the input, the number N < 15 is given,
the number of ponds. In the second line, the number U < 20 is given,
the number of rivers between the lakes. In the following U lines,
pairs of 2 numbers R and Q are given, which means there is a river flowing
from R to Q, where R and Q are pond numbers.
In the last line, the number L is given, in which pond Santa Claus will release the fish.

Output: The number of ponds that will be stocked except the initial one is printed out.

Input:
11
19
3 3
7 8
7 3
1 7
0 0
7 2
6 3
2 0
0 9
6 10
1 2
2 8
5 7
4 3
10 4
3 9
7 10
9 4
4 10
7

Result:
7
*/

package Labs.Lab10_GraphsAlgorithms;

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
//        matrix[destination][source] = weight; // For undirected graph
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

    private List<Edge> getAllEdges() {
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

    public int countLakes(AdjacencyMatrixGraph<Integer> graph, int startVertex) {
        int counter = 0;
        Set<Integer> visited = new HashSet<>();
        visited.add(startVertex);
        for (int vertex : graph.getNeighbors(startVertex)) {
            if (!visited.contains(vertex)) {
                visited.add(vertex);
                counter++;
            }
        }
        return counter;
    }
}

public class SantasFish {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        AdjacencyMatrixGraph<Integer> graph = new AdjacencyMatrixGraph<>(n);
        int edges = scanner.nextInt();
        for (int i = 0; i < edges; i++) {
            graph.addEdge(scanner.nextInt(), scanner.nextInt(), 1);
        }
        int startVertex = scanner.nextInt();
        System.out.println(graph.prim(startVertex).size());
    }
}

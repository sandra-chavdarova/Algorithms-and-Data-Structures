/*
Потребно е дадени материјали да се трансформираат во други материјали.
Дадени се можните директни трансформации и цената за секоја од нив.
Да се одреди најмалата цена за да се транформираат почетните материјали во целните.

Влез: Во првиот ред е даден бројот на материјали N во секоја група (почетни и целни).
Потоа во следните N редови се дадени парови на материјали:
почетниот материјал и материјалот во кој треба да се трансформира.
Во следниот ред е даден бројот на можни трансформации M,
а потоа во следните M редови се дадени трансформациите и нивните цени.

Излез:  Минималната цена да се трансформираат сите материјали.

/

Given materials need to be transformed into other materials.
You're given the possible direct transformations and the price of each transformation.
Determine the minimum price to transform the starting materials into the goal materials.

Input: The first line contains the number of materials in each set (starting/goal) N.
The next N lines contain the pairs of materials:
starting material and the material it needs to be transformed into.
The next line contains the number of possible transformations M,
followed by M lines that contain the transformations and their prices.

Output: The minimum cost to transform all materials.

Input:
4
Wood Wood
Iron Steel
Steel Iron
Glass Mirror
6
Wood Iron 2
Iron Steel 5
Steel Iron 5
Steel Mirror 1
Mirror Iron 2
Glass Mirror 20

Result:
28

(Explanation:
transformation wood->wood 0
transformation iron->steel 5
transformation steel->iron 3
transformation glass->mirror 20
total: 28)
*/

package Labs.Lab10_GraphsAlgorithms;

import java.util.*;

import DataStructures.GraphsAlgorithms.AdjacencyListGraph;

public class TransformingMaterials {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        AdjacencyListGraph<String> graph = new AdjacencyListGraph<>();
        String[] start = new String[n];
        String[] end = new String[n];
        for (int i = 0; i < n; i++) {
            start[i] = scanner.next();
            graph.addVertex(start[i]);
            end[i] = scanner.next();
            graph.addVertex(end[i]);
        }
        int m = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < m; i++) {
            graph.addEdge(scanner.next(), scanner.next(), scanner.nextInt());
            scanner.nextLine();
        }

        int total = 0;
        for (int i = 0; i < n; i++) {
            String s = start[i];
            String e = end[i];
            Map<String, Integer> shortestPaths = graph.shortestPath(s);
            if (shortestPaths.containsKey(e)) {
                total += shortestPaths.get(e);
            }
        }
        System.out.println(total);
    }
}
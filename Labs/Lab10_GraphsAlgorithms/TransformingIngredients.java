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

import DataStructures.GraphsAlgorithms.AdjacencyListGraph;

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

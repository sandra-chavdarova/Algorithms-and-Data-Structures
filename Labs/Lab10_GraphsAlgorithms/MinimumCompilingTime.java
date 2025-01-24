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

import DataStructures.TopologicalSort.AdjacencyListGraph;

public class MinimumCompilingTime {
    public static int maxCompileTime(AdjacencyListGraph<String> graph, String startVertex, Map<String, Integer> days) {
        int max = 0;
        for (String vertex : graph.getNeighbors(startVertex)) {
            if (max < days.get(vertex))
                max = days.get(vertex);
        }
        return max;
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
        for (int i = topologicalSort.size() - 1; i >= 0; i--) {
            compileTimes.put(topologicalSort.get(i), compileTimes.get(topologicalSort.get(i)) + maxCompileTime(graph, topologicalSort.get(i), compileTimes));
            if (compileTimes.get(topologicalSort.get(i)) > result)
                result = compileTimes.get(topologicalSort.get(i));
        }
        System.out.println(result);
    }
}

/*
Даден е проект што се состои од повеќе задачи.
За секоја задача се потребни одреден број денови за да се заврши,
а задчите може да се зависни од други задачи
(не може да се почне со задача 2 пред да се зврши задача 1).
Да се одреди колку најмалку денови ќе бидат потребни да се заврши целиот проект
ако на независни задачи може да се работи паралелно.

Влез: Во првиот ред е даден бројот на задачи N.
Потоа во следните N редови се дадени задачите и времето што е потребно
за завршување на секоја задача. Во следниот ред е даден
бројот на зависности M, а потоа во следните M редови се дадени зависностите.
(Задача2 Задача1 значи дека Задача2 зависи од Задача1).

Излез:  Минималното време да се заврши целиот проект.

/

You're given a project that consists of multiple tasks.
Each task takes a certain amount of days to complete,
and tasks can be dependent on each other
(can't start task 2 before task 1 is completed).
Determine the minimum number of days to complete the entire project
if it's possible to work on independent tasks in parallel.

Input: The first line contains the number of tasks N.
The next N lines contain the names of the tasks and the time needed
for each task. The next line contains the number of dependencies M,
followed by M lines that contain the dependencies.
(Task2 Task1 means that Task2 depends on Task1)

Output: The minimum number of days to complete the project.

Input:
5
Task1 3
Task2 5
Task3 2
Task4 7
Task5 4
2
Task2 Task1
Task4 Task3

Result:
9

Explanation:
tasks 1, 3 and 5 can be done in parallel.
After task 1 is done, task 2 can be started,
and will need at least (5+3) 8 days.
Task 4 can start after task 3 and needs at least (2 + 7) 9 days.
Because tasks 1 and 2 and tasks 3 and 4 can be done in parallel,
and task 5 can also be done in parallel,
it takes 9 days to complete the entire project.
*/

package Labs.Lab10_GraphsAlgorithms;

import java.util.*;

import DataStructures.TopologicalSort.AdjacencyListGraph;

public class MinimumNumberOfDays {
    public static int findMaxTime(AdjacencyListGraph<String> graph, String vertex, Map<String, Integer> days) {
        int numberOfDays = days.get(vertex);
        int max = 0;
        for (String v : graph.getNeighbors(vertex)) {
            if (max < days.get(v))
                max = days.get(v);
        }
        return numberOfDays + max;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        AdjacencyListGraph<String> graph = new AdjacencyListGraph<>();
        Map<String, Integer> days = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String vertex = scanner.next();
            int weight = scanner.nextInt();
            graph.addVertex(vertex);
            days.put(vertex, weight);
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
            days.put(topologicalSort.get(i), findMaxTime(graph, topologicalSort.get(i), days));
            if (days.get(topologicalSort.get(i)) > result)
                result = days.get(topologicalSort.get(i));
        }
        System.out.println(result);
    }
}
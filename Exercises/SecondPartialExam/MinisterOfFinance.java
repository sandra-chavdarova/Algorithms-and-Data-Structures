/*
Министерот за транспорт и врски има на располагање одреден буџет кој треба да го искористи
за да поврзе што е можно повеќе градови преку железнички сообраќај со главната железничка станица.

На влез се дадени бројот на градовите, самите градови и помеѓѕ кои градови има патишта.
Потоа е дадена главната железничка станица и буџетот.

Треба да се испечати кок=лку градови најмногу ќе бидат посетени и колкав дел од буџетот ќе се искористи.

Input:
5
Prilep
Bitola
Stip
Veles
Kicevo
5
Prilep Bitola 10
Veles Stip 12
Bitola Kicevo 15
Veles Prilep 13
Kicevo Prilep 20
Prilep 48

Result:
3 35
*/

package Exercises.SecondPartialExam;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import java.util.*;

import DataStructures.Edge;
import DataStructures.GraphsAlgorithms.AdjacencyMatrixGraph;

public class MinisterOfFinance {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        AdjacencyMatrixGraph<String> graph = new AdjacencyMatrixGraph<>(n);
        List<String> cities = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String vertex = scanner.next();
            graph.addVertex(i, vertex);
            map.put(vertex, i);
            cities.add(vertex);
        }
        int edges = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < edges; i++) {
            graph.addEdge(map.get(scanner.next()), map.get(scanner.next()), scanner.nextInt());
        }
        String station = scanner.next();
        int budget = scanner.nextInt();
        List<Edge> railways = graph.prim(map.get(station));
        int sum = 0, counter = 0;
        for (Edge e : railways) {
            System.out.print(e.getWeight() + " ");
            if (e.getWeight() > budget)
                break;
            budget -= e.getWeight();
            sum += e.getWeight();
            counter++;
        }
        System.out.println();
        System.out.println(counter + " " + sum);
    }
}

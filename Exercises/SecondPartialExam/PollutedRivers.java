/*
Во дадена мрежа од реки постои извор на загадување.
Загадувањето се пропагира низводно по текот на реките.
Постои дадена должина која загаденета вода може да ја помине пред да се разреди и стане незабележлива.
Ваша задача е, за дадена мрежа и извор на загадување, да најдете кои локации на реките ќе бидат загадени.

Влез:
Во првата линија на влезот се дадени два броеви М и N:
бројот на локации од интерес и бројот на речни сегменти помеѓу локациите.
Во втората линија на влезот се дадени имињата на локациите одделени со празно место.
Во наредните N линии дадени се следните податоци: почетна точка на речен сегмент,
крајна точка на речен сегмент и должина на сегментот.
Во последниот ред е дадено во која точка е изворот на загадувањето,
како и должината после која загадувањето се разредува.

Излез:
На излез треба да се испечатат сите точки кај кои ќе има загадување на водата, секоја во нов ред.
Редоследот на печатење треба да биде исти како и редоследот според кој се прочитани.

Input:
8 8
A B C D E F G H
A B 2
C D 3
B E 5
D E 7
E F 3
E G 4
F H 1
G H 2
A 8

Result:
A
B
E
*/

package Exercises.SecondPartialExam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.*;

import DataStructures.GraphsAlgorithms.AdjacencyListGraph;

public class PollutedRivers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        scanner.nextLine();
        AdjacencyListGraph<String> graph = new AdjacencyListGraph<>();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String vertex = scanner.next();
            graph.addVertex(vertex);
            list.add(vertex);
        }
        for (int i = 0; i < m; i++) {
            graph.addEdge(scanner.next(), scanner.next(), scanner.nextInt());
            scanner.nextLine();
        }
        String startVertex = scanner.next();
        int distance = scanner.nextInt();
        Map<String, Integer> edges = graph.shortestPath(startVertex);
        for (int i = 0; i < list.size(); i++) {
            if (edges.get(list.get(i)) <= distance)
                System.out.println(list.get(i));
        }
    }
}

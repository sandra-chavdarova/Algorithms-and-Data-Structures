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

import DataStructures.Edge;
import DataStructures.GraphsAlgorithms.AdjacencyMatrixGraph;

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

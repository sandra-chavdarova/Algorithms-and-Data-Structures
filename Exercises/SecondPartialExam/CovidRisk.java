/*
Поради ковид пандемијата при секое тестирање на даден пациент
се зачувува општината во која живее, неговото презиме
и дали е позитивен или негативен на корона вирусот.
Потребни се статистички податоци врз основа на кои ќе се одреди
ризик факторот за дадена општина.
За таа цел ќе се чуваат две хеш табели (CBHT),
едната за позитивни, а другата за негативни пациенти од корона вирусот.
Ваша задача е за дадена општина на излез да го испечатите
ризик факторот за дадената општина.

Ризик фактор = (бр. на негативни пациенти) / (бр. на позитивни пациенти)

Забелешка: Можни е да се појават пациенти со исто презиме.
Истите треба да се земат како посебни вредности во статистиката.

Влез:
На влез најпрво е даден бројот на пациенти – N,
а потоа секој пациент е даден во нов ред во форматот:
"Општина во која живее" "Презиме на пациент" "Резултати од тестот(позитивен/негативен)"
На крај е дадена општината за која треба да се пресмета ризик факторот.

Излез:
Децимален број заокружен на две децимали
кој го претставува ризик факторот за дадената општина.

Input:
6
Centar Stojanoski negative
Centar Trajkovski positive
Centar Petkovski positive
Karposh Stojanoski positive
Karposh Trajkovski negative
Centar Trajkovski positive
Centar

Output:
0.75

(излезот се добива како 3/(1+3) за општината Центар)
*/

package Exercises.SecondPartialExam;

import java.util.Scanner;

public class CovidRisk {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        CBHT<String, Integer> positive = new CBHT<>(n + 1);
        CBHT<String, Integer> negative = new CBHT<>(n + 1);
        for (int i = 0; i < n; i++) {
            String place = scanner.next();
            String surname = scanner.next();
            String state = scanner.next();
            if (state.equals("positive")) {
                if (positive.search(place) == null)
                    positive.insert(place, 1);
                else
                    positive.search(place).element.value++;
            } else {
                if (negative.search(place) == null)
                    negative.insert(place, 1);
                else
                    negative.search(place).element.value++;
            }
        }
        String place = scanner.next();
        float resut = (float) positive.search(place).element.value / (positive.search(place).element.value + negative.search(place).element.value);
        System.out.println(resut);
    }
}

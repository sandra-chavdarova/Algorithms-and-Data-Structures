/*
Во магацинот на една фармацевтска компанија се чуваат најразлични видови лекови.
За секој лек потребно е да се чуваат податоци за името на лекот, цената во денари и намената на лекот.
За поефикасен пристап до податоците за лековите, фармацевтската компанија одлучила
податоците да ги чува во хеш табела (CBHT) каде се сместуваат соодветните податоци.
Хеш табелата е достапна до крајните клиенти и истите може да пребаруваат низ внесените податоци.
Бидејќи на пазарот постојат повеќе лекови кои таргетираат иста болест,
најчесто клиентите го бараат оној лек кој има најниска цена.
Па вашата задача е со користење на хеш табелата, за дадена намена (болест),
да го испечатите лекот кој има најниска цена на пазарот.
Доколку за бараната намена во магацинот нема лек се печати "Nema lek za baranata namena vo magacin.".

Влез:
Најпрво е даден бројот на лекови - N, а потоа секој лек е даден во нов ред во форматот:
“Име на лек” “Намена” “Цена во денари”
На крај е дадена намената за која треба да се пронајде лекот со најниска цена.

Излез:
Името на лекот со најмала цена.

Input:
5
Analgin Glavobolka 80
Daleron Glavobolka 90
Lineks Bolki_vo_stomak 150
Spazmeks Bolki_vo_stomak 150
Loratadin Alergija 150
Glavobolka

Result:
Analgin
*/

package Exercises.SecondPartialExam;

import java.util.Scanner;

public class Pharmacy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        CBHT<String, String> medication = new CBHT<>(n + 1); // purpose -> medication
        CBHT<String, Integer> prices = new CBHT<>(n + 1);    // medication -> price
        for (int i = 0; i < n; i++) {
            String med = scanner.next();
            String purpose = scanner.next();
            int price = scanner.nextInt();
            if (medication.search(purpose) == null) {
                medication.insert(purpose, med);
                prices.insert(med, price);
            } else {
                if (prices.search(medication.search(purpose).element.value).element.value > price) {
                    medication.insert(purpose, med);
                    prices.insert(med, price);
                }
            }
            scanner.nextLine();
        }
        String purpose = scanner.next();
        if (medication.search(purpose) == null)
            System.out.println("Nema lek za baranata namena");
        else
            System.out.println(prices.search(medication.search(purpose).element.value).element.key);
    }
}
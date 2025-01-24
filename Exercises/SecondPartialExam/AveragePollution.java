/*
На стандарден влез е даден цел број N кој означува број на мерења,
и потоа во следните N линии дадени се мерења во форма: општина, мерка.
За некои општини можно е да се дадени повеќе мерки.

Во последната линија од влезот ќе биде дадено име на една општина,
и ваша задача е да го отпечатите средното загадување
во таа општина базирано врз дадените мерења.

Од податочни структури, за решавање на оваа задача се дозволени само hash структурите.

Input:
5
Karposh 5.2
Chair 3.11
Centar 2.23
Karposh 1.7
Karposh 2.67
Karposh

Result:
3.19
*/

package Exercises.SecondPartialExam;

import java.util.Scanner;

import DataStructures.MapEntry;
import DataStructures.CBHT;
import DataStructures.SLLNode;

public class AveragePollution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        CBHT<String, Float> averagePollutions = new CBHT<>(n + 1);
        CBHT<String, Integer> frequencies = new CBHT<>(n + 1);
        for (int i = 0; i < n; i++) {
            String name = scanner.next();
            float pollution = scanner.nextFloat();
            if (averagePollutions.search(name) == null) {
                averagePollutions.insert(name, pollution);
                frequencies.insert(name, 1);
            } else {
                float p = averagePollutions.search(name).element.value;
                averagePollutions.insert(name, p + pollution);
                frequencies.search(name).element.value++;
            }
            scanner.nextLine();
        }
        String result = scanner.next();
        float average = averagePollutions.search(result).element.value / frequencies.search(result).element.value;
        System.out.println((float) Math.round(average * 100) / 100);
    }
}
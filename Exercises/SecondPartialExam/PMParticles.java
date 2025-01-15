/*
Се внесува општина и треба да се најде просекот на ПМ Честичките за таа општина.

Input:
6
Aerodrom 234.43
Centar 222.434
Aerodrom 123.54
Karpos 342.12
Aerodrom 153.34
GaziBaba 231.42
Aerodrom

Result:
170.44
*/

package Exercises.SecondPartialExam;

import java.util.Scanner;

public class PMParticles {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        CBHT<String, Float> pmParticles = new CBHT<>(n + 1);
        CBHT<String, Integer> counters = new CBHT<>(n + 1);
        for (int i = 0; i < n; i++) {
            String name = scanner.next();
            float number = scanner.nextFloat();
            if (pmParticles.search(name) == null) {
                pmParticles.insert(name, number);
                counters.insert(name, 1);
            } else {
                pmParticles.search(name).element.value += number;
                counters.search(name).element.value++;
            }
            scanner.nextLine();
        }
        String name = scanner.next();
        float result = pmParticles.search(name).element.value / counters.search(name).element.value;
        System.out.println((float) Math.round(result * 100) / 100);
    }
}

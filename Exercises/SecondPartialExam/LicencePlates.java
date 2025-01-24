/*
На семафорите низ градот планирано е да се постават радари со камери,
кои ќе ги забележуваат возилата што возат над дозволената брзина.
За таа цел, во Хеш табела се чуваат регистарските таблички
со информациите на сопственикот на возилото (име и презиме),
така што во моментот кога ќе биде забележана регистарската табличка на возило
што ја надминува дозволената брзина со сложеност O(1), да се добијат информациите за сопственикот на возилото.

Забелешка: Може да користите и готови Java класи за податочните структури.
Hint: За времето може да искористите некоја од готовите класи на java како SimpleDateFormat или Date.

(Задачата се смета за точна ако 7/10 тест примери се точни)

Влез: Во првиот ред е даден бројот на регистарски таблички - N кои ќе се внесуваат во хеш табелата.
Во секој нареден нов ред се’ до N дадена е регистарската табличка,
името и презимето на сопственикот на возилото одвоени со празно место.
Во следниот ред е дадена максималната дозволена брзина,
а потоа во нов ред е даден дневниот извештај од радарот,
односно листа од возила со регистарски таблички што биле забележани од радарот,
со која брзина поминале и точно време, одвоени со празно место (редоследот не е хронолошки).

Излез: Дневниот извештај од радарот треба да се преработи,
така што треба да се добијат информации за возачите кои направиле престап.
Излезот треба да биде во формат: име и презиме на сопствениците на возила кои ја надминале дозволената брзина,
одвоени со празно место и подредени според времето кога радарот ги забележал.

Input:
5
SK1234AA Anita Angelovska
OH1212BE Aleksandar Antov
ST0989OO Ognen Spirovski
ST0000AB Sara Spasovska
SK8888KD Dino Ackov
50
SK8888KD 48 14:00:00
ST0000AB 55 12:00:02
ST0989OO 60 08:10:00
SK1234AA 65 20:00:10
OH1212BE 50 22:00:21

Result:
Ognen Spirovski
Sara Spasovska
Anita Angelovska

(во ова решение не се користат класите SimpleDateFormat и Date)
*/

package Exercises.SecondPartialExam;

import java.util.*;
import java.text.ParseException;

import DataStructures.MapEntry;
import DataStructures.CBHT;
import DataStructures.SLLNode;

public class LicencePlates {
    static class Driver {
        String name;
        int time;

        public Driver(String name, int time) {
            this.name = name;
            this.time = time;
        }
    }

    static void sort(LinkedList<Driver> drivers) {
        for (int i = 0; i < drivers.size(); i++) {
            for (int j = 0; j < drivers.size() - 1; j++) {
                if (drivers.get(j).time > drivers.get(j + 1).time) {
                    Driver temp = drivers.get(j);
                    drivers.set(j, drivers.get(j + 1));
                    drivers.set(j + 1, temp);
                }
            }
        }
    }

    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        CBHT<String, String> table = new CBHT<>(n + 1);
        for (int i = 0; i < n; i++) {
            String plate = scanner.next();
            String name = scanner.next();
            String surname = scanner.next();
            table.insert(plate, name + " " + surname);
        }
        int speed = scanner.nextInt();
        scanner.nextLine();
        String[] info = scanner.nextLine().split(" ");
        LinkedList<Driver> drivers = new LinkedList<>();
        for (int i = 0; i < info.length - 2; i += 3) {
            String plate = info[i];
            int driverSpeed = Integer.parseInt(info[i + 1]);
            String[] times = info[i + 2].split(":");
            int time = Integer.parseInt(times[0]) * 3600 + Integer.parseInt(times[1]) * 60 + Integer.parseInt(times[2]);

            if (driverSpeed > speed) {
                String name = table.search(plate).element.value;
                drivers.add(new Driver(name, time));
            }
        }
        sort(drivers);
        for (Driver driver : drivers) {
            System.out.println(driver.name);
        }
    }
}
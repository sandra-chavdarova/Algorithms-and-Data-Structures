/*
На влез во оваа задача ќе ви бидат дадени редови како следниот формат:
Ime Prezime budzhet ip_adresa vreme grad cena
Пример
Jovan Todorov 1000 10.73.112.200 15:30 Bitola 760
Кое што значи дека лицето со Име и Презиме Jovan Todorov,
има буџет од 1000 денари, има IP адреса со мрежа 10.73.112 и домаќин (host number) 200,
и се приклучил во 15:30 часот за да купи билет кон Bitola кој што чини 760 денари.

Ќе ви бидат дадени N такви редови, по што ќе следи празен ред па уште M редови од истиот формат, кои ќе ги користиме за тестирање.

Од редот за тестирање треба да го извадите градот и потоа да го одговорите следното прашање со овој град:

Од сите N лица на влез, кои купуваат билет за до истиот град,
колку од нив се вклучиле подоцна од 11:59;
и од овие, кој од нив се вклучил најрано?

(погледнете го тест примерот!)
(доколку има повеќе со исто најмало време тогаш кој е првиот таков во влезот?) (секогаш ќе постои барем еден таков)

Ова ќе треба да го направите за сите M редови за тестирање!
Препорака, користете OBHT и/или CBHT.
-----------------------------------------------------------------------------------------------
At the input of this task you will be given rows with the following format:
First_name Last_name budget ip_address time city price
Example
Jovan Todorov 1000 10.73.112.200 15:30 Bitola 760
Which means that the person with First name and Last name Jovan Todorov,
has a budget of 1000 denars, has an IP address with network 10.73.112 and host number 200,
and joined at 15:30 to buy a ticked to Bitola which costs 760 denars.

You will be given N such rows, followed by an empty row and M more rows of the same format, which we will use for testing.

From the test row you need to extract the city and then answer the following question with this city:

Of the N people at the input, from the ones who buy a ticket to the same city,
how many of them joined later than 11:59;
and of these, which one joined the earliest?

(if there are more with the same minimum time then which one is the first one in the entry?) (there will always be at least one)

You will need to do this for all M rows for testing!
Recommendation, use OBHT and/or CBHT.

Input:
5
Jovan Todorov    1000    10.73.112.200     16:30   Bitola     760
Mitko Janev      4350    132.28.112.200    12:15   Krusevo    4000
Sara Dobreva     2700    10.73.60.29       14:35   Bitola     2500
Mence Trajanova  4000    10.73.112.112     11:25   Bitola     4200
Viktor Jovev     2200    10.73.112.79      15:15   Strumica   1800

1
Jovan Todorov    1000    10.73.112.200     16:30   Bitola     760

Result:
City: Bitola has the following number of customers:
2
The user who logged on earliest after noon from that city is:
Sara Dobreva with salary 2700 from address 10.73.60.29 who logged in at 14:35
*/

package Labs.Lab06_HashTables;

import java.util.Scanner;

import static java.lang.Integer.parseInt;

import DataStructures.CBHT;

public class EarliestCustomerByCity {
    static class Person {
        String name;
        String surname;
        Integer budget;
        String ip_address;
        String time;
        Integer hour;
        Integer minutes;
        String city;
        Integer price;

        public Person(String name, String surname, Integer budget, String ip_address, String time, Integer hour, Integer minutes, String city, Integer price) {
            this.name = name;
            this.surname = surname;
            this.budget = budget;
            this.ip_address = ip_address;
            this.time = time;
            this.hour = hour;
            this.minutes = minutes;
            this.city = city;
            this.price = price;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        CBHT<String, Person> tableCities = new CBHT<>(n);
        CBHT<String, Integer> earliest = new CBHT<>(n);
        for (int i = 0; i < n; i++) {

            String name = scanner.next();
            String surname = scanner.next();
            Integer budget = scanner.nextInt();
            String ip_address = scanner.next();
            String time = scanner.next();

            String[] split = time.split(":");

            Integer hour = parseInt(split[0]);
            Integer minutes = parseInt(split[1]);
            String city = scanner.next();
            Integer price = scanner.nextInt();

            scanner.nextLine();

            Person person = new Person(name, surname, budget, ip_address, time, hour, minutes, city, price);
            if (tableCities.search(person.city) == null) {
                if (person.hour >= 12) {
                    tableCities.insert(person.city, person);
                    earliest.insert(person.city, 1);
                }
            } else {
                if (person.hour >= 12) {
                    Person existingPerson = tableCities.search(person.city).element.value;
                    earliest.insert(person.city, earliest.search(person.city).element.value + 1);
                    if (existingPerson.hour > person.hour) {
                        tableCities.insert(person.city, person);

                    } else if (existingPerson.hour.equals(person.hour) && existingPerson.minutes > person.minutes) {
                        tableCities.insert(person.city, person);
                    }
                }
            }
        }

        int m = scanner.nextInt();
        for (int i = 0; i < m; i++) {
            scanner.nextLine();
            String name = scanner.next();
            String surname = scanner.next();
            Integer budget = scanner.nextInt();
            String ip_address = scanner.next();
            String time = scanner.next();

            String[] split = time.split(":");

            Integer hour = parseInt(split[0]);
            Integer minutes = parseInt(split[1]);
            String city = scanner.next();
            Integer price = scanner.nextInt();

            Person person = new Person(name, surname, budget, ip_address, time, hour, minutes, city, price);
            System.out.println("City: " + city + " has the following number of customers:");
            Integer total = earliest.search(city).element.value;
            System.out.println(total);
            System.out.println("The user who logged on earliest after noon from that city is:");
            Person result = tableCities.search(city).element.value;

            System.out.println(result.name + " " + result.surname + " with salary " + result.budget + " from address " + result.ip_address + " who logged in at " + result.time);
            System.out.println();
        }
    }
}
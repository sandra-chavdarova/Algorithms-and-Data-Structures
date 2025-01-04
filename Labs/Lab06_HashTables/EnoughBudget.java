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
Од сите N лица на влез, кои купуваат билет за до истиот град
колку од нив имале доволно буџет за да го купат билетот;
и од овие, кој од нив платил најголем износ?

(погледнете го тест примерот!)
(доколку има повеќе со ист најголем износ тогаш кој е првиот таков во влезот?) (секогаш ќе постои барем еден таков)

Ова ќе треба да го направите за сите M редови за тестирање!

Препорака, користете OBHT и/или CBHT.

----------------------

At the input of this task you will be given rows with the following format:
First_name Last_name budget ip_address time city price
Example
Jovan Todorov 1000 10.73.112.200 15:30 Bitola 760
Which means that the person with First name and Last name Jovan Todorov,
has a budget of 1000 denars, has an IP address with network 10.73.112 and host number 200,
and joined at 15:30 to buy a ticked to Bitola which costs 760 denars.

You will be given N such rows, followed by an empty row and M more rows of the same format, which we will use for testing.

From the test row you need to extract the city and then answer the following question with this city:

Of the N people at the input, from the ones who buy a ticket to the same city
how many of them had enough budget to buy the ticket;
and of these, which one paid the highest amount?

(if there are more with the same highest amount then which one is the first one in the input?) (there will always be at least one such)

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
The user who spent the most purchasing for that city is:
Sara Dobreva with salary 2700 from address 10.73.60.29 who spent 2500
*/

package Labs.Lab06_HashTables;

import java.util.Scanner;

public class EnoughBudget {
    static class Person {
        String name;
        String surname;
        int budget;
        String IPaddress;
        String time;
        String city;
        int price;

        public Person(String name, String surname, int budget, String IPaddress, String time, String city, int price) {
            this.name = name;
            this.surname = surname;
            this.budget = budget;
            this.IPaddress = IPaddress;
            this.time = time;
            this.city = city;
            this.price = price;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        CBHT<String, Person> table = new CBHT<>(n);
        CBHT<String, Integer> customers = new CBHT<>(n);
        for (int i = 0; i < n; i++) {
            String name = scanner.next();
            String surname = scanner.next();
            int budget = scanner.nextInt();
            String IPaddress = scanner.next();
            String time = scanner.next();
            String city = scanner.next();
            int price = scanner.nextInt();
            scanner.nextLine();
            Person person = new Person(name, surname, budget, IPaddress, time, city, price);
            if (table.search(city) == null) {
                if (price <= budget) {
                    table.insert(city, person);
                    customers.insert(city, 1);
                }
            } else {
                if (price <= budget) {
                    customers.insert(city, customers.search(city).element.value + 1);
                    Person existing = table.search(city).element.value;
                    if (existing.price < price) {
                        table.insert(city, person);
                    }
                }
            }
        }
        int m = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < m; i++) {
            String name = scanner.next();
            String surname = scanner.next();
            int budget = scanner.nextInt();
            String IPaddress = scanner.next();
            String time = scanner.next();
            String city = scanner.next();
            int price = scanner.nextInt();
            scanner.nextLine();
            Person person = new Person(name, surname, budget, IPaddress, time, city, price);
            System.out.println("City: " + city + " has the following number of customers:");
            System.out.println(customers.search(city).element.value);
            System.out.println("The user who spent the most purchasing for that city is:");
            Person result = table.search(city).element.value;
            System.out.println(result.name + " " + result.surname + " with salary " + result.budget + " from address " + result.IPaddress + " who spent " + result.price);
            System.out.println();
        }
    }
}

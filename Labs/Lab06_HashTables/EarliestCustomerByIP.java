/*
На влез во оваа задача ќе ви бидат дадени редови како следниот формат:
Ime Prezime budzhet ip_adresa vreme grad cena
Пример
Jovan Todorov 1000 10.73.112.200 15:30 Bitola 760
Кое што значи дека лицето со Име и Презиме Jovan Todorov, има буџет од 1000 денари,
има IP адреса со мрежа 10.73.112 и домаќин (host number) 200,
и се приклучил во 15:30 часот за да купи билет кон Bitola кој што чини 760 денари.

Ќе ви бидат дадени N такви редови, по што ќе следи празен ред па уште M редови од истиот формат, кои ќе ги користиме за тестирање.

Од редот за тестирање треба да ја извадите IP адресата на мрежата и потоа да го одговорите следното прашање со оваа мрежа:

Од сите N лица на влез, кои што се поврзуваат со истата мрежа (од било кој домаќин во мрежата)
колку од нив се вклучиле подоцна од 11:59;
и од овие, кој од нив се вклучил најрано?

(погледнете го тест примерот!)
(доколку има повеќе со исто најмало време тогаш кој е првиот таков во влезот?) (секогаш ќе постои барем еден таков)

Ова ќе треба да го направите за сите M редови за тестирање!

Препорака, користете OBHT и/или CBHT.

----------------------

At the input of this task you will be given rows with the following format:
First_name Last_name budget ip_address time city price
Example
Jovan Todorov 1000 10.73.112.200 15:30 Bitola 760
Which means that the person with First name and Last name Jovan Todorov, has a budget of 1000 denars,
 has an IP address with network 10.73.112 and host number 200,
 and joined at 15:30 to buy a ticked to Bitola which costs 760 denars.

You will be given N such rows, followed by an empty row and M more rows of the same format, which we will use for testing.

From the test line you need to extract the IP address of the network and then answer the following question with this network:

Of the N people at the input, from the ones who connect from the same network (from any host on the network)
how many of them logged in later than 11:59;
and of these, which one logged in the earliest?

(if there are more with the same minimum time then which one is the first one in the input?)
(there will always be at least one of them)

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
IP network: 10.73.112 has the following number of users:
2
The user who logged on earliest after noon from that network is:
Viktor Jovev with salary 2200 from address 10.73.112.79 who logged in at 15:15
*/

package Labs.Lab06_HashTables;

import java.util.Scanner;

import static java.lang.Integer.parseInt;

class Person {
    String name;
    String surname;
    int budget;
    String IPaddress;
    String time;
    int hour;
    int minutes;
    String city;
    int price;
    String network;

    public Person(String name, String surname, int budget, String IPaddress, String time, int hour, int minutes, String city, int price, String network) {
        this.name = name;
        this.surname = surname;
        this.budget = budget;
        this.IPaddress = IPaddress;
        this.time = time;
        this.hour = hour;
        this.minutes = minutes;
        this.city = city;
        this.price = price;
        this.network = network;
    }
}

public class EarliestCustomerByIP {
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

            String[] split = time.split(":");
            int hour = Integer.parseInt(split[0]);
            int minutes = Integer.parseInt(split[1]);

            String city = scanner.next();
            int price = scanner.nextInt();
            scanner.nextLine();

            String[] networks = IPaddress.split("\\.");
            String network = String.join(".", networks[0], networks[1], networks[2]);

            Person person = new Person(name, surname, budget, IPaddress, time, hour, minutes, city, price, network);

            if (table.search(network) == null) {
                if (hour >= 12) {
                    table.insert(network, person);
                    customers.insert(network, 1);
                }
            } else {
                if (hour >= 12) {
                    customers.insert(network, customers.search(network).element.value + 1);
                    Person existing = table.search(network).element.value;
                    if (existing.hour > hour) {
                        table.insert(network, person);
                    } else if (existing.hour == hour && existing.minutes > minutes) {
                        table.insert(network, person);
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

            String[] split = time.split(":");
            int hour = Integer.parseInt(split[0]);
            int minutes = Integer.parseInt(split[1]);

            String city = scanner.next();
            int price = scanner.nextInt();
            scanner.nextLine();

            String[] networks = IPaddress.split("\\.");
            String network = String.join(".", networks[0], networks[1], networks[2]);

            Person person = new Person(name, surname, budget, IPaddress, time, hour, minutes, city, price, network);

            System.out.println("IP network: " + network + " has the following number of users:");
            System.out.println(customers.search(network).element.value);
            System.out.println("The user who logged on earliest after noon from that network is:");
            Person result = table.search(network).element.value;
            System.out.println(result.name + " " + result.surname + " with salary " + result.budget + " from address " + result.IPaddress + " who logged in at " + result.time);
            System.out.println();
        }
    }
}

/*
Во оваа задача ќе работите со два објекти „Вработени“ и „Проекти“.
Еден вработен е дефиниран со 2 вредности: Име (String), Возраст (цел број).
Еден проект е дефиниран со 2 вредности: Работно Време (цел број), Плата по час (цел број).
За даден проект вкупната плата се пресметува како производ на работното време и платата по час.
Вработените ќе добиваат понудени проекти, и треба да го изберат проектот од кој ќе добијат
најголема плата (доколку имаат избор од повеќе проекти со иста најголема плата, тогаш првиот таков).

Ваша задача ќе биде да им помогнете на вработените со тоа што ќе искористите
CBHT со 10 кофички каде што за секој вработен ќе ја чувате неговата најдобра понуда.
За хеш функција користете производ од возраста на вработениот и ASCII вредноста
на првата буква од името на вработениот.

Влез:
Во првиот ред е даден еден цел број N кој го означува бројот на понуди.
Потоа во следните N редови се дадени по 4 вредности, информациите за понудите,
името и возраста на вработениот и работното време и платата од час на проектот соодветно:

N
name_1 age_1 time_1 rate_1
name_2 age_2 time_2 rate_2
...
name_N age_N time_N rate_N

Излез:
Отпечатете ја целата табела (со готовиот toString метод).
Секој вработен да се печати во формат "<name, age>" каде на местото на name и age
треба да стои името и возраста на вработениот соодветно (имплементирајте го ова во toString метод).
Секој проект да се печати во формат "<time, rate>" каде на местото на time и rate
треба да стои работното време и платата по час на проектот соодветно (имплементирајте го ова во toString метод).

-----------------------------------

In this task, you will work with two objects "Employees" and "Projects".
An employee is defined with 2 values: Name (String), Age (integer).
A project is defined with 2 values: Working Time (integer), Hourly Salary (integer).
For a given project, the total salary is calculated as the product of the working time and the hourly salary.
Employees will be offered projects, and they should choose the project
from which they will receive the highest salary (if they have a choice of multiple projects
with the same highest salary, then the first one).

Your task will be to help employees by using CBHT with 10 buckets where
for each employee you will store their best offer.
For the hash function, use the product of the employee's age
and the ASCII value of the first letter of the employee's name.

Input:
The first line contains an integer N that indicates the number of offers.
Then, in the next N rows, 4 values ​​are given, the information about the offers,
the name and age of the employee and the working hours and hourly wage of the project respectively:

N
name_1 age_1 time_1 rate_1
name_2 age_2 time_2 rate_2
...
name_N age_N time_N rate_N

Output:
Print the entire table (with the ready-made toString method).
Each employee should be printed in the format "<name, age>" where in place of name and age should be
the name and age of the employee respectively (implement this in the toString method).
Each project should be printed in the format "<time, rate>" where in place of time and rate should be
the working hours and hourly wage of the project respectively (implement this in the toString method).

Input:
5
Martin 25 3 100
Jana 26 4 90
Martin 25 5 120
Jana 26 2 95
Nenad 20 6 80

Result:
0:<<Nenad, 20>,<6, 80>>
1:
2:
3:
4:<<Jana, 26>,<4, 90>>
5:<<Martin, 25>,<5, 120>>
6:
7:
8:
9:
*/

package Labs.Lab06_HashTables;

import java.util.Objects;
import java.util.Scanner;

import DataStructures.SLLNode;
import DataStructures.MapEntry;
import DataStructures.CBHT;

class Employee implements Comparable<Employee> {
    String name;
    Integer age;

    public Employee(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "<" + name + ", " + age + ">";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name) && Objects.equals(age, employee.age);
    }

    @Override
    public int hashCode() {
        return age * (name.charAt(0));
    }

    @Override
    public int compareTo(Employee o) {
        return 0;
    }
}

class Project {
    Integer time;
    Integer fee;

    public Project(Integer time, Integer fee) {
        this.time = time;
        this.fee = fee;
    }

    public int totalFee() {
        return time * fee;
    }

    @Override
    public String toString() {
        return "<" + time + ", " + fee + ">";
    }
}

public class BestProjectOffer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        CBHT<Employee, Project> table = new CBHT<Employee, Project>(10);
        for (int i = 0; i < n; i++) {
            String name = scanner.next();
            Integer age = scanner.nextInt();
            Employee employee = new Employee(name, age);
            Integer time = scanner.nextInt();
            Integer fee = scanner.nextInt();
            Project project = new Project(time, fee);
            if (table.search(employee) == null) {
                table.insert(employee, project);
            } else {
                SLLNode<MapEntry<Employee, Project>> newOffer = table.search(employee);
                Project best = newOffer.element.value;
                if (project.totalFee() > best.totalFee()) {
                    table.insert(employee, project);
                }
            }
        }
        System.out.println(table);
    }
}

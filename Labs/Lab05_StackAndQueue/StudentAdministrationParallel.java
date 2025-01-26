/*

*/

package Labs.Lab05_StackAndQueue;

import java.util.*;

import DataStructures.LinkedQueue;

public class StudentAdministrationParallel {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        LinkedQueue<Student1> submitDocuments = new LinkedQueue<>();
        LinkedQueue<Student1> index = new LinkedQueue<>();
        LinkedQueue<Student1> receiveDocuments = new LinkedQueue<>();
        for (int i = 0; i < n; i++) {
            String name = scanner.nextLine();
            int activity1 = scanner.nextInt();
            int activity2 = scanner.nextInt();
            int activity3 = scanner.nextInt();
            Student1 student = new Student1(name, activity1, activity2, activity3);
            if (activity1 == 1)
                submitDocuments.enqueue(student);
            else if (activity2 == 1)
                index.enqueue(student);
            else if (activity3 == 1)
                receiveDocuments.enqueue(student);
            scanner.nextLine();
        }
        while (!submitDocuments.isEmpty() || !index.isEmpty() || !receiveDocuments.isEmpty()) {
            for (int i = 0; i < 2; i++) {
                if (!submitDocuments.isEmpty()) {
                    Student1 student = submitDocuments.dequeue();
                    student.submitDocuments = 0;
                    if (student.index == 1)
                        index.enqueue(student);
                    else if (student.receiveDocuments == 1)
                        receiveDocuments.enqueue(student);
                    else
                        System.out.println(student.name);
                }
            }
            for (int i = 0; i < 3; i++) {
                if (!index.isEmpty()) {
                    Student1 student = index.dequeue();
                    student.index = 0;
                    if (student.receiveDocuments == 1)
                        receiveDocuments.enqueue(student);
                    else
                        System.out.println(student.name);
                }
            }
            if (!receiveDocuments.isEmpty()) {
                Student1 student = receiveDocuments.dequeue();
                student.receiveDocuments = 0;
                System.out.println(student.name);
            }
        }
    }
}

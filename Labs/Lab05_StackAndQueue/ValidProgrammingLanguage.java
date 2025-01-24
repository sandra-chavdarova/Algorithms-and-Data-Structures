/*
Даден е код на модифициран програмски јазик каде функциите се претставени со отворен и затворен таг
("imeFunkcija" и "endimeFunkcija"). Ваша задача е да дефинирате дали даден програмски код е валиден.

Пример валиден код:
func1
func2
endfunc2
func3
endfunc3
endfunc1

Пример невалиден код (испуштен е затворен таг за func3):
func1
func2
endfunc2
func3
endfunc1

Влез: Код со модифициран програмски јазик, каде секој таг е напишан во нов ред.
Се внесуваат тагови се додека не се внесе "x".

Излез: "Valid" - доколку е валиден кодот, "Invalid" - доколку не е валиден кодот.

/

We are given code in a modified programming language is given, where functions are represented
by an opening and closing tag ("functionName" and "endfunctionName").
Your task is to determine whether the given code is valid.

Example of valid code:
func1
func2
endfunc2
func3
endfunc3
endfunc1

Example of invalid code (missing closing tag for func3):
func1
func2
endfunc2
func3
endfunc1

Input: Code in a modified programming language, where each tag is written in a new line.
Tags are entered until "x" is entered.

Output: "Valid" if the code is valid, "Invalid" if the code is not valid.

Input:
func1
func2
endfunc2
func3
endfunc3
endfunc1
x

Result:
Valid
*/

package Labs.Lab05_StackAndQueue;

import java.util.NoSuchElementException;
import java.util.Scanner;

import DataStructures.Stack;
import DataStructures.ArrayStack;

public class ValidProgrammingLanguage {
    public static boolean isValid(String[] functions) {
        Stack<String> strings = new ArrayStack<>(100);
        for (int i = 0; i < functions.length && functions[i] != null; i++) {
            if (functions[i].startsWith("func")) {
                strings.push(functions[i]);
            }
            if (functions[i].startsWith("end") && !functions[i].substring(3).equals(strings.pop())) {
                return false;
            }
        }
        return strings.isEmpty();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int i = 0;
        String[] functions = new String[100];
        while (true) {
            functions[i] = scanner.nextLine();
            if (functions[i].equals("x"))
                break;
            i++;
        }
        if (isValid(functions)) {
            System.out.println("Valid");
        } else {
            System.out.println("Invalid");
        }
    }
}

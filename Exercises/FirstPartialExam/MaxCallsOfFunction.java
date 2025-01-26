/*
Да се најде функцијата која повикува најголем број функции од дадени повици на функции.
Повиците и враќањата од функции се дадени на влез.
На излез да се испечати името на функцијата од која се повикани најголем број други функции.
Се бројат само директни повици на функции. Рекурзивни повици нема да бидат излистани.
Повиците од нулто ниво се прават од main функцијата.
На влез прво е даден бројот на редови во влезот N,
а потоа се излистани повиците и враќањата од процедурите.

Input:
12
Call a
Call b
Call c
Return
Call d
Return
Call e
Return
Return
Call f
Return
Return

Result:
b 3

Објаснување:
Од функцијата b се повикани функциите c, d и е

--------------------------------------------

Find the function that makes the most calls to other functions.
The calls and returns of functions are given on input.
The output should contain the name of the function that made most of the calls,
as well as the number of calls made. Only direct function calls are counted.
Recursive calls will not be listed. Zero-level calls are made from the main function.
The input contains a number N, which is the number of next lines on the input.
In the next N lines, the calls and returns are listed.

Input:
12
Call a
Call b
Call c
Return
Call d
Return
Call e
Return
Return
Call f
Return
Return

Result:
b 3

Explanation:
The function b calls the functions c, d and е
*/

package Exercises.FirstPartialExam;

import java.util.Scanner;

import DataStructures.Stack;
import DataStructures.LinkedStack;

public class MaxCallsOfFunction {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int max = 0;
        int main = 0;
        String function = "main";
        Stack<String> stack = new LinkedStack<>();
        Stack<Integer> calls = new LinkedStack<>();
        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            if (type.equals("Call")) {
                if (stack.isEmpty())
                    main++;
                else {
                    int counter = calls.pop();
                    calls.push(counter + 1);
                }
                stack.push(scanner.next());
                calls.push(0);
            } else {
                String f = stack.pop();
                int counter = calls.pop();
                if (counter > max) {
                    max = counter;
                    function = f;
                }
                if (main > max) {
                    max = main;
                    function = "main";
                }
            }
        }
        System.out.println(function + " " + max);
    }
}
/*
2.
10
Call a
Return
Call b
Return
Call c
Return
Call d
Return
Call e
Return

main 5
----------
3.
14
Call a
Call b
Call c
Return
Call d
Return
Call e
Return
Return
Call f
Call g
Return
Return
Return

b 3
----------
4.
10
Call a
Call b
Return
Call c
Return
Call d
Return
Call e
Return
Return

a 4
----------
5.
10
Call c
Call b
Return
Call a
Return
Call d
Return
Call e
Return
Return

c 4
----------
6.
8
Call a
Call b
Return
Call c
Return
Call d
Return
Return

a 3
----------
7.
12
Call c
Call b
Return
Call a
Return
Call d
Return
Call e
Return
Call f
Return
Return

c 5
----------
8.
2
Call c
Return

main 1
----------
9.
16
Call a
Call b
Call c
Return
Call d
Return
Call e
Return
Return
Call f
Call g
Call e
Return
Return
Return
Return

b 3
----------
10.
2
Call a
Return

main 1
*/
/*
Да се потсетиме дека стринг А е подсеквенца на стринг В доколку А може да се добие од В
со бришење на неколку знаци од било кое место во стрингот В, на пример:
Некои подсеквенци на стрингот “wowwo” се стринговите "wowwo", "wowo", "oo", "wow", "",
но следните стрингови не се: "owoo", "owwwo", "ooo", бидејќи НЕ моде да се додаваат знаци.

Забележете дека има различни подсеквенци од знаци кои што го даваат истиот резултат,
на пример од “wowwo” можеме да ги земеме знаците на индекси 0, 1 и 2 или пак 0, 1 и 3,
и за двете подсеквенци ќе добиеме стринг “wow”.
Всушност, WOW фактор на даден стринг е дефиниран како бројот на подсеквенци
кои го даваат стрингот “wow” како резултат. Вие треба да го пресметате WOW факторот на стринг
што ќе ви биде даден на влезот.

Влез: Стрингот за кој што треба да го пресметате WOW факторот.
Излез: Бараниот резултат.
Пример:
Влез:                Излез:
wwoww                  4

Објаснување за првиот пример:
w |wow| w
w |wo| w |w|
|w| w |ow| w
|w| w |o| w |w|

---------------------------
Recall that a string A is a subsequence of a string B if A can be obtained from B
by deleting a few characters from anywhere in the string B, for example:
Some subsequences of the string “wowwo” are the strings "wowwo", "wowo", "oo", "wow", "",
but the following strings are not: "owoo", "owwwo", "ooo", because characters cannot be added.

Note that there are different subsequences of characters that give the same result,
for example, from “wowwo” we can take the characters at indices 0, 1 and 2 or 0, 1 and 3,
and for both subsequences we will get the string “wow”.
In fact, the WOW factor of a given string is defined as the number of subsequences
that give the string “wow” as a result. You need to calculate the WOW factor of the string
that will be given to you as input.

Input: The string for which you need to calculate the WOW factor.
Output: The desired result.
Example:
Input:                Output:
wwoww                    4

Explanation of the example:
w |wow| w
w |wo| w |w|
|w| w |ow| w
|w| w |o| w |w|

_____НАПОМЕНА_____: Заради ограничувањата на тест системот, користете while(scanner.hasNext()){ ...your solution... }
за да тестирате повеќе тест примери со едно извршување на програмата.
_____NOTICE_____: Due to test system limitations, use while(scanner.hasNext()){ ...your solution... }
to test multiple test cases with a single execution of the program.

Input:
wwoww
wooooowooooowwwoowwo
owoowwowwwoowwoowwoowoo
wooowwoowwowwowowowwwowowowwowooww
wwwwwwwwwwwoooooooooooo
wowwwooowwwwwwwwwwwww
woowooooowwwowowoooowwo
owowwoowwoowowwooowwowwoo
wwwwwwwwoooooooowwwwwwwww
oooooooooooowwwwwwwwwwwooooooooo
oooowwwwwwwwowwwoooooooowww

Result:
4
100
172
830
0
172
180
287
576
0
312
*/

package Exercises.FirstPartialExam;

import java.util.Scanner;

public class WOWFactor {
    // Brute force
    public static int count(String line) {
        int counter = 0;
        int n = line.length();
        //
        for (int i = 0; i < n; i++) {
            if (line.charAt(i) == 'w') {
                for (int j = i + 1; j < n; j++) {
                    if (line.charAt(j) == 'o') {
                        for (int k = j + 1; k < n; k++) {
                            if (line.charAt(k) == 'w') {
                                counter++;
                            }
                        }
                    }
                }
            }
        }
        return counter;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            System.out.println(count(line));
        }
    }
}

/*
        int indexEnd = -1;
        int indexStart = -1;
        for (int i = n - 1; i > 0; i--) {
            if (line.charAt(i) == 'w') {
                indexEnd = i;
                break;
            }
        }
        for (int i = 0; i < indexEnd; i++) {
            if (line.charAt(i) == 'w') {
                indexStart = i;
                break;
            }
        }

        for (int i = indexStart; i < indexEnd; i++) {
            if (line.charAt(i) == 'w') {
                int o = 0;
                for (int j = i + 1; j <= indexEnd; j++) {
                    if (line.charAt(j) == 'o') {
                        o++;
                    } else {
                        if (o > 0) {
                            counter++;
                        }
                    }
                }
            }
        }
        */
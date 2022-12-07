package com.company.basic;

import java.util.*;


/**
 * В этих упражнениях ты научишься работать с извлечением и инверсией.
 *
 * Извлечение это когда у тебя есть часть кода которая может быть вынесена в отдельную функцию.
 * Инверсии это когда ты переносишь исключения или ошибки наверх функции чтобы провалидировать код самого начала.
 *
 * Все это нужно для того чтобы упростить чтение и понимание кода. Как пример код может выглядеть так:
 *
 * {
 *     условие {
 *         условие {
 *             условие {
 *                 {
 *                     КУЧА КОДА 1
 *                 }
 *             } исключение {
 *                 КУЧА КОДА 2
 *             }
 *         } исключение {
 *             КУЧА КОДА 3
 *         }
 *     } исключение { чутка кода 1 }
 * }
 *
 * Что можно превратить в более читабельный вид:
 *
 * функция1 {
 *     КУЧА КОДА 1
 * }
 *
 * функция2 {
 *     КУЧА КОДА 2
 * }
 *
 * функция3 {
 *     КУЧА КОДА 3
 * }
 *
 *
 * {
 *     исключение { чутка кода 1 }
 *     исключение { функция3 }
 *     исключение { функция2 }
 *     функция1
 * }
 *
 * Ниже будет приведен пример, а именно просмотри изменения в коде между calculateEvenNumbers и calculateEvenNumbersV2
 */
public class Main {
    private static HashMap<Integer, String> users = new HashMap<>(Map.of(
            0, "Naruto",
            1, "Madara"
    ));

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int bottom = scanner.nextInt();
        int top = scanner.nextInt();

        System.out.println(calculateEvenNumbers(bottom, top));
        System.out.println(calculateEvenNumbersV2(bottom, top));
    }

    static int calculateEvenNumbers(int bottom, int top) {
        if (top > bottom) {
            int sum = 0;

            for (int i = bottom; i < top; i++) {
                if (i % 2 == 0) { // это можно извлечь в отдельную функцию
                    sum += i;
                }
            }

            return sum;
        } else { // это можно инвертировать
            return 0;
        }
    }

    static int filterNumber(int number) {
        if (number % 2 == 0) {
            return number;
        }

        return 0;
    }

    static int calculateEvenNumbersV2(int bottom, int top) {
        if (top <= bottom) { // инвертированное условие
            return 0;
        }

        int sum = 0;

        for (int i = bottom; i < top; i++) {
            sum += filterNumber(i); // вынесенная часть кода
        }

        return sum;
    }


    /**
     * Функция принимает строку формата "int:String":
     * 0:Naruto
     * 2132:dick
     */
    static void registerUser(String user) {
        String[] parts = user.split(":");

        if (parts.length == 2) {
            int userId = Integer.parseInt(parts[0]);

            if (userId >= 0) {
                String userName = parts[1];

                if (users.containsKey(userId)) {
                    users.replace(userId, userName);
                } else {
                    users.put(userId, userName);
                }
            } else {
                throw new IllegalArgumentException("Invalid user id: " + userId);
            }
        } else {
            throw new IllegalArgumentException("Invalid user string: " + user);
        }
    }

    // TODO: инвертируй и вынеси что сможешь в функции registerUser
    static void registerUserV2(String user) {

    }
}
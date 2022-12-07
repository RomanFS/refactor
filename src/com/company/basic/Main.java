package com.company.basic;

import java.util.*;

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
                if (i % 2 == 0) {
                    sum += i;
                }
            }

            return sum;
        } else {
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
        if (top <= bottom) {
            return 0;
        }

        int sum = 0;

        for (int i = bottom; i < top; i++) {
            sum += filterNumber(i);
        }

        return sum;
    }

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

    static void registerUserV2(String user) {

    }
}
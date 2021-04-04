package com.epam.automation.fundamentals.optionaltask;

 /*Задание. Ввести n чисел с консоли.
1. Найти самое короткое и самое длинное число. Вывести найденные числа и их длину.
2. Вывести числа в порядке возрастания (убывания) значений их длины.
3. Вывести на консоль те числа, длина которых меньше (больше) средней длины по всем числам, а также длину.
4. Найти число, в котором количество различных цифр минимально. Если таких чисел несколько, найти первое из них.
5. Найти количество чисел, содержащих только четные цифры, а среди оставшихся — количество чисел с равным числом четных и нечетных цифр.
6. Найти число, цифры в котором идут в строгом порядке возрастания. Если таких чисел несколько, найти первое из них.
7. Найти число, состоящее только из различных цифр. Если таких чисел несколько, найти первое из них.*/

import java.util.Arrays;
import java.util.Scanner;

public class NumProcessor {
    private static final int NUMS_INPUT_AMOUNT = 8;
    private static final String NUMS_INPUT_REQUEST = "Please, consistently type in %d numbers," +
            "(press \"enter\" after each number)\n";
    private static final int MENU_OPTIONS_AMOUNT = 7;
    private static final String MENU = "\nPlease, make a choice:\n" +
            "1. Find the shortest and the longest number\n" +
            "2. Print numbers in ascending order of their length\n" +
            "3. Print numbers with length less than the average length of all the numbers\n" +
            "4. Find number with min different digits in it\n" +
            "5. Find amount of numbers with even digits and among the rest numbers find amount of numbers with equal amount of even and odd digits\n" +
            "6. Find number with strictly ascend order digits in it\n" +
            "7. Find number with only different digits in it.\n" +
            "0. For exit\n";
    public static final String WRONG_INPUT = "Wrong input, type in integer value";
    private static Scanner sc;
    private static int[] nums;

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        int option = 0;

        nums = getNums();

        do {
            option = getOption();
            switch (option) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    task1();
                    break;
                case 2:
                    task2();
                    break;
                case 3:
                    task3();
                    break;
                case 4:
                    task4();
                    break;
                case 5:
                    task5();
                    break;
                case 6:
                    task6();
                    break;
                case 7:
                    task7();
                    break;
                default:
                    continue;
            }

        } while (option != 0);
    }

    private static void task1() {
        int shortNum = nums[0];
        int longNum = nums[0];
        int shortLength = 0;
        int longLength = 0;

        for (int i = 0; i < nums.length; i++) {
            if (String.valueOf(nums[i]).length() < String.valueOf(shortNum).length()) {
                shortNum = nums[i];
                shortLength = String.valueOf(nums[i]).length();
            }

            if (String.valueOf(nums[i]).length() > String.valueOf(longNum).length()) {
                longNum = nums[i];
                longLength = String.valueOf(nums[i]).length();
            }
        }

        System.out.printf("The shortest number is %d, its length is %d\n" +
                "The longest number is %d, its length is %d\n", shortNum, shortLength, longNum, longLength);
    }

    private static void task2() {
        int[] tempArr = new int[NUMS_INPUT_AMOUNT];
        System.arraycopy(nums, 0, tempArr, 0, nums.length);

        sortIntArrByLength(tempArr);

        System.out.println(Arrays.toString(tempArr));
    }

    private static void task3() {
        float averageLength = 0;
        for (int num : nums) {
            averageLength += String.valueOf(num).length();
        }
        averageLength = averageLength / nums.length;

        for (int num : nums) {
            if (String.valueOf(num).length() < averageLength) {
                System.out.printf("Value: %d; length: %d\n", num, String.valueOf(num).length());
            }
        }
    }

    private static void task4() {
        int result = nums[0];
        for (int num : nums) {
            if (countDiffDigits(num) < countDiffDigits(result)) {
                result = num;
            }
        }
        System.out.println(result);
    }

    private static void task5() {
        int onlyEvenCounter = 0;
        int evenEqualOddCounter = 0;
        for (int num : nums) {
            if (countEven(num) == String.valueOf(num).length()) {
                onlyEvenCounter++;
            }
            if (2 * countEven(num) == String.valueOf(num).length()) {
                evenEqualOddCounter++;
            }
        }
        System.out.printf("Amount of numbers with only even digits is %d\n" +
                        "Amount of numbers with equal amount of even and odd digits is %d\n",
                onlyEvenCounter, evenEqualOddCounter);
    }

    private static void task6() {                       //Do numbers with single digit consider as number with ascend order?
        int result = 0;
        boolean hasNumberWithAscendDigOrder = false;
        for (int num : nums) {
            if (isAscendDigits(num)) {
                hasNumberWithAscendDigOrder = true;
                result = num;
                break;
            }
        }
        if (hasNumberWithAscendDigOrder) {
            System.out.println(result);
        } else {
            System.out.println("There's no numbers with ascend order digits among your input");
        }
    }

    private static void task7() {
        int result = 0;
        boolean hasNumWithDiffDigits = false;

        for (int num : nums) {
            if (countDiffDigits(num) == String.valueOf(num).length()) {
                hasNumWithDiffDigits = true;
                result = num;
                break;
            }
        }

        if (hasNumWithDiffDigits) {
            System.out.println(result);
        } else {
            System.out.println("There's no numbers with only different digits among your input");
        }
    }

    private static boolean isAscendDigits(int value) {
        char[] arr = String.valueOf(value).toCharArray();

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {              //what use: > or >=. Has numbers 5555 or 2445  ascend order?
                return false;
            }
        }
        return true;
    }

    private static int countEven(int number) {
        int result = 0;
        char[] arr = String.valueOf(number).toCharArray();
        for (char value : arr) {
            if (value % 2 == 0) {
                result++;
            }
        }
        return result;
    }

    private static int countDiffDigits(int value) {         //Returns Amount of different digits within a number
        String tempString = String.valueOf(value);
        char[] temp = tempString.toCharArray();
        int counter = 0;

        flag:
        for (int i = 0; i < temp.length; i++) {
            for (int j = i; j < temp.length; j++) {
                if (i == j) {
                    continue;
                }
                if (temp[i] == temp[j]) {
                    continue flag;
                }
            }
            counter++;
        }
        return counter;
    }

    private static void sortIntArrByLength(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (String.valueOf(arr[j]).length() < String.valueOf(arr[i]).length()) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    private static int getOption() {
        int option = 0;

        do {
            System.out.print(MENU);
            option = getNumber();
        } while (option < 0 || option > 12);

        return option;
    }

    private static int[] getNums() {
        int[] nums = new int[NUMS_INPUT_AMOUNT];

        System.out.printf(NUMS_INPUT_REQUEST, NUMS_INPUT_AMOUNT);

        for (int i = 0; i < nums.length; i++) {

            nums[i] = getNumber();
        }
        return nums;

        //return new int[]{1237516, 2868, 2468, 888223, 0, 8255, 98745621, 3153}; //fills array without input, for testing.
    }

    private static int getNumber() {
        while (!sc.hasNextInt()) {
            System.out.println(WRONG_INPUT);
            sc.next();
        }
        return sc.nextInt();
    }
}

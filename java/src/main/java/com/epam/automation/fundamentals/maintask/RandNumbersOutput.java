package com.epam.automation.fundamentals.maintask;

//Вывести заданное количество случайных чисел с переходом и без перехода на новую строку

public class RandNumbersOutput {
    final static int NUMBERS_AMOUNT = 18;
    final static int NUMBERS_IN_ROW_ = 5;

    public static void main(String[] args) {
        for (int i = 1; i <= NUMBERS_AMOUNT; i++) {
            System.out.printf("%3d", (int) (Math.random() * 100));
            if (i % 5 == 0){
                System.out.println();
            }
        }
    }
}

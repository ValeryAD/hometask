package com.epam.automation.fundamentals.maintask;


public class IntegerArgs {
    final static String SUM_RESULT = "Sum of arguments is %d";
    final static String MULT_RESULT = "Product of arguments is %d";

    public static void main(String[] args) {
        long sum = 0;
        long product = 1;

        for(int i = 0; i < args.length; i++){
            sum += Integer.valueOf(args[i]);
            product *= Integer.valueOf(args[i]);
        }

        System.out.printf(SUM_RESULT, sum);
        System.out.println();
        System.out.printf(MULT_RESULT, product);
    }
}

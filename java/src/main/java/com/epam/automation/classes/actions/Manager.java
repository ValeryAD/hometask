package com.epam.automation.classes.actions;

import com.epam.automation.classes.entities.Faculty;
import com.epam.automation.classes.entities.Student;

import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Manager {

    private final static String MAIN_MENU = "Choose item to execute:\n" +
            "1 - TASK 1 \tprint list of student of specified faculty\n" +
            "2 - TASK 2 \tprint list of student for every faculty and year of study\n" +
            "3 - TASK 3 \tprint list of student that born after specified year\n" +
            "4 - TASK 4 \tprint list of student of specified group\n" +
            "0 - for exit\n";
    private final static String WRONG_INPUT = "Wrong input. Please type in integer value representing item of menu";
    private static final String FACULTY_REQUEST = "\nChoose faculty:\n";
    private static final String FOR_RETURN = "0 - for return\n";
    private static final String TYPE_YEAR_REQUEST = "type in value of the year (integer value)\n";
    private static final String TYPE_YEAR_MESSAGE = "\nThe youngest student was born in %s, the oldest one in %s\n";
    private static final int AMOUNT_OF_STUDENTS_TO_GENERATE = 400;
    private static final String WRONG_GROUP_INPUT = "Type in number of group (three digit integer)";
    private static final String GROUP_INPUT_REQUEST = "Type in number of a group you wish to print.";
    private static final String GROUP_OUTPUT_ANNOUNCE = "Next groups exist in our university:\n";
    private static final String GROUP_NOT_EXISTS_MESSAGE = "\nThere's no group with such number\n";

    private static Scanner scanner;
    private static Student[] students;


    static {
        scanner = new Scanner(System.in);
        students = StudentCreator.generateStudentArr(AMOUNT_OF_STUDENTS_TO_GENERATE);
    }

    public static void main(String[] args) {
        int choice = 0;

        do {
            System.out.println(MAIN_MENU);
            choice = intInputRequest(WRONG_INPUT);

            switch (choice) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    runTask1();
                    break;
                case 2:
                    runTask2();
                    break;
                case 3:
                    runTask3();
                    break;
                case 4:
                    runTask4();
                    break;
                default:
                    System.out.println(WRONG_INPUT);
                    continue;
            }

        } while (choice != 0);

    }

    private static void runTask1() {
        Faculty[] faculties = Faculty.values();
        int choice = 0;
        Faculty selectedFaculty;
        Student[] studResult;

        do {
            System.out.println(FACULTY_REQUEST);
            choice = 0;
            for (int i = 0; i < faculties.length; i++) {
                System.out.printf("%d - %s\n", i + 1, faculties[i].getName());
            }
            System.out.println(FOR_RETURN);

            choice = intInputRequest(WRONG_INPUT);


            if (choice == 0) {
                return;
            } else if (choice > 0 && choice <= faculties.length) {
                selectedFaculty = faculties[choice - 1];
                StudentAction.task1(students, selectedFaculty);
            } else {
                System.out.println(WRONG_INPUT);
            }
        } while (true);
    }

    private static void runTask2() {
        StudentAction.task2(students);
    }

    private static void runTask3() {
        SimpleDateFormat dformat = new SimpleDateFormat("yyyy");
        String leastYear = dformat.format(StudentAction.findTheYoungest(students).getDateOfBirth());
        String greatestYear = dformat.format(StudentAction.findTheOldest(students).getDateOfBirth());
        int year = 0;

        System.out.printf(TYPE_YEAR_MESSAGE, leastYear, greatestYear);

        do {
            System.out.println(TYPE_YEAR_REQUEST);
            year = intInputRequest(TYPE_YEAR_REQUEST);
        } while (year < 0);

        StudentPrinter.printArray(StudentAction.task3(students, year));
    }

    private static void runTask4() {
        String group = "";
        System.out.println(GROUP_INPUT_REQUEST);
        System.out.println(GROUP_OUTPUT_ANNOUNCE);
        StudentPrinter.printAllGroups();

        do{
           group = String.valueOf(intInputRequest(WRONG_GROUP_INPUT));
           if(group.length() == 3){
               break;
           }else{
               System.out.println(WRONG_GROUP_INPUT);
           }
        }while(true);

        Student[] studResult = StudentAction.getStudentsOfGroup(students, group);

        if(studResult.length != 0){
            StudentPrinter.printArray(studResult);
        }else{
            System.out.println(GROUP_NOT_EXISTS_MESSAGE);
        }

    }

    private static int intInputRequest(String messageIfWrong) {
        while (!scanner.hasNextInt()) {
            System.out.println(messageIfWrong);
            scanner.next();
        }
        return scanner.nextInt();
    }

}

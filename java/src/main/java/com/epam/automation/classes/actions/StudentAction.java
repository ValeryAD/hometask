package com.epam.automation.classes.actions;

import com.epam.automation.classes.entities.*;

import java.util.Date;
import java.util.GregorianCalendar;


public class StudentAction {

    private static final int AMOUNT_OF_STUDENTS = 120;
    private static final int YEAR_OF_BIRTH = 1990;


    public static void task1(Student[] students, Faculty faculty) {

        Student[] studResult = getStudentsOfFaculty(students, faculty);
        StudentPrinter.printArray(studResult);
        System.out.printf("The faculty of %s has %d students\n", faculty.getName(), studResult.length);
    }

    public static void task2(Student[] students) {
        Faculty[] faculties = Faculty.values();
        Student[] studOfFaculty;

        for (Faculty faculty : faculties) {
            studOfFaculty = getStudentsOfFaculty(students, faculty);
            for (int i = 1; i <= Student.MAX_YEAR_OF_STUDY; i++) {
                System.out.println(StudentPrinter.rowDivider + "\n");
                System.out.printf("\t\t\tfaculty of %s, year of study %d:\n", faculty, i);
                System.out.println(StudentPrinter.rowDivider + "\n");
                for (Student student : studOfFaculty) {
                    if (student.getYearOfStudy() == i) {
                        StudentPrinter.print(student);
                        System.out.println(StudentPrinter.rowDivider);
                    }
                }
            }
        }
    }


    public static Student[] task3(Student[] students, int yearOfBirth) {
        Date minDate = new GregorianCalendar(yearOfBirth + 1, 0, 1).getTime();
        int counter = 0;

        for (Student st : students) {
            if (minDate.before(st.getDateOfBirth())) {
                counter++;
            }
        }

        Student[] studResult = new Student[counter];
        counter = 0;

        for (Student st : students) {
            if (minDate.before(st.getDateOfBirth())) {
                studResult[counter++] = st;
            }
        }

        return studResult;
    }

    //returns array of students of specified faculty
    private static Student[] getStudentsOfFaculty(Student[] students, Faculty faculty) {

        int counter = 0;

        for (Student st : students) {
            if (faculty.equals(st.getFaculty())) {
                counter++;
            }
        }

        Student[] studResult = new Student[counter];
        counter = 0;
        for (Student st : students) {
            if (faculty.equals(st.getFaculty())) {
                studResult[counter++] = st;
            }
        }
        return studResult;
    }

    //returns array of students of specified group
    private static Student[] getStudentsOfGroup(Student[] students, Group group) {
        int counter = 0;

        for (Student student : students) {
            if (student.getGroup().equals(group)) {
                counter++;
            }
        }

        Student[] studResult = new Student[counter];
        counter = 0;

        for (Student student : students) {
            if (student.getGroup().equals(group)) {
                studResult[counter++] = student;
            }
        }
        return studResult;
    }

    private static Student[] getStudentsOfYearOfStudy(Student[] students, int yearOfStudy) {
        int counter = 0;

        for (Student student : students) {
            if (student.getYearOfStudy() == yearOfStudy) {
                counter++;
            }
        }

        Student[] studResult = new Student[counter];
        counter = 0;

        for (Student student : students) {
            if (student.getYearOfStudy() == yearOfStudy) {
                studResult[counter++] = student;
            }
        }
        return studResult;
    }

    public static Student findTheYoungest(Student[] students) {
        if (students == null) return null;

        Student studResult = students[0];

        for (Student st : students) {
            if (st != null && st.getDateOfBirth().before(studResult.getDateOfBirth())) {
                studResult = st;
            }
        }
        return studResult;
    }

    public static Student findTheOldest(Student[] students) {
        if (students == null) return null;

        Student studResult = students[0];

        for (Student st : students) {
            if (st != null && st.getDateOfBirth().after(studResult.getDateOfBirth())) {
                studResult = st;
            }
        }
        return studResult;
    }
}

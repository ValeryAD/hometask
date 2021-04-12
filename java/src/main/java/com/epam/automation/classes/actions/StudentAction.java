package com.epam.automation.classes.actions;

import com.epam.automation.classes.entities.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;


public class StudentAction {

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
            for (int i = 1; i <= StudentCreator.MAX_YEAR_OF_STUDY; i++) {
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
    public static Student[] getStudentsOfGroup(Student[] students, String group) {
        int counter = 0;

        for (Student student : students) {
            if (student.getGroup().equals(group)) {
                counter++;
            }
        }

        if(counter == 0){
            return  new Student[0];
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

    public static int countLastDigitOfYearOfAdmission(Student student){
            if(student.getYearOfStudy() == 0) return -1;
            SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy");
            int thisYear = Integer.valueOf(sdFormat.format(new Date()));
            sdFormat = new SimpleDateFormat("M");
            boolean iSFirstHalfOfTheYear = Integer.valueOf(sdFormat.format(new Date())) < 8;

            int yearOfAdmission = thisYear - (student.getYearOfStudy()-1) - (iSFirstHalfOfTheYear ? 1 : 0);

            return yearOfAdmission % 10;
    }
}

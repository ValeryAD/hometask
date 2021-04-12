package com.epam.automation.classes.actions;

import com.epam.automation.classes.entities.Faculty;
import com.epam.automation.classes.entities.Student;


import java.text.SimpleDateFormat;

public class StudentPrinter {

    private static final String CLARIFY_DATA = "to be clarified";
    private static final String FORMAT_STR = "%10s|%-12s|%-12s|%-14s|%-15s|%-20s|%-15s|%-15s|%-15s|%-15s|%-36s|%-15s|%-15s";
    public static final String rowDivider;
    public static final String STUD_IS_NULL_MESSAGE = "\t\t\t\ta student object you're trying to print is null";
    public static final String STUD_ARRAY_IS_NULL_MESSAGE = "\t\t\t\tan array you're trying to print is null";

    static {
        StringBuilder sb = new StringBuilder("-");
        for (int i = 0; i < 220; i++) {
            sb.append("-");
        }
        rowDivider = sb.toString();
    }

    public static void print(Student st) {
        if (st == null) {
            System.err.println(rowDivider + "\n" + STUD_IS_NULL_MESSAGE + "\n" + rowDivider);
            return;
        }
        SimpleDateFormat dateForm = new SimpleDateFormat("dd.MM.yyyy");

        String str = String.format(FORMAT_STR,
                st.getId(),
                st.getSecondName() != null ? st.getSecondName() : CLARIFY_DATA,
                st.getName() != null ? st.getName() : CLARIFY_DATA,
                st.getPatronymic() != null ? st.getPatronymic() : CLARIFY_DATA,
                st.getAddress() != null && st.getAddress().getCity() != null ? st.getAddress().getCity() : CLARIFY_DATA,
                st.getAddress() != null && st.getAddress().getStreet() != null ? st.getAddress().getStreet() : CLARIFY_DATA,
                st.getAddress() != null && st.getAddress().getHouseNum() != null ? st.getAddress().getHouseNum() : CLARIFY_DATA,
                st.getAddress() != null && st.getAddress().getApartment() != null ? st.getAddress().getApartment() : CLARIFY_DATA,
                st.getAddress() != null && st.getAddress().getPhoneNumber() > 0 ? st.getAddress().getPhoneNumber() : CLARIFY_DATA,
                st.getDateOfBirth() != null ? dateForm.format(st.getDateOfBirth()) : CLARIFY_DATA,
                st.getFaculty() != null ? st.getFaculty() : CLARIFY_DATA,
                st.getYearOfStudy() > 0 ? st.getYearOfStudy() : CLARIFY_DATA,
                st.getGroup() != null ? st.getGroup() : CLARIFY_DATA);
        System.out.println(str);

    }

    public static void printArray(Student[] sts) {

        if (sts == null) {
            System.err.println(rowDivider + "\n" + STUD_ARRAY_IS_NULL_MESSAGE + "\n" + rowDivider);
            return;
        }

        String head = String.format(FORMAT_STR, "ID", "second name", "name", "patronymic",
                "city", "street", "house number", "apartment", "phone",
                "date of birth", "faculty", "year of study", "group");

        System.out.println(rowDivider);
        System.out.println(head);
        System.out.println(rowDivider);

        for (int i = 0; i < sts.length; i++) {
            print(sts[i]);
            System.out.println(rowDivider);
        }
        System.out.println();
    }

    public static void printAllGroups() {
        int facultyAmount = Faculty.values().length;
        StringBuilder sb = new StringBuilder();
        Student tempSt = new Student();
        for (int i = 1; i <= StudentCreator.MAX_YEAR_OF_STUDY; i++) {
            tempSt.setYearOfStudy(i);
            System.out.print("\t\t");
            for (int j = 1; j <= facultyAmount; j++) {
                for (int k = 1; k <= StudentCreator.GROUPS_AMOUNT; k++) {
                    System.out.printf("%d%d%d\t", StudentAction.countLastDigitOfYearOfAdmission(tempSt), j, k);
                }
            }
            System.out.println();
        }
    }

}

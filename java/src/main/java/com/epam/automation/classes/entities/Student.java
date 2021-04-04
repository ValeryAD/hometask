package com.epam.automation.classes.entities;

import java.util.Date;


public class Student extends Person {

    public static final int MAX_YEAR_OF_STUDY = 6;

    private static long lastId = 0;
    private Date dateOfBirth;
    private Address address;
    private Faculty faculty;
    private int yearOfStudy;
    private Group group;

    public Student() {
    }

    public Student(String secondName, String name, String patronymic) {
        super(secondName, name, patronymic);
    }

    public Student(String secondName, String name, String patronymic, Date dateOfBirth,
                   Address address, Faculty faculty, int yearOfStudy, Group group) {
        super(secondName, name, patronymic);
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.faculty = faculty;
        this.yearOfStudy = yearOfStudy;
        this.group = group;
    }

    @Override
    protected long getNextId(){
        return ++lastId;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public int getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(int yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;

        Student student = (Student) obj;

        return this.getId() == student.getId(); // if IDs are equal hence this is the same student

        /*if (!super.equals(student)) return false;
        if (this.yearOfStudy != student.yearOfStudy) return false;
         return this.dateOfBirth != null ? this.dateOfBirth.equals(student.dateOfBirth) : student.dateOfBirth == null ||
                 this.address != null ? this.address.equals(student.address) : student.address == null ||
                 this.faculty != null ? this.faculty.equals(student.faculty) : student.faculty == null ||
                 this.group != null ? this.group.equals(student.group) : student.group == null;*/
    }

    @Override
    public int hashCode() {
        int result = 31 * super.hashCode();
        result = 31 * result + yearOfStudy;
        result = 31 * result + (faculty != null ? faculty.hashCode() : 0) +
                (group != null ? group.hashCode() : 0);
        result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0) +
                (address != null ? address.hashCode() : 0);
        return result;
    }

    @Override
    public String toString(){
        String fullName = String.format("%s %s.%s.",getSecondName(), getName().substring(0,1), getPatronymic().substring(0,1));
        String str = String.format("Student: ID:%d; %s, %s, year of study %d", getId(), fullName, faculty, yearOfStudy);
        return str;
    }
}
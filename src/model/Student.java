/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author edgar
 */
public class Student {
    int id;
    String firstName;
    String lastName;
    int age;
    int grade;
    
    public Student(){}
    
    public Student(String firstName, String lastName, int age) {
        this.id = 0;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.grade = 0;
    }
    
    public Student(int id, String firstName, String lastName, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.grade = 0;
    }

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        } else {
            System.out.println("Not supported id");
        }
    }

    public void setFirstName(String firstName) {
        if (firstName != null && !firstName.isEmpty()) {
            this.firstName = firstName;
        } else {
            System.out.println("Not supported firstName");
        }
    }

    public void setLastName(String lastName) {
        if (lastName != null && !lastName.isEmpty()) {
            this.lastName = lastName;
        } else {
            System.out.println("Not supported lastName");
        }
    }

    public void setAge(int age) {
        if (age > 0) {
            this.age = age;
        } else {
            System.out.println("Not supported age");
        }   
    }

    public void setGrade(int grade) {
        if (grade >= 0) {
            this.grade = grade;
        } else {
            System.out.println("Not supported grade");
        }
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public int getGrade() {
        return grade;
    }
}

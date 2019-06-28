/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author edgar
 */
public class Packet {
    ArrayList<Student> sentStudentList = new ArrayList<>();

    public void setSentStudents(ArrayList<Student> sentStudentList) {
        this.sentStudentList = sentStudentList;
    }

    public ArrayList<Student> getSentStudents() {
        return sentStudentList;
    }
    
    public static class Packet01Message {
        static String message;
        
        public String getMessage() {
            return message;
        }
    }
}

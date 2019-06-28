/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.*;
import java.util.ArrayList;
import model.Student;

/**
 *
 * @author edgar
 */
public class ActionsDB {
    private static final String databaseUrl = "jdbc:sqlite:kd2_database.db";
    
    // Connect to DB
    public static void connect() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(databaseUrl);
            
            System.out.println("CONNECTION TO SQLITE HAS BEEN ESTABLISHED");
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                // If conection is not equal to null, close the connection
                if (con != null) {
                    con.close();
                }
            } catch(SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    public static void createAndInserDB(ArrayList<Student> students) {
        String sql = "CREATE TABLE IF NOT EXISTS STUDENTS (\n"
                + "StudentID INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + "Lastname TEXT NOT NULL,\n"
                + "Firstname TEXT NOT NULL,\n"
                + "Age INTEGER NOT NULL"
                +")";
        
        try (Connection con = DriverManager.getConnection(databaseUrl)){
            Statement stmt = con.createStatement();
            stmt.execute(sql);
            System.out.println("CREATED STUDENTS TABLE");
            
            // Input Students Info in DB           
            for (Student s : students) {
                stmt.executeUpdate("INSERT INTO STUDENTS (Lastname,Firstname,Age) " +
                    "VALUES ('" + s.getLastName() + "' ,"
                    + "'" + s.getFirstName()+ "' ,"
                    + "'" + s.getAge() + "');");
                System.out.println("STUDENT " + s.getFirstName() + " INSERTED IN DB");
            }           
        } catch (SQLException e) {
           System.out.println(e.getMessage()); 
        }
    }
    
    public static ArrayList<Student> selectAllStudentsSQL() {
        ArrayList<Student> tmpStudentList = new ArrayList<Student>();
        
        try (Connection con = DriverManager.getConnection(databaseUrl)) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM STUDENTS");   
            
            while(rs.next()) {
                int id = rs.getInt("StudentID");
                String firstname = rs.getString("Firstname");
                String lastname = rs.getString("Lastname");
                int age = rs.getInt("Age");
                
                Student tmpStudent = new Student();
                tmpStudent.setId(id);
                tmpStudent.setFirstName(firstname);
                tmpStudent.setLastName(lastname);
                tmpStudent.setAge(age);
                
                tmpStudentList.add(tmpStudent);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return tmpStudentList;
    }
    
    public static void main(String [] args) {
        connect();
    }
}

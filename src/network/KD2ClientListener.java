/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import com.esotericsoftware.kryonet.*;
import com.esotericsoftware.kryo.*;
import java.util.ArrayList;
import java.util.Scanner;
import model.Packet;
import model.Student;

/**
 *
 * @author edgar
 */
public class KD2ClientListener extends Listener{
    private Client client;
    
    public void init(Client client) {
        this.client = client;
    }
    
    @Override
    public void connected(Connection c) {
        client.sendTCP("Client Connected");
    }
    
    @Override
    public void disconnected(Connection c) {
        client.sendTCP("Client disconnected");
    }
    
    public ArrayList<Student> fillGrades(ArrayList<Student> students) {
        ArrayList<Student> tmpStudentList = new ArrayList<Student>();
        Scanner in = new Scanner(System.in);
        
        System.out.println("Please grade the available students:\n");
        
        for (Student s : students) {
            System.out.println("Student:" + s.getFirstName() + " " + s.getLastName());
            int grade = in.nextInt();
            s.setGrade(grade);
            tmpStudentList.add(s);
        }
        
        System.out.println("\nThank you for grading!");
        
        return tmpStudentList;
    }
    
    @Override
    public void received(Connection c, Object o) {
        if (o instanceof Packet.Packet01Message) {
            Packet.Packet01Message p = (Packet.Packet01Message) o;
            System.out.println("[SERVER] Received message from the host:" + p.getMessage());
        }
        
        if (o instanceof String) {
            System.out.println("SERVER: " + o);
        }
        
        if (o instanceof ArrayList) {
            ArrayList<Student> tmp = (ArrayList<Student>) o;
            ArrayList<Student> filledGradesStudentList = fillGrades(tmp);
            
            client.sendTCP(filledGradesStudentList);
        }
    }
}

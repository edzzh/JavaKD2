/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import com.esotericsoftware.kryo.*;
import com.esotericsoftware.kryonet.*;
import java.util.ArrayList;
import model.*;

/**
 *
 * @author edgar
 */
public class KD2ServerListener extends Listener{
    private Server server;
    
    public void init(Server server) {
        this.server = server;
    }
    
    @Override
    public void connected(Connection c) {
        System.out.println("Received New Connection From: " + c.getRemoteAddressTCP().toString());
        
        c.sendTCP("Hello Client!");
        
        KD2Server.getAndSendStudents(c);
    }
    
    @Override
    public void received(Connection c, Object o) {
        if (o instanceof Packet.Packet01Message) {
            Packet.Packet01Message p = (Packet.Packet01Message) o;
            System.out.println("[CLIENT]: Received message - " + p.getMessage());
        }
        
        if (o instanceof String) {
            System.out.println(o);
        }
        
        if (o instanceof ArrayList) {
            ArrayList<Student> tmp = (ArrayList<Student>) o;
            ArrayList<Integer> grades = new ArrayList<Integer>();
            Integer sum = 0;
            
            for (Student s : tmp) {
                grades.add(s.getGrade());
            }
            
            if (!grades.isEmpty()) {
                for (Integer grade : grades) {
                    sum += grade;
                }
            }
            
            server.sendToTCP(c.getID(), "Average score: " + (sum.doubleValue() / grades.size()));
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import com.esotericsoftware.kryo.*;
import com.esotericsoftware.kryonet.*;
import database.ActionsDB;
import java.io.IOException;
import java.util.ArrayList;
import model.*;

/**
 *
 * @author edgar
 */
public class KD2Server {
    private final int port = 55223;
    public static Server server;
    private KD2ServerListener serverListener;
    
    public KD2Server () throws IOException {
        server = new Server();
        
        serverListener = new KD2ServerListener();
        serverListener.init(server);
        server.addListener(serverListener);
        
        server.bind(port);
        
        registerPackets();
        
        //Create Students
        insertStudents();
        
        server.start();
        
        System.out.println("Server has been started!");
    }
    
    private void registerPackets() {
        Kryo kryo = server.getKryo();
        kryo.register(model.Packet.class);
        kryo.register(Packet.Packet01Message.class);
        kryo.register(java.util.ArrayList.class);
        kryo.register(model.Student.class);
    }
    
    public void insertStudents() {
        ArrayList<Student> students = new ArrayList<>();

        students.add(new Student("Edgars","Hartmanis",19));
        students.add(new Student("Ricards","Sotaks",20));
        students.add(new Student("Normunds","Berzs",22));

        ActionsDB.createAndInserDB(students);
    }
    
    public void configureAndStartServer() throws IOException {
        server = new Server();
        
        serverListener = new KD2ServerListener();
        server.addListener(serverListener);
        
        server.bind(port);
        
        registerPackets();
        
        server.start(); 
    }
    
    public static void getAndSendStudents(Connection c) {
        ArrayList<Student> tmpStudentList = ActionsDB.selectAllStudentsSQL();
        System.out.println(c.getID());
        server.sendToTCP(c.getID(), tmpStudentList);
    }
}

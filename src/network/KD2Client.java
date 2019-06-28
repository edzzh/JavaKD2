/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import com.esotericsoftware.kryo.*;
import com.esotericsoftware.kryonet.*;
import java.io.IOException;
import model.Packet;

/**
 *
 * @author edgar
 */
public class KD2Client {
    private KD2ClientListener clientListener;
    public Client client;
    private String ipAdress = "127.0.0.1";
    private final int port = 55223;
    
    public KD2Client () throws IOException {
        client = new Client();
        
        clientListener = new KD2ClientListener();
        clientListener.init(client);
        
        client.addListener(clientListener);
        
        registerPackets();
        
        new Thread(client).start();
        
        client.connect(8080, ipAdress, port);
    }
    
    private void registerPackets() {
        Kryo kryo = client.getKryo();  
        kryo.register(model.Packet.class);
        kryo.register(Packet.Packet01Message.class);
        kryo.register(java.util.ArrayList.class);
        kryo.register(model.Student.class);
    }
    
    public void configureAndStartClient() throws IOException {
        client = new Client();
        
        clientListener = new KD2ClientListener();
        client.addListener(clientListener);
        
        registerPackets();
        
        client.start();
        client.connect(5000, ipAdress, port);  
    }
}

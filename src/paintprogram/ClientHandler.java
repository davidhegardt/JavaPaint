/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paintprogram;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class to handle client objects.
 * Creates streams, sets expression and calculates result.
 * @author David Hegardt
 */
public class ClientHandler extends Thread {
    private Socket s;
    private String adress;
    private ObjectInputStream in = null; // Receive string vector
    
    private ObjectOutputStream out = null; // Write standard string   
    Vector<String> clientInfo = null;
    Vector<Ritobject> currShapes = null;
    
    /**
     * Client handler constructor
     * Requires a socket on creation.
     * @param inSocket 
     */
    public ClientHandler(Socket inSocket) {
        this.s = inSocket;
        adress = s.getInetAddress().getHostAddress();
    }
    
    /**
     * Calls function to create streams and create objects.
     * Retrieves name through instream.
     */
    @Override
    public void run() {
        
        
        createStreams();
        try {
        while(true) {
            readName();
            //nameToServer();
            
            
            //in.reset();
            
            //out.flush();
            System.out.println("Outputstream has been flushed");
            //clientInfo.clear();
            System.out.println("Calculation has been reset");
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
        finally {
        try {
            in.close();
            out.close();
            s.close();
        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
    
    /**
     * Function to create object input stream to recieve vector
     * Creates object output stream to send vector
     */
    public void createStreams() {
        
        try {
            out = new ObjectOutputStream(s.getOutputStream());            
            System.out.println("Setting up object output stream..");
        } catch (IOException ex) {
            System.out.print("Error! Kunde inte skapa strömmar med adressen " + adress);
            return;
        }
        
        try {
            in = new ObjectInputStream(s.getInputStream()); 
            
            System.out.println("Setting up object input stream..");
        } catch (IOException ex) {
            System.out.print("Error! Kunde inte skapa strömmar med adressen " + adress);
            return;
        }       
        
    }
   
    /**
     * Reads name that notifies server to retrieve name vector
     * 
     * @throws InterruptedException 
     */
    public synchronized void readName() throws InterruptedException {
        try {
            clientInfo = (Vector<String>)in.readObject();
            System.out.println("Name read succesfully from client");
            System.out.println(clientInfo.get(0));
            notifyAll();
            
        } catch (IOException | ClassNotFoundException ex) {
            ex.getMessage();
        }
        
        
    }
    
   /* Not implemented..
    public synchronized void readShapes() {
        try {
            currShapes = (Vector<Ritobject>)in.readObject();
            System.out.println("Shapes read succesfully from client");
            
            //notifyAll();
            
        } catch (IOException | ClassNotFoundException ex) {
            ex.getMessage();
        }
    }
    */
    
    public synchronized Vector<String> getClient() throws InterruptedException {
        //notify();
        while (clientInfo == null) {
            wait();
        }
        return clientInfo;
    }
    
   


}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paintprogram;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Dave
 */
public class Paintserver extends Thread {
    
    private ObjectInputStream in = null;
    private Vector<String> clientInfo = null;
    private int portnummer;
    private ClientHandler theHandler;
    private serverGUI gui;
    private String address;
    
    
    Paintserver(int currPort) throws IOException {
        this.portnummer = currPort;
        //Run();
    }
    
    /**
     * Main function - starts server through serversocket using input portnumber
     * loops through and accepts new clients sent from clienthandler
     */
    public void Run() throws InterruptedException, InvocationTargetException {
        
        
        
        SwingUtilities.invokeAndWait(new Runnable() {public void run() {
                    gui = new serverGUI(); 
                    gui.setVisible(true);}                                       // Create a new Swing class, use invokeandwait
                });
        
        
        
        
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(portnummer);
            System.out.println("Server socket setup successfully! on portnumber : " + portnummer);
            
            address = ss.getInetAddress().getHostAddress();
            
            gui.updateGUI(portnummer,address);
            
        } catch (IOException ex) {
            System.out.println("Error kunde inte lyssna på" + portnummer);
        }
        
        
        
        
       
        while(true) {
            try {
                System.out.println("Väntar på anslutning");
                Socket s = ss.accept();            
                
                
                theHandler = new ClientHandler(s);
                theHandler.start();
                System.out.println("klient ansluten\n");
                getInfo();

            } catch (IOException ex) {
                Logger.getLogger(Paintserver.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }
    
    public void getInfo() throws InterruptedException {        
        

        gui.addPerson(theHandler.getClient());       

    }
    
    public int getPort() {
        return this.portnummer;
    }
    
    public static void main(String args[]) throws InterruptedException, InvocationTargetException {      
        
        
        JFrame frame = new JFrame();
        String code = JOptionPane.showInputDialog(frame,"Enter port for the server)","Indata",JOptionPane.WARNING_MESSAGE);
        
        int port = Integer.parseInt(code);
        System.out.println(code);
        
        if (code == null) {
            JOptionPane.showMessageDialog(frame,"Felaktigt lösenord","Login",JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        else {
            try {
                Paintserver connServer = new Paintserver(port);            
                connServer.Run();
            
            } catch (IOException ex) {
                Logger.getLogger(Paintserver.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
        
        
    }
    
    public void startGUI() {
        JFrame frame = new JFrame();
        String code = JOptionPane.showInputDialog(frame,"Enter port for the server)","Indata",JOptionPane.WARNING_MESSAGE);
        
        int port = Integer.parseInt(code);
        System.out.println(code);
        
        if (code == null) {
            JOptionPane.showMessageDialog(frame,"Felaktig inmatning","Error",JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        else {
            try {
                Paintserver connServer = new Paintserver(port);                
            } catch (IOException ex) {
                Logger.getLogger(Paintserver.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
    }
    
}

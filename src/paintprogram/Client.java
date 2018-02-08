/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paintprogram;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 * Client class extends thread
 * creates a new thread of Client-class.
 * Creates a new socket and gets result from the server
 * @author David Hegardt
 */
public class Client extends Thread {
    
    private String name;
    private String ip;
    private int portnumber;
    private Paintprogram paintGUI;
    private Vector<Ritobject> currShapes;
    private int temp = 0;
    private boolean update = false;
    private static ObjectInputStream in = null;
    private static ObjectOutputStream out = null;
    private ObjectOutputStream out2 = null;
    private static Socket s;
    /**
     * Main function
     * sets up new JFrame and populates fields
     * 
     * @param args
     * @throws InterruptedException 
     */
    public static void main(String[] args) throws InterruptedException {
    JFrame frame = new JFrame("InputDialog Example #2");
    JTextField ipfield = new JTextField(10);
    JTextField portfield = new JTextField(10);
    JTextField namefield = new JTextField(10);
    
    JPanel myPanel = new JPanel();
    myPanel.add(new JLabel("Input name :"));
    myPanel.add(namefield);
    myPanel.add(new JLabel("Input IP of server :"));
    myPanel.add(ipfield);
    myPanel.add(new JLabel("Input port of server :"));
    myPanel.add(portfield);
    
    int result = JOptionPane.showConfirmDialog(null, myPanel, 
               "Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
    
    if (result == JOptionPane.OK_OPTION) {
         System.out.println("x value: " + ipfield.getText());
         System.out.println("y value: " + portfield.getText());
         
         String tmpIp = ipfield.getText();
         String tmpName = namefield.getText();
         int tmpPort = Integer.valueOf(portfield.getText());
         
         Client klient = null;
         klient = new Client(tmpIp, tmpPort, tmpName);
         
        try {
            klient.Run();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
  }
  /**
     * Overload constructor creates new Client object 
     * 
     * @param currHost Hostname to use
     * @param portNr Portnumber to use
     */
    
  public Client(String currIp,int portNr, String currName) {
      this.ip = currIp;
      this.portnumber = portNr;
      this.name = currName;
  }
  /**
   * Run function
   * calls GUI class and sets up new instance of the paintprogram.
   * Creates socket to retrieve data
   * @throws IOException
   * @throws InterruptedException 
   */
  
  
  public void Run() throws IOException, InterruptedException {
        try {
            SwingUtilities.invokeAndWait(new Runnable() {public void run() {
                paintGUI = new Paintprogram(); }                                       // Create a new Swing class, use invokeandwait
            });
        } catch (InterruptedException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        s = new Socket(ip, portnumber);
        System.out.print("This code running" + ip + portnumber);
        try {
            out = new ObjectOutputStream(s.getOutputStream());          // Setup new object streams to send vector to server
            in = new ObjectInputStream(s.getInputStream());             // Setup new object streams to recieve vector from server
            
            
            System.out.println("Socket har satts upp av klient");
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Cannot create socket");
        }
        
        Vector<String> clientInfo = new Vector<String>();
        clientInfo.add(name);
        clientInfo.add(s.getInetAddress().getCanonicalHostName());
        
        out.writeObject(clientInfo);
        //out.reset();
        //int temp = 0;
        
        /**
         * Function not implemented..
         * 
         */
       /* while (paintGUI.getShapes() != null && paintGUI.newShape) {
            
                currShapes = paintGUI.getShapes();
               //System.out.println(currShapes.size());
                if (currShapes.size() > temp) {
                    temp = currShapes.size();
                    update = true;
                    System.out.println("Temp storlek :" + temp);
                    //out2 = new ObjectOutputStream(s.getOutputStream());
                    out.writeObject(currShapes);
                    
                } else {
                    update = false;
                }
        
        }
       */ 
        
        
        
  }
  
  /**
   * Retrieves shape objects
   * Not implemented...
   * @throws IOException
   * @throws InterruptedException 
   */
  public synchronized void getShapes() throws IOException, InterruptedException {
            while (!update) {
                wait();
            }
            
            
            notify();
        }
}

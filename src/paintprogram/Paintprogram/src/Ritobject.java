/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paintprogram;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Dave
 */
public abstract class Ritobject extends JPanel {
    
    public abstract void Draw(Graphics g); 
   
    
    public abstract Color getColor();
    
    public abstract void setColor(Color newColor);
    
    public enum Shapes {
        Freeform,Rectangle
    }

}





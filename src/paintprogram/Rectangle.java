/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paintprogram;

import com.sun.glass.ui.Cursor;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Creates shape class
 * uses coordinates to create and draw shape
 * @author David Hegardt
 */
public class Rectangle extends Ritobject{
    
    int width;
    int height;
    double placeX;
    double placeY;
    int brushSize;
    Color currColor;
    Shape currShape;
    
    /**
     * Draw the shape object using Graphics object
     * @param g 
     */
    @Override
    public void Draw(Graphics g) {    
        super.repaint();
        //super.paintComponent(g);        
        Graphics2D g2=(Graphics2D)g;
        //BufferedImage oldImage = new BufferedImage(900, 450,BufferedImage.TYPE_INT_ARGB);
        //Graphics2D g2= oldImage.createGraphics();
        g2.setStroke(new BasicStroke(brushSize));        
        //g.draw(currShape);
        g2.draw(currShape);
        
        
        
        
        
    }   
    
    /**
     * Sets color of shape
     * 
     * @param newColor 
     */
    
    @Override
    public void setColor(Color newColor) {
       this.currColor = newColor;
    }
    
    /**
     * Retuns color of shape
     * @return 
     */
    @Override
    public Color getColor() {
        return this.currColor;
    }
    /**
     * Shape constructor
     * @param pWidth - with of shape
     * @param pHeight - height of shape
     * @param pPlaceX - placement of shape
     * @param pPlaceY - placement of shape
     * @param bSize - brush size
     */    
    Rectangle(int pWidth,int pHeight,int pPlaceX, int pPlaceY, int bSize) {
        this.width = pWidth;
        this.height = pHeight;
        this.placeX = pPlaceX;
        this.placeY = pPlaceY;
        this.brushSize = bSize;
        this.currShape = new Rectangle2D.Double(placeX,placeY,width,height);        
        setVisible(true);
    }
    
    /**
     * Not really used..
     * @param g 
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2=(Graphics2D)g;
        
        g2.draw(currShape);
    }
    
    
}

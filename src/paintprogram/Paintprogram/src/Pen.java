/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paintprogram;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.*;
import java.util.Vector;
import javax.swing.JPanel;

/**
 *
 * @author Dave
 */
public class Pen extends Ritobject {
    
    int width;
    int height;
    int placeX;
    int placeY;
    Color currColor;
    Shape currShape;
    int brushSize;
    
    
    @Override
    public void Draw(Graphics g) {

        currShape = new Line2D.Double(placeX, placeY, width, height);        
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D)g;
        g2.setStroke(new BasicStroke(brushSize));
        //g2.drawLine(placeX, placeY, width, height);
        g2.draw(currShape);
        
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2=(Graphics2D)g;
        
        //g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }
    
   
    
    
    @Override
    public void setColor(Color newColor) {
       this.currColor = newColor;
    }
    
    @Override
    public Color getColor() {
        return this.currColor;
    }
    
    Pen(int pWidth,int pHeight,int pPlaceX, int pPlaceY, int bSize) {
        this.width = pWidth;
        this.height = pHeight;
        this.placeX = pPlaceX;
        this.placeY = pPlaceY;
        this.brushSize = bSize;
        setVisible(true);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paintprogram;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Dave
 */
public class testdraw extends JPanel {
    Shape shp=new Rectangle2D.Double(100,100,100,100);

	testdraw()
	{	System.out.println("shp="+shp);
		setVisible(true);	// why here?
	}

	public static void main(String[] args)
	{	new testdraw();
	}


	//	Draw shape only
	public void paint(Graphics g)
	{	
                g.dispose();
                super.paint(g);
		System.out.println("--> paint(.)");
		Graphics2D g2=(Graphics2D)g;
		g2.draw(shp);
	}
        
        @Override
        public void update(Graphics g) {
            g.dispose();
            paint(g);
        }
}

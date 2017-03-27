/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package part3.simple.panel;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Digitek
 */
public class MainPanel extends JPanel {

    public MainPanel() {
        super();
        setBackground(Color.CYAN);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.drawLine(0, 0, getWidth()-1, getHeight()-1);
        g.drawLine(getWidth()-1, 0, 0, getHeight()-1);
    }

}

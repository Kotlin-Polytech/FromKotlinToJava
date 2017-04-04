/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package part3.simple.primitives.swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.*;

public class MainFrame extends JFrame {

    private MainFrame(String s) {
        super(s);
        setSize(500, 400);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // NB: this works in primitive cases,
        // but generally it's not a good idea to paint something
        // using graphic context from other components
        // E.g. we cannot change background color this way
        // Better way is described in "panel" example
        g = getContentPane().getGraphics();
        Color color = new Color(0, 0, 255);
        g.setColor(color);
        g.fillRect(100, 100, 100, 50);
        color = new Color(0, 255, 255, 128);
        g.setColor(color);
        g.fillRect(150, 100, 100, 50);
        color = Color.RED;
        g.setColor(color);
        g.drawRect(100, 200, 100, 50);
        g.drawLine(100, 200, 200, 250);
        g.drawLine(200, 200, 100, 250);
        g.setColor(Color.MAGENTA);
        g.drawRect(275, 100, 100, 50);
        g.fillOval(275, 100, 100, 50);
        g.setColor(Color.RED);
        g.drawArc(100, 300, 50, 50, 0, 90);
        g.drawArc(150, 300, 50, 50, 180, 90);
        g.setColor(new Color(0, 128, 0));
        g.setFont(new Font("Serif", Font.ITALIC, 24));
        g.drawString("Graphic primitives", 100, 75);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame("Swing primitives demo"));
    }   
}

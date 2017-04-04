/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package part3.simple.panel;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Digitek
 */
public class MainFrame extends JFrame {
    private MainFrame(String s) {
        super(s);
        setSize(600, 400);
        JPanel panel = new MainPanel();
        setContentPane(panel);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // It's better to draw on panel, not here
        g.setColor(Color.BLACK);
        g.drawLine(0, 0, getWidth() - 1, getHeight() - 1);
        g.drawLine(getWidth() - 1, 0, 0, getHeight() - 1);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame("Swing frame with panel"));
    }
}

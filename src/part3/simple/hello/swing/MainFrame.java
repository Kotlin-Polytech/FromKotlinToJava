/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package part3.simple.hello.swing;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MainFrame extends JFrame {

    private MainFrame(String s) {
        super(s);
        setSize(400, 150);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame("Простое окно Swing"));
    }
}

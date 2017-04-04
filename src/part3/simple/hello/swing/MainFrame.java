/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package part3.simple.hello.swing;

import javax.swing.*;

public class MainFrame extends JFrame {

    private MainFrame(String s) {
        super(s);
        setSize(400, 150);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame("Hello Swing"));
    }
}

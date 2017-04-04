/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package part3.simple.components.swing;

import javax.swing.*;

/**
 *
 * @author Digitek
 */
public class MainFrame extends JFrame {

    private MainFrame(String s) {
        super(s);
        setSize(400, 400);
        setLayout(null);
        JLabel label = new JLabel("Label");
        label.setBounds(100, 50, 100, 40);
        add(label);
        JButton button = new JButton("Button");
        button.setBounds(100, 100, 100, 40);
        add(button);        
        JCheckBox checkbox = new JCheckBox("Checkbox");
        checkbox.setBounds(100, 150, 100, 40);
        add(checkbox);
        String[] comboStrings = {"Combobox 1", "Combobox 2"};
        JComboBox<String> choice = new JComboBox<>(comboStrings);
        choice.setBounds(100, 200, 100, 20);
        add(choice);  
        JList<String> list = new JList<>(new String[] {"List 1", "List 2", "List 3", "List 4"});
        list.setBounds(100, 250, 100, 90);
        add(list);        
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame("Компоненты Swing"));
    }

}

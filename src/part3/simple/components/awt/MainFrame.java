/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package part3.simple.components.awt;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends Frame {

    MainFrame(String s) {
        super(s);
        setSize(400, 400);
        setLayout(null);
        Label label = new Label("Label");
        label.setBounds(100, 50, 100, 40);
        add(label);
        Button button = new Button("Button");
        button.setBounds(100, 100, 100, 40);
        add(button);        
        Checkbox checkbox = new Checkbox("Checkbox");
        checkbox.setBounds(100, 150, 100, 40);
        add(checkbox);
        Choice choice = new Choice();
        choice.add("Combobox 1");
        choice.add("Combobox 2");
        choice.setBounds(100, 200, 100, 20);
        add(choice);  
        List list = new List();
        list.add("List 1");
        list.add("List 2");
        list.add("List 3");
        list.add("List 4");
        list.setBounds(100, 250, 100, 90);
        add(list);        
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new MainFrame("Компоненты AWT");
    }

}

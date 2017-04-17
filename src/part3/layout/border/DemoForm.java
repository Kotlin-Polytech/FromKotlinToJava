package part3.layout.border;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class DemoForm {
    private JPanel rootPanel;
    private JCheckBox showCheckBox;
    private JRadioButton squareRadioButton;
    private JRadioButton triangleRadioButton;
    private JButton exitButton;


    public DemoForm() {
        showCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

            }
        });
    }

    JPanel getRootPanel() {
        return rootPanel;
    }
}

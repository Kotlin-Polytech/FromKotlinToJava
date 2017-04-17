package part3.layout.border;

import javax.swing.*;

public class DemoFrame extends JFrame {

    private DemoFrame() {
        setSize(600, 400);
        setContentPane(new DemoForm().getRootPanel());
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DemoFrame::new);
    }
}

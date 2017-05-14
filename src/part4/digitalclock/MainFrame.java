package part4.digitalclock;

import javax.swing.*;

public class MainFrame extends JFrame {

    private MainFrame() {
        super("Электронные часы");
        setSize(400, 100);
        setContentPane(new ClockPanel());
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }

}

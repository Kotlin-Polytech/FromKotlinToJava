package part3.painting.clock.swing;

import javax.swing.*;

/**
 * Основной фрейм программы
 * @author Mikhail Glukhikh
 */
public class ClockFrame extends JFrame {

    private ClockFrame() {
        super("Часы со стрелками");
        setSize(400, 400);
        setContentPane(new ClockPanel());
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ClockFrame::new);
    }
}

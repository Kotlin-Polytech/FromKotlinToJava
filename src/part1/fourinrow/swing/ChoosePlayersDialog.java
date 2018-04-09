package part1.fourinrow.swing;

import javax.swing.*;
import java.awt.event.*;

public class ChoosePlayersDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JRadioButton yellowComputerButton;
    private JRadioButton redHumanButton;
    private JRadioButton redComputerButton;
    private JRadioButton yellowHumanButton;

    private boolean exit = false;

    private ChoosePlayersDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> onOK());
        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(
                e -> onCancel(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT
        );
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        exit = true;
        dispose();
    }

    public static void main(String[] args) {
        ChoosePlayersDialog dialog = new ChoosePlayersDialog();
        dialog.pack();
        dialog.setVisible(true);
        if (dialog.exit) System.exit(0);
        SwingUtilities.invokeLater(() -> new FourInRowFrame(
                dialog.yellowHumanButton.isSelected(),
                dialog.redHumanButton.isSelected()
        ));
    }
}

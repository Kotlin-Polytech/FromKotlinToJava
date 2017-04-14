package part3.layout.border;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class MainFrame extends JFrame {

    private MainPanel mainPanel;
    private JPanel optionPanel;
    private JCheckBox drawingBox;
    private JRadioButton square;
    private JRadioButton triangle;

    private void initMainPanel() {
        mainPanel = new MainPanel();
        mainPanel.setBackground(Color.CYAN);
        mainPanel.setBorder(new LineBorder(Color.BLUE, 2));
        this.add(mainPanel, BorderLayout.CENTER);
    }

    private void initHeadPanel() {
        JPanel headPanel = new JPanel();
        headPanel.setPreferredSize(new Dimension(200, 30));
        headPanel.setBorder(new LineBorder(Color.ORANGE, 2));
        headPanel.setBackground(Color.YELLOW);
        JLabel headLabel = new JLabel("ПРОСТЫЕ КОМПОНЕНТЫ");
        ImageIcon icon = new ImageIcon("headIcon.gif");
        headLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headLabel.setIcon(icon);
        headLabel.setBorder(new LineBorder(Color.BLACK, 1));
        headPanel.add(headLabel);
        this.add(headPanel, BorderLayout.NORTH);
    }

    private void initRadioPanel() {
        JPanel radioPanel = new JPanel();
        radioPanel.setBorder(new LineBorder(Color.BLACK, 1));
        radioPanel.setPreferredSize(new Dimension(125, 100));
        radioPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel head = new JLabel("Выберите");
        radioPanel.add(head);
        square = new JRadioButton("квадрат");
        triangle = new JRadioButton("треугольник");
        ActionListener listener = e -> {
            String command = e.getActionCommand();
            if (command.equals("Square")) {
                mainPanel.setDrawingKind(0);
            } else {
                mainPanel.setDrawingKind(1);
            }
        };
        square.setEnabled(false);
        square.setSelected(true);
        square.addActionListener(listener);
        square.setActionCommand("Square");
        triangle.setEnabled(false);
        triangle.setSelected(false);
        triangle.addActionListener(listener);
        triangle.setActionCommand("Triangle");
        radioPanel.add(square);
        radioPanel.add(triangle);
        ButtonGroup bg = new ButtonGroup();
        bg.add(square);
        bg.add(triangle);
        optionPanel.add(radioPanel);
    }

    private void initOptionPanel() {
        optionPanel = new JPanel();
        optionPanel.setPreferredSize(new Dimension(200, 300));
        optionPanel.setBorder(new LineBorder(Color.DARK_GRAY, 2));
        drawingBox = new JCheckBox("Показать рисунок");
        drawingBox.addItemListener(e -> {
            mainPanel.setDrawing(drawingBox.isSelected());
            square.setEnabled(drawingBox.isSelected());
            triangle.setEnabled(drawingBox.isSelected());
        });
        optionPanel.add(drawingBox);
        initRadioPanel();
        JButton exitButton = new JButton("Выход");
        exitButton.addActionListener(e -> System.exit(0));
        optionPanel.add(exitButton);
        this.add(optionPanel, BorderLayout.EAST);
    }

    private MainFrame(String s) {
        super(s);
        setSize(600, 400);
        this.setLayout(new BorderLayout());
        initMainPanel();
        initHeadPanel();
        initOptionPanel();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame("Демонстрация компонентов"));
    }

}

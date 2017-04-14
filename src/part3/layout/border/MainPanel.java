package part3.layout.border;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

@SuppressWarnings("WeakerAccess")
public class MainPanel extends JPanel {

    private boolean drawingOn;
    private int drawingKind;

    public MainPanel() {
        super();
        drawingOn = false;
        drawingKind = 0;
    }

    public void setDrawing(boolean on) {
        drawingOn = on;
        repaint();
    }

    public void setDrawingKind(int kind) {
        drawingKind = kind;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (drawingOn) {
            g.setColor(Color.BLUE);
            if (drawingKind==0)
                g.drawRect(50, 50, 100, 100);
            else {
                g.drawLine(50, 50, 150, 150);
                g.drawLine(50, 50, 150, 50);
                g.drawLine(150, 50, 150, 150);
            }
        }
    }

}

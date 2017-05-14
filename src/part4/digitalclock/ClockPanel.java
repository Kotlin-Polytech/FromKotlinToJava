package part4.digitalclock;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.JPanel;

@SuppressWarnings("WeakerAccess")
public class ClockPanel extends JPanel {


    public ClockPanel() {
        super();
        setBackground(Color.BLACK);
        new PeriodicThread(1000, ev -> repaint()).start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        final Calendar calendar = Calendar.getInstance();
        final int hour = calendar.get(Calendar.HOUR);
        final int minute = calendar.get(Calendar.MINUTE);
        final int second = calendar.get(Calendar.SECOND);
        String sb = String.format("%02d", hour) + ":" +
                String.format("%02d", minute) + ":" + String.format("%02d", second);
        g.setColor(Color.GREEN);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 56));
        g.drawString(sb, 100, 50);
    }

}

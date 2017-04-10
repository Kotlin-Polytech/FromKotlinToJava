package part3.painting.g2d;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("WeakerAccess")
public class MainPanel extends JPanel {
    private Image image;

    public MainPanel() {
        super();
        ImageIcon icon = new ImageIcon("files/shield.gif");
        image = icon.getImage();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        // Преобразование координат
        AffineTransform at = AffineTransform.getRotateInstance(
                Math.PI / 6.0, 100, 50);
        at.concatenate(AffineTransform.getScaleInstance(1.0, 0.5));
        g2d.setTransform(at);
        g2d.setColor(new Color(0, 128, 0));
        g2d.setFont(new Font("Serif", Font.ITALIC, 24));
        g2d.drawString("Графические примитивы", 100, 50);
        g2d.drawRect(75, 25, 300, 50);
        // Перья
        g2d.setTransform(new AffineTransform());
        g2d.setColor(new Color(255, 0, 0));
        g2d.drawLine(50, 250, 100, 250);
        g2d.drawLine(100, 250, 100, 300);
        g2d.setStroke(new BasicStroke(10));
        g2d.drawLine(150, 250, 200, 250);
        g2d.drawLine(200, 250, 200, 300);
        g2d.setStroke(new BasicStroke(10, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
        g2d.drawLine(250, 250, 300, 250);
        g2d.drawLine(300, 250, 300, 300);
        g2d.setStroke(new BasicStroke(5, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER,
                1, new float[]{10, 5, 20, 5, 10, 5}, 0));
        g2d.drawLine(50, 400, 350, 400);
        // Кисти
        g2d.setPaint(Color.MAGENTA);
        g2d.fillRect(500, 50, 100, 100);
        g2d.setPaint(new GradientPaint(650, 50, Color.RED, 750, 150, Color.BLUE));
        g2d.fillRect(650, 50, 100, 100);
        g2d.fillRect(500, 200, 250, 100);
        // Изображения
        for (int x=100; x<=500; x+=50) {
            for (int y=450; y<=500; y+=50) {
               g2d.drawImage(image, x, y, this);
            }
        }
        // Стрелка
        GeneralPath shape = new GeneralPath();
        shape.moveTo(-1.0, 0.0);
        shape.lineTo(-1.0, -90.0);
        shape.lineTo(-10.0, -80.0);
        shape.lineTo(0.0, -100.0);
        shape.lineTo(10.0, -80.0);
        shape.lineTo(1.0, -90.0);
        shape.lineTo(1.0, 0.0);
        shape.closePath();
        AffineTransform transform = AffineTransform.getTranslateInstance(150.0, 200.0);
        g2d.setTransform(transform);
        g2d.fill(shape);
        // Эллипс с рамкой
        BufferedImage ellImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics2D imageGraphics = ellImage.createGraphics();
        imageGraphics.setBackground(new Color(0, 0, 0, 0));
        Ellipse2D e2d = new Ellipse2D.Double(0.0, 0.0, 100.0, 100.0);
        imageGraphics.setColor(Color.RED);
        imageGraphics.fill(e2d);
        imageGraphics.setColor(Color.BLUE);
        imageGraphics.draw(e2d);
        g2d.drawImage(ellImage, 450, 250, this);
    }
}

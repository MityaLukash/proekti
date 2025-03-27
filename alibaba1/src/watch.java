import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class watch extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();
        int radius = Math.min(width, height) / 2 - 20;
        
        // Рисуем круг
        g2d.drawOval((width - 2 * radius) / 2, (height - 2 * radius) / 2, 2 * radius, 2 * radius);
        
        // Рисуем числа от 1 до 12
        for (int i = 1; i <= 12; i++) {
            double angle = Math.toRadians(i * 30 - 90); // 30 градусов между цифрами
            int x = (int) (width / 2 + radius * Math.cos(angle));
            int y = (int) (height / 2 + radius * Math.sin(angle));
            g2d.drawString(String.valueOf(i), x, y);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Циферблат часов");
        watch clockDial = new watch();
        frame.add(clockDial);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

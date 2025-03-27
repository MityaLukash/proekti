import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.Calendar;
import java.util.TimeZone;

public class hhh extends JPanel {
    
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
            double angle = Math.toRadians(i * 30 - 90);
            int x = (int) (width / 2 + radius * Math.cos(angle));
            int y = (int) (height / 2 + radius * Math.sin(angle));
            g2d.drawString(String.valueOf(i), x, y);
        }

        // Получаем текущее время в Москве
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Moscow"));
        int hours = calendar.get(Calendar.HOUR) % 12; // Часы (0-11)
        int minutes = calendar.get(Calendar.MINUTE); // Минуты (0-59)

        // Рисуем часовую стрелку
        double hourAngle = Math.toRadians(hours * 30 + minutes * 0.5 - 90);
        int hourLength = radius / 2;
        int hourX = (int) (width / 2 + hourLength * Math.cos(hourAngle));
        int hourY = (int) (height / 2 + hourLength * Math.sin(hourAngle));
        g2d.setStroke(new BasicStroke(6));
        g2d.drawLine(width / 2, height / 2, hourX, hourY);

        // Рисуем минутную стрелку
        double minuteAngle = Math.toRadians(minutes * 6 - 90);
        int minuteLength = (int) (radius * 0.8);
        int minuteX = (int) (width / 2 + minuteLength * Math.cos(minuteAngle));
        int minuteY = (int) (height / 2 + minuteLength * Math.sin(minuteAngle));
        g2d.setStroke(new BasicStroke(4));
        g2d.drawLine(width / 2, height / 2, minuteX, minuteY);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Циферблат часов");
        hhh clockDial = new hhh();
        frame.add(clockDial);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Обновляем циферблат каждую минуту
        Timer timer = new Timer(60000, e -> clockDial.repaint());
        timer.start();
    }
}

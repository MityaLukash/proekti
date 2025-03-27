import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.TimeZone;

public class Clock extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();
        int radius = Math.min(width, height) / 2 - 30; // Увеличиваем отступ для цифр

        // Рисуем круг
        g2d.drawOval((width - 2 * radius) / 2, (height - 2 * radius) / 2, 2 * radius, 2 * radius);

        // Рисуем рамку внутри круга
        g2d.setStroke(new BasicStroke(3));
        g2d.drawOval((width - 2 * (radius - 5)) / 2, (height - 2 * (radius - 5)) / 2, 2 * (radius - 5), 2 * (radius - 5));

        // Рисуем числа от 1 до 12
        Font font = new Font("Arial", Font.BOLD, 18);
        g2d.setFont(font);

        for (int i = 1; i <= 12; i++) {
            double angle = Math.toRadians(i * 30 - 90);
            int x = (int) (width / 2 + (radius - 25) * Math.cos(angle)); // Увеличиваем отступ от края для цифр
            int y = (int) (height / 2 + (radius - 25) * Math.sin(angle));
            // Смещаем текст, чтобы центрировалось лучше
            FontMetrics fm = g2d.getFontMetrics();
            int textWidth = fm.stringWidth(String.valueOf(i));
            int textHeight = fm.getAscent();
            g2d.drawString(String.valueOf(i), x - textWidth / 2, y + textHeight / 4);
        }

        // Получаем текущее время в Москве
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Moscow"));
        int hours = calendar.get(Calendar.HOUR) % 12; // Часы (0-11)
        int minutes = calendar.get(Calendar.MINUTE); // Минуты (0-59)
        int seconds = calendar.get(Calendar.SECOND); // Секунды (0-59)

        // Рисуем часовую стрелку
        drawHourHand(g2d, width, height, radius, hours, minutes);
        // Рисуем минутную стрелку
        drawMinuteHand(g2d, width, height, radius, minutes);
        // Рисуем секундную стрелку
        drawSecondHand(g2d, width, height, radius, seconds);
        // Рисуем секундные палочки
        drawSecondMarkers(g2d, width, height, radius);

        // Устанавливаем цвет для стрелок
        g2d.setColor(Color.BLACK);
    }

    private void drawHourHand(Graphics2D g2d, int width, int height, int radius, int hours, int minutes) {
        double hourAngle = Math.toRadians(hours * 30 + minutes * 0.5 - 90);
        int hourLength = radius / 2;
        int hourX = (int) (width / 2 + hourLength * Math.cos(hourAngle));
        int hourY = (int) (height / 2 + hourLength * Math.sin(hourAngle));
        g2d.setStroke(new BasicStroke(6));
        g2d.drawLine(width / 2, height / 2, hourX, hourY);
    }

    private void drawMinuteHand(Graphics2D g2d, int width, int height, int radius, int minutes) {
        double minuteAngle = Math.toRadians(minutes * 6 - 90);
        int minuteLength = (int) (radius * 0.8);
        int minuteX = (int) (width / 2 + minuteLength * Math.cos(minuteAngle));
        int minuteY = (int) (height / 2 + minuteLength * Math.sin(minuteAngle));
        g2d.setStroke(new BasicStroke(4));
        g2d.drawLine(width / 2, height / 2, minuteX, minuteY);
    }

    private void drawSecondHand(Graphics2D g2d, int width, int height, int radius, int seconds) {
        double secondAngle = Math.toRadians(seconds * 6 - 90);
        int secondLength = (int) (radius * 0.9);
        int secondX = (int) (width / 2 + secondLength * Math.cos(secondAngle));
        int secondY = (int) (height / 2 + secondLength * Math.sin(secondAngle));
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(Color.RED);
        g2d.drawLine(width / 2, height / 2, secondX, secondY);
    }

    private void drawSecondMarkers(Graphics2D g2d, int width, int height, int radius) {
        g2d.setColor(Color.BLACK);
        for (int i = 0; i < 60; i++) {
            double angle = Math.toRadians(i * 6 - 90);
            
            int x1 = (int) (width / 2 + (radius - 10) * Math.cos(angle));
            int y1 = (int) (height / 2 + (radius - 10) * Math.sin(angle));
            int x2 = (int) (width / 2 + radius * Math.cos(angle));
            int y2 = (int) (height / 2 + radius * Math.sin(angle));
            g2d.drawLine(x1, y1, x2, y2);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Циферблат часов");
        Clock clockDial = new Clock();
        frame.add(clockDial);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Обновляем циферблат каждую секунду
        Timer timer = new Timer(1000, e -> clockDial.repaint());
        timer.start();
    }
}

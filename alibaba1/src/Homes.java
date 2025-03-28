import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Homes extends JPanel {
    private Color innerSquareColor = Color.BLACK;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Рисуем внешний дом(Первый прямоугольник)
        g.setColor(Color.BLACK);
        g.drawRect(50, 50, 400, 200);
        
        // Рисуем окно для дома(Второй прямоугольник)
        g.setColor(innerSquareColor);
        g.fillRect(90, 90, 320,120);
    }
//Добавляем кнопку "включения света" при которой будет меняться цвет второго прямоугольника(Окна)
    public static void main(String[] args) {
        JFrame frame = new JFrame("Homes");
        Homes panel = new Homes();
        JButton button = new JButton("Включить свет");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.changeColor();
            }
        });

        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);
        frame.add(button, BorderLayout.SOUTH);
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void changeColor() {
        innerSquareColor = Color.YELLOW;
        repaint();
    }
}
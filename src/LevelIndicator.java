import javax.swing.*;
import java.awt.*;

public class LevelIndicator extends JPanel {
    private int minValue;
    private int maxValue;
    private int currentValue;

    public LevelIndicator(int minValue, int maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.currentValue = minValue;
        setPreferredSize(new Dimension(50, 200)); // Задаем размер панели
    }

    public void updateValue(int value) {
        this.currentValue = Math.max(minValue, Math.min(maxValue, value)); // Ограничиваем значение
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();

        // Отрисовываем фон
        g.setColor(Color.white);
        g.fillRect(0, 0, width, height);

        // Определяем положение и высоту индикатора
        int indicatorHeight = (int) ((double) (currentValue - minValue) / (maxValue - minValue) * height);

        // Ограничиваем высоту индикатора, чтобы он не выходил за границы
        indicatorHeight = Math.min(indicatorHeight, height);

        g.setColor(Color.GREEN);
        g.fillRect(0, height - indicatorHeight, width, indicatorHeight);
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public int getCurrentValue() {
        return currentValue;
    }
}
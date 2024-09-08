import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class DataGraph extends JPanel {
    private List<Integer> dataHistory;
    private int minValue, maxValue, threshold, hysteresis;

    public DataGraph() {
        this.dataHistory = new ArrayList<>();
    }

    public void setData(List<Integer> data, int minValue, int maxValue, int threshold, int hysteresis) {
        this.dataHistory = data;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.threshold = threshold;
        this.hysteresis = hysteresis;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);

        int width = getWidth();
        int height = getHeight();
        int dataCount = dataHistory.size();

        // Ограничение количества отображаемых данных в зависимости от ширины графика
        int visibleDataCount = width; // количество значений, которое помещается на графике
        int startIndex = Math.max(0, dataCount - visibleDataCount);

        // Масштабируемые размеры
        int stepX = Math.max(1, width / Math.max(1, visibleDataCount - 1));

        // Рисуем накопленные данные
        int x = 0;
        for (int i = startIndex + 1; i < dataHistory.size(); i++) {
            int prevY = height - (int)((double)(dataHistory.get(i - 1) - minValue) / (maxValue - minValue) * height);
            int currY = height - (int)((double)(dataHistory.get(i) - minValue) / (maxValue - minValue) * height);
            g.drawLine(x, prevY, x + stepX, currY);
            x += stepX;
        }

        // Линии порога и гистерезиса
        g.setColor(Color.RED);
        int thresholdY = height - (int)((double)(threshold - minValue) / (maxValue - minValue) * height);
        g.drawLine(0, thresholdY, width, thresholdY); // Сплошная линия порога

        g.setColor(Color.GRAY);
        int hysteresisY1 = thresholdY - (int)((double)(hysteresis) / (maxValue - minValue) * height);
        int hysteresisY2 = thresholdY + (int)((double)(hysteresis) / (maxValue - minValue) * height);
        g.drawLine(0, hysteresisY1, width, hysteresisY1); // Пунктирная линия гистерезиса (верхняя)
        g.drawLine(0, hysteresisY2, width, hysteresisY2); // Пунктирная линия гистерезиса (нижняя)
    }
}
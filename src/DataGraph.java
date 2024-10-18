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

        int visibleDataCount = width;
        int startIndex = Math.max(0, dataCount - visibleDataCount);

        int stepX = Math.max(1, width / Math.max(1, visibleDataCount - 1));

        g.setColor(Color.RED);
        int thresholdY = height - (int)((double)(threshold - minValue) / (maxValue - minValue) * height);
        g.drawLine(0, thresholdY, width, thresholdY);

        g.setColor(Color.GRAY);
        int hysteresisY1 = thresholdY - (int)((double)(hysteresis) / (maxValue - minValue) * height);
        int hysteresisY2 = thresholdY + (int)((double)(hysteresis) / (maxValue - minValue) * height);
        g.drawLine(0, hysteresisY1, width, hysteresisY1);
        g.drawLine(0, hysteresisY2, width, hysteresisY2);

        ((Graphics2D)g).setStroke(new BasicStroke(3.0f));
        g.setColor(Color.BLUE);
        int x = 0;
        for (int i = startIndex + 1; i < dataHistory.size(); i++) {
            int prevY = height - (int)((double)(dataHistory.get(i - 1) - minValue) / (maxValue - minValue) * height);
            int currY = height - (int)((double)(dataHistory.get(i) - minValue) / (maxValue - minValue) * height);
            g.drawLine(x, prevY, x + stepX, currY);
            if (prevY < hysteresisY2 && currY >= hysteresisY2) {
                g.setColor(Color.CYAN);
                g.drawOval(x, hysteresisY2, 1,1);
                g.setColor(Color.BLUE);
            }
            else if (prevY > hysteresisY1 && currY <= hysteresisY1) {
                g.setColor(Color.CYAN);
                g.drawOval(x, hysteresisY1, 1,1);
                g.setColor(Color.BLUE);
            }
            x += stepX;
        }
        ((Graphics2D)g).setStroke(new BasicStroke(1.0f));

    }
}
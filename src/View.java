import javax.swing.*;
import java.awt.*;
import java.util.List;

public class View extends JFrame {
    private JSlider slider;
    private JTextField minValueField, maxValueField, thresholdField, hysteresisField;
    private JButton applySettingsButton;
    private LevelIndicator levelIndicator;
    private DataGraph dataGraph;

    public View() {
        setTitle("Dynamic GUI with Level Indicator (MVC)");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);

        // Панель слайдера и контролов
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(5, 2));

        // Поля для настройки
        minValueField = new JTextField("0");
        maxValueField = new JTextField("100");
        thresholdField = new JTextField("70");
        hysteresisField = new JTextField("5");

        controlPanel.add(new JLabel("Min Значение:"));
        controlPanel.add(minValueField);

        controlPanel.add(new JLabel("Max Значение:"));
        controlPanel.add(maxValueField);

        controlPanel.add(new JLabel("Граница:"));
        controlPanel.add(thresholdField);

        controlPanel.add(new JLabel("Дребезг:"));
        controlPanel.add(hysteresisField);

        applySettingsButton = new JButton("Применить");
        controlPanel.add(applySettingsButton);

        // Настройка слайдера
        slider = new JSlider(JSlider.VERTICAL, 0, 100, 50);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        // Настройка LevelIndicator
        levelIndicator = new LevelIndicator(0, 100);

        // Настройка DataGraph
        dataGraph = new DataGraph();

        // Добавляем JScrollPane для прокрутки графика
        JScrollPane scrollPane = new JScrollPane(dataGraph);

        // Создаем отдельную панель для индикатора уровня и графика
        JPanel displayPanel = new JPanel(new BorderLayout());
        displayPanel.add(levelIndicator, BorderLayout.WEST);
        displayPanel.add(new JScrollPane(dataGraph), BorderLayout.CENTER); // График с прокруткой


        add(slider, BorderLayout.WEST);
        add(displayPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
    }

    public void updateLevelIndicator(int value) {
        levelIndicator.updateValue(value);
    }

    public void updateDataGraph(List<Integer> data, int minValue, int maxValue, int threshold, int hysteresis) {
        dataGraph.setData(data, minValue, maxValue, threshold, hysteresis);
        levelIndicator.setMinValue(minValue);
        levelIndicator.setMaxValue(maxValue);
        levelIndicator.updateValue(levelIndicator.getCurrentValue()); // Обновляем значение индикатора
    }

    public void updateSliderRange(int min, int max) {
        slider.setMinimum(min);
        slider.setMaximum(max);
        slider.setMajorTickSpacing((max - min) / 5);
    }

    public JSlider getSlider() {
        return slider;
    }

    public JButton getApplySettingsButton() {
        return applySettingsButton;
    }

    public JTextField getMinValueField() {
        return minValueField;
    }

    public JTextField getMaxValueField() {
        return maxValueField;
    }

    public JTextField getThresholdField() {
        return thresholdField;
    }

    public JTextField getHysteresisField() {
        return hysteresisField;
    }

    public DataGraph getDataGraph() {
        return dataGraph;
    }
}
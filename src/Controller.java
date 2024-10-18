import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class Controller {
    private Model model;
    private View view;
    private Timer timer;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;

        view.getApplySettingsButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int minValue = Integer.parseInt(view.getMinValueField().getText());
                int maxValue = Integer.parseInt(view.getMaxValueField().getText());
                int threshold = Integer.parseInt(view.getThresholdField().getText());
                int hysteresis = Integer.parseInt(view.getHysteresisField().getText());

                model.setMinValue(minValue);
                model.setMaxValue(maxValue);
                model.setThreshold(threshold);
                model.setHysteresis(hysteresis);

                view.updateSliderRange(minValue, maxValue);
                view.updateDataGraph(model.getDataHistory(), minValue, maxValue, threshold, hysteresis);

                model.getDataHistory().clear();
                view.getDataGraph().repaint();
            }
        });

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                int sliderValue = view.getSlider().getValue();
                model.addData(sliderValue);
                view.updateLevelIndicator(sliderValue);
                view.updateDataGraph(model.getDataHistory(), model.getMinValue(), model.getMaxValue(), model.getThreshold(), model.getHysteresis());
            }
        }, 0, 100); // Обновление каждые 100 миллисекунд
    }
}

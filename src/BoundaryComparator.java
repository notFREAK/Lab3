import java.util.function.Consumer;
import java.text.SimpleDateFormat;
import java.util.Date;
public class BoundaryComparator {
    private final SimpleDateFormat dateFormat;
    private int threshold;
    private int hysteresis;
    private Consumer<String> boundaryEventHandler;

    public BoundaryComparator(int threshold, int hysteresis, Consumer<String> boundaryEventHandler) {
        this.threshold = threshold;
        this.hysteresis = hysteresis;
        this.boundaryEventHandler = boundaryEventHandler;
        this.dateFormat = new SimpleDateFormat("HH:mm:ss.S");
    }

    public void checkBoundary(int previousValue, int currentValue) {
        if (previousValue < threshold + hysteresis && currentValue >= threshold + hysteresis) {
            String formattedTime = dateFormat.format(new Date());
            boundaryEventHandler.accept("Пересечена граница:\n Направление: Вверх Время: " + formattedTime);
        } else if (previousValue > threshold - hysteresis && currentValue <= threshold - hysteresis) {
            String formattedTime = dateFormat.format(new Date());
            boundaryEventHandler.accept("Пересечена граница:\n Направление: Вниз Время: " + formattedTime);
        }
    }
    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public void setHysteresis(int hysteresis) {
        this.hysteresis = hysteresis;
    }
}
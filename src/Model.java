import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Model {
    private int minValue;
    private int maxValue;
    private int threshold;
    private int hysteresis;
    private List<Integer> dataHistory;
    private BoundaryComparator boundaryComparator;

    public Model(int minValue, int maxValue, int threshold, int hysteresis, Consumer<String> boundaryEventHandler) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.threshold = threshold;
        this.hysteresis = hysteresis;
        this.dataHistory = new ArrayList<>();
        this.boundaryComparator = new BoundaryComparator(threshold, hysteresis, boundaryEventHandler);
    }

    public void addData(int value) {
        if (!dataHistory.isEmpty()) {
            int lastValue = dataHistory.get(dataHistory.size() - 1);
            boundaryComparator.checkBoundary(lastValue, value);
        }
        dataHistory.add(value);
    }

    // Геттеры и сеттеры для minValue, maxValue, threshold, hysteresis и dataHistory

    public void setThreshold(int threshold) {
        this.threshold = threshold;
        boundaryComparator.setThreshold(threshold);
    }

    public void setHysteresis(int hysteresis) {
        this.hysteresis = hysteresis;
        boundaryComparator.setHysteresis(hysteresis);
    }

    public List<Integer> getDataHistory() {
        return dataHistory;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public int getThreshold() {
        return threshold;
    }

    public int getHysteresis() {
        return hysteresis;
    }
}
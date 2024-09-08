import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

// Интерфейс слушателя данных
interface DataListener {
    void onDataReceived(int value);
}
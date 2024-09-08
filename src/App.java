import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Model model = new Model(0, 100, 70, 5, (message) -> {
                System.out.println(message);
            });
            View view = new View();
            Controller controller = new Controller(model, view);
            view.setVisible(true);
        });
    }
}
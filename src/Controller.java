import java.util.HashMap;
import java.util.Vector;

public class Controller {
    private Controller instance;
    private HashMap<String, Vector<String>> table;

    public Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }
}

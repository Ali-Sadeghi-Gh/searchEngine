import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

public class Controller {
    private static Controller instance;
    private HashMap<String, Vector<String>> table;

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public void createTable() throws IOException {
        table = new HashMap<>();

        File dir = new File("src/resources");
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                String[] words = FileToString.readFile(child);
                addToTable(words, child.getName());
            }
        } else {
            throw new RuntimeException("path isn't a directory");
        }
    }

    private void addToTable(String[] words, String fileName) {
        for (String word : words) {
            if (table.containsKey(word)) {
                if (!table.get(word).lastElement().equals(fileName)) {
                    table.get(word).add(fileName);
                }
            } else {
                Vector<String> vector = new Vector<>();
                vector.add(fileName);
                table.put(word, vector);
            }
        }
    }

    public void handleQuery(String query) {
        Vector<String> vector = table.get(query);
        for (String str : vector) {
            System.out.println(str);
        }
    }
}

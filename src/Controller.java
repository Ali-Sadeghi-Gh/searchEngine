import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

public class Controller {
    private static Controller instance;
    private HashMap<String, Vector<String>> table;

    private Controller() {

    }

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
            table.remove("");
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
        Vector<Vector<String>> vectors = decodeQuery(query.toLowerCase());
        HashSet<String> orItems = itemsUnion(vectors.get(1));
        HashSet<String> norItems = itemsUnion(vectors.get(2));
        HashMap<String, Integer> results = new HashMap<>();
        Vector<String> items = vectors.get(0);
        if (items != null) {
            int min = Integer.MAX_VALUE;
            int minIndex = -1;
            for (int i = 0; i < items.size(); i++) {
                int size = table.get(items.get(i)).size();
                if (size < min) {
                    min = size;
                    minIndex = i;
                }
            }
            for (String s : table.get(items.get(minIndex))) {
                results.put(s, 0);
            }
            for (String item : items) {
                for (String s : table.get(item)) {
                    if (results.containsKey(s)) {
                        results.replace(s, results.get(s) + 1);
                    }
                }
            }
        }
//        if (vector == null) {
//            System.out.println("nothing!");
//            return;
//        }
//        for (String str : vector) {
//            System.out.println(str);
//        }
    }

    private Vector<Vector<String>> decodeQuery(String query) {
        String[] strings = query.split("//s+");
        Vector<Vector<String>> vectors = new Vector<>();
        vectors.add(new Vector<>());
        vectors.add(new Vector<>());
        vectors.add(new Vector<>());
        for (String string : strings) {
            switch (string.charAt(0)) {
                case '+' -> vectors.get(1).add(string.substring(1));
                case '-' -> vectors.get(2).add(string.substring(1));
                default -> vectors.get(0).add(string);
            }
        }
        return vectors;
    }


    private HashSet<String> itemsUnion(Vector<String> items) {
        HashSet<String> set = new HashSet<>();
        if (items == null) {
            return set;
        }
        for (String item : items) {
            Vector<String> vector = table.get(item);
            set.addAll(vector);
        }
        return set;
    }
}

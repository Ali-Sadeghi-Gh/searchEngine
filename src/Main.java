import java.io.*;
import java.util.HashMap;
import java.util.Vector;

public class Main {
    public static void main(String[] args) throws IOException {
        File dir = new File("src/resources");
        File[] directoryListing = dir.listFiles();
        HashMap<String, Vector<String>> table = new HashMap<>();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                String[] words = FileToString.readFile(child);
                String fileName = child.getName();
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
        } else {
            throw new RuntimeException("path isn't a directory");
        }


        System.out.println(table.get("engineering"));

    }
}
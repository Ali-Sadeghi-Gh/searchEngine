import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

public class InvertedIndexManager {
    private final HashMap<String, Vector<String>> invertedIndex;

    public InvertedIndexManager() {
        invertedIndex = new HashMap<>();
    }

    public HashMap<String, Vector<String>> getInvertedIndex() {
        return invertedIndex;
    }

    public void createInvertedIndexOfFiles(File[] files) throws IOException {
        for (File child : files) {
            String[] words = FileToString.readFile(child);
            addToInvertedIndex(words, child.getName());
        }
        invertedIndex.remove("");
    }

    private void addToInvertedIndex(String[] words, String fileName) {
        for (String word : words) {
            if (invertedIndex.containsKey(word)) {
                if (!invertedIndex.get(word).lastElement().equals(fileName)) {
                    invertedIndex.get(word).add(fileName);
                }
            } else {
                Vector<String> vector = new Vector<>();
                vector.add(fileName);
                invertedIndex.put(word, vector);
            }
        }
    }
}

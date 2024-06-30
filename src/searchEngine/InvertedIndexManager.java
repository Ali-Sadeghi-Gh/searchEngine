package searchEngine;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

public class InvertedIndexManager<K> {
    private final HashMap<String, Vector<K>> invertedIndex;

    public InvertedIndexManager() {
        invertedIndex = new HashMap<>();
    }

    public Vector<K> findDocsByWord(String word) {
        return invertedIndex.get(word);
    }

    public void createInvertedIndexOfFiles(File[] files) throws IOException {
        for (File child : files) {
            // String[] words = FileToWordsConvertor.convertFileToWords(child, new Vector<>(), new SpaceTokenizer());
            // addToInvertedIndex(words, child.getName());
        }
        invertedIndex.remove("");
    }

    public void addData(HashMap<K, String> data) { //TODO keySet
        for (K k : data.keySet()) {

        }
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

package searchEngine;

import searchEngine.filters.Filter;
import searchEngine.tokenizers.Tokenizer;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;

public class InvertedIndexManager<K> {
    private final HashMap<String, Vector<K>> invertedIndex;
    private Vector<Filter> filters;
    private Tokenizer tokenizer;

    public InvertedIndexManager(Vector<Filter> filters, Tokenizer tokenizer) {
        invertedIndex = new HashMap<>();
        this.tokenizer = tokenizer;
        this.filters = filters;
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
        for (K key : data.keySet()) {
            String str = applyFilters(data.get(key), filters);
            updateInvertedIndex(tokenizer.tokenize(str), key);
        }
    }

    private String applyFilters(String str, Vector<Filter> filters) {
        for (Filter filter : filters) {
            str = filter.doFilter(str);
        }
        return str;
    }

    private void updateInvertedIndex(String[] words, K key) { //TODO search
        for (String word : words) {
            if (invertedIndex.containsKey(word)) {
                if (!invertedIndex.get(word).lastElement().equals(key)) {
                    invertedIndex.get(word).add(key);
                }
            } else {
                Vector<K> keys = new Vector<>();
                keys.add(key);
                invertedIndex.put(word, keys);
            }
        }
    }
}

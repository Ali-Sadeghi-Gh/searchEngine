package searchEngine;

import searchEngine.filters.Filter;
import searchEngine.tokenizers.Tokenizer;

import java.util.HashMap;
import java.util.Vector;

public class InvertedIndexManager<K> {
    private final HashMap<String, Vector<K>> invertedIndex;
    private final Vector<Filter> filters;
    private final Tokenizer tokenizer;

    public InvertedIndexManager(Vector<Filter> filters, Tokenizer tokenizer) {
        invertedIndex = new HashMap<>();
        this.tokenizer = tokenizer;
        this.filters = filters;
    }

    public Vector<K> findKeysByWord(String word) {
        return invertedIndex.get(word);
    }

    public void addData(HashMap<K, String> data) { //TODO keySet
        for (K key : data.keySet()) {
            String str = applyFilters(data.get(key));
            updateInvertedIndex(tokenizer.tokenize(str), key);
        }
        invertedIndex.remove("");
    }

    private String applyFilters(String str) {
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

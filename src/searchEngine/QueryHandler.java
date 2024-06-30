package searchEngine;

import searchEngine.decoders.Decoder;

import java.util.HashSet;
import java.util.Vector;

public class QueryHandler {
    private final InvertedIndexManager invertedIndexManager;
    private final Decoder decoder;

    public QueryHandler(InvertedIndexManager invertedIndexManager, Decoder decoder) {
        this.invertedIndexManager = invertedIndexManager;
        this.decoder = decoder;
    }

    public HashSet<String> getQueryResult(String query) {
        Vector<Vector<String>> vectors = decoder.decode(query.toLowerCase());
        HashSet<String> orItems = itemsUnion(vectors.get(1));
        HashSet<String> norItems = itemsUnion(vectors.get(2));
        HashSet<String> results;
        Vector<String> items = vectors.get(0);
        if (items.size() != 0) {
            int minIndex = getMinIndex(items);
            if (minIndex == -1) {
                results = new HashSet<>();
            } else {
                results = new HashSet<>(invertedIndexManager.findDocsByWord(items.get(minIndex)));
                for (String item : items) {
                    Vector<String> foundDocs = invertedIndexManager.findDocsByWord(item);
                    if (foundDocs != null) {
                        results.removeIf(s -> !foundDocs.contains(s));
                    } else {
                        results.clear();
                    }
                }
                if (orItems.size() != 0) {
                    results.removeIf(s -> !orItems.contains(s));
                }
            }
        } else {
            results = orItems;
        }
        results.removeIf(norItems::contains);

        return results;
    }

    private int getMinIndex(Vector<String> items) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = 0; i < items.size(); i++) {
            Vector<String> foundDocs = invertedIndexManager.findDocsByWord(items.get(i));
            if (foundDocs == null) {
                return -1;
            }
            int size = foundDocs.size();
            if (size < min) {
                min = size;
                minIndex = i;
            }
        }
        return minIndex;
    }

    private HashSet<String> itemsUnion(Vector<String> items) {
        HashSet<String> set = new HashSet<>();
        if (items == null) {
            return set;
        }
        for (String item : items) {
            Vector<String> foundDocs = invertedIndexManager.findDocsByWord(item);
            if (foundDocs != null) {
                set.addAll(foundDocs);
            }
        }
        return set;
    }
}

package searchEngine;

import searchEngine.decoders.Decoder;
import searchEngine.decoders.Query;

import java.util.HashSet;
import java.util.Vector;

public class QueryHandler<K> {
    private final InvertedIndexManager<K> invertedIndexManager;
    private final Decoder decoder;

    public QueryHandler(InvertedIndexManager<K> invertedIndexManager, Decoder decoder) {
        this.invertedIndexManager = invertedIndexManager;
        this.decoder = decoder;
    }

    private HashSet<K> removeForbidden(HashSet<K> base, HashSet<K> forbidden) {
        HashSet<K> result = new HashSet<>();
        for (K key : base) {
            if (!forbidden.contains(key)) {
                result.add(key);
            }
        }
        return result;
    }
    
    
    public HashSet<K> getQueryResult(String queryStr) {
        Query query = decoder.decode(queryStr.toLowerCase());
        HashSet<K> results = new HashSet<>();
        
        if (query.compulsories().isEmpty()) {
            if (query.optionals().isEmpty()) {
                HashSet<K> norItems = itemsUnion(query.forbidden());
                return removeForbidden(invertedIndexManager.getAllKeys() ,norItems);
            } else {
                results = itemsUnion(query.optionals());
            }
        } else {
            if (query.optionals().isEmpty()) {
                // TODO
            } else {
                
            }
        }
















//        HashSet<K> orItems = itemsUnion(vectors.get(1));
//        HashSet<K> norItems = itemsUnion(vectors.get(2));
//        HashSet<K> results;
//        Vector<String> items = vectors.get(0);
//        if (items.size() != 0) {
//            int minIndex = getMinIndex(items);
//            if (minIndex == -1) {
//                results = new HashSet<>();
//            } else {
//                results = new HashSet<>(invertedIndexManager.findKeysByWord(items.get(minIndex)));
//                for (String item : items) {
//                    Vector<String> foundKeys = invertedIndexManager.findKeysByWord(item);
//                    if (foundKeys != null) {
//                        results.removeIf(s -> !foundKeys.contains(s));
//                    } else {
//                        results.clear();
//                    }
//                }
//                if (orItems.size() != 0) {
//                    results.removeIf(s -> !orItems.contains(s));
//                }
//            }
//        } else {
//            results = orItems;
//        }
//        results.removeIf(norItems::contains);

        return null;
    }

    private int getMinIndex(Vector<String> items) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = 0; i < items.size(); i++) {
            HashSet<K> foundKeys = invertedIndexManager.findKeysByWord(items.get(i));
            if (foundKeys == null) {
                return -1;
            }
            int size = foundKeys.size();
            if (size < min) {
                min = size;
                minIndex = i;
            }
        }
        return minIndex;
    }

    private HashSet<K> itemsUnion(Vector<String> items) {
        HashSet<K> set = new HashSet<>();
        if (items == null) {
            return set;
        }
        for (String item : items) {
            HashSet<K> foundKeys = invertedIndexManager.findKeysByWord(item);
            if (foundKeys != null) {
                set.addAll(foundKeys);
            }
        }
        return set;
    }
}

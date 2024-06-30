import java.util.HashSet;
import java.util.Vector;

public class QueryHandler {
    private final InvertedIndexManager invertedIndexManager;

    public QueryHandler(InvertedIndexManager invertedIndexManager) {
        this.invertedIndexManager = invertedIndexManager;
    }

    public HashSet<String> getQueryResult(String query) {
        Vector<Vector<String>> vectors = decodeQuery(query.toLowerCase());
        HashSet<String> orItems = itemsUnion(vectors.get(1));
        HashSet<String> norItems = itemsUnion(vectors.get(2));
        HashSet<String> results;
        Vector<String> items = vectors.get(0);
        if (items.size() != 0) {
            int minIndex = getMinIndex(items);
            if (minIndex == -1) {
                results = new HashSet<>();
            } else {
                results = new HashSet<>(invertedIndexManager.getInvertedIndex().get(items.get(minIndex)));
                for (String item : items) {
                    Vector<String> vector = invertedIndexManager.getInvertedIndex().get(item);
                    if (vector != null) {
                        results.removeIf(s -> !vector.contains(s));
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
            Vector<String> vector = invertedIndexManager.getInvertedIndex().get(items.get(i));
            if (vector == null) {
                return -1;
            }
            int size = vector.size();
            if (size < min) {
                min = size;
                minIndex = i;
            }
        }
        return minIndex;
    }

    private Vector<Vector<String>> decodeQuery(String query) {
        String[] strings = query.split("\\s+");
        Vector<Vector<String>> vectors = new Vector<>();
        vectors.add(new Vector<>());
        vectors.add(new Vector<>());
        vectors.add(new Vector<>());
        for (String string : strings) {
            if (string.equals("")) {
                continue;
            }
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
            Vector<String> vector = invertedIndexManager.getInvertedIndex().get(item);
            if (vector != null) {
                set.addAll(vector);
            }
        }
        return set;
    }
}

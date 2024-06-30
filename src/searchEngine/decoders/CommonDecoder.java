package searchEngine.decoders;

import java.util.Vector;

public class CommonDecoder implements Decoder {
    @Override
    public Vector<Vector<String>> decode(String query) {
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
}

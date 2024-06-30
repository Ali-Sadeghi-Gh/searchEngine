package searchEngine.decoders;

import java.util.Vector;

public interface Decoder {
    Vector<Vector<String>> decode(String query);
}

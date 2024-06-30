package searchEngine.decoders;

import java.util.Vector;

public interface Decoder {
    QueryType decode(String query);
}

package searchEngine.decoders;

import java.util.Vector;

public class QueryType {
    private final Vector<String> compulsories;
    private final Vector<String> optionals;
    private final Vector<String> forbidden;

    public QueryType(Vector<String> compulsories, Vector<String> optionals, Vector<String> forbidden) {
        this.compulsories = compulsories;
        this.optionals = optionals;
        this.forbidden = forbidden;
    }

    public Vector<String> getCompulsories() {
        return compulsories;
    }

    public Vector<String> getOptionals() {
        return optionals;
    }

    public Vector<String> getForbidden() {
        return forbidden;
    }
}

package searchEngine;

import searchEngine.filters.Filter;
import searchEngine.tokenizers.Tokenizer;

import java.util.Vector;

public class SearchEngine<K> {
    private Vector<Filter> filters;
    private Tokenizer tokenizer;


    public SearchEngine(Vector<Filter> filters, Tokenizer tokenizer) {
        this.filters = filters;
        this.tokenizer = tokenizer;
    }
}

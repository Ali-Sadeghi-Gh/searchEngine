package searchEngine;

import searchEngine.decoders.Decoder;
import searchEngine.filters.Filter;
import searchEngine.tokenizers.Tokenizer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

public class SearchEngine<K> {
    private Vector<Filter> filters;
    private Tokenizer tokenizer;
    private Decoder decoder;
    private InvertedIndexManager invertedIndexManager;

    public SearchEngine(Vector<Filter> filters, Tokenizer tokenizer, Decoder decoder) throws IOException {
        this.filters = filters;
        this.tokenizer = tokenizer;
        this.decoder = decoder;
        this.invertedIndexManager = new InvertedIndexManager<K>();
    }

    public void addData(HashMap<K, String> data) {
        // TODO handle input data
    }

    public String search(String query) {
        // Todo search

        return "";
    }

}

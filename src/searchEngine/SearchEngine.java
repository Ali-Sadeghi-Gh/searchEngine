package searchEngine;

import searchEngine.decoders.CommonDecoder;
import searchEngine.decoders.Decoder;
import searchEngine.filters.Filter;
import searchEngine.tokenizers.SpaceTokenizer;
import searchEngine.tokenizers.Tokenizer;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

public class SearchEngine<K> {
    private Vector<Filter> filters;
    private Tokenizer tokenizer;
    private Decoder decoder;
    private InvertedIndexManager invertedIndexManager;

    public SearchEngine(Vector<Filter> filters, Tokenizer tokenizer, Decoder decoder) throws IOException {
        this.filters = filters == null ? new Vector<>() : filters;
        this.tokenizer = tokenizer == null ? new SpaceTokenizer() : tokenizer;
        this.decoder = decoder == null ? new CommonDecoder() : decoder;
        this.invertedIndexManager = new InvertedIndexManager<K>(filters, tokenizer);
    }

    public void addData(HashMap<K, String> data) {
        // TODO handle input data
    }

    public HashSet<K> search(String query) {
        // Todo search

        return null;
    }

}

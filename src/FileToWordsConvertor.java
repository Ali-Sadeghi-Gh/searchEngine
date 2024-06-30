import searchEngine.filters.Filter;
import searchEngine.tokenizers.SpaceTokenizer;
import searchEngine.tokenizers.Tokenizer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class FileToWordsConvertor {

    public static String[] convertFileToWords(File file, Vector<Filter> filters, Tokenizer tokenizer) throws IOException {


        return tokenizer.tokenize(null);
    }

    private static String applyFilters(String str, Vector<Filter> filters) {
        for (Filter filter : filters) {
            str = filter.doFilter(str);
        }
        return str;
    }

    public static String[] convertFileToWords(File file) throws IOException {
        return convertFileToWords(file, new Vector<>(), new SpaceTokenizer());
    }
}

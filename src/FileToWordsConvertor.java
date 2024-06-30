import filters.Filter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class FileToWordsConvertor {

    public static String[] convertFileToWords(File file, Vector<Filter> filters) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line.toLowerCase()).append(" ");
        }
        String str = sb.toString();
        String filteredStr = applyFilters(str, filters);

        return filteredStr.split("\\s+");
    }

    private static String applyFilters(String str, Vector<Filter> filters) {
        for (Filter filter : filters) {
            str = filter.doFilter(str);
        }
        return str;
    }

    public static String[] convertFileToWords(File file) throws IOException {
        return convertFileToWords(file, new Vector<>());
    }
}

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileToStringConvertor {

    public static String[] convertFileToString(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line.toLowerCase()).append(" ");
        }
        String str = sb.toString();
        str = Filters.doNumberFilter(str);
        str = Filters.doPunctuationFilter(str);
        return str.split("\\s+");
    }


}

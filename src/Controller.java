import java.io.File;
import java.io.IOException;
import java.util.HashSet;

public class Controller {
    private static Controller instance;
    private final InvertedIndexManager invertedIndexManager;
    private final QueryHandler queryHandler;

    private Controller() {
        invertedIndexManager = new InvertedIndexManager();
        queryHandler = new QueryHandler(invertedIndexManager);
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public void createInvertedIndex() throws IOException {
        File dir = new File("src/resources");
        File[] files = dir.listFiles();
        if (files != null) {
            invertedIndexManager.createInvertedIndexOfFiles(files);
        } else {
            throw new RuntimeException("path isn't a directory");
        }
    }

    public void handleQuery(String query) {
        HashSet<String> results = queryHandler.getQueryResult(query);
        printResult(results);
    }

    private void printResult(HashSet<String> results) {
        if (results.size() == 0) {
            System.out.println("nothing!");
            return;
        }
        for (String str : results) {
            System.out.println(str);
        }
    }
}

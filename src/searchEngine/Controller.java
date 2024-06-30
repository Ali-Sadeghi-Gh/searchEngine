package searchEngine;

import searchEngine.decoders.CommonDecoder;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;

public class Controller {
    private static Controller instance;
    private final InvertedIndexManager invertedIndexManager;
    private final QueryHandler queryHandler;

    private Controller() {
        invertedIndexManager = new InvertedIndexManager();
        queryHandler = new QueryHandler(invertedIndexManager, new CommonDecoder());
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
        // TODO return answer
    }


}

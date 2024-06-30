import searchEngine.Controller;
import searchEngine.SearchEngine;
import searchEngine.decoders.CommonDecoder;
import searchEngine.tokenizers.SpaceTokenizer;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Vector;

public class Main {
    public static void main(String[] args) throws IOException {
        File[] files = getAllFiles("src/resources");
        HashMap<String, String> data = new HashMap<>();
        for (File file : files) {
            String fileContent = readFileContent(file);
                    data.put(file.getName(), fileContent);
        }

        SearchEngine<String> searchEngine = new SearchEngine<>(new Vector<>(), new SpaceTokenizer(), new CommonDecoder());

        searchEngine.addData(data);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String query = scanner.nextLine();
            if (query.equals("q")) {
                break;
            }
            searchEngine.search(scanner.nextLine());
        }
    }

    private static File[] getAllFiles(String path) {
        File dir = new File(path);
        File[] files = dir.listFiles();
        if (files != null) {
            return files;
        } else {
            throw new RuntimeException("path isn't a directory");
        }
    }

    private static String readFileContent(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line.toLowerCase()).append(" ");
        }
        return sb.toString();
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
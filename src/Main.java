import searchEngine.Controller;

import java.io.*;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Controller.getInstance().createInvertedIndex();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String query = scanner.nextLine();
            if (query.equals("q")) {
                break;
            }
            Controller.getInstance().handleQuery(query);
        }
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
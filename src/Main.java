import java.io.*;
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
}
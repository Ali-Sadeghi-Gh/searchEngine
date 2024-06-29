import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        File dir = new File("src/resources");
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                BufferedReader br = new BufferedReader(new FileReader(child));
                String line;
                while ((line = br.readLine()) != null) {

                }

            }
        } else {
            throw new RuntimeException("path isn't a directory");
        }


        File file = new File("src/resources/Clean Agile.txt");
        System.out.println(file.exists());

    }
}
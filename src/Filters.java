public class Filters {

    public static String doNumberFilter(String str) {
        return str.replaceAll("[0-9]", " ");
    }

    public static String doPunctuationFilter(String str) {
        return str.replaceAll("\\p{Punct}", " ");
    }
}

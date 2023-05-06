package CustomExceptions;

public class StringSubtractionException extends Exception{

    public StringSubtractionException(int n, String text) {
        System.out.println(text.substring(0, text.length()-n));

    }

}

package CustomExceptions;

public class StringMultiplicationException extends Exception {

    public StringMultiplicationException(int n, String text) {
        for (int i = 0; i < n; i++) {
            System.out.print(text);
        }
        System.out.println();
    }

}

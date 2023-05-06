package CustomExceptions;

public class MoreThanThreeDigitsException extends Exception{

    public MoreThanThreeDigitsException() {
        super("More than three digits were input.");
    }

}
